package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML;

public class Estado {

    private int IdEstado;
    private String Nombre;
    public com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Pais Pais;

    public Estado() {
    }
    
    public Estado(int IdEstado, String Nombre) {
        this.IdEstado = IdEstado;
        this.Nombre = Nombre;
    }
    
    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
