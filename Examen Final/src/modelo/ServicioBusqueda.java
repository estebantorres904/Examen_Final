package modelo;

import java.util.ArrayList;

public class ServicioBusqueda {

    public ArrayList<Predio> buscar(ArrayList<Predio> predios, String columna, String criterio) {
        ArrayList<Predio> resultados = new ArrayList<>();

        if (predios == null || predios.isEmpty() || criterio == null) {
            return resultados;
        }

        String criterioLimpio = criterio.trim();
        int primeraPosicion = buscarPrimeraPosicion(predios, columna, criterioLimpio);

        if (primeraPosicion == -1) {
            return resultados;
        }

        int ultimaPosicion = buscarUltimaPosicion(predios, columna, criterioLimpio);

        for (int i = primeraPosicion; i <= ultimaPosicion; i++) {
            resultados.add(predios.get(i));
        }

        return resultados;
    }

    private int buscarPrimeraPosicion(ArrayList<Predio> predios, String columna, String criterio) {
        int inicio = 0;
        int fin = predios.size() - 1;
        int posicion = -1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int comparacion = comparar(obtenerValor(predios.get(medio), columna), criterio);

            if (comparacion == 0) {
                posicion = medio;
                fin = medio - 1;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return posicion;
    }

    private int buscarUltimaPosicion(ArrayList<Predio> predios, String columna, String criterio) {
        int inicio = 0;
        int fin = predios.size() - 1;
        int posicion = -1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int comparacion = comparar(obtenerValor(predios.get(medio), columna), criterio);

            if (comparacion == 0) {
                posicion = medio;
                inicio = medio + 1;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return posicion;
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
