package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Colonia {

    private int IdColonia;
    private String Nombre;
    private String CodigoPostal;
    @ManyToOne
    @JoinColumn( name = "idmunicipio")
    public Municipio Municipio;

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    

}
