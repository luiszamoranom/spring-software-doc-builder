package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
