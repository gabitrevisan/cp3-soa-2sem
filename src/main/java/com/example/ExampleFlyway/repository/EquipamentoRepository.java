package com.example.ExampleFlyway.repository;

import com.example.ExampleFlyway.domain.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}