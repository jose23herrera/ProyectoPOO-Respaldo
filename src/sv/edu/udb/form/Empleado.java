package sv.edu.udb.form;

import sv.edu.udb.beans.EmpleadoBeans;
import sv.edu.udb.datos.DepartamentoDatos;
import sv.edu.udb.datos.EmpleadoDatos;
import sv.edu.udb.datos.RolDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Empleado extends JFrame {
    private JLabel lblTituloEmpleado;
    private JLabel lblDepartamento;
    private JLabel lblRol;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblTelefono;
    private JComboBox cboDepartamento;
    private JComboBox cboRol;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JLabel lblListaEmpleados;
    private JTable tblEmpleados;
    private JPanel pnlEmpleados;
    private JTextField txtEdad;
    private JLabel lblIDEmpleado;
    private JTextField txtIDEmpleado;


    DefaultTableModel modelo = null;
    EmpleadoBeans empleadoBeans = null;
    EmpleadoDatos empleadoDatos = new EmpleadoDatos();
    DepartamentoDatos departamentoDatos = new DepartamentoDatos();
    RolDatos rolDatos = new RolDatos();

    public Empleado(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlEmpleados);
        this.setMinimumSize(new Dimension(1250, 720));
        this.setLocationRelativeTo(getParent());
        modelo = empleadoDatos.selectEmpleado();
        tblEmpleados.setModel(modelo);
        cboDepartamento.setModel(departamentoDatos.selectDepartamentos());
        cboRol.setModel(rolDatos.selectRoles());
        btnAgregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CapturadeDatos();
            }
        });

        btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LimpiarControles();
            }
        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EliminarDatos();
            }
        });
        tblEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ObtenerFiladeTabla(e);
            }
        });
    }

    private void CapturadeDatos() {
        int idEmpleado;
        int idDepartamento;
        int idRol;
        String nombre;
        int edad;
        String telefono;

        if(txtIDEmpleado.getText().isEmpty()) idEmpleado = 0;
        else idEmpleado = Integer.parseInt(txtIDEmpleado.getText());
        idDepartamento = departamentoDatos.getIdDepartamento(cboDepartamento.getSelectedItem().toString());
        idRol = rolDatos.getIdRol(cboRol.getSelectedItem().toString());
        nombre = txtNombre.getText();
        edad = Integer.parseInt(txtEdad.getText());
        telefono = txtTelefono.getText();

        empleadoBeans = new EmpleadoBeans(idEmpleado,idDepartamento, idRol, nombre, edad, telefono);

        if(btnAgregar.getText().equals("Agregar")) {
            empleadoDatos.insert(empleadoBeans);
        }
        else {
            if (btnAgregar.getText().equals("Editar")) {
                empleadoDatos.update(empleadoBeans);
            }
        }

        modelo = empleadoDatos.selectEmpleado();
        tblEmpleados.setModel(modelo);
    }

    public void LimpiarControles() {
        cboDepartamento.setSelectedIndex(0);
        cboRol.setSelectedIndex(0);
        txtNombre.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        btnAgregar.setText("Agregar");
    }

    private void ObtenerFiladeTabla(MouseEvent e) {
        int fila = tblEmpleados.rowAtPoint(e.getPoint());
        int columna = tblEmpleados.columnAtPoint(e.getPoint());
        if((fila>-1) && (columna>-1)) {
            txtIDEmpleado.setText(modelo.getValueAt(fila,0).toString());
            cboDepartamento.setSelectedItem(modelo.getValueAt(fila,1).toString());
            cboRol.setSelectedItem(modelo.getValueAt(fila,2).toString());
            txtNombre.setText(modelo.getValueAt(fila,3).toString());
            txtEdad.setText(modelo.getValueAt(fila,4).toString());
            txtTelefono.setText(modelo.getValueAt(fila,5).toString());
            btnAgregar.setText("Editar");
        }
    }

    public void EliminarDatos() {
        empleadoDatos.delete(Integer.parseInt(txtIDEmpleado.getText()));
        LimpiarControles();
        modelo = empleadoDatos.selectEmpleado();
        tblEmpleados.setModel(modelo);
    }

    public static void main(String[] args) {
        JFrame frame = new Empleado("Control de Empleados");
        frame.setVisible(true);
    }
}
