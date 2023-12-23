package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// done excepciones
public class UsuarioInstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // usuario <otm -- mto> UsuarioInstanciaModulo
    @ManyToOne
    @JsonManagedReference
    private Usuario usuario;

    // instanciaModulo <otm -- mto> UsuarioInstanciaModulo
    @ManyToOne
    @JsonManagedReference
    private InstanciaModulo instanciaModulo ;
}
