package sv.edu.udb.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sv.edu.udb.beans.CasoBeans;
import sv.edu.udb.datos.CasoDatos;
import sv.edu.udb.datos.DepartamentoDatos;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Caso extends JFrame{

    private JLabel lblTituloCaso;
    private JLabel lblIDCaso;
    private JLabel lblIDDepartamento;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblFechaInicio;
    private JLabel lblFechaLimiteEntrega;
    private JLabel lblListaCasos;
    private JPanel pnlCasos;
    private JTable tblCasos;
    private JComboBox cboIDDepartamento;
    private JTextField txtFechaEntrega;
    private JTextField txtIDCaso;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    /*private JPanel pnlFechaInicio;
    private JPanel pnlFechaFin;
    private JPanel pnlFechaLimite;*/
    private JLabel lblInfoExtra;
    private JTextField txtInfoExtra;
    private JLabel lblEstado;
    private JComboBox cboEstado;
    private JLabel lblFechaFin;
    private JLabel lblCodigo;
    private JTextField txtCodigo;

    /*private JDateChooser dateChooser = new JDateChooser();
    private JDateChooser fechaFin = new JDateChooser();
    private JDateChooser fechaLimite = new JDateChooser();
    private Calendar cld = Calendar.getInstance();*/

    DefaultTableModel modelo = null;
    CasoBeans casoBeans = null;
    CasoBeans casoBeans2 = null;
    CasoDatos casoDatos = new CasoDatos();
    DepartamentoDatos departamentoDatos = new DepartamentoDatos();

    public Caso(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlCasos);
        this.setMinimumSize(new Dimension(1250, 720));
        this.setLocationRelativeTo(getParent());

        //Fecha
        /*dateChooser.setDateFormatString("yyyy-MM-dd");
        fechaFin.setDateFormatString("yyyy-MM-dd");
        fechaLimite.setDateFormatString("yyyy-MM-dd");
        pnlFechaInicio.add(dateChooser);
        pnlFechaFin.add(fechaFin);
        pnlFechaLimite.add(fechaLimite);*/

        modelo = casoDatos.selectCaso();
        tblCasos.setModel(modelo);
        cboIDDepartamento.setModel(departamentoDatos.selectDepartamentos());
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
        tblCasos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ObtenerFiladeTabla(e);
            }
        });

        txtFechaInicio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == KeyEvent.VK_SLASH) ||
                        (c == '-'))) {
                    JOptionPane.showMessageDialog(null, "Caracter Inválido");
                    e.consume();
                }
            }
        });
        txtFechaFin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == KeyEvent.VK_SLASH) ||
                        (c == '-'))) {
                    JOptionPane.showMessageDialog(null, "Caracter Inválido");
                    e.consume();
                }
            }
        });
        txtFechaEntrega.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == KeyEvent.VK_SLASH) ||
                        (c == '-'))) {
                    JOptionPane.showMessageDialog(null, "Caracter Inválido");
                    e.consume();
                }
            }
        });
    }



    public void CapturadeDatos() {
        int idCaso;
        int idDepartamento;
        String nombre;
        String descripcion;
        String fechaInicio;
        String fechaLimiteEntrega;
        String infoExtra;
        String estado;
        String fechaFin;
        String fechafn;
        modelo = casoDatos.selectCaso();
        tblCasos.setModel(modelo);

        if (txtIDCaso.getText().isEmpty()) {
            idCaso = 0;
        } else {
            idCaso = Integer.parseInt(txtIDCaso.getText());
        }
        idDepartamento = departamentoDatos.getIdDepartamento(cboIDDepartamento.getSelectedItem().toString());
        nombre = txtNombre.getText();
        descripcion = txtDescripcion.getText();
        infoExtra = txtInfoExtra.getText();
        estado = cboEstado.getSelectedItem().toString();
        //Fecha
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fechaInicio = sdf.format(dateChooser.getDate());
        fechafn = sdf.format(fechaFin.getDate());
        fechaLimiteEntrega = sdf.format(fechaLimite.getDate());*/

        fechaInicio = txtFechaInicio.getText();
        fechafn = txtFechaFin.getText();
        if(fechafn.equals("Vacío") || fechafn.isEmpty()) {
            fechaFin = null;
        }
        else {
            fechaFin = fechafn;
        }
        fechaLimiteEntrega = txtFechaEntrega.getText();

        casoBeans = new CasoBeans(idDepartamento, nombre, descripcion, fechaInicio, fechaFin, fechaLimiteEntrega, infoExtra, estado);
        casoBeans2 = new CasoBeans(idCaso, idDepartamento, nombre, descripcion, fechaInicio, fechaFin, fechaLimiteEntrega, infoExtra, estado);

        if (btnAgregar.getText().equals("Agregar")) {
            casoDatos.insert(casoBeans);
        } else {
            if (btnAgregar.getText().equals("Editar")) {
                casoDatos.update(casoBeans2);
            }
        }

        modelo = casoDatos.selectCaso();
        tblCasos.setModel(modelo);
    }

    public void LimpiarControles() {
        txtIDCaso.setText("");
        cboIDDepartamento.setSelectedIndex(0);
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtFechaInicio.setText("");
        txtFechaEntrega.setText("");
        txtFechaFin.setText("");
        btnAgregar.setText("Agregar");

        //Calendario
        /*dateChooser.setDate(cld.getTime());
        fechaFin.setDate(cld.getTime());
        fechaLimite.setDate(cld.getTime());*/
    }

    private void ObtenerFiladeTabla(MouseEvent e) {
        int fila = tblCasos.rowAtPoint(e.getPoint());
        int columna = tblCasos.columnAtPoint(e.getPoint());
        if((fila>-1) && (columna>-1)) {
            txtIDCaso.setText(modelo.getValueAt(fila,0).toString());
            txtCodigo.setText(modelo.getValueAt(fila, 1).toString());
            cboIDDepartamento.setSelectedItem(modelo.getValueAt(fila,2).toString());
            txtNombre.setText(modelo.getValueAt(fila,3).toString());
            txtDescripcion.setText(modelo.getValueAt(fila,4).toString());
            txtFechaInicio.setText(modelo.getValueAt(fila,5).toString());

            /*try {
                String fecha = modelo.getValueAt(fila, 4).toString();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                dateChooser.setDate(date);
                String fechaF = modelo.getValueAt(fila, 5).toString();
                Date dateF = new SimpleDateFormat("yyyy-MM-dd").parse(fechaF);
                fechaFin.setDate(dateF);
                String FechaL = modelo.getValueAt(fila, 6).toString();
                Date dateL = new SimpleDateFormat("yyyy-MM-dd").parse(FechaL);
                fechaLimite.setDate(dateL);
            }catch (Exception ex){
                System.out.println(ex);
            }*/

            txtFechaFin.setText(modelo.getValueAt(fila,6).toString());
            txtFechaEntrega.setText(modelo.getValueAt(fila,7).toString());
            txtInfoExtra.setText(modelo.getValueAt(fila, 8).toString());
            cboEstado.setSelectedItem(modelo.getValueAt(fila, 9).toString());
            btnAgregar.setText("Editar");
        }
    }

    public void EliminarDatos() {
        casoDatos.delete(Integer.parseInt(txtIDCaso.getText()));
        LimpiarControles();
        modelo = casoDatos.selectCaso();
        tblCasos.setModel(modelo);
    }

    public static void main(String[] args) {
        JFrame frame = new Caso("Control de Casos");
        frame.setVisible(true);
    }
}
