package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(String rut){
        return usuarioRepository.findById(rut);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> findByEstadoTrue(){
        return usuarioRepository.findByEstadoTrue();
    }

    public List<Usuario> findByEstadoFalse(){
        return  usuarioRepository.findByEstadoFalse();
    }

    public Optional<Usuario> login(String rut, String contrasena) {
        Optional<Usuario> posibleUsuario = usuarioRepository.findById(rut);
        if(posibleUsuario.isPresent()){
            Usuario usuario = posibleUsuario.get();
            if(usuario.getContrasena().equals(contrasena)){
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
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
        Usuario usuario = new Usuario(rut,nombres,apellidos,contrasena,email);
        usuarioRepository.save(usuario);
        return usuario;
    }
}
