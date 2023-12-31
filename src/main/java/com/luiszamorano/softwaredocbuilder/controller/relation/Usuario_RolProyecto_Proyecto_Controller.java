package com.luiszamorano.softwaredocbuilder.controller.relation;

import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolProyecto_Proyecto;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.relation.Usuario_RolProyecto_Proyecto_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario_rolproyecto_proyecto")
public class Usuario_RolProyecto_Proyecto_Controller {
    @Autowired
    private Usuario_RolProyecto_Proyecto_Service usuarioRolProyectoProyectoService;

    private record FindByIdRecord(Long id){}
    @GetMapping("/id/{id}")
    public ResponseEntity<GenericResponse<Optional<Usuario_RolProyecto_Proyecto>>> findById(@ModelAttribute FindByIdRecord record){
        Optional<Usuario_RolProyecto_Proyecto> posibleCoincidencia = usuarioRolProyectoProyectoService.findById(record.id);
        if(posibleCoincidencia.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(
                            posibleCoincidencia,
                            "usuario_rolproyecto_proyecto encontrado con ese id"
                    ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<Usuario_RolProyecto_Proyecto>>> findAll(){
        List<Usuario_RolProyecto_Proyecto> lista = usuarioRolProyectoProyectoService.findAll();
        if(!lista.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(
                    lista,
                    "usuario_rolproyecto_proyecto's encontrados"
            ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
