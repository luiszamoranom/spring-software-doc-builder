package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario.CredencialesInvalidasUsuarioException;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario.SinUsuarioException;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario.UsuarioNoEncontradoException;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario.UsuarioYaExisteException;
import com.luiszamorano.softwaredocbuilder.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(String rut){
        Optional<Usuario> posibleUsuario = usuarioRepository.findById(rut);
        if(posibleUsuario.isEmpty()){
            throw new UsuarioNoEncontradoException("no se encuentra ningun usuario con ese rut");
        }
        return posibleUsuario.get();
    }

    public List<Usuario> findAll(){
        List<Usuario> todosLosUsuarios = usuarioRepository.findAll();
        if(todosLosUsuarios.size()==0){
            throw new SinUsuarioException("sin usuarios");
        }
        return todosLosUsuarios;
    }

    public List<Usuario> findByEstadoTrue(){
        List<Usuario> todosLosUsuariosConEstadoTrue = usuarioRepository.findByEstadoTrue();
        if(todosLosUsuariosConEstadoTrue.isEmpty()){
            throw new SinUsuarioException("sin usuarios con estado true");
        }
        return todosLosUsuariosConEstadoTrue;
    }

    public List<Usuario> findByEstadoFalse(){
        List<Usuario> todosLosUsuariosConEstadoFalse = usuarioRepository.findByEstadoFalse();
        if(todosLosUsuariosConEstadoFalse.isEmpty()){
            throw new SinUsuarioException("sin usuarios con estado false");
        }
        return todosLosUsuariosConEstadoFalse;
    }

    public Usuario login(String rut, String contrasena) {
        Optional<Usuario> posibleUsuario = usuarioRepository.findById(rut);
        if(posibleUsuario.isEmpty()){
            throw new UsuarioNoEncontradoException("no se encuentra usuario con ese id");
        }
        if(!posibleUsuario.get().getContrasena().equals(contrasena)){
            throw new CredencialesInvalidasUsuarioException("credenciales invalidas para ese usuario");
        }
        return posibleUsuario.get();
    }

    public Optional<Usuario> cambiarEstado(String rut, Boolean estado) {
        Optional<Usuario> posibleUsuario = usuarioRepository.findById(rut);
        if(posibleUsuario.isPresent()) {
            Usuario usuario = posibleUsuario.get();
            usuario.setEstado(estado);
            usuarioRepository.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    public Optional<Usuario> update(String rut, String nombres, String apellidos, String contrasena, String email) {
        Optional<Usuario> posibleUsuario = usuarioRepository.findById(rut);
        if(posibleUsuario.isPresent()) {
            Usuario usuario = posibleUsuario.get();
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setContrasena(contrasena);
            usuario.setEmail(email);
            usuarioRepository.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    public Usuario save(String rut,
                        String nombres,
                        String apellidos,
                        String contrasena,
                        String email) {
        if(usuarioRepository.existsById(rut)){
            throw  new UsuarioYaExisteException("ya existe un usuario con ese rut, no se puede guardar");
        }
        Usuario usuario = new Usuario(rut,nombres,apellidos,contrasena,email);
        usuarioRepository.save(usuario);
        return usuario;
    }
}
