package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Alumno {

    private int IdAlumno;
//    @Pattern(regexp = "",message = "Solo acepto letras")
    @NotNull(message = "No puedo ser nulo")
    @NotEmpty(message = "No puedo ser vacio")
    @Size(min = 3, max = 50, message = "más de 2 letras min")
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String Telefono;
    
    private String Email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    @Valid
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
    
    public void setSemestre(Semestre Semestre) {
        this.Semestre = Semestre;
    }
    
    public Semestre getSemestre(){
        return Semestre;
    }

    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }

    
}
