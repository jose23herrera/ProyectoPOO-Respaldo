package sv.edu.udb.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sv.edu.udb.beans.UsuarioBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.JOptionPane;

public class UsuarioDatos {
    private final String SQL_Select = "select r.NombreRol, e.ID_Rol, e.Nombre, u.Usuario, u.Contraseña from empleado as e inner join usuarios as u on e.ID_Empleado = u.ID_Empleado\n" +
            "inner join rol as r on r.ID_Rol = e.ID_Rol\n" +
            "where Usuario = ? and Contraseña = ?;";

    public UsuarioBeans validarUsuario(String usuario, String contraseña){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioBeans ub = new UsuarioBeans("",0,"","", "");

        //Inicio validacion de datos
        if(usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un usuario");
        }else if(contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña");
        }else{
            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_Select);
                stmt.setString(1, usuario);
                stmt.setString(2, contraseña);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ub.setNombreRol(rs.getString(1));
                    ub.setNivelAcceso(rs.getInt(2));
                    ub.setNombre(rs.getString(3));
                    ub.setUsuario(rs.getString(4));
                    ub.setPassword(rs.getString(5));
                    JOptionPane.showMessageDialog(null, "Bienvenido " +ub.getNombre());
                } else{
                    JOptionPane.showMessageDialog(null, "Creadenciales inválidas");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            }
        }
        return ub;
    }
}
