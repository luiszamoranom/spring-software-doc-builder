package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
// todo campos faltantes
public class ContenidoEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // contenido <OTM--MTN> contenidoentrada
    @ManyToOne
    @JsonManagedReference
    private Contenido contenido;


    // Entrada <OTM--MTN> contenidoentrada
    @ManyToOne
    @JsonManagedReference
    private Entrada entrada;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
