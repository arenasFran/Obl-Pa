package sistema;

// Estudiantes: Francisco Arenas (332800) y Pedro Wattimo (338849)

import dominio.Despliegue;
import dominio.DesplieguePorCodigo;
import dominio.Enlace;
import dominio.Region;
import interfaz.*;
import tads.ABB;
import java.util.regex.Pattern;

public class ImplementacionSistema implements Sistema  {

    private static final Pattern PATRON_CODIGO = Pattern.compile("^[A-Z]{3}-[0-9]{3}-[A-Za-z0-9]{4}$");

    private ABB<Despliegue> desplieguesPorId;
    private ABB<DesplieguePorCodigo> desplieguesPorCodigo;
    private ABB<DesplieguePorCodigo>[] desplieguesPorModalidad;
    private int maxRegiones;
    private Region[] regiones;
    private int cantidadRegiones;
    private Enlace[][] enlaces;


    @Override
    public Retorno inicializarSistema(int maxRegiones) {
        if (maxRegiones <= 3) {
            return Retorno.error1("La cantidad máxima de regiones debe ser mayor a 3");
        }
        this.maxRegiones = maxRegiones;
        this.desplieguesPorId = new ABB<>();
        this.desplieguesPorCodigo = new ABB<>();
        this.desplieguesPorModalidad = crearArbolesPorModalidad();
        this.regiones = new Region[maxRegiones];
        this.cantidadRegiones = 0;
        this.enlaces = new Enlace[maxRegiones][maxRegiones];
        return Retorno.ok();
    }

    @Override
    public Retorno registrarDespliegue(String id, String codigo, String descripcion, boolean cuantizado, Modalidad modalidad) {
        if (esVacio(id) || esVacio(codigo) || esVacio(descripcion) || modalidad == null) {
            return Retorno.error1("Id, código, descripción y modalidad son obligatorios");
        }
        if (!PATRON_CODIGO.matcher(codigo).matches()) {
            return Retorno.error2("El código no tiene el formato válido");
        }
        if (desplieguesPorId.buscar(new Despliegue(id, "", "", false, Modalidad.OTROS)) != null) {
            return Retorno.error3("Ya existe un despliegue con ese id");
        }
        DesplieguePorCodigo claveCodigo = new DesplieguePorCodigo(
                new Despliegue("", codigo, "", false, Modalidad.OTROS));
        if (desplieguesPorCodigo.buscar(claveCodigo) != null) {
            return Retorno.error4("Ya existe un despliegue con ese código");
        }
        Despliegue despliegue = new Despliegue(id, codigo, descripcion, cuantizado, modalidad);
        desplieguesPorId.insertar(despliegue);
        DesplieguePorCodigo desplieguePorCodigo = new DesplieguePorCodigo(despliegue);
        desplieguesPorCodigo.insertar(desplieguePorCodigo);
        desplieguesPorModalidad[modalidad.getIndice()].insertar(desplieguePorCodigo);
        return Retorno.ok();
    }

    @Override
    public Retorno buscarDesplieguePorId(String id) {
        if (esVacio(id)) {
            return Retorno.error1("El id es obligatorio");
        }
        Despliegue despliegue = desplieguesPorId.buscar(new Despliegue(id, "", "", false, Modalidad.OTROS));
        if (despliegue == null) {
            return Retorno.error2("No existe un despliegue con ese id");
        }
        return Retorno.ok(desplieguesPorId.getCantRecorridos(), despliegue.toString());
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    @SuppressWarnings("unchecked")
    private ABB<DesplieguePorCodigo>[] crearArbolesPorModalidad() {
        ABB<DesplieguePorCodigo>[] arboles = (ABB<DesplieguePorCodigo>[]) new ABB<?>[Modalidad.values().length];
        for (int i = 0; i < arboles.length; i++) {
            arboles[i] = new ABB<>();
        }
        return arboles;
    }

    @Override
    public Retorno listarDesplieguesPorIdAscendente() {
        return Retorno.ok(desplieguesPorId.listarAscendente());
    }

    @Override
    public Retorno listarDesplieguesPorIdDescendente() {
        return Retorno.ok(desplieguesPorId.listarDescendente());
    }

    @Override
    public Retorno buscarDesplieguePorCodigo(String codigo) {
        if (esVacio(codigo)) {
            return Retorno.error1("El código es obligatorio");
        }
        DesplieguePorCodigo desplieguePorCodigo = desplieguesPorCodigo.buscar(
                new DesplieguePorCodigo(new Despliegue("", codigo, "", false, Modalidad.OTROS)));
        if (desplieguePorCodigo == null) {
            return Retorno.error2("No existe un despliegue con ese código");
        }
        return Retorno.ok(desplieguesPorCodigo.getCantRecorridos(), desplieguePorCodigo.toString());
    }

    @Override
    public Retorno listarDesplieguesPorCodigoAscendente() {
        return Retorno.ok(desplieguesPorCodigo.listarAscendente());
    }

    @Override
    public Retorno listarDesplieguesPorModalidad(Modalidad unaModalidad) {
        if (unaModalidad == null) {
            return Retorno.ok("");
        }
        return Retorno.ok(desplieguesPorModalidad[unaModalidad.getIndice()].listarAscendente());
    }

    @Override
    public Retorno registrarRegion(String codigo, String nombre, String pais, String zona) {
        if (cantidadRegiones >= maxRegiones) {
            return Retorno.error1("Se alcanzó el máximo de regiones");
        }
        if (esVacio(codigo) || esVacio(nombre) || esVacio(pais) || esVacio(zona)) {
            return Retorno.error2("Código, nombre, país y zona son obligatorios");
        }
        for (int i = 0; i < cantidadRegiones; i++) {
            if (regiones[i].getCodigo().equals(codigo)) {
                return Retorno.error3("Ya existe una región con ese código");
            }
        }
        regiones[cantidadRegiones] = new Region(codigo, nombre, pais, zona);
        cantidadRegiones++;
        return Retorno.ok();
    }

    @Override
    public Retorno registrarEnlace(String codigoOrigen, String codigoDestino, int latencia, int costo) {
        if (esVacio(codigoOrigen) || esVacio(codigoDestino)) {
            return Retorno.error1("Los códigos de origen y destino son obligatorios");
        }
        int indiceOrigen = buscarIndiceRegion(codigoOrigen);
        if (indiceOrigen < 0) {
            return Retorno.error2("No existe la región de origen");
        }
        int indiceDestino = buscarIndiceRegion(codigoDestino);
        if (indiceDestino < 0) {
            return Retorno.error3("No existe la región de destino");
        }
        if (latencia <= 0) {
            return Retorno.error4("La latencia debe ser mayor a cero");
        }
        if (costo <= 0) {
            return Retorno.error5("El costo debe ser mayor a cero");
        }
        if (enlaces[indiceOrigen][indiceDestino] != null) {
            return Retorno.error6("Ya existe un enlace entre las regiones");
        }
        Enlace enlace = new Enlace(latencia, costo);
        enlaces[indiceOrigen][indiceDestino] = enlace;
        enlaces[indiceDestino][indiceOrigen] = enlace;
        return Retorno.ok();
    }

    private int buscarIndiceRegion(String codigo) {
        for (int i = 0; i < cantidadRegiones; i++) {
            if (regiones[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Retorno redRegionesPorCantidadDeSaltos(String codigoOrigen, int cantidad) {
        if (cantidad < 0) {
            return Retorno.error1("La cantidad de saltos no puede ser negativa");
        }
        if (esVacio(codigoOrigen)) {
            return Retorno.error2("El código de origen es obligatorio");
        }
        int indiceOrigen = buscarIndiceRegion(codigoOrigen);
        if (indiceOrigen < 0) {
            return Retorno.error3("No existe la región de origen");
        }

        int[] distancias = new int[cantidadRegiones];
        int[] cola = new int[cantidadRegiones];
        for (int i = 0; i < cantidadRegiones; i++) {
            distancias[i] = -1;
        }
        int inicioCola = 0;
        int finCola = 0;
        cola[finCola++] = indiceOrigen;
        distancias[indiceOrigen] = 0;

        while (inicioCola < finCola) {
            int actual = cola[inicioCola++];
            if (distancias[actual] == cantidad) {
                continue;
            }
            for (int vecino = 0; vecino < cantidadRegiones; vecino++) {
                if (enlaces[actual][vecino] != null && distancias[vecino] == -1) {
                    distancias[vecino] = distancias[actual] + 1;
                    cola[finCola++] = vecino;
                }
            }
        }

        boolean[] agregada = new boolean[cantidadRegiones];
        StringBuilder resultado = new StringBuilder();
        int incluidas = 0;
        for (int i = 0; i < cantidadRegiones; i++) {
            if (distancias[i] >= 0 && distancias[i] <= cantidad) {
                incluidas++;
            }
        }
        for (int posicion = 0; posicion < incluidas; posicion++) {
            int menorIndice = -1;
            for (int i = 0; i < cantidadRegiones; i++) {
                if (distancias[i] >= 0 && distancias[i] <= cantidad && !agregada[i]
                        && (menorIndice == -1 || regiones[i].getCodigo()
                        .compareTo(regiones[menorIndice].getCodigo()) < 0)) {
                    menorIndice = i;
                }
            }
            if (posicion > 0) {
                resultado.append("|");
            }
            Region region = regiones[menorIndice];
            resultado.append(region.getCodigo()).append(";")
                    .append(region.getNombre()).append(";")
                    .append(region.getPais()).append(";")
                    .append(region.getZona());
            agregada[menorIndice] = true;
        }
        return Retorno.ok(resultado.toString());
    }

    @Override
    public Retorno rutaMenorLatencia(String codigoOrigen, String codigoDestino) {
        if (esVacio(codigoOrigen) || esVacio(codigoDestino)) {
            return Retorno.error1("Los códigos de origen y destino son obligatorios");
        }
        int indiceOrigen = buscarIndiceRegion(codigoOrigen);
        if (indiceOrigen < 0) {
            return Retorno.error2("No existe la región de origen");
        }
        int indiceDestino = buscarIndiceRegion(codigoDestino);
        if (indiceDestino < 0) {
            return Retorno.error3("No existe la región de destino");
        }
        return calcularRuta(indiceOrigen, indiceDestino, true);
    }

    private Retorno calcularRuta(int indiceOrigen, int indiceDestino, boolean optimizarLatencia) {
        long[] distancias = new long[cantidadRegiones];
        int[] predecesores = new int[cantidadRegiones];
        boolean[] visitadas = new boolean[cantidadRegiones];
        for (int i = 0; i < cantidadRegiones; i++) {
            distancias[i] = Long.MAX_VALUE;
            predecesores[i] = -1;
        }
        distancias[indiceOrigen] = 0;

        for (int paso = 0; paso < cantidadRegiones; paso++) {
            int actual = -1;
            for (int i = 0; i < cantidadRegiones; i++) {
                if (!visitadas[i] && distancias[i] != Long.MAX_VALUE
                        && (actual == -1 || distancias[i] < distancias[actual])) {
                    actual = i;
                }
            }
            if (actual == -1 || actual == indiceDestino) {
                break;
            }
            visitadas[actual] = true;
            for (int vecino = 0; vecino < cantidadRegiones; vecino++) {
                Enlace enlace = enlaces[actual][vecino];
                if (enlace != null && !visitadas[vecino]) {
                    int peso = optimizarLatencia ? enlace.getLatencia() : enlace.getCosto();
                    long nuevaDistancia = distancias[actual] + peso;
                    if (nuevaDistancia < distancias[vecino]) {
                        distancias[vecino] = nuevaDistancia;
                        predecesores[vecino] = actual;
                    }
                }
            }
        }

        if (distancias[indiceDestino] == Long.MAX_VALUE) {
            return Retorno.error4("No existe una ruta entre las regiones");
        }
        int[] ruta = new int[cantidadRegiones];
        int largoRuta = 0;
        for (int actual = indiceDestino; actual != -1; actual = predecesores[actual]) {
            ruta[largoRuta++] = actual;
        }
        StringBuilder valorString = new StringBuilder();
        for (int i = largoRuta - 1; i >= 0; i--) {
            if (valorString.length() > 0) {
                valorString.append("|");
            }
            Region region = regiones[ruta[i]];
            valorString.append(region.getCodigo()).append(";")
                    .append(region.getNombre()).append(";")
                    .append(region.getPais()).append(";")
                    .append(region.getZona());
        }
        return Retorno.ok((int) distancias[indiceDestino], valorString.toString());
    }

    @Override
    public Retorno rutaMenorCosto(String codigoOrigen, String codigoDestino) {
        if (esVacio(codigoOrigen) || esVacio(codigoDestino)) {
            return Retorno.error1("Los códigos de origen y destino son obligatorios");
        }
        int indiceOrigen = buscarIndiceRegion(codigoOrigen);
        if (indiceOrigen < 0) {
            return Retorno.error2("No existe la región de origen");
        }
        int indiceDestino = buscarIndiceRegion(codigoDestino);
        if (indiceDestino < 0) {
            return Retorno.error3("No existe la región de destino");
        }
        return calcularRuta(indiceOrigen, indiceDestino, false);
    }
}
