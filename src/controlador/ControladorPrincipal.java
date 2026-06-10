package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Predio;
import modelo.ServicioArchivo;
import modelo.ServicioBusqueda;
import modelo.ServicioOrdenamiento;
import vista.FrmPrincipal;

public class ControladorPrincipal {

    private FrmPrincipal vista;
    private ServicioArchivo servicioArchivo;
    private ServicioOrdenamiento servicioOrdenamiento;
    private ServicioBusqueda servicioBusqueda;
    private ArrayList<Predio> predios;
    private String columnaOrdenada;

    public ControladorPrincipal(FrmPrincipal vista) {
        this.vista = vista;
        this.servicioArchivo = new ServicioArchivo();
        this.servicioOrdenamiento = new ServicioOrdenamiento();
        this.servicioBusqueda = new ServicioBusqueda();
        this.predios = new ArrayList<>();
        this.columnaOrdenada = "";

        agregarEventos();
    }

    private void agregarEventos() {
        vista.getBtnBuscar().addActionListener(e -> buscarPredios());
    }

    public void cargarArchivo(String rutaArchivo) {
        predios = servicioArchivo.leerCsv(rutaArchivo);
        columnaOrdenada = "";
        limpiarTabla();
        vista.getLblTiempo().setText("Tiempo: 0 ms");

        JOptionPane.showMessageDialog(vista, "Registros cargados: " + predios.size());
    }

    private void buscarPredios() {
        if (predios == null || predios.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Primero debe cargar un archivo CSV.");
            return;
        }

        String columna = vista.getCboColumna().getSelectedItem().toString();
        String criterio = vista.getTxtBusqueda().getText().trim();

        if (criterio.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese un criterio de busqueda.");
            return;
        }

        long inicio = System.nanoTime();

        if (!columna.equals(columnaOrdenada)) {
            servicioOrdenamiento.ordenar(predios, columna);
            columnaOrdenada = columna;
        }

        ArrayList<Predio> resultados = servicioBusqueda.buscar(predios, columna, criterio);

        long fin = System.nanoTime();
        double tiempoMilisegundos = (fin - inicio) / 1_000_000.0;

        mostrarResultados(resultados);
        vista.getLblTiempo().setText(String.format("Tiempo: %.3f ms", tiempoMilisegundos));

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se encontraron resultados.");
        }
    }

    private void mostrarResultados(ArrayList<Predio> resultados) {
        DefaultTableModel modeloTabla = vista.getModeloTabla();
        modeloTabla.setRowCount(0);

        for (Predio predio : resultados) {
            Object[] fila = {
                    predio.getNpn(),
                    predio.getMunicipio(),
                    predio.getDireccion(),
                    predio.getFicha()
            };

            modeloTabla.addRow(fila);
        }
    }

    private void limpiarTabla() {
        vista.getModeloTabla().setRowCount(0);
    }
}
