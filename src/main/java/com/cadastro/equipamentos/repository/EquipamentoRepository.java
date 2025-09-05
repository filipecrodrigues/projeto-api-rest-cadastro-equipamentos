package com.cadastro.equipamentos.repository;

import com.cadastro.equipamentos.entites.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento,String> {

}
