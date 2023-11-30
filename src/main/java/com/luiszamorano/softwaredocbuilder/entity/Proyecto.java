package com.luiszamorano.softwaredocbuilder.entity;

import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "proyecto")
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {
    @EmbeddedId
    private ProyectoPK proyectoPK;
}
