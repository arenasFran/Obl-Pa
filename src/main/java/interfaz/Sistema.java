package interfaz;

/**
 * Provee una interfaz para interactuar con el sistema
 */
public interface Sistema {

    Retorno inicializarSistema(int maxRegiones);
    Retorno registrarDespliegue(String id, String codigo, String descripcion, boolean cuantizado, Modalidad modalidad);

    Retorno buscarDesplieguePorId(String id);
    Retorno listarDesplieguesPorIdAscendente();
    Retorno listarDesplieguesPorIdDescendente();

    Retorno buscarDesplieguePorCodigo(String codigo);
    Retorno listarDesplieguesPorCodigoAscendente();

    Retorno listarDesplieguesPorModalidad(Modalidad unaModalidad);

    Retorno registrarRegion(String codigo, String nombre, String pais, String zona);
    Retorno registrarEnlace(String codigoOrigen, String codigoDestino, int latencia, int costo);
    Retorno redRegionesPorCantidadDeSaltos(String codigoOrigen, int cantidad);

    Retorno rutaMenorLatencia(String codigoOrigen, String codigoDestino);
    Retorno rutaMenorCosto(String codigoOrigen, String codigoDestino);

}
