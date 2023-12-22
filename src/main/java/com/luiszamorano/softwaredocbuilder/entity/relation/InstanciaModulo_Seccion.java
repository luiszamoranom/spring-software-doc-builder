package com.luiszamorano.softwaredocbuilder.entity.relation;


import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class InstanciaModulo_Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "modulo_nombre", referencedColumnName = "modulo_nombre"),
            @JoinColumn(name = "ano", referencedColumnName = "ano"),
            @JoinColumn(name = "semestre", referencedColumnName = "semestre"),
            @JoinColumn(name = "seccion", referencedColumnName = "seccion")
    })
    private InstanciaModulo instanciaModulo;

    @ManyToOne
    private Seccion seccion;

    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    public InstanciaModulo_Seccion(InstanciaModulo instanciaModulo, Seccion seccion) {
        this.instanciaModulo = instanciaModulo;
        this.seccion = seccion;
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
