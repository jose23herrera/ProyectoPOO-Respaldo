package sv.edu.udb.form;

import sv.edu.udb.beans.UsuarioBeans;
import sv.edu.udb.datos.UsuarioDatos;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField txtUser;
    private JPasswordField pwdContrasenia;
    private JButton btnIngresar;
    private JToolBar.Separator sep1;
    private JLabel lblLogo1;
    private JLabel lblTitulo1;
    private JLabel lblTitulo2;
    private JLabel lblLogo2;
    private JLabel lblLogo3;
    private JToolBar.Separator sep2;
    private JPanel pnlLogin;
    UsuarioDatos ud = new UsuarioDatos();

    public Login(){
        //Valores default al iniciar la ventana
        super("Ingreso");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlLogin);
        this.setSize(400,250);
        this.setLocationRelativeTo(getParent());
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                capturaDatos();
            }
        });
    }

    //Definicion de metodos
    private void capturaDatos(){
        UsuarioBeans ub;
        int opc;
        String user = txtUser.getText();
        String pass = new String(pwdContrasenia.getPassword());
        ub = ud.validarUsuario(user, pass);
        opc = ub.getNivelAcceso();

        /*if(ub.getNivelAcceso() == 1){
            JFrame ma = new AdminMenu(ub);
            ma.setVisible(true);
            this.dispose();
        } else if(ub.getNivelAcceso() > 1){
            JOptionPane.showMessageDialog(null, "No es administrador");
            txtUser.setText("");
            pwdContrasenia.setText("");
        }*/

        switch (opc){
            case 1:
                JFrame ma = new AdminMenu(user);
                ma.setVisible(true);
                this.dispose();
                break;
            case 2:
                JFrame jd = new JefeDesarrollo();
                jd.setVisible(true);
                this.dispose();
                break;
            case 3:
                JFrame em = new Empleado("Vista de empleado");
                em.setVisible(true);
                this.dispose();
                break;
        }//Fin switch
    }//Fin metodo capturaDatos()
}//Fin clase Login
