package com.luiszamorano.softwaredocbuilder.entity;

import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "instanciamodulo")
@NoArgsConstructor
public class InstanciaModulo {
    @EmbeddedId
    private InstanciaModuloPK instanciaModuloPK;

    public InstanciaModulo(InstanciaModuloPK instanciaModuloPK){
        this.instanciaModuloPK=instanciaModuloPK;
    }
}
