package com.example.ExampleFlyway.dto;

import java.time.LocalDateTime;

public record ReservaRequestDTO(
    Long equipamentoId,
    Long professorId,
    String sala,
    LocalDateTime retirada,
    LocalDateTime entrega
) {}