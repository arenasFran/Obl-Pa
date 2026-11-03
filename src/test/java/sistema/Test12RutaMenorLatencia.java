package sistema;

import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12RutaMenorLatencia {
    private ImplementacionSistema sistema;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6);
        sistema.registrarRegion("A", "Región A", "País", "Zona A");
        sistema.registrarRegion("B", "Región B", "País", "Zona B");
        sistema.registrarRegion("C", "Región C", "País", "Zona C");
        sistema.registrarRegion("D", "Región D", "País", "Zona D");
        sistema.registrarRegion("E", "Región E", "País", "Zona E");
        sistema.registrarRegion("F", "Región F", "País", "Zona F");
        sistema.registrarEnlace("A", "B", 2, 20);
        sistema.registrarEnlace("B", "D", 2, 20);
        sistema.registrarEnlace("A", "C", 1, 20);
        sistema.registrarEnlace("C", "D", 8, 20);
        sistema.registrarEnlace("D", "E", 3, 20);
    }

    @Test
    void eligeLaRutaConMenorLatenciaTotal() {
        Retorno retorno = sistema.rutaMenorLatencia("A", "D");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(4, retorno.getValorInteger());
        assertEquals("A;Región A;País;Zona A|B;Región B;País;Zona B|D;Región D;País;Zona D",
                retorno.getValorString());
    }

    @Test
    void origenIgualADestinoDevuelveCeroYUnaRegion() {
        Retorno retorno = sistema.rutaMenorLatencia("A", "A");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("A;Región A;País;Zona A", retorno.getValorString());
    }

    @Test
    void rutaIndirectaValidaYRegionDesconectadaNoTieneRuta() {
        assertEquals(7, sistema.rutaMenorLatencia("A", "E").getValorInteger());
        assertEquals(Retorno.Resultado.ERROR_4, sistema.rutaMenorLatencia("A", "F").getResultado());
    }

    @Test
    void erroresEnElOrdenEspecificado() {
        assertEquals(Retorno.Resultado.ERROR_1, sistema.rutaMenorLatencia(null, "A").getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.rutaMenorLatencia("X", "A").getResultado());
        assertEquals(Retorno.Resultado.ERROR_3, sistema.rutaMenorLatencia("A", "X").getResultado());
    }
}
