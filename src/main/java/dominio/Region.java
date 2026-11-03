package dominio;

public class Region {
    private final String codigo;
    private final String nombre;
    private final String pais;
    private final String zona;

    public Region(String codigo, String nombre, String pais, String zona) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.zona = zona;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getZona() {
        return zona;
    }
}
