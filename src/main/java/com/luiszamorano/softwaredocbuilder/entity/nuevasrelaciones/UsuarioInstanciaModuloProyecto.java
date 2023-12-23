package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//TODO excepciones
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
}
