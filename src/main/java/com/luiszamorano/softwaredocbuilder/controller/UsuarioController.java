package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.RolUniversidadService;
import com.luiszamorano.softwaredocbuilder.service.UniversidadService;
import com.luiszamorano.softwaredocbuilder.service.UsuarioService;
import com.luiszamorano.softwaredocbuilder.service.relation.Usuario_RolUniversidad_Universidad_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UniversidadService universidadService;

    @Autowired
    private Usuario_RolUniversidad_Universidad_Service usuarioRolUniversidadUniversidadService;

    @Autowired
    private RolUniversidadService rolUniversidadService;

    private record FindByIdRecord(String rut){}
    @GetMapping(path = "/findByRut")
    public ResponseEntity<GenericResponse<Optional<Usuario>>> findById(@RequestBody FindByIdRecord record){
        Optional<Usuario> usuarioRespuesta = usuarioService.findById(record.rut);
        if(usuarioRespuesta.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(usuarioRespuesta,"usuario encontrado con ese rut"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new GenericResponse<>(Optional.empty(),"usuario no encontrado"),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<Usuario>>> findAll(){
        List<Usuario> usuariosRespuesta =usuarioService.findAll();
        if(!usuariosRespuesta.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(usuariosRespuesta,"usuarios encontrados"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin usuarios"),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/habilitados")
    public ResponseEntity<GenericResponse<List<Usuario>>> findByEstadoTrue(){
        List<Usuario> usuariosRespuesta =usuarioService.findByEstadoTrue();
        if(!usuariosRespuesta.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(usuariosRespuesta,"usuarios encontrados filtrados por estado true"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin usuarios"),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/deshabilitados")
    public ResponseEntity<GenericResponse<List<Usuario>>> findByEstadoFalse(){
        List<Usuario> usuariosRespuesta =usuarioService.findByEstadoFalse();
        if(!usuariosRespuesta.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(usuariosRespuesta,"usuarios encontrados filtrados por estado false"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"usuario encontrado con ese rut"),HttpStatus.NO_CONTENT);
    }


    private record LoginRecord(String rut, String contrasena) {}
    @PostMapping(path = "/login")
    public ResponseEntity<GenericResponse<Optional<Usuario>>> login(@RequestBody LoginRecord record){
        Optional<Usuario> usuarioRespuesta = usuarioService.login(record.rut, record.contrasena);
        if(usuarioRespuesta.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(usuarioRespuesta,"usuario logueado"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new GenericResponse<>(Optional.empty(),"credenciales incorrectas"),HttpStatus.UNAUTHORIZED);
    }


    private record CambiarEstadoRecord(String rut, Boolean estado) {}
    @PatchMapping("/cambiar_estado")
    public ResponseEntity cambiarEstado(@RequestBody CambiarEstadoRecord record){
        Optional<Usuario> usuarioRespuesta = usuarioService.cambiarEstado(record.rut, record.estado);
        if(usuarioRespuesta.isPresent()){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new  ResponseEntity(HttpStatus.NO_CONTENT);
    }


    private record UpdateRecord(String rut, String nombres, String apellidos, String contrasena, String email) {}
    @PatchMapping(path = "/actualizar")
    public ResponseEntity<GenericResponse<Optional<Usuario>>> update(@RequestBody UpdateRecord record){
        Optional<Usuario> usuarioRespuesta = usuarioService.update(record.rut,record.nombres,record.apellidos,record.contrasena,record.email);
        if(usuarioRespuesta.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(usuarioRespuesta,"usuario actualizado"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new GenericResponse<>(Optional.empty(),"usuario no encontrado"),HttpStatus.NO_CONTENT);
    }

    private record GuardarRecord(String rut, String nombres, String apellidos, String contrasena, String email,
                                 String abreviacionUniversidad, String nombreRol) {}
    @PostMapping(path = "/guardar")
    public ResponseEntity<HttpStatus> guardar(@RequestBody GuardarRecord record){
        Optional<Usuario> posibleUsuario = usuarioService.findById(record.rut);
        Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacionUniversidad);
        Optional<RolUniversidad> posibleRol = rolUniversidadService.findByNombre(record.nombreRol);
        if(!posibleUsuario.isPresent() && posibleUniversidad.isPresent() && posibleRol.isPresent()){
            Usuario usuarioGuardado = usuarioService.save(
                record.rut,
                record.nombres,
                record.apellidos,
                record.contrasena,
                record.email)
            ;
            usuarioRolUniversidadUniversidadService.anadirRolValidoEnUniversidadValidadAUsuarioExistente(
                usuarioGuardado,
                posibleRol.get(),
                posibleUniversidad.get()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
