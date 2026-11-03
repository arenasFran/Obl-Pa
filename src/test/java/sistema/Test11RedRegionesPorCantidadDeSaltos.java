package sistema;

import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RedRegionesPorCantidadDeSaltos {
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
        sistema.registrarEnlace("A", "B", 10, 10);
        sistema.registrarEnlace("A", "C", 10, 10);
        sistema.registrarEnlace("B", "D", 10, 10);
        sistema.registrarEnlace("D", "E", 10, 10);
    }

    @Test
    void cantidadCeroIncluyeSoloOrigen() {
        Retorno retorno = sistema.redRegionesPorCantidadDeSaltos("A", 0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("A;Región A;País;Zona A", retorno.getValorString());
    }

    @Test
    void resultadosOrdenadosYHastaLaCantidadDeSaltos() {
        assertEquals("A;Región A;País;Zona A|B;Región B;País;Zona B|C;Región C;País;Zona C",
                sistema.redRegionesPorCantidadDeSaltos("A", 1).getValorString());
        assertEquals("A;Región A;País;Zona A|B;Región B;País;Zona B|C;Región C;País;Zona C|D;Región D;País;Zona D",
                sistema.redRegionesPorCantidadDeSaltos("A", 2).getValorString());
        assertEquals("A;Región A;País;Zona A|B;Región B;País;Zona B|C;Región C;País;Zona C|D;Región D;País;Zona D|E;Región E;País;Zona E",
                sistema.redRegionesPorCantidadDeSaltos("A", 3).getValorString());
    }

    @Test
    void omiteRegionesDesconectadas() {
        assertEquals("F;Región F;País;Zona F",
                sistema.redRegionesPorCantidadDeSaltos("F", 4).getValorString());
    }

    @Test
    void erroresEnElOrdenEspecificado() {
        assertEquals(Retorno.Resultado.ERROR_1, sistema.redRegionesPorCantidadDeSaltos("", -1).getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.redRegionesPorCantidadDeSaltos(null, 0).getResultado());
        assertEquals(Retorno.Resultado.ERROR_3, sistema.redRegionesPorCantidadDeSaltos("X", 0).getResultado());
    }
}
