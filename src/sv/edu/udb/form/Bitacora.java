package sv.edu.udb.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import sv.edu.udb.beans.BitacoraBeans;
import sv.edu.udb.datos.BitacoraDatos;
import sv.edu.udb.datos.CasoDatos;
import sv.edu.udb.datos.EmpleadoDatos;
import sv.edu.udb.datos.DepartamentoDatos;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bitacora extends JFrame {
    private JLabel lblTituloBitacoras;
    private JLabel lblIDBitacora;
    private JLabel lblIDEmpleado;
    private JLabel lblIDCaso;
    private JLabel lblDescripcion;
    private JLabel lblFecha;
    private JLabel lblPorcentajaProgreso;
    private JLabel lblListaBitacoras;
    private JTable tblBitacoras;
    private JPanel pnlBitacoras;
    private JTextField txtIDBitacora;
    private JComboBox cboEmpleado;
    private JComboBox cboCaso;
    private JTextField txtDescripcion;
    private JTextField txtPorcentaje;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JPanel pnlContainer;
    private JDateChooser  dateChooser = new JDateChooser();
    private Calendar cld = Calendar.getInstance();

    DefaultTableModel modelo = null;
    BitacoraBeans bitacoraBeans = null;
    BitacoraDatos bitacoraDatos = new BitacoraDatos();
    EmpleadoDatos empleadoDatos = new EmpleadoDatos();
    CasoDatos casoDatos = new CasoDatos();

    public Bitacora(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlBitacoras);
        this.setMinimumSize(new Dimension(1250, 720));
        this.setLocationRelativeTo(getParent());

        //Calendario
        dateChooser.setDateFormatString("yyyy-MM-dd");
        pnlContainer.add(dateChooser);


        modelo = bitacoraDatos.selectBitacora();
        tblBitacoras.setModel(modelo);
        cboEmpleado.setModel(empleadoDatos.selectEmpleados());
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
        tblBitacoras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);


                try {
                    ObtenerFiladeTabla(e);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        txtPorcentaje.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE)
                        )) {
                    JOptionPane.showMessageDialog(null, "Caracter Inválido");
                    e.consume();
                }
            }
        });
    }

    public void CapturadeDatos() {
        int idBitacora;
        int idEmpleado;
        int idCaso;
        String descripcion;
        String fecha;
        int porcentaje;

        //Fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        modelo = bitacoraDatos.selectBitacora();
        tblBitacoras.setModel(modelo);
        if(txtIDBitacora.getText().isEmpty()) idBitacora = 0;
        else idBitacora = Integer.parseInt(txtIDBitacora.getText());
        idEmpleado = empleadoDatos.getIdEmpleado(cboEmpleado.getSelectedItem().toString());
        idCaso = casoDatos.getIdCaso(cboCaso.getSelectedItem().toString());
        descripcion = txtDescripcion.getText();
        //Espacio de fecha
        fecha = sdf.format(dateChooser.getDate());
        porcentaje = Integer.parseInt(txtPorcentaje.getText());

        bitacoraBeans = new BitacoraBeans(idBitacora, idEmpleado, idCaso, descripcion, fecha, porcentaje);

        if(btnAgregar.getText().equals("Agregar")) {
            bitacoraDatos.insert(bitacoraBeans);
        }
        else {
            if (btnAgregar.getText().equals("Editar")) {
                bitacoraDatos.update(bitacoraBeans);
            }
        }
        modelo = bitacoraDatos.selectBitacora();
        tblBitacoras.setModel(modelo);
    }

    public void LimpiarControles() {
        txtIDBitacora.setText("");
        cboEmpleado.setSelectedIndex(0);
        cboCaso.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtPorcentaje.setText("");
        btnAgregar.setText("Agregar");
        //Calendario
        dateChooser.setDate(cld.getTime());
    }

    private void ObtenerFiladeTabla(MouseEvent e) throws ParseException {
        int fila = tblBitacoras.rowAtPoint(e.getPoint());
        int columna = tblBitacoras.columnAtPoint(e.getPoint());
        if((fila>-1) && (columna>-1)) {
            txtIDBitacora.setText(modelo.getValueAt(fila,0).toString());
            cboEmpleado.setSelectedItem(modelo.getValueAt(fila,1).toString());
            cboCaso.setSelectedItem(modelo.getValueAt(fila,2).toString());
            txtDescripcion.setText(modelo.getValueAt(fila,3).toString());

            //Espacio de fecha
            try {
                String fecha = modelo.getValueAt(fila, 4).toString();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                dateChooser.setDate(date);
            }catch(Exception ex){
                System.out.println(ex);
            }


            txtPorcentaje.setText(modelo.getValueAt(fila,5).toString());
            btnAgregar.setText("Editar");
        }
    }

    public void EliminarDatos() {
        bitacoraDatos.delete(Integer.parseInt(txtIDBitacora.getText()));
        LimpiarControles();
        modelo = bitacoraDatos.selectBitacora();
        tblBitacoras.setModel(modelo);
    }

    public static void main(String[] args) {
        JFrame frame = new Bitacora("Control de Bitacoras");
        frame.setVisible(true);
    }
}
