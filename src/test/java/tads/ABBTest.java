package tads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ABBTest {
    private ABB<Integer> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ABB<>();
        arbol.insertar(5);
        arbol.insertar(3);
        arbol.insertar(8);
        arbol.insertar(1);
    }

    @Test
    void listarAscendenteOk() {
        assertEquals("1|3|5|8", arbol.listarAscendente());
    }

    @Test
    void listarDescendenteOk() {
        assertEquals("8|5|3|1", arbol.listarDescendente());
    }

    @Test
    void buscarEncontrado() {
        assertEquals(8, arbol.buscar(8));
        assertEquals(2, arbol.getCantRecorridos());
    }

    @Test
    void buscarEncontradoMasProfundo() {
        assertEquals(1, arbol.buscar(1));
        assertEquals(3, arbol.getCantRecorridos());
    }

    @Test
    void buscarNoExiste() {
        assertNull(arbol.buscar(99));
        assertEquals(2, arbol.getCantRecorridos());
    }

    @Test
    void insertarDuplicadoNoCambiaNada() {
        arbol.insertar(5);
        assertEquals("1|3|5|8", arbol.listarAscendente());
    }

    @Test
    void arbolVacio() {
        ABB<Integer> vacio = new ABB<>();
        assertEquals("", vacio.listarAscendente());
        assertEquals("", vacio.listarDescendente());
        assertNull(vacio.buscar(1));
        assertEquals(0, vacio.getCantRecorridos());
    }
}