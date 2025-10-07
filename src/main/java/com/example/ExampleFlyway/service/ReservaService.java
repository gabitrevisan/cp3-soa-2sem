package com.example.ExampleFlyway.service;

import com.example.ExampleFlyway.domain.Equipamento;
import com.example.ExampleFlyway.domain.Professor;
import com.example.ExampleFlyway.domain.Reserva;
import com.example.ExampleFlyway.dto.ReservaRequestDTO;
import com.example.ExampleFlyway.dto.ReservaResponseDTO;
import com.example.ExampleFlyway.repository.EquipamentoRepository;
import com.example.ExampleFlyway.repository.ProfessorRepository;
import com.example.ExampleFlyway.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final ProfessorRepository professorRepository;

    public ReservaService(ReservaRepository reservaRepository, EquipamentoRepository equipamentoRepository, ProfessorRepository professorRepository) {
        this.reservaRepository = reservaRepository;
        this.equipamentoRepository = equipamentoRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    public ReservaResponseDTO reservar(ReservaRequestDTO dto) {
        List<Reserva> conflitos = reservaRepository.findConflitos(dto.equipamentoId(), dto.retirada(), dto.entrega());
        if (!conflitos.isEmpty()) {
            throw new IllegalStateException("Equipamento indisponível para o período solicitado.");
        }

        Equipamento equipamento = equipamentoRepository.findById(dto.equipamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado"));
        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Reserva novaReserva = new Reserva();
        novaReserva.setEquipamento(equipamento);
        novaReserva.setProfessor(professor);
        novaReserva.setSala(dto.sala());
        novaReserva.setRetirada(dto.retirada());
        novaReserva.setEntrega(dto.entrega());

        reservaRepository.save(novaReserva);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new ReservaResponseDTO(
                novaReserva.getId(),
                equipamento.getDescricao(),
                professor.getNome(),
                novaReserva.getSala(),
                novaReserva.getRetirada().format(formatter),
                novaReserva.getEntrega().format(formatter)
        );
    }
}