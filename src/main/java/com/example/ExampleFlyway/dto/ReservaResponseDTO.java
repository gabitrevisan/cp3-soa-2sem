package com.example.ExampleFlyway.dto;

public record ReservaResponseDTO(
    Long id,
    String equipamento,
    String professor,
    String sala,
    String retirada,
    String entrega
) {}