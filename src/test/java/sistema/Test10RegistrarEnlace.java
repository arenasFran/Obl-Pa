package sistema;

import interfaz.Retorno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarEnlace {
    private ImplementacionSistema sistema;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(5);
        sistema.registrarRegion("us-east-1", "Virginia", "Estados Unidos", "a");
        sistema.registrarRegion("eu-west-1", "Irlanda", "Irlanda", "b");
    }

    @Test
    void registraEnlaceValido() {
        assertEquals(Retorno.Resultado.OK,
                sistema.registrarEnlace("us-east-1", "eu-west-1", 80, 12).getResultado());
    }

    @Test
    void validaStringsYRegionesEnOrden() {
        assertEquals(Retorno.Resultado.ERROR_1,
                sistema.registrarEnlace(null, "eu-west-1", 80, 12).getResultado());
        assertEquals(Retorno.Resultado.ERROR_2,
                sistema.registrarEnlace("no-existe", "destino-inexistente", 80, 12).getResultado());
        assertEquals(Retorno.Resultado.ERROR_3,
                sistema.registrarEnlace("us-east-1", "destino-inexistente", 80, 12).getResultado());
        assertEquals(Retorno.Resultado.ERROR_1,
                sistema.registrarEnlace("us-east-1", " ", 80, 12).getResultado());
    }

    @Test
    void validaLatenciaAntesQueCosto() {
        assertEquals(Retorno.Resultado.ERROR_4,
                sistema.registrarEnlace("us-east-1", "eu-west-1", 0, 0).getResultado());
        assertEquals(Retorno.Resultado.ERROR_5,
                sistema.registrarEnlace("us-east-1", "eu-west-1", 1, 0).getResultado());
    }

    @Test
    void enlaceDuplicadoEnSentidoInversoDaError6() {
        sistema.registrarEnlace("us-east-1", "eu-west-1", 80, 12);
        assertEquals(Retorno.Resultado.ERROR_6,
                sistema.registrarEnlace("eu-west-1", "us-east-1", 90, 15).getResultado());
    }
}
