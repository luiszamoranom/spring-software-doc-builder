package com.luiszamorano.softwaredocbuilder.seeder;

import com.luiszamorano.softwaredocbuilder.entity.*;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
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
    private RolUniversidadRepository rolPlataformaRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private InstanciaModuloRepository instanciaModuloRepository;

    @Autowired
    private RolProyectoRepository rolProyectoRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @PostConstruct
    public void seed(){
        roles();
        rolesProyecto();
        universidades();
        modulos();

        usuarios();
        instancias();

        proyectos();
    }

    public void usuarios(){
        if(usuarioRepository.count()==0){
            Usuario usuarioAdmin = new Usuario("00.000.000-0","Sr","Admin","administrador","admin@softwaredocbuilder.cl");
            usuarioAdmin.setRol_plataforma("Administrador");
            usuarioRepository.save(usuarioAdmin);
        }
    }

    public void universidades(){
        if(universidadRepository.count()==0){
            universidadRepository.save(new Universidad("UTALCA","Universidad de Talca"));
            universidadRepository.save(new Universidad("UCM","Universidad Catolica del Maule"));
            universidadRepository.save(new Universidad("UCHILE","Universidad de Chile"));
            universidadRepository.save(new Universidad("UC","Universidad Catolica"));
            universidadRepository.save(new Universidad("UTFSM","Universidad Federico Santa Maria"));
        }
    }

    public void roles(){
        if(universidadRepository.count()==0){
            rolPlataformaRepository.save(new RolUniversidad("Jefe de Carrera"));
            rolPlataformaRepository.save(new RolUniversidad("Profesor"));
            rolPlataformaRepository.save(new RolUniversidad("Estudiante"));
        }
    }

    public void modulos(){
        if(moduloRepository.count()==0){
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
    }

    public void instancias(){
        if(instanciaModuloRepository.count()==0){
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

    public void rolesProyecto(){
        if(rolProyectoRepository.count()==0){
            rolProyectoRepository.save(new RolProyecto("Lider"));
            rolProyectoRepository.save(new RolProyecto("Diseñador"));
            rolProyectoRepository.save(new RolProyecto("Analista"));
            rolProyectoRepository.save(new RolProyecto("Programador"));
            rolProyectoRepository.save(new RolProyecto("Tester"));
        }
    }

    public void proyectos(){
        if(proyectoRepository.count()==0){
            List<InstanciaModulo> instancias = instanciaModuloRepository.findAll();
            for(InstanciaModulo instancia: instancias){
                for(int i=0; i<5; i++){
                    proyectoRepository.save(
                            new Proyecto(
                                    new ProyectoPK(
                                            "Proyecto demo N° "+i+" para la instancia "+ instancia.getInstanciaModuloPK().getModulo().getNombre(),
                                            instancia
                                    )
                            )
                    );
                }
            }
        }
    }
}
