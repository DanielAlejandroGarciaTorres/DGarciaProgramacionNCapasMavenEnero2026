package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Colonia;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Direccion;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Semestre;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AlumnoDAOJPAImplementation implements IAlumnoJPA{

    @Autowired
    private EntityManager entityManager; // JPA

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try {
            
            TypedQuery<Alumno> queryAlumno = entityManager.createQuery("FROM Alumno", Alumno.class);
            List<Alumno> alumnos = queryAlumno.getResultList();
            
            
            // mapper ...  AlumnoJPA -> AlumnoML   -- modelMapper 
            
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    @Override
    @Transactional
    public Result Add(com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno alumno) {
        Result result = new Result();
        
        try{
            /* ML -> JPA*/
            Alumno alumnoJPA = new Alumno();
            alumnoJPA.setNombre("Daniel Alejandro");
            alumnoJPA.setApellidoPaterno("García");
            alumnoJPA.setApellidoMaterno("Torres");
            alumnoJPA.setEmail("dgarcia3@digis01.com");
            alumnoJPA.setTelefono("5566998877");
            alumnoJPA.Semestre = new Semestre();
            alumnoJPA.Semestre.setIdSemestre(1);
            
            alumnoJPA.Direcciones = new ArrayList<>();
            Direccion direccionJPA = new Direccion();
            alumnoJPA.Direcciones.add(direccionJPA);
            
            direccionJPA.setCalle("Hamburgo");
            direccionJPA.setNumeroInterior("14");
            direccionJPA.setNumeroExterior("199");
            direccionJPA.Colonia = new Colonia();
            direccionJPA.Colonia.setIdColonia(7);
            
            direccionJPA.Alumno = alumnoJPA;
            
            entityManager.persist(alumnoJPA);
            
            result.correct = true;
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }

    @Override
    public Result GetById(int idAlumno) {
        Result result = new Result();
        try{
            Alumno alumno = entityManager.find(Alumno.class, idAlumno);
            // jpa -> Ml
        }catch(Exception ex){
        }
        return result;
    }

    @Override
    @Transactional
    public Result Update(com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno alumno) {
         Result result = new Result();
        try{
            Alumno alumnoBD = entityManager.find(Alumno.class, alumno.getIdAlumno());
            if (alumno != null) { // alumno si existe
                //ML -> JPA
                Alumno alumnoJPA = new Alumno();
                alumnoJPA.Direcciones = alumnoBD.Direcciones;
                entityManager.merge(alumnoJPA);
            }
        }catch(Exception ex){
        }
        return result;
    }

    @Override
    @Transactional
    public Result Delete(int idAlumno) {
        Result result = new Result();
        try{
            Alumno alumno = entityManager.find(Alumno.class, idAlumno);
            if (alumno != null) {
                entityManager.remove(alumno);
                result.correct = true;
            } else {
                result.correct = false;
                result.errorMessage = "No se encontro el recurso";
            }
        }catch(Exception ex){
        }
        return result;
    }
    
    
    public com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno AlumnoJPAtoML(Alumno alumno){
        com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno alumnoML = new com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno();
        
        alumnoML.setIdAlumno(alumno.getIdAlumno());
        alumnoML.setNombre(alumno.getNombre());
        alumnoML.setApellidoPaterno(alumno.getApellidoPaterno());
        alumnoML.setApellidoMaterno(alumno.getApellidoMaterno());
        alumnoML.setTelefono(alumno.getTelefono());
        alumnoML.setEmail(alumno.getEmail());
        alumnoML.setPassword(alumno.getPassword());
        alumnoML.setStatus(alumno.getStatus());
        alumnoML.Semestre = new com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Semestre();
        alumnoML.Semestre.setIdSemestre(alumno.Semestre.getIdSemestre());
        alumnoML.Semestre.setNombre(alumno.Semestre.getNombre());
        
        return alumnoML;
    }
    
    public Alumno AlumnoMLtoJPA (com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno alumno){
        
        Alumno alumnoJPA = new Alumno();
        
        alumnoJPA.setIdAlumno(alumno.getIdAlumno());
        alumnoJPA.setNombre(alumno.getNombre());
        alumnoJPA.setApellidoPaterno(alumno.getApellidoPaterno());
        alumnoJPA.setApellidoMaterno(alumno.getApellidoMaterno());
        alumnoJPA.setTelefono(alumno.getTelefono());
        alumnoJPA.setEmail(alumno.getEmail());
        alumnoJPA.setPassword(alumno.getPassword());
        alumnoJPA.setStatus(alumno.getStatus());
        alumnoJPA.Semestre = new Semestre();
        alumnoJPA.Semestre.setIdSemestre(alumno.Semestre.getIdSemestre());
        alumnoJPA.Semestre.setNombre(alumno.Semestre.getNombre());
        
        return alumnoJPA;
    }

    @Override
    public Result GetByEmail(String Email) {
        Result result = new Result();
        
        try {
            
            TypedQuery<Alumno> queryAlumno = entityManager.createQuery("FROM Alumno WHERE Email = :pEmail", Alumno.class);
            queryAlumno.setParameter("pEmail", Email);
            Alumno alumno = queryAlumno.getSingleResult();
            
            /*JPA -> ML*/
            
            result.object = AlumnoJPAtoML(alumno);
            result.correct = true;
            
            
        }catch(Exception ex){
            
        }
        
        return result;
    }
    
}
