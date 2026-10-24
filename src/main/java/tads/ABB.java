package tads;

public class ABB <T extends Comparable<T>>{
    private Nodo<T> raiz;

    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato);
        }
        if (dato.compareTo(nodo.getDato()) < 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), dato));
        } else if (dato.compareTo(nodo.getDato()) > 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), dato));
        }
        return nodo;
    }
}
