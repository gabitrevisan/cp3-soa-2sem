package com.example.ExampleFlyway.repository;

import com.example.ExampleFlyway.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("""
        select r from Reserva r
        where r.equipamento.id = :equipamentoId
        and r.retirada < :fim
        and r.entrega > :inicio
    """)
    List<Reserva> findConflitos(Long equipamentoId, LocalDateTime inicio, LocalDateTime fim);
}