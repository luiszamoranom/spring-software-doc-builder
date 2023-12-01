package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.RolProyecto;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.RolProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/rolproyecto")
public class RolProyectoController {
    @Autowired
    private RolProyectoService rolProyectoService;

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<RolProyecto>>> findAll(){
        List<RolProyecto> roles = rolProyectoService.findAll();
        if(!roles.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(
                    roles,"roles encontrados"
            ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
