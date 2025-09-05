package com.cadastro.equipamentos.service;

import com.cadastro.equipamentos.entites.Equipamento;
import com.cadastro.equipamentos.repository.EquipamentoRepository;
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
public class EquipamentoService {

        // injeção de dependencias
        @Autowired
        private EquipamentoRepository repository;


        //Salva um novo equipamento no banco de dados.
        public Equipamento salvarEquipamento(Equipamento equipamento){
            return repository.save(equipamento);
        }


        //Retorna uma lista com todos os equipamentos cadastrados.
        public List<Equipamento> listarTodos(){
            return repository.findAll();
        }


        //Busca um equipamento pelo seu ID.
        public Optional<Equipamento> buscarPorId(String id){
            return repository.findById(id);
        }


        //Atualiza um equipamento existente com novos dados
        public Equipamento atualizar(String id, Equipamento atualizado) {
        Equipamento existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado: " + id));

        existente.setMarca(atualizado.getMarca());
        existente.setModelo(atualizado.getModelo());
        existente.setDataDeEntrega(atualizado.getDataDeEntrega());

        return repository.save(existente);
    }

        //Remove um Equipamento do banco de dados pelo número de série.
        public void deletar(String numeroDeSerie) {
        if (!repository.existsById(numeroDeSerie)) {
            throw new RuntimeException("Equipamento não encontrado: " + numeroDeSerie);
        }
        repository.deleteById(numeroDeSerie);
    }


    //Calcula o número de dias entre a data de entrega e a data atual.
        public long calcularDiasDeUso (LocalDate dataEntrega){
            return ChronoUnit.DAYS.between(dataEntrega, LocalDate.now());
        }


        public byte[] generateExcel () throws IOException{
            List<Equipamento> equipamentos = repository.findAll();

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
                for (Equipamento equipamento : equipamentos){

                    Row linha = sheet.createRow(numeroDeLinhas++);
                    linha.createCell(0).setCellValue(equipamento.getNumeroDeSerie());
                    linha.createCell(1).setCellValue(equipamento.getMarca());
                    linha.createCell(2).setCellValue(equipamento.getModelo());
                    linha.createCell(3).setCellValue(equipamento.getDataDeEntrega().toString());
                    linha.createCell(4).setCellValue(calcularDiasDeUso(equipamento.getDataDeEntrega()));
                }
                workbook.write(out);
                return out.toByteArray();

            }
        }

}
