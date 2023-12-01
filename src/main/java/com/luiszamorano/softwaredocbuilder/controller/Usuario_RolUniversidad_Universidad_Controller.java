package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.dto.GenericDTO;
import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.RolUniversidadService;
import com.luiszamorano.softwaredocbuilder.service.UniversidadService;
import com.luiszamorano.softwaredocbuilder.service.UsuarioService;
import com.luiszamorano.softwaredocbuilder.service.Usuario_RolUniversidad_Universidad_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario_roluniversidad_universidad")
public class Usuario_RolUniversidad_Universidad_Controller {
    @Autowired
    private Usuario_RolUniversidad_Universidad_Service usuarioRolUniversidadUniversidadService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolUniversidadService rolUniversidadService;

    @Autowired
    private UniversidadService universidadService;

    private record FindByIdRecord(String rut, String rol, String abreviacion){}
    @GetMapping(path = "/findById")
    public ResponseEntity<GenericResponse<Optional<Usuario_RolUniversidad_Universidad>>> findById(@ModelAttribute FindByIdRecord record){
        Optional<Usuario> posibleUsuario = usuarioService.findById(record.rut);
        Optional<RolUniversidad> posibleRolUniversidad = rolUniversidadService.findByNombre(record.rol);
        Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacion);

        if(posibleUsuario.isPresent() && posibleRolUniversidad.isPresent() && posibleUniversidad.isPresent()){
            Usuario_RolUniversidad_Universidad_PK pk = new Usuario_RolUniversidad_Universidad_PK(
                    posibleUsuario.get(),
                    posibleUniversidad.get(),
                    posibleRolUniversidad.get()
            );

            Optional<Usuario_RolUniversidad_Universidad> finded = usuarioRolUniversidadUniversidadService.findById(
                    pk
            );

            if(finded.isPresent()){
                new ResponseEntity<>(new GenericResponse<>(
                        finded,
                        "usuario_roluniversidad_universidad encontrado"
                ),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<Usuario_RolUniversidad_Universidad>>> findAll(){
        List<Usuario_RolUniversidad_Universidad> todos = usuarioRolUniversidadUniversidadService.findAll();

        if(!todos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(
                    todos,
                    "usuario_roluniversidad_universidad's encontrados"
            ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private record FindByUniversidad(String abreviacion){}
    @GetMapping("/findByUniversidad")
    public ResponseEntity<GenericResponse<List<Usuario_RolUniversidad_Universidad>>> findByUniversidad(
            @ModelAttribute FindByUniversidad record){
        Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacion);
        List<Usuario_RolUniversidad_Universidad> todos = usuarioRolUniversidadUniversidadService.findAll();

        if(!todos.isEmpty()){

            if(posibleUniversidad.isPresent()){
                List<Usuario_RolUniversidad_Universidad> filtrados = todos.stream().filter(
                        c -> c.getUsuarioRolUniversidadUniversidadPk().getUniversidad().equals(posibleUniversidad.get())
                ).collect(Collectors.toList());


                if(!filtrados.isEmpty()){
                    return new ResponseEntity<>(new GenericResponse<>(
                            filtrados,
                            "usuario_roluniversidad_universidad's encontrados y filtrados por la universidad ingresada"
                    ),HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private record FindByUniversidadAndRol(String abreviacion, String rol){}
    @GetMapping("/findByUniverisadAndRol")
    public ResponseEntity<GenericResponse<List<Usuario_RolUniversidad_Universidad>>> findByUniversidadAndRol(
            @ModelAttribute FindByUniversidadAndRol record){
        Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacion);
        Optional<RolUniversidad> posibleRol = rolUniversidadService.findByNombre(record.rol);
        List<Usuario_RolUniversidad_Universidad> todos = usuarioRolUniversidadUniversidadService.findAll();

        if(!todos.isEmpty()){
            if(posibleUniversidad.isPresent() && posibleRol.isPresent()){
                List<Usuario_RolUniversidad_Universidad> filtrados = todos.stream().filter(
                        c -> c.getUsuarioRolUniversidadUniversidadPk().getUniversidad().equals(posibleUniversidad.get())
                                && c.getUsuarioRolUniversidadUniversidadPk().getRolUniversidad().equals(posibleRol.get())
                ).collect(Collectors.toList());


                if(!filtrados.isEmpty()){
                    return new ResponseEntity<>(new GenericResponse<>(
                            filtrados,
                            "usuario_roluniversidad_universidad's encontrados y filtrados por la universidad ingresada"
                    ),HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* Ejemplo de prueba:
    http://localhost:8080/usuario_roluniversidad_universidad/findByUsuario?rut=11.000.000-0
     */
    private record FindByUsuario(String rut){}
    @GetMapping("/findByUsuario")
    public ResponseEntity<GenericResponse<List<GenericDTO>>> findByUsuario(@ModelAttribute FindByUsuario record){
        Optional<Usuario> posibleUsuario = usuarioService.findById(record.rut);
        List<Usuario_RolUniversidad_Universidad> todos = usuarioRolUniversidadUniversidadService.findAll();

        if(!todos.isEmpty()){
            if(posibleUsuario.isPresent()){
                List<Usuario_RolUniversidad_Universidad> filtrados = usuarioRolUniversidadUniversidadService.findByUsuario(posibleUsuario.get());

                List<GenericDTO> filtradosDTO = filtrados.stream().map(item -> {
                    GenericDTO dto = new GenericDTO();

                    Universidad universidad = item.getUsuarioRolUniversidadUniversidadPk().getUniversidad();
                    dto.anadirAtributo("abreviacion", universidad.getAbreviacion());

                    RolUniversidad rolUniversidad = item.getUsuarioRolUniversidadUniversidadPk().getRolUniversidad();
                    dto.anadirAtributo("nombreRolUniversidad", rolUniversidad.getNombre());

                    return dto;
                }).collect(Collectors.toList());


                if(!filtrados.isEmpty()){
                    return new ResponseEntity<>(new GenericResponse<>(
                            filtradosDTO,
                            "usuario_roluniversidad_universidad's encontrados, filtrados por rut"
                    ),HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
