package com.luiszamorano.softwaredocbuilder.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse<T> {
    private T filas;
    private String mensaje;
}
