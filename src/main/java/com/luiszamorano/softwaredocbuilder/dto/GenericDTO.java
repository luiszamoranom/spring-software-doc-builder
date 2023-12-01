package com.luiszamorano.softwaredocbuilder.dto;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class GenericDTO {
    private Map<String, Object> fila = new HashMap<>();

    public void anadirAtributo(String key, Object value) {
        this.fila.put(key, value);
    }
}