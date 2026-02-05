package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML;

import java.util.Date;
import java.util.List;

public class Alumno {

    private int IdAlumno;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Telefono;
    private String Email;
    private Date FechaNacimiento; 
    public Semestre Semestre;
    public List<Direccion> Direcciones;

    public Alumno() {
    }

    public Alumno(int IdAlumno, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String Telefono, String Email, Date FechaNacimiento) {
        this.IdAlumno = IdAlumno;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.Telefono = Telefono;
        this.Email = Email;
        this.FechaNacimiento = FechaNacimiento;
    }

    
    
    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }
    
    

}
