package sv.edu.udb.form;
import sv.edu.udb.beans.DepartamentoBeans;
import sv.edu.udb.datos.DepartamentoDatos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Departamento extends JFrame {
    private JLabel lblTituloDepartamento;
    private JLabel lblIDDepartamento;
    private JLabel lblNombreDepartamento;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tblDepartamentos;
    private JPanel pnlDepartamentos;
    private JTextField txtIDDepartamento;
    private JTextField txtNombreDepartamento;
    private JLabel lblPrefijo;
    private JTextField txtPrefijo;
    DefaultTableModel modelo = null;
    DepartamentoBeans departamentoBeans = null;
    DepartamentoDatos departamentoDatos = new DepartamentoDatos();

    public Departamento(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlDepartamentos);
        this.setMinimumSize(new Dimension(1250, 720));
        this.setLocationRelativeTo(getParent());
        modelo = departamentoDatos.selectDepartamento();
        tblDepartamentos.setModel(modelo);
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
        tblDepartamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ObtenerFiladeTabla(e);
            }
        });
    }

    public void CapturadeDatos() {
        int idDepartamento;
        String nombreDepartamento;
        String prefijo;
        if(txtIDDepartamento.getText().isEmpty()) idDepartamento = 0;
        else idDepartamento = Integer.parseInt(txtIDDepartamento.getText());
        nombreDepartamento = txtNombreDepartamento.getText();
        prefijo = txtPrefijo.getText();

        departamentoBeans = new DepartamentoBeans(idDepartamento, nombreDepartamento, prefijo);

        if(btnAgregar.getText().equals("Agregar")) {
            departamentoDatos.insert(departamentoBeans);
        }
        else {
            if (btnAgregar.getText().equals("Editar")) {
                departamentoDatos.update(departamentoBeans);
            }
        }

        modelo = departamentoDatos.selectDepartamento();
        tblDepartamentos.setModel(modelo);
    }

    public void LimpiarControles() {
        txtIDDepartamento.setText("");
        txtNombreDepartamento.setText("");
        txtPrefijo.setText("");
        btnAgregar.setText("Agregar");
    }

    private void ObtenerFiladeTabla(MouseEvent e) {
        int fila = tblDepartamentos.rowAtPoint(e.getPoint());
        int columna = tblDepartamentos.columnAtPoint(e.getPoint());
        if((fila>-1) && (columna>-1)) {
            txtIDDepartamento.setText(modelo.getValueAt(fila,0).toString());
            txtNombreDepartamento.setText(modelo.getValueAt(fila,1).toString());
            txtPrefijo.setText(modelo.getValueAt(fila,2).toString());
            btnAgregar.setText("Editar");
        }
    }

    public void EliminarDatos() {
        departamentoDatos.delete(Integer.parseInt(txtIDDepartamento.getText()));
        LimpiarControles();
        modelo = departamentoDatos.selectDepartamento();
        tblDepartamentos.setModel(modelo);
    }

    public static void main(String[] args) {
        JFrame frame = new Departamento("Control de Departamentos");
        frame.setVisible(true);
    }
}
