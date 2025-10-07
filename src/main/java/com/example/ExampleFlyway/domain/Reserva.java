package com.example.ExampleFlyway.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    private String sala;
    private LocalDateTime retirada;
    private LocalDateTime entrega;

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Equipamento getEquipamento() { return equipamento; }
    public void setEquipamento(Equipamento equipamento) { this.equipamento = equipamento; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }
    public LocalDateTime getRetirada() { return retirada; }
    public void setRetirada(LocalDateTime retirada) { this.retirada = retirada; }
    public LocalDateTime getEntrega() { return entrega; }
    public void setEntrega(LocalDateTime entrega) { this.entrega = entrega; }
}