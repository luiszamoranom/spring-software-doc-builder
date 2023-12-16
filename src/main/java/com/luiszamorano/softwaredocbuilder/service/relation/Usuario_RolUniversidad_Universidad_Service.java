package com.luiszamorano.softwaredocbuilder.service.relation;

import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import com.luiszamorano.softwaredocbuilder.repository.Usuario_RolUniversidad_Universidad_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Usuario_RolUniversidad_Universidad_Service {
    @Autowired
    private Usuario_RolUniversidad_Universidad_Repository usuarioRolUniversidadUniversidadRepository;

    public List<Usuario_RolUniversidad_Universidad> findAll(){
        return usuarioRolUniversidadUniversidadRepository.findAll();
    }

    public Optional<Usuario_RolUniversidad_Universidad> findById(Usuario_RolUniversidad_Universidad_PK id){
        return usuarioRolUniversidadUniversidadRepository.findById(id);
    }

    public List<Usuario_RolUniversidad_Universidad> findByUsuario(Usuario usuario){
        return usuarioRolUniversidadUniversidadRepository.findByUsuarioRolUniversidadUniversidadPkUsuario(usuario);
    }

    public void anadirRolValidoEnUniversidadValidadAUsuarioExistente(Usuario usuarioGuardado, RolUniversidad rolUniversidad, Universidad universidad) {
        usuarioRolUniversidadUniversidadRepository.save(
                new Usuario_RolUniversidad_Universidad(
                        new Usuario_RolUniversidad_Universidad_PK(usuarioGuardado,universidad,rolUniversidad)
                )
        );
    }

    public Map<String, Long> countByUsuarioRolUniversidadUniversidadPkUniversidad(Universidad universidad) {
        List<Object[]> resultados = usuarioRolUniversidadUniversidadRepository.countUsuariosByUniversidad();
        Map<String, Long> conteoPorUniversidad = new HashMap<>();
        for (Object[] resultado : resultados) {
            String abreviacion = (String) resultado[0];
            Long conteo = (Long) resultado[1];
            conteoPorUniversidad.put(abreviacion, conteo);
        }
        return conteoPorUniversidad;
    }
}
