package com.luiszamorano.softwaredocbuilder.seeder;

import com.github.javafaker.Faker;
import com.luiszamorano.softwaredocbuilder.entity.*;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.repository.*;
import com.luiszamorano.softwaredocbuilder.repository.refactor.Usuario_RolUniversidad_Universidad_Repository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*

Quiero instancias con semestre distinto y año distinto
 */
@Service
public class Seeder {

    @Autowired
    private Usuario_RolUniversidad_Universidad_Repository usuarioRolUniversidadUniversidadRepository;

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
    private RolUniversidadRepository rolUniversidadRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @PostConstruct
    public void seed() throws InterruptedException {
        roles();
        dormir();
        rolesProyecto();
        dormir();
        universidades();
        dormir();
        modulos();

        dormir();
        usuarios();
        dormir();
        roles_en_universidad();

        dormir();
        instancias();

        dormir();
        proyectos();
    }

    public void dormir() throws InterruptedException {
        try {
            Thread.sleep(1000*1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void usuarios(){
        if(usuarioRepository.count()==0){
            Faker faker = new Faker();

            Usuario usuarioAdmin = new Usuario("99.999.999-9","Sr","Admin","administrador","admin@softwaredocbuilder.cl");
            usuarioAdmin.setRol_plataforma("Administrador");
            usuarioRepository.save(usuarioAdmin);

            for(int i=0; i<55; i++){
                String valorI=Integer.toString(i);
                String  rut;
                if(i<10){
                    rut = "0"+valorI+".000.000-0";
                }else{
                    rut = valorI+".000.000-0";
                }
                Usuario usuario = new Usuario(rut,faker.name().firstName(),faker.name().lastName(),"pass"+valorI,"demo"+valorI+"@softwaredocbuilder.cl");
                usuarioRepository.save(usuario);
            }

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
            Universidad utalca = universidadRepository.findById("UTALCA").get();
            moduloRepository.save(new Modulo("Requisitos de Software","semestre 5 utalca",utalca));
            moduloRepository.save(new Modulo("Diseño de Software","semestre 6 utalca",utalca));
            moduloRepository.save(new Modulo("Construccion de Software","semestre 7 utalca",utalca));
            moduloRepository.save(new Modulo("Taller de Software","semestre 9 utalca",utalca));


            Universidad ucm = universidadRepository.findById("UCM").get();
            moduloRepository.save(new Modulo("Ingenieria de Software 1","semestre 8 ucm",ucm));
            moduloRepository.save(new Modulo("Ingenieria de Software 2","semestre 9 ucm",ucm));
            moduloRepository.save(new Modulo("Taller de desarrollo de software","semestre 10 ucm",ucm));
        


            Universidad uch = universidadRepository.findById("UCHILE").get();
            moduloRepository.save(new Modulo("Programación de software de sistemas","semestre 6 uch",uch));
            moduloRepository.save(new Modulo("Ingenieria de Software","semestre 7 uch",uch));
            moduloRepository.save(new Modulo("Proyecto de Software","semestre 10 uch",uch));


            Universidad uc = universidadRepository.findById("UC").get();
            moduloRepository.save(new Modulo("Ingeniería del Software","semestre 4 uc",uc));


            Universidad utfsm = universidadRepository.findById("UTFSM").get();
            moduloRepository.save(new Modulo("Analisis y diseño de software","semestre 6 utfsm",utfsm));
            moduloRepository.save(new Modulo("Fundamentos de Ingenieria de Software","semestre 7 utfsm",utfsm));
        }
    }

    public void instancias(){
        Usuario profesorUTALCA= usuarioRepository.findById("01.000.000-0").get();
        Usuario profesorUCM = usuarioRepository.findById("12.000.000-0").get();
        Usuario profesorUCHILE = usuarioRepository.findById("23.000.000-0").get();
        Usuario profesorUC = usuarioRepository.findById("33.000.000-0").get();
        Usuario profesorUTFSM = usuarioRepository.findById("44.000.000-0").get();

        Universidad utalca = universidadRepository.findById("UTALCA").get();
        RolUniversidad rolEstudiante = rolUniversidadRepository.findByNombre("Estudiante").get();
        List<Usuario_RolUniversidad_Universidad> estudiantesUTALCA = usuarioRolUniversidadUniversidadRepository.findByUsuarioRolUniversidadUniversidadPkUniversidadAndUsuarioRolUniversidadUniversidadPkRolUniversidad(
            utalca,rolEstudiante
        );

        List<Usuario_RolUniversidad_Universidad> estudiantesUCM = usuarioRolUniversidadUniversidadRepository.findByUsuarioRolUniversidadUniversidadPkUniversidadAndUsuarioRolUniversidadUniversidadPkRolUniversidad(
                utalca,rolEstudiante
        );

        List<Usuario_RolUniversidad_Universidad> estudiantesUCHILE = usuarioRolUniversidadUniversidadRepository.findByUsuarioRolUniversidadUniversidadPkUniversidadAndUsuarioRolUniversidadUniversidadPkRolUniversidad(
                utalca,rolEstudiante
        );

        List<Usuario_RolUniversidad_Universidad> estudiantesUC = usuarioRolUniversidadUniversidadRepository.findByUsuarioRolUniversidadUniversidadPkUniversidadAndUsuarioRolUniversidadUniversidadPkRolUniversidad(
                utalca,rolEstudiante
        );

        List<Usuario_RolUniversidad_Universidad> estudiantesUTFSM = usuarioRolUniversidadUniversidadRepository.findByUsuarioRolUniversidadUniversidadPkUniversidadAndUsuarioRolUniversidadUniversidadPkRolUniversidad(
                utalca,rolEstudiante
        );

        if(instanciaModuloRepository.count()==0){
            List<Modulo> modulos = moduloRepository.findAll();
            List<Character> secciones = List.of('a', 'b', 'c');
            for(Modulo modulo: modulos){
                Usuario profesorAsignado = new Usuario();
                List<Usuario_RolUniversidad_Universidad> estudiantes = new ArrayList<>();


                switch (modulo.getUniversidad().getAbreviacion()){
                    case "UTALCA":
                        profesorAsignado=profesorUTALCA;
                        estudiantes=estudiantesUTALCA;
                        break;
                    case "UCM":
                        profesorAsignado=profesorUCM;
                        estudiantes=estudiantesUCM;
                        break;
                    case "UCHILE":
                        profesorAsignado=profesorUCHILE;
                        estudiantes=estudiantesUCHILE;
                        break;
                    case "UC":
                        profesorAsignado=profesorUC;
                        estudiantes=estudiantesUC;
                        break;
                    case "UTFSM":
                        profesorAsignado=profesorUTFSM;
                        estudiantes=estudiantesUTFSM;
                        break;
                }

                for(Character seccion: secciones){

                    InstanciaModulo nuevaInstancia = new InstanciaModulo(new InstanciaModuloPK(
                            moduloRepository.findById(modulo.getNombre()).get(),
                            2023,
                            2,
                            seccion
                    ),profesorAsignado);
                    for(Usuario_RolUniversidad_Universidad estudiante : estudiantes){
                        Usuario usuarioIt = estudiante.getUsuarioRolUniversidadUniversidadPk().getUsuario();
                        UsuarioEstudiante_InstanciaModulo usuarioEstudianteInstanciaModulo = new UsuarioEstudiante_InstanciaModulo(
                                usuarioIt,
                                nuevaInstancia
                        );
                        nuevaInstancia.getUsuarioEstudianteInstanciaModuloList().add(usuarioEstudianteInstanciaModulo);
                    }
                    instanciaModuloRepository.save(nuevaInstancia);
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


    public void roles_en_universidad(){
        if(usuarioRolUniversidadUniversidadRepository.count()==0){
            List<Usuario> usuarios = usuarioRepository.findAll();
            List<RolUniversidad> roles_en_universidad = rolUniversidadRepository.findAll();
            List<Universidad> universidades = universidadRepository.findAll();
            for(int i=0; i < 55; i++){
                if(i<11){
                    usuarioRolUniversidadUniversidadRepository.save(
                            new Usuario_RolUniversidad_Universidad(
                                    new Usuario_RolUniversidad_Universidad_PK(
                                            usuarios.get(0),
                                            universidades.get(0),
                                            roles_en_universidad.get(0)
                                    )
                            )
                    );
                    if(i>1 && i<6){
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(0),
                                                roles_en_universidad.get(1)
                                        )
                                )
                        );
                    }else{
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(0),
                                                roles_en_universidad.get(2)
                                        )
                                )
                        );
                    }
                }else if(i<22){
                    usuarioRolUniversidadUniversidadRepository.save(
                            new Usuario_RolUniversidad_Universidad(
                                    new Usuario_RolUniversidad_Universidad_PK(
                                            usuarios.get(11),
                                            universidades.get(1),
                                            roles_en_universidad.get(0)
                                    )
                            )
                    );
                    if(i>12 && i<16){
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(1),
                                                roles_en_universidad.get(1)
                                        )
                                )
                        );
                    }else{
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(1),
                                                roles_en_universidad.get(2)
                                        )
                                )
                        );
                    }
                }else if(i<33){
                    usuarioRolUniversidadUniversidadRepository.save(
                            new Usuario_RolUniversidad_Universidad(
                                    new Usuario_RolUniversidad_Universidad_PK(
                                            usuarios.get(0),
                                            universidades.get(2),
                                            roles_en_universidad.get(0)
                                    )
                            )
                    );
                    if(i>23 && i<28){
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(2),
                                                roles_en_universidad.get(1)
                                        )
                                )
                        );
                    }else{
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(2),
                                                roles_en_universidad.get(2)
                                        )
                                )
                        );
                    }
                }else if(i<44){
                    usuarioRolUniversidadUniversidadRepository.save(
                            new Usuario_RolUniversidad_Universidad(
                                    new Usuario_RolUniversidad_Universidad_PK(
                                            usuarios.get(0),
                                            universidades.get(3),
                                            roles_en_universidad.get(0)
                                    )
                            )
                    );
                    if(i>33 && i<38){
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(3),
                                                roles_en_universidad.get(1)
                                        )
                                )
                        );
                    }else{
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(3),
                                                roles_en_universidad.get(2)
                                        )
                                )
                        );
                    }
                }else{
                    usuarioRolUniversidadUniversidadRepository.save(
                            new Usuario_RolUniversidad_Universidad(
                                    new Usuario_RolUniversidad_Universidad_PK(
                                            usuarios.get(0),
                                            universidades.get(4),
                                            roles_en_universidad.get(0)
                                    )
                            )
                    );
                    if(i>44 && i<48){
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(4),
                                                roles_en_universidad.get(1)
                                        )
                                )
                        );
                    }else{
                        usuarioRolUniversidadUniversidadRepository.save(
                                new Usuario_RolUniversidad_Universidad(
                                        new Usuario_RolUniversidad_Universidad_PK(
                                                usuarios.get(i),
                                                universidades.get(4),
                                                roles_en_universidad.get(2)
                                        )
                                )
                        );
                    }
                }
            }
        }
    }

}
