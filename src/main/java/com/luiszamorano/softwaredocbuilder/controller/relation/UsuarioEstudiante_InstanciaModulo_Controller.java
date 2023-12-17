package com.luiszamorano.softwaredocbuilder.controller.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.InstanciaModuloService;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import com.luiszamorano.softwaredocbuilder.service.UsuarioService;
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

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private InstanciaModuloService instanciaModuloService;

    @Autowired
    private UsuarioService usuarioService;

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
    public ResponseEntity<GenericResponse<List<UsuarioEstudiante_InstanciaModulo>>> findByUsuario(@RequestParam String rut){
        Optional<Usuario> posibleUsuario = usuarioService.findById(rut);
        if(posibleUsuario.isPresent()){
            List<UsuarioEstudiante_InstanciaModulo> resultados = usuarioEstudianteInstanciaModuloServicio.findByUsuario(posibleUsuario.get());
            if(!resultados.isEmpty()){
                return new ResponseEntity<>(
                        new GenericResponse<>(
                                resultados,"usuarioestudiante_instanciamodulo encontrados y filtrados por usuario"
                        ), HttpStatus.OK
                );
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
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

    @GetMapping("/findByUsuarioAndInstanciaModuloPK")
    public ResponseEntity<GenericResponse<Optional<UsuarioEstudiante_InstanciaModulo>>> findByUsuarioAndInstanciaModuloPK(
            @RequestParam String nombre_modulo,
            @RequestParam int ano,
            @RequestParam int semestre,
            @RequestParam char seccion,
            @RequestParam String rut_usuario
    ){
        Optional<Modulo> posibleModulo = moduloService.findById(nombre_modulo);
        Optional<Usuario> posibleUsuario = usuarioService.findById(rut_usuario);

        if(posibleModulo.isPresent() && posibleUsuario.isPresent()){
            InstanciaModuloPK instanciaModuloPK = new InstanciaModuloPK(
                    posibleModulo.get(),ano,semestre,seccion
            );



            Optional<UsuarioEstudiante_InstanciaModulo> posibleCoincidencia =
                    usuarioEstudianteInstanciaModuloServicio.findByUsuarioAndInstanciaModuloPK(
                    posibleUsuario.get(),
                    instanciaModuloPK
            );

            if(posibleCoincidencia.isPresent()){
                return new ResponseEntity<>(
                        new GenericResponse<>(
                                posibleCoincidencia,"usuarioestudiante_instanciamodulo encontrado por usuario e instancia"
                        ), HttpStatus.OK
                );
            }


        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @GetMapping("/findByInstanciaModuloPK")
    public ResponseEntity<GenericResponse<List<UsuarioEstudiante_InstanciaModulo>>> findByInstanciaModuloPK(
            @RequestParam String nombre_modulo,
            @RequestParam int ano,
            @RequestParam int semestre,
            @RequestParam char seccion
    ){
        Optional<Modulo> posibleModulo = moduloService.findById(nombre_modulo);

        if(posibleModulo.isPresent()){
            InstanciaModuloPK instanciaModuloPK = new InstanciaModuloPK(
                    posibleModulo.get(),ano,semestre,seccion
            );



            List<UsuarioEstudiante_InstanciaModulo> resultados = usuarioEstudianteInstanciaModuloServicio.findByInstanciaModuloPK(
                    instanciaModuloPK
            );

            if(!resultados.isEmpty()){
                return new ResponseEntity<>(
                        new GenericResponse<>(
                                resultados,"usuarioestudiante_instanciamodulo encontrados, filtrados por instancia"
                        ), HttpStatus.OK
                );
            }


        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<UsuarioEstudiante_InstanciaModulo>> save(
            @RequestParam String nombre_modulo,
            @RequestParam int ano,
            @RequestParam int semestre,
            @RequestParam char seccion,
            @RequestParam String rut_usuario
    ){
        Optional<Modulo> posibleModulo = moduloService.findById(nombre_modulo);
        Optional<Usuario> posibleUsuario = usuarioService.findById(rut_usuario);

        if(posibleModulo.isPresent() && posibleUsuario.isPresent()){
            InstanciaModuloPK instanciaModuloPK = new InstanciaModuloPK(
                    posibleModulo.get(),ano,semestre,seccion
            );



            Optional<UsuarioEstudiante_InstanciaModulo> resultados = usuarioEstudianteInstanciaModuloServicio.findByUsuarioAndInstanciaModuloPK(
                    posibleUsuario.get(),
                    instanciaModuloPK
            );

            Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(instanciaModuloPK);
            if(resultados.isEmpty() && posibleInstancia.isPresent()){

                UsuarioEstudiante_InstanciaModulo usuarioGuardado =
                        usuarioEstudianteInstanciaModuloServicio.save(posibleUsuario.get(),posibleInstancia.get());

                return new ResponseEntity<>(
                        new GenericResponse<>(
                                usuarioGuardado,"usuarioestudiante_instanciamodulo guardado"
                        ), HttpStatus.OK
                );
            }


        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
