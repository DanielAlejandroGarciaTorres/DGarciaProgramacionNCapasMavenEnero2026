package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Table(name = "ALUMNODIRECCION")
public class Alumno {

    //idalumno
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idalumno")
    private int IdAlumno;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    @Column(name = "telefono")
    private String Telefono;
    @Column(name = "email")
    private String Email;
//    @Column(name = "apellidopaterno")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date FechaNacimiento;
    @ManyToOne
    @JoinColumn(name = "idsemestre") // FK
    public Semestre Semestre; 
    //
//    public List<Direccion> Direcciones;
//    //Lob
//    public String Imagen;

    public Alumno() {
    }

//    public Alumno(int IdAlumno, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String Telefono, String Email, Date FechaNacimiento) {
//        this.IdAlumno = IdAlumno;
//        this.Nombre = Nombre;
//        this.ApellidoPaterno = ApellidoPaterno;
//        this.ApellidoMaterno = ApellidoMaterno;
//        this.Telefono = Telefono;
//        this.Email = Email;
//        this.FechaNacimiento = FechaNacimiento;
//    }

    
    
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
    
    
//
//    public Date getFechaNacimiento() {
//        return FechaNacimiento;
//    }
//
//    public void setFechaNacimiento(Date FechaNacimiento) {
//        this.FechaNacimiento = FechaNacimiento;
//    }
    
    public void setSemestre(Semestre Semestre) {
        this.Semestre = Semestre;
    }
    
    public Semestre getSemestre(){
        return Semestre;
    }

//    public List<Direccion> getDirecciones() {
//        return Direcciones;
//    }
//
//    public void setDirecciones(List<Direccion> Direcciones) {
//        this.Direcciones = Direcciones;
//    }
//
//    public String getImagen() {
//        return Imagen;
//    }
//
//    public void setImagen(String Imagen) {
//        this.Imagen = Imagen;
//    }

    
}

