package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import com.luiszamorano.softwaredocbuilder.service.UniversidadService;

import org.hibernate.annotations.processing.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/modulo")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;

    @Autowired
    private UniversidadService universidadService;

    private record FindByIdRecord(String nombre){}
    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<GenericResponse<Optional<Modulo>>> findById(@ModelAttribute FindByIdRecord record){
        Optional<Modulo> posibleModulo = moduloService.findById(record.nombre);
        if(posibleModulo.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleModulo,"modulo encontrado con ese nombre"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<Modulo>>> findAll(){
        List<Modulo> modulos = moduloService.findAll();
        if(!modulos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(modulos,"modulos encontrados"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<GenericResponse<List<Modulo>>> findByEstadoTrue(){
        List<Modulo> modulos = moduloService.findByEstadoTrue();
        if(!modulos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(modulos,"modulos encontrados y filtrados por estado true"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/deshabilitados")
    public ResponseEntity<GenericResponse<List<Modulo>>>  findByEstadoFalse(){
        List<Modulo> modulos = moduloService.findByEstadoFalse();
        if(!modulos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(modulos,"modulos encontrados y filtrados por estado false"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private record CambiarEstadoRecord(String nombre, Boolean estado){}
    @PatchMapping(path = "/cambiar_estado")
    public ResponseEntity<GenericResponse<Optional<Modulo>>> cambiarEstado(@RequestBody CambiarEstadoRecord record) {
        Optional<Modulo> posibleModulo = moduloService.cambiarEstado(record.nombre,record.estado);
        if(posibleModulo.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleModulo,"modulo con estado actualizado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    private record UpdateRecord(String nombre, String descripcion){}
    @PatchMapping(path = "/actualizar")
    public ResponseEntity<GenericResponse<Optional<Modulo>>> update(@RequestBody UpdateRecord record) {
        Optional<Modulo> posibleModulo = moduloService.update(record.nombre,record.descripcion);
        if(posibleModulo.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleModulo,"modulo  actualizado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private record SaveRecord(String nombre, String descripcion, String abreviacionUniversidad){}
    @PostMapping("/guardar")
    public ResponseEntity<GenericResponse<Modulo>> save(@RequestBody SaveRecord record){
        Optional<Modulo> posibleModulo = moduloService.findById(record.nombre);
        if(!posibleModulo.isPresent()){
            Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacionUniversidad);
            if(posibleUniversidad.isPresent()){
                Modulo nuevoModulo = new Modulo(record.nombre,record.descripcion,posibleUniversidad.get());
                return new ResponseEntity<>(new GenericResponse<>(moduloService.save(nuevoModulo),"modulo creado"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @GetMapping("/cantidadModulosEnUniversidad")
    public ResponseEntity<GenericResponse<List<Map<String, Object>>>> cantidadModulosEnUniversidad(){
        List<Map<String, Object>> modulosEnUniversidad = moduloService.cantidadModulosEnUniversidad() ;
        if(!modulosEnUniversidad.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(moduloService.cantidadModulosEnUniversidad(),"cantidad de modulos por universidad"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private record BuscarPorUniversidadRecord(String abreviacion){}
    @GetMapping("/busqueda_por_universidad")
    public ResponseEntity<GenericResponse<List<Modulo>>> findByUniversidad(@RequestBody BuscarPorUniversidadRecord record){
        Optional<Universidad> posibleUniversidad = universidadService.findById(record.abreviacion);
        if(posibleUniversidad.isPresent()){
            List<Modulo> modulosDeUniversidad = moduloService.findByUniversidad(posibleUniversidad.get());
            if(!modulosDeUniversidad.isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(modulosDeUniversidad,"modulos de la universidad seleccionada"), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
