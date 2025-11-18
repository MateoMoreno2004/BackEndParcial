package com.example.clinicas.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicaDto {
    private Long identificador;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    private String direccion;

    @Min(value = 0, message = "Cantidad de camas no puede ser negativa")
    private Integer cantidadCamas;

    private String telefono;

    private String ciudad;

    private LocalDate fechaCreacion;
}
