package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// todo repositorio
// todo servicio
// todo controlador
public class UsuarioInstanciaModuloProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // usuario <OTM--MTO> UsuarioInstanciaModuloProyecto
    @ManyToOne
    @JsonManagedReference
    private Usuario usuario;

    // instanciaModuloProyecto <OTM--MTO> UsuarioInstanciaModuloProyecto
    @ManyToOne
    @JsonManagedReference
    private InstanciaModuloProyecto instanciaModuloProyecto;

    public UsuarioInstanciaModuloProyecto(Usuario usuario, InstanciaModuloProyecto instanciaModuloProyecto) {
        this.usuario = usuario;
        this.instanciaModuloProyecto = instanciaModuloProyecto;
    }
}
