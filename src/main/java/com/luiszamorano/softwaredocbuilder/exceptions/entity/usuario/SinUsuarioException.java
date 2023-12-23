package com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario;

public class SinUsuarioException extends RuntimeException{
    public SinUsuarioException(String message) {
        super(message);
    }
}
