package sv.edu.udb.form;

import sv.edu.udb.datos.AsignacionDatos;
import sv.edu.udb.datos.EmpleadoDatos;
import sv.edu.udb.datos.CasoDatos;
import sv.edu.udb.beans.AsignacionBeans;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Asignacion extends JFrame {
    private JLabel lblTituloAsignacion;
    private JLabel lblIDAsginacio;
    private JLabel lblEmpleado;
    private JLabel lblCaso;
    private JLabel lblFechaLimite;
    private JLabel lblDescripcion;
    private JTextField txtIDAsignacion;
    private JComboBox cboEmpleado;
    private JComboBox cboCaso;
    private JTextField txtFecha;
    private JTextField txtDescripcion;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JLabel lblListaAsignaciones;
    private JTable tblAsignaciones;
    private JPanel pnlAsignaciones;

    DefaultTableModel modelo = null;
    AsignacionBeans asignacionBeans = null;
    AsignacionDatos asignacionDatos = new AsignacionDatos();
    EmpleadoDatos empleadoDatos = new EmpleadoDatos();
    CasoDatos casoDatos = new CasoDatos();

    public Asignacion() {
        this.setTitle("Control de Asignaciones");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlAsignaciones);
        this.setMinimumSize(new Dimension(1250, 720));
        this.setLocationRelativeTo(getParent());

        modelo = asignacionDatos.selectAsignacion();
        tblAsignaciones.setModel(modelo);
        cboEmpleado.setModel(empleadoDatos.selectProgramadores());
        cboCaso.setModel(casoDatos.selectCasosAceptados());

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

        tblAsignaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ObtenerFiladeTabla(e);
            }
        });
    }

    public void CapturadeDatos() {
        int idAsignacion;
        int idEmpleado;
        int idCaso;
        String fechaLimite;
        String descripcion;

        modelo = asignacionDatos.selectAsignacion();
        tblAsignaciones.setModel(modelo);

        if (txtIDAsignacion.getText().isEmpty()) idAsignacion = 0;
        else idAsignacion = Integer.parseInt(txtIDAsignacion.getText());

        idEmpleado = empleadoDatos.getIdEmpleado(cboEmpleado.getSelectedItem().toString());
        idCaso = casoDatos.getIdCaso(cboCaso.getSelectedItem().toString());
        fechaLimite = txtFecha.getText();
        descripcion = txtDescripcion.getText();

        asignacionBeans = new AsignacionBeans(idAsignacion, idEmpleado, idCaso, fechaLimite, descripcion);

        if (btnAgregar.getText().equals("Agregar")) {
            asignacionDatos.insert(asignacionBeans);
        } else {
            if (btnAgregar.getText().equals("Editar")) {
                asignacionDatos.update(asignacionBeans);
            }
        }

        modelo = asignacionDatos.selectAsignacion();
        tblAsignaciones.setModel(modelo);
    }

    public void LimpiarControles() {
        txtIDAsignacion.setText("");
        cboEmpleado.setSelectedIndex(0);
        cboCaso.setSelectedIndex(0);
        txtFecha.setText("");
        txtDescripcion.setText("");
        btnAgregar.setText("Agregar");
    }

    private void ObtenerFiladeTabla(MouseEvent e) {
        int fila = tblAsignaciones.rowAtPoint(e.getPoint());
        int columna = tblAsignaciones.columnAtPoint(e.getPoint());

        if ((fila > -1) && (columna > -1)) {
            txtIDAsignacion.setText(modelo.getValueAt(fila, 0).toString());
            cboEmpleado.setSelectedItem(modelo.getValueAt(fila, 1).toString());
            cboCaso.setSelectedItem(modelo.getValueAt(fila, 2).toString());
            txtFecha.setText(modelo.getValueAt(fila, 3).toString());
            txtDescripcion.setText(modelo.getValueAt(fila, 4).toString());
            btnAgregar.setText("Editar");
        }
    }

    public void EliminarDatos() {
        asignacionDatos.delete(Integer.parseInt(txtIDAsignacion.getText()));
        LimpiarControles();
        modelo = asignacionDatos.selectAsignacion();
        tblAsignaciones.setModel(modelo);
    }

    /*public static void main(String[] args) {
        JFrame frame = new Asignacion();
        frame.setVisible(true);
    }*/
}
