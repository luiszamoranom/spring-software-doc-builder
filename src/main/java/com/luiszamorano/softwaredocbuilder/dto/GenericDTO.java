package com.luiszamorano.softwaredocbuilder.dto;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class GenericDTO {
    private Map<String, Object> atributos = new HashMap<>();

    public void anadirAtributo(String key, Object value) {
        this.atributos.put(key, value);
    }
}