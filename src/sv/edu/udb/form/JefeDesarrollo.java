package sv.edu.udb.form;

import sv.edu.udb.datos.CasoDatos;
import sv.edu.udb.form.Asignacion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JefeDesarrollo extends JFrame{

    private JRadioButton rbtAceptarCaso;
    private JRadioButton rbtRechazarCaso;
    private JTextField txtMotivo;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPanel pnlJefeDev;
    private JLabel lblTitulo;
    private JLabel lblDescCaso;
    private JLabel lblDescAcciones;
    private JLabel lblDescMotivo;
    private JTable tblCasos;
    private JTextField txtIDCaso;
    private JLabel lblCasosEspera;
    private JButton btnAsignar;

    CasoDatos casoDatos = new CasoDatos();
    DefaultTableModel modelo = null;

    public JefeDesarrollo(){
        this.setTitle("Vista Jefe Desarrollo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlJefeDev);
        this.setSize(1200,750);
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
        modelo = casoDatos.selectCasosEnEspera();
        tblCasos.setModel(modelo);
        rbtRechazarCaso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbtRechazarCaso.isSelected()){
                    txtMotivo.setEnabled(true);
                    lblDescMotivo.setEnabled(true);
                }
            }
        });
        rbtAceptarCaso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rbtAceptarCaso.isSelected()){
                    txtMotivo.setEnabled(false);
                    lblDescMotivo.setEnabled(false);
                }
            }
        });

        tblCasos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ObtenerFiladeTabla(e);
            }
        });
        btnAceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int idCaso = Integer.parseInt(txtIDCaso.getText().toString());
                String motivo = txtMotivo.getText();
                if(rbtAceptarCaso.isSelected()) {
                    String estado = rbtAceptarCaso.getText();
                    casoDatos.actualizarEstadoCaso(idCaso, estado, motivo);
                    modelo = casoDatos.selectCasosEnEspera();
                    tblCasos.setModel(modelo);
                }
                if(rbtRechazarCaso.isSelected()) {
                    String estado = rbtRechazarCaso.getText();
                    casoDatos.actualizarEstadoCaso(idCaso, estado, motivo);
                    modelo = casoDatos.selectCasosEnEspera();
                    tblCasos.setModel(modelo);
                }
            }
        });
        btnAsignar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame asignacion = new Asignacion();
                asignacion.setVisible(true);
            }
        });
    }

    private void ObtenerFiladeTabla(MouseEvent e) {
        int fila = tblCasos.rowAtPoint(e.getPoint());
        int columna = tblCasos.columnAtPoint(e.getPoint());
        if ((fila > -1) && (columna > -1)) {
            txtIDCaso.setText(modelo.getValueAt(fila, 0).toString());
        }
    }
}
