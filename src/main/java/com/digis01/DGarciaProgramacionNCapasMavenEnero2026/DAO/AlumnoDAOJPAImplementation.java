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
    
}
