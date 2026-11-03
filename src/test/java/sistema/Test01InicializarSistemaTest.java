package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01InicializarSistemaTest {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();


    @Test
    void inicializarSistemaOk() {
        retorno = s.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.inicializarSistema(50);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void inicializarSistemaError1() {
        retorno = s.inicializarSistema(3);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.inicializarSistema(2);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.inicializarSistema(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.inicializarSistema(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }




}
