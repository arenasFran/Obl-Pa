package sistema;

import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test13RutaMenorCosto {
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
        sistema.registrarEnlace("A", "C", 1, 1);
        sistema.registrarEnlace("C", "D", 8, 1);
        sistema.registrarEnlace("D", "E", 3, 4);
    }

    @Test
    void eligeLaRutaConMenorCostoTotal() {
        Retorno retorno = sistema.rutaMenorCosto("A", "D");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("A;Región A;País;Zona A|C;Región C;País;Zona C|D;Región D;País;Zona D",
                retorno.getValorString());
    }

    @Test
    void puedeElegirRutaDistintaALaDeMenorLatencia() {
        assertEquals("A;Región A;País;Zona A|B;Región B;País;Zona B|D;Región D;País;Zona D",
                sistema.rutaMenorLatencia("A", "D").getValorString());
        assertEquals("A;Región A;País;Zona A|C;Región C;País;Zona C|D;Región D;País;Zona D",
                sistema.rutaMenorCosto("A", "D").getValorString());
    }

    @Test
    void origenIgualADestinoYDestinoDesconectado() {
        Retorno mismaRegion = sistema.rutaMenorCosto("A", "A");
        assertEquals(Retorno.Resultado.OK, mismaRegion.getResultado());
        assertEquals(0, mismaRegion.getValorInteger());
        assertEquals("A;Región A;País;Zona A", mismaRegion.getValorString());
        assertEquals(Retorno.Resultado.ERROR_4, sistema.rutaMenorCosto("A", "F").getResultado());
    }

    @Test
    void erroresEnElOrdenEspecificado() {
        assertEquals(Retorno.Resultado.ERROR_1, sistema.rutaMenorCosto(" ", "A").getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.rutaMenorCosto("X", "A").getResultado());
        assertEquals(Retorno.Resultado.ERROR_3, sistema.rutaMenorCosto("A", "X").getResultado());
    }
}
