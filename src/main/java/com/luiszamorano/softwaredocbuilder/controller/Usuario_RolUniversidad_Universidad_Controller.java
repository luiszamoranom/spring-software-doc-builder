package com.luiszamorano.softwaredocbuilder.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public ResponseEntity<GenericResponse<Optional<Usuario_RolUniversidad_Universidad>>> findById(FindByIdRecord record){
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

}
