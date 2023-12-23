package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// todo campos faltantes
// done excepciones
public class UsuarioInstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public UsuarioInstanciaModulo(Usuario usuario, InstanciaModulo instanciaModulo) {
        this.usuario = usuario;
        this.instanciaModulo = instanciaModulo;
    }

    // usuario <otm -- mto> UsuarioInstanciaModulo
    @ManyToOne
    @JsonManagedReference
    private Usuario usuario;

    // instanciaModulo <otm -- mto> UsuarioInstanciaModulo
    @ManyToOne
    @JsonManagedReference
    private InstanciaModulo instanciaModulo ;
}
