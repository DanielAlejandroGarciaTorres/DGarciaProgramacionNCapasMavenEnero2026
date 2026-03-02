package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
            
            
            // mapper ...
            
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
