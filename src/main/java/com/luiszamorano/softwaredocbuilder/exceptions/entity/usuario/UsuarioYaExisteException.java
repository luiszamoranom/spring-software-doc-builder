package com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario;

public class UsuarioYaExisteException extends RuntimeException{
    public UsuarioYaExisteException(String message) {
        super(message);
    }
}
