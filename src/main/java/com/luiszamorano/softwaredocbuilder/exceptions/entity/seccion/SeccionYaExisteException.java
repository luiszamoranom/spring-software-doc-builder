package com.luiszamorano.softwaredocbuilder.exceptions.entity.seccion;

public class SeccionYaExisteException extends RuntimeException{
    public SeccionYaExisteException(String message) {
        super(message);
    }
}
