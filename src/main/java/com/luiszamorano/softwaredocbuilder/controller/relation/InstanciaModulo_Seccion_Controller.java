package com.luiszamorano.softwaredocbuilder.controller.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.relation.InstanciaModulo_Seccion;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.InstanciaModuloService;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import com.luiszamorano.softwaredocbuilder.service.SeccionService;
import com.luiszamorano.softwaredocbuilder.service.relation.InstanciaModulo_Seccion_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instanciamodulo_seccion")
public class InstanciaModulo_Seccion_Controller {
    @Autowired
    private InstanciaModulo_Seccion_Service instanciaModuloSeccionService;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private InstanciaModuloService instanciaModuloService;

    @Autowired
    private SeccionService seccionService;

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<InstanciaModulo_Seccion>>> findAll(){
        List<InstanciaModulo_Seccion> secciones = instanciaModuloSeccionService.findAll();
        if(secciones.size() > 0){
            return new ResponseEntity<>(new GenericResponse<>(
                    secciones,"instanciamodulos secciones encontradas"
            ), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findById")
    public ResponseEntity<GenericResponse<InstanciaModulo_Seccion>> findById(@RequestParam Long id){
        Optional<InstanciaModulo_Seccion> posibleEntrada = instanciaModuloSeccionService.findById(id);
        if(posibleEntrada.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(
                    posibleEntrada.get(),"instanciamodulo_seccion encontrada con dicho id"
            ), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByInstanciaModulo")
    public ResponseEntity<GenericResponse<List<InstanciaModulo_Seccion>>> findByInstanciaModulo(
            @RequestParam String nombre_modulo,
            @RequestParam int ano,
            @RequestParam int semestre,
            @RequestParam char seccion
    ) {
        Optional<Modulo> posibleModulo = moduloService.findById(nombre_modulo);
        if (posibleModulo.isPresent()) {
            InstanciaModuloPK instanciaModuloPK = new InstanciaModuloPK(
                    posibleModulo.get(),
                    ano,
                    semestre,
                    seccion
            );
            Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(instanciaModuloPK);
            if (posibleInstancia.isPresent()) {
                List<InstanciaModulo_Seccion> entradas = instanciaModuloSeccionService.findByInstanciaModulo(posibleInstancia.get());
                if (entradas.size() > 0) {
                    return new ResponseEntity<>(new GenericResponse<>(
                            entradas, "se encuentran coincidencias con dicha instanciamodulo"
                    ), HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<InstanciaModulo_Seccion>> save(
            @RequestParam String nombre_modulo,
            @RequestParam int ano,
            @RequestParam int semestre,
            @RequestParam char seccion,
            @RequestParam String nombre_seccion
    ){
        Optional<Modulo> posibleModulo = moduloService.findById(nombre_modulo);
        if(posibleModulo.isPresent()){
            InstanciaModuloPK instanciaModuloPK = new InstanciaModuloPK(
                    posibleModulo.get(),
                    ano,
                    semestre,
                    seccion
            );
            Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(instanciaModuloPK);
            if(posibleInstancia.isPresent()){
                Optional<Seccion> posibleSeccion = seccionService.findById(nombre_seccion);
                if(posibleSeccion.isPresent()){
                    Optional<InstanciaModulo_Seccion> posibleEntrada = instanciaModuloSeccionService.findByInstanciaModuloAndSeccion(
                            posibleInstancia.get(),
                            posibleSeccion.get()
                    );
                    if(posibleEntrada.isEmpty()){
                        instanciaModuloSeccionService.save(posibleEntrada.get());
                        return new ResponseEntity<>(new GenericResponse<>(
                                posibleEntrada.get(),"se crea exitosamente la instanciamodulo_seccion"
                        ), HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse<Seccion>> delete(@RequestParam Long id){
        Optional<InstanciaModulo_Seccion> posibleEntrada = instanciaModuloSeccionService.findById(id);
        if(posibleEntrada.isPresent()){
            instanciaModuloSeccionService.delete(posibleEntrada.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
