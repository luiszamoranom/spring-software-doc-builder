package com.luiszamorano.softwaredocbuilder.controller.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.relation.UsuarioEstudiante_InstanciaModulo_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarioestudiante_instanciamodulo")
public class UsuarioEstudiante_InstanciaModulo_Controller {
    @Autowired
    private UsuarioEstudiante_InstanciaModulo_Service usuarioEstudianteInstanciaModuloServicio;

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<UsuarioEstudiante_InstanciaModulo>>> findAll(){
        List<UsuarioEstudiante_InstanciaModulo> resultados = usuarioEstudianteInstanciaModuloServicio.findAll();
        if(!resultados.isEmpty()){
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            resultados,"usuarioestudiante_instanciamodulo encontrados"
                    ), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findByUsuario")
    public ResponseEntity<GenericResponse<List<UsuarioEstudiante_InstanciaModulo>>> findByUsuario(@RequestParam Usuario usuario){
        List<UsuarioEstudiante_InstanciaModulo> resultados = usuarioEstudianteInstanciaModuloServicio.findByUsuario(usuario);
        if(!resultados.isEmpty()){
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            resultados,"usuarioestudiante_instanciamodulo encontrados y filtrados por usuario"
                    ), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findById")
    public ResponseEntity<GenericResponse<Optional<UsuarioEstudiante_InstanciaModulo>>> findById(@RequestParam Long id){
        Optional<UsuarioEstudiante_InstanciaModulo> resultados = usuarioEstudianteInstanciaModuloServicio.findById(id);
        if(resultados.isPresent()){
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            resultados,"usuarioestudiante_instanciamodulo encontrados y filtrados por usuario"
                    ), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
