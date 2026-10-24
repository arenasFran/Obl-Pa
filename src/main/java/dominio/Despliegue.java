package dominio;

import interfaz.Modalidad;

public class Despliegue implements Comparable<Despliegue> {

    private String id;
    private String codigo;
    private String descripcion;
    private boolean cuantizado;
    private Modalidad modalidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isCuantizado() {
        return cuantizado;
    }

    public void setCuantizado(boolean cuantizado) {
        this.cuantizado = cuantizado;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }


    public Despliegue(String id, String codigo, String descripcion, boolean cuantizado, Modalidad modalidad) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cuantizado = cuantizado;
        this.modalidad = modalidad;
    }

    @Override
    public String toString() {
        return id + ";" + codigo + ";" + descripcion + ";" + cuantizado + ";" + modalidad.getTexto();
    }

    @Override
    public int compareTo(Despliegue otro) {
        return this.id.compareTo(otro.id);
    }
}
