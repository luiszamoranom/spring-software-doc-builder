package com.luiszamorano.softwaredocbuilder.entity.pkCompuestas;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Proyecto_Seccion_Id implements Serializable {
    @Column
    private ProyectoPK proyectoId;

    @Column
    private String seccionId;
}
