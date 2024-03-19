package sv.edu.udb.form;

import sv.edu.udb.beans.UsuarioBeans;
import sv.edu.udb.form.Login;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame {
    private JPanel pnlAdmin;
    private JButton btnSalir;
    private JButton btnDepart;
    private JButton btnUsuario;
    private JLabel lblUser;


    public AdminMenu(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlAdmin);
        this.setMinimumSize(new Dimension(550, 400));
        this.setLocationRelativeTo(getParent());


        btnDepart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblUser.main((String[]) null);
                dispose();
            }
        });

        btnUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblUser.main((String[]) null);
                dispose();
            }
        });

       /* btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.main((String[]) null);
                dispose();
            }
        });

        */

    }
    public static void main(String[] args) {
        JFrame frame = new AdminMenu("Menú Administrador");
        frame.setVisible(true);
    }
}


