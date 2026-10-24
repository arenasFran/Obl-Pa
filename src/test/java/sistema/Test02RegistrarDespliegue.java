package sistema;

import interfaz.Modalidad;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02RegistrarDespliegue {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarDespliegueOk() {
        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarDespliegueError1() {
        retorno = s.registrarDespliegue("", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());


        retorno = s.registrarDespliegue(null, "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", null, "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", null, true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());


        retorno = s.registrarDespliegue("  ", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "   ", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "   ", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarDespliegueError2() {
        retorno = s.registrarDespliegue("DEP-0001", "1LM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "L2M-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LL4-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "324-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLMM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());


        retorno = s.registrarDespliegue("DEP-0001", "LLM-A70-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-0B0-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-07C-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-0709-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-ABC-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());


        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A11B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A12", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A!B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarDespliegueError3() {
        s.registrarDespliegue("DEP-0001", "LLM-128-B8C3", "Modelo de texto 128B en producción", true, Modalidad.TEXTO);
        retorno = s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void registrarDespliegueError4() {
        s.registrarDespliegue("DEP-0001", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        retorno = s.registrarDespliegue("DEP-0002", "LLM-070-A1B2", "Modelo de texto 70B en producción", true, Modalidad.TEXTO);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

}
