package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Direccion {

    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    @ManyToOne
    @JoinColumn(name = "idcolonia")
    public Colonia Colonia;
//    public Alumno Alumno;
    
    // alumno
    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }
    
    
    
}
