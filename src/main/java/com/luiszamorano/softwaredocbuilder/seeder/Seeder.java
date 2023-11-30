package com.luiszamorano.softwaredocbuilder.seeder;

import com.luiszamorano.softwaredocbuilder.entity.*;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Seeder {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UniversidadRepository universidadRepository;

    @Autowired
    private RolPlataformaRepository rolPlataformaRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    InstanciaModuloRepository instanciaModuloRepository;

    @PostConstruct
    public void seed(){
        roles();
        universidades();
        modulos();
        usuarios();
        instancias();
    }

    public void usuarios(){
        Usuario usuarioAdmin = new Usuario("00.000.000-0","Sr","Admin","administrador","admin@softwaredocbuilder.cl");
        usuarioAdmin.setRol_plataforma("Administrador");
        usuarioRepository.save(usuarioAdmin);
    }

    public void universidades(){
        universidadRepository.save(new Universidad("UTALCA","Universidad de Talca"));
        universidadRepository.save(new Universidad("UCM","Universidad Catolica del Maule"));
        universidadRepository.save(new Universidad("UCHILE","Universidad de Chile"));
        universidadRepository.save(new Universidad("UC","Universidad Catolica"));
        universidadRepository.save(new Universidad("UTFSM","Universidad Federico Santa Maria"));
    }

    public void roles(){
        rolPlataformaRepository.save(new RolPlataforma("Jefe de Carrera"));
        rolPlataformaRepository.save(new RolPlataforma("Profesor"));
        rolPlataformaRepository.save(new RolPlataforma("Estudiante"));
    }

    public void modulos(){
        moduloRepository.save(new Modulo("Requisitos de Software","semestre 5 utalca"));
        moduloRepository.save(new Modulo("Diseño de Software","semestre 6 utalca"));
        moduloRepository.save(new Modulo("Construccion de Software","semestre 7 utalca"));
        moduloRepository.save(new Modulo("Taller de Software","semestre 9 utalca"));

        moduloRepository.save(new Modulo("Ingenieria de Software 1","semestre 8 ucm"));
        moduloRepository.save(new Modulo("Ingenieria de Software 2","semestre 9 ucm"));
        moduloRepository.save(new Modulo("Taller de desarrollo de software","semestre 10 ucm"));

        moduloRepository.save(new Modulo("Programación de software de sistemas","semestre 6 uch"));
        moduloRepository.save(new Modulo("Ingenieria de Software","semestre 7 uch"));
        moduloRepository.save(new Modulo("Proyecto de Software","semestre 10 uch"));

        moduloRepository.save(new Modulo("Ingeniería de Software","semestre 4 uc"));

        moduloRepository.save(new Modulo("Analisis y diseño de software","semestre 6 utfsm"));
        moduloRepository.save(new Modulo("Ingenieria de Software","semestre 7 utfsm"));
    }

    public void instancias(){
        List<Modulo> modulos = moduloRepository.findAll();
        List<Character> secciones = List.of('a', 'b', 'c');
        for(Modulo modulo: modulos){
            for(Character seccion: secciones){
                instanciaModuloRepository.save(new InstanciaModulo(new InstanciaModuloPK(
                        moduloRepository.findById(modulo.getNombre()).get(),
                        2023,
                        2,
                        seccion
                )));
            }
        }
    }
}
