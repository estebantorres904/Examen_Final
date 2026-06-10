package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmPrincipal extends JFrame {

    private JComboBox<String> cboColumna;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JLabel lblTiempo;
    private JTable tblResultados;
    private DefaultTableModel modeloTabla;

    public FrmPrincipal() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Busqueda de Predios");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        cboColumna = new JComboBox<>();
        cboColumna.addItem("NPN");
        cboColumna.addItem("Municipio");
        cboColumna.addItem("Direccion");
        cboColumna.addItem("Ficha");

        txtBusqueda = new JTextField(25);
        btnBuscar = new JButton("Buscar");
        lblTiempo = new JLabel("Tiempo: 0 ms");

        panelSuperior.add(new JLabel("Columna:"));
        panelSuperior.add(cboColumna);
        panelSuperior.add(new JLabel("Busqueda:"));
        panelSuperior.add(txtBusqueda);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(lblTiempo);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("NPN");
        modeloTabla.addColumn("Municipio");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Ficha");

        tblResultados = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tblResultados);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }

    public JComboBox<String> getCboColumna() {
        return cboColumna;
    }

    public JTextField getTxtBusqueda() {
        return txtBusqueda;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JLabel getLblTiempo() {
        return lblTiempo;
    }

    public JTable getTblResultados() {
        return tblResultados;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}
