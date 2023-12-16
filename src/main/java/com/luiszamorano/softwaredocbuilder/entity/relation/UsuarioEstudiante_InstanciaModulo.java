package com.luiszamorano.softwaredocbuilder.entity.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsuarioEstudiante_InstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "modulo_nombre", referencedColumnName = "modulo_nombre"),
            @JoinColumn(name = "ano", referencedColumnName = "ano"),
            @JoinColumn(name = "semestre", referencedColumnName = "semestre"),
            @JoinColumn(name = "seccion", referencedColumnName = "seccion")
    })
    private InstanciaModulo instaciaModulo;

    public UsuarioEstudiante_InstanciaModulo(Usuario usuario, InstanciaModulo instanciaModulo){
        this.usuario=usuario;
        this.instaciaModulo=instanciaModulo;
    }

    public UsuarioEstudiante_InstanciaModulo() {

    }
}
