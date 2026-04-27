package modelo;

import java.util.Date;

public class Habitante {
    private int idHabitante;
    private String nombreSexo;
    private String nombreVivienda;
    private String nombreEscolaridad;
    private String nombreOcupacion;
    private String nombre;
    private String paterno;
    private String materno;
    private int edad;
    private Date fechaNac;
    private String curp;
    private int idSexo;
    private int idVivienda;
    private int idEscolaridad;
    private int idOcupacion;
    private boolean estatus;
    private Date createdAt;
    private Date updatedAt;

    public Habitante() {}

    // Getters y Setters
    public int getIdHabitante() { 
        return idHabitante; 
    }
    
    public void setIdHabitante(int idHabitante) { 
        this.idHabitante = idHabitante; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getPaterno() { 
        return paterno; 
    }
    public void setPaterno(String paterno) { 
        this.paterno = paterno; 
    }

    public String getMaterno() { 
        return materno; 
    }
    public void setMaterno(String materno) { 
        this.materno = materno; 
    }

    public int getEdad() { 
        return edad; 
    }
    public void setEdad(int edad) { 
        this.edad = edad; 
    }

    public Date getFechaNac() { 
        return fechaNac; 
    }
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac; 
    }

    public String getCurp() { 
        return curp; 
    }
    public void setCurp(String curp) { 
        this.curp = curp; 
    }

    public int getIdSexo() { 
        return idSexo; 
    }
    public void setIdSexo(int idSexo) { 
        this.idSexo = idSexo; 
    }

    public int getIdVivienda() { 
        return idVivienda; 
    }
    public void setIdVivienda(int idVivienda) { 
        this.idVivienda = idVivienda; 
    }

    public int getIdEscolaridad() { 
        return idEscolaridad; 
    }
    public void setIdEscolaridad(int idEscolaridad) { 
        this.idEscolaridad = idEscolaridad; 
    }

    public int getIdOcupacion() { 
        return idOcupacion; 
    }
    public void setIdOcupacion(int idOcupacion) { 
        this.idOcupacion = idOcupacion; 
    }

    public boolean isEstatus() { 
        return estatus; 
    }
    public void setEstatus(boolean estatus) { 
        this.estatus = estatus; 
    }

    public Date getCreatedAt() { 
        return createdAt; 
    }
    public void setCreatedAt(Date createdAt) { 
        this.createdAt = createdAt; 
    }

    public Date getUpdatedAt() { 
        return updatedAt; 
    }
    public void setUpdatedAt(Date updatedAt) { 
        this.updatedAt = updatedAt; 
    }
    public String getNombreSexo() { return nombreSexo; }
    public void setNombreSexo(String nombreSexo) { this.nombreSexo = nombreSexo; }

    public String getNombreVivienda() { return nombreVivienda; }
    public void setNombreVivienda(String nombreVivienda) { this.nombreVivienda = nombreVivienda; }

    public String getNombreEscolaridad() { return nombreEscolaridad; }
    public void setNombreEscolaridad(String nombreEscolaridad) { this.nombreEscolaridad = nombreEscolaridad; }

    public String getNombreOcupacion() { return nombreOcupacion; }
    public void setNombreOcupacion(String nombreOcupacion) { this.nombreOcupacion = nombreOcupacion; }
}