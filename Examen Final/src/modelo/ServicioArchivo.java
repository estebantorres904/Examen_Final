package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ServicioArchivo {

    public ArrayList<Predio> leerCsv(String rutaArchivo) {
        ArrayList<Predio> predios = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = lector.readLine()) != null) {
                if (primeraLinea && esEncabezado(linea)) {
                    primeraLinea = false;
                    continue;
                }

                primeraLinea = false;
                Predio predio = convertirLineaAPredio(linea);

                if (predio != null) {
                    predios.add(predio);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return predios;
    }

    private Predio convertirLineaAPredio(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            return null;
        }

        ArrayList<String> datos = separarLineaCsv(linea);

        if (datos.size() < 4) {
            return null;
        }

        String npn = limpiarDato(datos.get(0));
        String municipio = limpiarDato(datos.get(1));
        String direccion = obtenerDireccion(datos);
        String ficha = limpiarDato(datos.get(datos.size() - 1));

        if (npn.isEmpty() || municipio.isEmpty() || ficha.isEmpty()) {
            return null;
        }

        return new Predio(npn, municipio, direccion, ficha);
    }

    private String obtenerDireccion(ArrayList<String> datos) {
        StringBuilder direccion = new StringBuilder();

        for (int i = 2; i < datos.size() - 1; i++) {
            if (i > 2) {
                direccion.append(",");
            }

            direccion.append(datos.get(i));
        }

        return limpiarDato(direccion.toString());
    }

    private ArrayList<String> separarLineaCsv(String linea) {
        ArrayList<String> datos = new ArrayList<>();
        StringBuilder datoActual = new StringBuilder();
        boolean dentroDeComillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char caracter = linea.charAt(i);

            if (caracter == '"') {
                dentroDeComillas = !dentroDeComillas;
            } else if (caracter == ',' && !dentroDeComillas) {
                datos.add(datoActual.toString());
                datoActual.setLength(0);
            } else {
                datoActual.append(caracter);
            }
        }

        datos.add(datoActual.toString());
        return datos;
    }

    private String limpiarDato(String dato) {
        return dato.trim();
    }

    private boolean esEncabezado(String linea) {
        String texto = linea.toLowerCase();

        return texto.contains("npn")
                && texto.contains("municipio")
                && texto.contains("direccion")
                && texto.contains("ficha");
    }
}
