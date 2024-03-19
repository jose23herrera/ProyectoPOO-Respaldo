package sv.edu.udb.datos;

import sv.edu.udb.beans.RolBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDatos {
    private final String SQL_INSERT = "INSERT INTO rol (ID_Rol, NombreRol) VALUES(?,?)";
    private final String SQL_UPDATE = "UPDATE rol SET NombreRol = ? WHERE ID_Rol = ?";
    private final String SQL_DELETE = "DELETE FROM rol WHERE ID_Rol = ?";
    private final String SQL_SELECT = "SELECT r.ID_Rol, r.NombreRol FROM rol r";
    private final String SQL_SELECT_ROLES = "SELECT ID_Rol, NombreRol FROM rol ORDER BY ID_Rol";
    private final String SQL_SELECT_IDROL = "SELECT ID_Rol FROM rol WHERE NombreRol = ?";

    public int insert(RolBeans rolBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, rolBeans.getIdRol());
            stmt.setString(index, rolBeans.getNombreRol());
            rows = stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int update(RolBeans rolBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, rolBeans.getNombreRol());
            stmt.setInt(index, rolBeans.getIdRol());
            rows = stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int delete(int idRol) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idRol);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public DefaultTableModel selectRol() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            for(int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for(int i = 1; i < numberOfColumns; i++) {
                    fila[i] = rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return dtm;
    }

    public DefaultComboBoxModel selectRoles() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_ROLES);
            rs = stmt.executeQuery();
            while (rs.next()){
                dtm.addElement(rs.getObject(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return dtm;
    }

    public int getIdRol(String rol) {
        int idRol = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_IDROL);
            stmt.setString(1, rol);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idRol = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return idRol;
    }
}
