package com.example.clinicas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "clinicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador; // PK según diagrama

    @Column(nullable = false)
    private String nombre;

    private String direccion;

    private Integer cantidadCamas;

    private String telefono;

    private String ciudad;

    private LocalDate fechaCreacion;
}
