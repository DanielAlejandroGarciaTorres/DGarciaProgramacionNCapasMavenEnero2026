package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import java.util.List;


public interface IAlumno {

    Result GetAll();
    Result Add(Alumno alumno);
    Result AddAll(List<Alumno> alumnos);
    
}
