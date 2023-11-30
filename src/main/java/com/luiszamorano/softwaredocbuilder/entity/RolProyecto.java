package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "rolproyecto")
@NoArgsConstructor
@AllArgsConstructor
public class RolProyecto {
    @Id
    private String nombre;
}
