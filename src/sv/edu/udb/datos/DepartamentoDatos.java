package sv.edu.udb.datos;

import sv.edu.udb.beans.DepartamentoBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDatos {
    private final String SQL_INSERT = "INSERT INTO departamento (ID_Departamento, NombreDepartamento, Prefijo) VALUES (?,?,?)";
    private final String SQL_UPDATE = "UPDATE departamento SET NombreDepartamento = ?, Prefijo = ? WHERE ID_Departamento = ?";
    private final String SQL_DELETE = "DELETE FROM departamento WHERE ID_Departamento = ?";
    private final String SQL_SELECT = "SELECT d.ID_Departamento, d.NombreDepartamento, d.Prefijo FROM departamento d";
    private final String SQL_SELECT_DEPARTAMENTOS = "SELECT ID_Departamento, NombreDepartamento FROM departamento ORDER BY ID_Departamento";
    private final String SQL_SELECT_IDDEPARTAMENTO = "SELECT ID_Departamento FROM departamento WHERE NombreDepartamento = ?";

    public int insert(DepartamentoBeans departamentoBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, departamentoBeans.getIdDepartamento());
            stmt.setString(index++, departamentoBeans.getNombreDepartamento());
            stmt.setString(index, departamentoBeans.getPrefijo());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int update(DepartamentoBeans departamentoBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, departamentoBeans.getNombreDepartamento());
            stmt.setString(index++, departamentoBeans.getPrefijo());
            stmt.setInt(index, departamentoBeans.getIdDepartamento());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int delete(int idDepartamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idDepartamento);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public DefaultTableModel selectDepartamento() {
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
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for (int i = 0; i < numberOfColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return dtm;
    }

    public DefaultComboBoxModel selectDepartamentos() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_DEPARTAMENTOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                dtm.addElement(rs.getObject(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return dtm;
    }

    public int getIdDepartamento(String departamento) {
        int idDepartamento = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_IDDEPARTAMENTO);
            stmt.setString(1, departamento);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idDepartamento = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return idDepartamento;
    }
}
