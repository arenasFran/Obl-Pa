package tads;

public class ABB <T extends Comparable<T>>{
    private Nodo<T> raiz;
    private int cantRecorridos;

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

    public T buscar(T dato) {
        cantRecorridos = 0;
        Nodo<T> actual = raiz;
        while (actual != null) {
            cantRecorridos++;
            int cmp = dato.compareTo(actual.getDato());
            if (cmp == 0) {
                return actual.getDato();
            }
            if (cmp < 0) {
                actual = actual.getIzquierdo();
            } else {
                actual = actual.getDerecho();
            }
        }
        return null;
    }

    public int getCantRecorridos() {
        return cantRecorridos;
    }

    public String listarAscendente() {
        StringBuilder sb = new StringBuilder();
        listarAscendenteRec(raiz, sb);
        return sb.toString();
    }

    private void listarAscendenteRec(Nodo<T> nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        listarAscendenteRec(nodo.getIzquierdo(), sb);
        if (sb.length() > 0) {
            sb.append("|");
        }
        sb.append(nodo.getDato().toString());
        listarAscendenteRec(nodo.getDerecho(), sb);
    }
        public String listarDescendente() {
            StringBuilder sb = new StringBuilder();
             listarDescendenteRec(raiz, sb);
             return sb.toString();
        }

        private void listarDescendenteRec(Nodo<T> nodo, StringBuilder sb) {
            if (nodo == null) {
                return;
            }
            listarDescendenteRec(nodo.getDerecho(), sb);
            if (sb.length()> 0) {
                sb.append("|");
            }
            sb.append(nodo.getDato().toString());
            listarDescendenteRec(nodo.getIzquierdo(), sb);
        }
}

