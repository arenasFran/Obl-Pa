package sistema;

import interfaz.Modalidad;
import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04a08ListarDespliegues {
    private ImplementacionSistema sistema;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);
        sistema.registrarDespliegue("DEP-0002", "VIS-004-C3D4", "Vision", false, Modalidad.VISION);
        sistema.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "Texto B", true, Modalidad.TEXTO);
        sistema.registrarDespliegue("DEP-0003", "LLM-030-A1B3", "Texto A", false, Modalidad.TEXTO);
    }

    @Test
    void listarPorIdAscendente() {
        Retorno retorno = sistema.listarDesplieguesPorIdAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("DEP-0001;LLM-070-A1B2;Texto B;true;Texto|"
                + "DEP-0002;VIS-004-C3D4;Vision;false;Visión|"
                + "DEP-0003;LLM-030-A1B3;Texto A;false;Texto", retorno.getValorString());
    }

    @Test
    void listarPorIdDescendente() {
        Retorno retorno = sistema.listarDesplieguesPorIdDescendente();
        assertEquals("DEP-0003;LLM-030-A1B3;Texto A;false;Texto|"
                + "DEP-0002;VIS-004-C3D4;Vision;false;Visión|"
                + "DEP-0001;LLM-070-A1B2;Texto B;true;Texto", retorno.getValorString());
    }

    @Test
    void buscarPorCodigo() {
        Retorno retorno = sistema.buscarDesplieguePorCodigo("VIS-004-C3D4");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals("DEP-0002;VIS-004-C3D4;Vision;false;Visión", retorno.getValorString());
    }

    @Test
    void buscarPorCodigoValidaErrores() {
        assertEquals(Retorno.Resultado.ERROR_1, sistema.buscarDesplieguePorCodigo(null).getResultado());
        assertEquals(Retorno.Resultado.ERROR_1, sistema.buscarDesplieguePorCodigo("  ").getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.buscarDesplieguePorCodigo("AUD-002-C3D4").getResultado());
    }

    @Test
    void listarPorCodigoAscendente() {
        Retorno retorno = sistema.listarDesplieguesPorCodigoAscendente();
        assertEquals("DEP-0003;LLM-030-A1B3;Texto A;false;Texto|"
                + "DEP-0001;LLM-070-A1B2;Texto B;true;Texto|"
                + "DEP-0002;VIS-004-C3D4;Vision;false;Visión", retorno.getValorString());
    }

    @Test
    void listarPorModalidadEnOrdenDeCodigo() {
        Retorno retorno = sistema.listarDesplieguesPorModalidad(Modalidad.TEXTO);
        assertEquals("DEP-0003;LLM-030-A1B3;Texto A;false;Texto|"
                + "DEP-0001;LLM-070-A1B2;Texto B;true;Texto", retorno.getValorString());
        assertEquals("", sistema.listarDesplieguesPorModalidad(Modalidad.AUDIO).getValorString());
        assertEquals("", sistema.listarDesplieguesPorModalidad(null).getValorString());
    }
}
