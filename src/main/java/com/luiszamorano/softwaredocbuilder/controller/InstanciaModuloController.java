package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.InstanciaModuloService;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import com.luiszamorano.softwaredocbuilder.service.RolUniversidadService;
import com.luiszamorano.softwaredocbuilder.service.UniversidadService;
import com.luiszamorano.softwaredocbuilder.service.UsuarioService;
import com.luiszamorano.softwaredocbuilder.service.relation.Usuario_RolUniversidad_Universidad_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/instanciamodulo")
public class InstanciaModuloController {
    @Autowired
    private InstanciaModuloService instanciaModuloService;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UniversidadService universidadService;

    @Autowired
    private RolUniversidadService rolUniversidadService;

    @Autowired
    private Usuario_RolUniversidad_Universidad_Service usuarioRolUniversidadUniversidadService;

    private record BuscarPorModuloRecord(String nombre, int ano, int semestre, char seccion) {
    }

    @GetMapping(path = "/findById")
    private ResponseEntity<GenericResponse<Optional<InstanciaModulo>>> findById(
            @RequestBody BuscarPorModuloRecord record) {
        Optional<Modulo> posibleModulo = moduloService.findById(record.nombre);
        if (posibleModulo.isPresent()) {
            Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(
                    new InstanciaModuloPK(
                            posibleModulo.get(), record.ano, record.semestre, record.seccion));
            if (posibleInstancia.isPresent()) {
                return new ResponseEntity<>(new GenericResponse<>(
                        posibleInstancia, "instancia encontrada"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    private ResponseEntity<GenericResponse<List<InstanciaModulo>>> findAll() {
        List<InstanciaModulo> instancias = instanciaModuloService.findAll();
        if (!instancias.isEmpty()) {
            return new ResponseEntity<>(new GenericResponse<>(
                    instancias, "instancias encontradas"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private record FindByModuloRecord(String nombre) {
    }

    @GetMapping("/findByModulo/{nombre}")
    private ResponseEntity<GenericResponse<List<InstanciaModulo>>> findByModulo(
            @PathVariable String nombre) {
        Optional<Modulo> posibleModulo = moduloService.findById(nombre);
        if (posibleModulo.isPresent()) {
            List<InstanciaModulo> instanciasDeModulo = instanciaModuloService.findByModulo(posibleModulo.get());
            if (!instanciasDeModulo.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(
                        instanciasDeModulo, "instancias encontradas del modulo " + nombre), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    private record GuardarRecord(String nombre, int ano, int semestre, char seccion, String rutProfesor,
            String abreviacionUniversidad) {}
    @PostMapping("/guardar")
    public ResponseEntity<GenericResponse<InstanciaModulo>> guardar(@RequestBody GuardarRecord record){
        Optional<Usuario> posibleUsuario = usuarioService.findById(record.rutProfesor);
        Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacionUniversidad);
        RolUniversidad rolProfesor = rolUniversidadService.findByNombre("Profesor").get();
        if(posibleUsuario.isPresent()){
            Usuario_RolUniversidad_Universidad_PK pkUsuarioRolUniversidad = new Usuario_RolUniversidad_Universidad_PK(
                posibleUsuario.get(),
                posibleUniversidad.get(),
                rolProfesor
            );
            Optional<Usuario_RolUniversidad_Universidad> posibleRolDelProfesor = usuarioRolUniversidadUniversidadService.findById(pkUsuarioRolUniversidad);
            if(posibleRolDelProfesor.isPresent()){
                Optional<Modulo> posibleModulo = moduloService.findById(record.nombre);
                if(posibleModulo.isPresent()){
                    InstanciaModuloPK pkInstancia = new InstanciaModuloPK(posibleModulo.get(), record.ano, record.semestre, record.seccion);
                    Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(pkInstancia);
                    if(!posibleInstancia.isPresent()){
                        InstanciaModulo nuevoModulo = new InstanciaModulo(pkInstancia,posibleUsuario.get());
                        instanciaModuloService.save(nuevoModulo);
                        return new ResponseEntity<>(new GenericResponse<>(
                        nuevoModulo, "nueva instanciamodulo guardad exitosamente"), HttpStatus.OK);

                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
