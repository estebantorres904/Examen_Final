package app;

import controlador.ControladorPrincipal;
import javax.swing.SwingUtilities;
import vista.FrmPrincipal;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> iniciarAplicacion());
    }

    private static void iniciarAplicacion() {
        FrmPrincipal vista = new FrmPrincipal();
        ControladorPrincipal controlador = new ControladorPrincipal(vista);

        controlador.cargarArchivo("src/datos/predios.csv");
        vista.setVisible(true);
    }

    private static void seleccionarArchivoCsv(ControladorPrincipal controlador, FrmPrincipal vista) {

        }
    }

