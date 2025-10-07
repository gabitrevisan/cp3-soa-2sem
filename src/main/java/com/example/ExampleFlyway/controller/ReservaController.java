package com.example.ExampleFlyway.controller;

import com.example.ExampleFlyway.dto.ReservaRequestDTO;
import com.example.ExampleFlyway.dto.ReservaResponseDTO;
import com.example.ExampleFlyway.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponseDTO criar(@RequestBody @Valid ReservaRequestDTO dto) {
        return service.reservar(dto);
    }
}