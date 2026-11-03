package sistema;

import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09RegistrarRegion {
    private ImplementacionSistema sistema;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
    }

    @Test
    void registraRegionValida() {
        assertEquals(Retorno.Resultado.OK,
                sistema.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "us-east-1a").getResultado());
    }

    @Test
    void camposNullOVaciosDanError2() {
        assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarRegion(null, "Nombre", "País", "Zona").getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarRegion("codigo", " ", "País", "Zona").getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarRegion("codigo", "Nombre", null, "Zona").getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarRegion("codigo", "Nombre", "País", "").getResultado());
    }

    @Test
    void codigoDuplicadoDaError3() {
        sistema.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "us-east-1a");
        assertEquals(Retorno.Resultado.ERROR_3,
                sistema.registrarRegion("us-east-1", "Otra", "Otro país", "otra-zona").getResultado());
    }

    @Test
    void capacidadLlenaTienePrioridad() {
        for (int i = 0; i < 4; i++) {
            sistema.registrarRegion("region-" + i, "Nombre", "País", "Zona");
        }
        assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarRegion(null, "", "", "").getResultado());
    }

    @Test
    void inicializarSistemaVaciaLasRegiones() {
        sistema.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "us-east-1a");
        sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK,
                sistema.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "us-east-1a").getResultado());
    }
}
