package sistema;

import interfaz.*;

public class ImplementacionSistema implements Sistema  {


    @Override
    public Retorno inicializarSistema(int maxRegiones) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarDespliegue(String id, String codigo, String descripcion, boolean cuantizado, Modalidad modalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarDesplieguePorId(String id) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorIdAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorIdDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarDesplieguePorCodigo(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorCodigoAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarDesplieguesPorModalidad(Modalidad unaModalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarRegion(String codigo, String nombre, String pais, String zona) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEnlace(String codigoOrigen, String codigoDestino, int latencia, int costo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno redRegionesPorCantidadDeSaltos(String codigoOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno rutaMenorLatencia(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno rutaMenorCosto(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }
}
