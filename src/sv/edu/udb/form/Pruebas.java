package sv.edu.udb.form;

import sv.edu.udb.datos.PruebasDatos;
import sv.edu.udb.datos.EmpleadoDatos;
import sv.edu.udb.datos.CasoDatos;
import sv.edu.udb.beans.PruebasBeans;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pruebas extends JFrame {
    private JLabel lblTituloPruebas;
    private JLabel lblIDPrueba;
    private JLabel lblEmpleado;
    private JLabel lblCaso;
    private JLabel lblFecha;
    private JLabel lblObservaciones;
    private JTextField txtIDPrueba;
    private JComboBox cboEmpleado;
    private JComboBox cboCaso;
    private JTextField txtFecha;
    private JTextField txtObservaciones;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JLabel lblListaPruebas;
    private JTable tblPruebas;
    private JPanel pnlPruebas;

    DefaultTableModel modelo = null;
    PruebasBeans pruebaBeans = null;
    PruebasDatos pruebaDatos = new PruebasDatos();
    EmpleadoDatos empleadoDatos = new EmpleadoDatos();
    CasoDatos casoDatos = new CasoDatos();

    public Pruebas(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlPruebas);
        this.setMinimumSize(new Dimension(1250, 720));
        this.setLocationRelativeTo(getParent());

        modelo = pruebaDatos.selectPrueba();
        tblPruebas.setModel(modelo);
        cboEmpleado.setModel(empleadoDatos.selectProbadores());
        cboCaso.setModel(casoDatos.selectCasos());

        btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CapturadeDatos();
            }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EliminarDatos();
            }
        });

        btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LimpiarControles();
            }
        });

        tblPruebas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ObtenerFiladeTabla(e);
            }
        });
    }

    public void CapturadeDatos() {
        int idPrueba;
        int idEmpleado;
        int idCaso;
        String fecha;
        String observaciones;

        modelo = pruebaDatos.selectPrueba();
        tblPruebas.setModel(modelo);

        if (txtIDPrueba.getText().isEmpty()) idPrueba = 0;
        else idPrueba = Integer.parseInt(txtIDPrueba.getText());

        idEmpleado = empleadoDatos.getIdEmpleado(cboEmpleado.getSelectedItem().toString());
        idCaso = casoDatos.getIdCaso(cboCaso.getSelectedItem().toString());
        fecha = txtFecha.getText();
        observaciones = txtObservaciones.getText();

        pruebaBeans = new PruebasBeans(idPrueba, idEmpleado, idCaso, fecha, observaciones);

        if (btnAgregar.getText().equals("Agregar")) {
            pruebaDatos.insert(pruebaBeans);
        } else {
            if (btnAgregar.getText().equals("Editar")) {
                pruebaDatos.update(pruebaBeans);
            }
        }

        modelo = pruebaDatos.selectPrueba();
        tblPruebas.setModel(modelo);
    }

    public void LimpiarControles() {
        txtIDPrueba.setText("");
        cboEmpleado.setSelectedIndex(0);
        cboCaso.setSelectedIndex(0);
        txtFecha.setText("");
        txtObservaciones.setText("");
        btnAgregar.setText("Agregar");
    }

    private void ObtenerFiladeTabla(MouseEvent e) {
        int fila = tblPruebas.rowAtPoint(e.getPoint());
        int columna = tblPruebas.columnAtPoint(e.getPoint());

        if ((fila > -1) && (columna > -1)) {
            txtIDPrueba.setText(modelo.getValueAt(fila, 0).toString());
            cboEmpleado.setSelectedItem(modelo.getValueAt(fila, 1).toString());
            cboCaso.setSelectedItem(modelo.getValueAt(fila, 2).toString());
            txtFecha.setText(modelo.getValueAt(fila, 3).toString());
            txtObservaciones.setText(modelo.getValueAt(fila, 4).toString());
            btnAgregar.setText("Editar");
        }
    }

    public void EliminarDatos() {
        pruebaDatos.delete(Integer.parseInt(txtIDPrueba.getText()));
        LimpiarControles();
        modelo = pruebaDatos.selectPrueba();
        tblPruebas.setModel(modelo);
    }

    public static void main(String[] args) {
        JFrame frame = new Pruebas("Gestión de Pruebas");
        frame.setVisible(true);
    }
}
