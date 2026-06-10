package modelo;

import java.util.ArrayList;

public class ServicioOrdenamiento {

    public void ordenar(ArrayList<Predio> predios, String columna) {
        if (predios == null || predios.size() <= 1) {
            return;
        }

        quickSort(predios, 0, predios.size() - 1, columna);
    }

    private void quickSort(ArrayList<Predio> predios, int inicio, int fin, String columna) {
        if (inicio < fin) {
            int posicionParticion = particionHoare(predios, inicio, fin, columna);

            quickSort(predios, inicio, posicionParticion, columna);
            quickSort(predios, posicionParticion + 1, fin, columna);
        }
    }

    private int particionHoare(ArrayList<Predio> predios, int inicio, int fin, String columna) {
        int posicionMedia = inicio + (fin - inicio) / 2;
        String pivote = obtenerValor(predios.get(posicionMedia), columna);
        int izquierda = inicio - 1;
        int derecha = fin + 1;

        while (true) {
            do {
                izquierda++;
            } while (comparar(obtenerValor(predios.get(izquierda), columna), pivote) < 0);

            do {
                derecha--;
            } while (comparar(obtenerValor(predios.get(derecha), columna), pivote) > 0);

            if (izquierda >= derecha) {
                return derecha;
            }

            intercambiar(predios, izquierda, derecha);
        }
    }

    private void intercambiar(ArrayList<Predio> predios, int posicionA, int posicionB) {
        Predio temporal = predios.get(posicionA);
        predios.set(posicionA, predios.get(posicionB));
        predios.set(posicionB, temporal);
    }

    private int comparar(String valorA, String valorB) {
        return valorA.compareToIgnoreCase(valorB);
    }

    private String obtenerValor(Predio predio, String columna) {
        if (columna == null) {
            return "";
        }

        String columnaNormalizada = columna.trim().toLowerCase();

        if (columnaNormalizada.equals("npn")) {
            return predio.getNpn();
        }

        if (columnaNormalizada.equals("municipio")) {
            return predio.getMunicipio();
        }

        if (columnaNormalizada.equals("direccion")) {
            return predio.getDireccion();
        }

        if (columnaNormalizada.equals("ficha")) {
            return predio.getFicha();
        }

        return "";
    }
}
