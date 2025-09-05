package com.cadastro.perifericos.service;

import com.cadastro.perifericos.entites.Periferico;
import com.cadastro.perifericos.repository.PerifericoRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PerifericoService {

        // injeção de dependencias
        @Autowired
        private PerifericoRepository repository;


        //Salva um novo periférico no banco de dados.
        public Periferico salvarPeriferico(Periferico periferico){
            return repository.save(periferico);
        }


        //Retorna uma lista com todos os periféricos cadastrados.
        public List<Periferico> listarTodos(){
            return repository.findAll();
        }


        //Busca um periférico pelo seu ID.
        public Optional<Periferico> buscarPorId(String id){
            return repository.findById(id);
        }


        //Atualiza um periférico existente com novos dados
        public Periferico atualizar(String id, Periferico atualizado) {
        Periferico existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periférico não encontrado: " + id));

        existente.setMarca(atualizado.getMarca());
        existente.setModelo(atualizado.getModelo());
        existente.setDataDeEntrega(atualizado.getDataDeEntrega());

        return repository.save(existente);
    }

        //Remove um periférico do banco de dados pelo número de série.
        public void deletar(String numeroDeSerie) {
        if (!repository.existsById(numeroDeSerie)) {
            throw new RuntimeException("Periférico não encontrado: " + numeroDeSerie);
        }
        repository.deleteById(numeroDeSerie);
    }


    //Calcula o número de dias entre a data de entrega e a data atual.
        public long calcularDiasDeUso (LocalDate dataEntrega){
            return ChronoUnit.DAYS.between(dataEntrega, LocalDate.now());
        }


        public byte[] generateExcel () throws IOException{
            List<Periferico>perifericos = repository.findAll();

            try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet("Perifericos");

                //Criação do cabeçalho
                Row header = sheet.createRow(0);
                String[] colunas = {"Numero de Serei","Marca", "Modelo","Data da Entrega", "Dias em uso"};
                for (int i =0; i < colunas.length; i++){
                    header.createCell(i).setCellValue(colunas[i]);
                }


                //Dados
                int numeroDeLinhas =1;
                for (Periferico periferico: perifericos){

                    Row linha = sheet.createRow(numeroDeLinhas++);
                    linha.createCell(0).setCellValue(periferico.getNumeroDeSerie());
                    linha.createCell(1).setCellValue(periferico.getMarca());
                    linha.createCell(2).setCellValue(periferico.getModelo());
                    linha.createCell(3).setCellValue(periferico.getDataDeEntrega().toString());
                    linha.createCell(4).setCellValue(calcularDiasDeUso(periferico.getDataDeEntrega()));
                }
                workbook.write(out);
                return out.toByteArray();

            }
        }

}
