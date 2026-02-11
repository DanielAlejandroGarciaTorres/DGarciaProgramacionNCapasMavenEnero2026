package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Direccion;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Semestre;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // manejo de logica de base datos
public class AlumnoDAOImplementation implements IAlumno {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result GetAll() {
        Result result = jdbcTemplate.execute("{CALL AlumnoDireccionGetAllSP(?)}", (CallableStatementCallback<Result>) callableStatement -> {
            Result resultSP = new Result();
            try {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                resultSP.objects = new ArrayList<>();

                int idAlumno = 0;

                while (resultSet.next()) {

                    idAlumno = resultSet.getInt("IdAlumno");
                    // el alumno ya esta en mi lista 
                    if (!resultSP.objects.isEmpty() && idAlumno == ((Alumno) (resultSP.objects.get(resultSP.objects.size() - 1))).getIdAlumno()) {
                        // direccion
                        Direccion direccion = new Direccion();

                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        // result.objects.get(n) --> permite traer un elemento espevcifico de la lista
                        // result.objects.size()-1 --> trae la ultima posición agregada 
                        Alumno alumno = ((Alumno) (resultSP.objects.get(resultSP.objects.size() - 1)));
                        alumno.Direcciones.add(direccion);
                    } else {
                        // el alumno no esta en mi lista 
                        Alumno alumno = new Alumno();
                        alumno.setIdAlumno(resultSet.getInt("IdAlumno"));
                        alumno.setNombre(resultSet.getString("NombreAlumno"));
                        alumno.Semestre = new Semestre();
                        alumno.Semestre.setIdSemestre(resultSet.getInt("IdSemestre"));
                        alumno.Semestre.setNombre(resultSet.getString("NombreSemestre"));

                        alumno.Direcciones = new ArrayList<>();

                        Direccion direccion = new Direccion();

                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                        direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                        alumno.Direcciones.add(direccion);
                        resultSP.objects.add(alumno);
                    }
                }
                resultSP.correct = true;

            } catch (Exception ex) {
                resultSP.correct = false;
                resultSP.errorMessage = ex.getLocalizedMessage();
                resultSP.ex = ex;
            }
            return resultSP;
        });

        return result;
    }

    @Override
    public Result Add(Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Autowired // Inyección de dependencias
//    private JdbcTemplate jdbcTemplate; // inyección de campo / field injection
}
