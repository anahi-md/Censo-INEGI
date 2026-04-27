package modelo;

import java.util.Date;

public class Vivienda {
    private int idVivienda;
    private String nombreVivienda;
    private String nombreMunicipio;
    private String nombreLocalidad;
    private String nombreTipoVivienda;
    private String calle;
    private String numExterior;
    private String numInterior;
    private String colonia;
    private String codigoPostal;
    private int idLocalidad;
    private int idMunicipio;
    private int idTipoVivienda;
    private int numCuartos;
    private int numBanios;
    private String materialPredominante;
    private String serviciosBasicos;
    private String observaciones;
    private boolean estatus;
    private Date createdAt;
    private Date updatedAt;

    public Vivienda() {}

    // Getters y Setters
    public int getIdVivienda() { return idVivienda; }
    public void setIdVivienda(int idVivienda) { this.idVivienda = idVivienda; 
    }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; 
    }

    public String getNumExterior() { return numExterior; }
    public void setNumExterior(String numExterior) { this.numExterior = numExterior; 
    }

    public String getNumInterior() { return numInterior; }
    public void setNumInterior(String numInterior) { this.numInterior = numInterior; 
    }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; 
    }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; 
    }

    public int getIdLocalidad() { return idLocalidad; }
    public void setIdLocalidad(int idLocalidad) { this.idLocalidad = idLocalidad; }

    public int getIdMunicipio() { return idMunicipio; }
    public void setIdMunicipio(int idMunicipio) { this.idMunicipio = idMunicipio; 
    }

    public int getIdTipoVivienda() { return idTipoVivienda; }
    public void setIdTipoVivienda(int idTipoVivienda) { this.idTipoVivienda = idTipoVivienda; 
    }

    public int getNumCuartos() { return numCuartos; }
    public void setNumCuartos(int numCuartos) { this.numCuartos = numCuartos; 
    }

    public int getNumBanios() { return numBanios; }
    public void setNumBanios(int numBanios) { this.numBanios = numBanios; 
    }

    public String getMaterialPredominante() { return materialPredominante; 
    }
    public void setMaterialPredominante(String materialPredominante) { 
        this.materialPredominante = materialPredominante; 
    }

    public String getServiciosBasicos() { return serviciosBasicos; }
    public void setServiciosBasicos(String serviciosBasicos) { this.serviciosBasicos = serviciosBasicos; 
    }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; 
    }

    public boolean isEstatus() { return estatus; }
    public void setEstatus(boolean estatus) { this.estatus = estatus; 
    }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; 
    }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; 
    }
    
    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getNombreTipoVivienda() {
        return nombreTipoVivienda;
    }

    public void setNombreTipoVivienda(String nombreTipoVivienda) {
        this.nombreTipoVivienda = nombreTipoVivienda;
    }
    
    public String getNombreVivienda() {
        return nombreVivienda;
    }

    public void setNombreVivienda(String nombreVivienda) {
        this.nombreVivienda = nombreVivienda;
    }
    
    @Override
    public String toString() {
        return idVivienda + " - " + calle + " #" + numExterior;
    }
}