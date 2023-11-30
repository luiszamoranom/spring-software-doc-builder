package com.luiszamorano.softwaredocbuilder.entity.pkCompuestas;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
public class Usuario_RolUniversidad_Universidad_PK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "usuario_rut")
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "universidad_abreviacion")
    @JsonManagedReference
    private Universidad universidad;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "roluniversidad_nombre")
    private RolUniversidad rolUniversidad;

    public Usuario_RolUniversidad_Universidad_PK(Usuario usuario, Universidad universidad, RolUniversidad rolUniversidad){
        this.usuario=usuario;
        this.universidad=universidad;
        this.rolUniversidad=rolUniversidad;
    }
}
