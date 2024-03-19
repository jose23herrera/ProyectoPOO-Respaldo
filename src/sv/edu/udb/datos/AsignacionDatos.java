package sv.edu.udb.datos;

import sv.edu.udb.beans.AsignacionBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AsignacionDatos {
    private final String SQL_INSERT = "INSERT INTO asignacion (ID_Asignacion, ID_Empleado, ID_Caso, FechaLimite, Descripcion) VALUES (?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE asignacion SET ID_Empleado = ?, ID_Caso = ?, FechaLimite = ?, Descripcion = ? WHERE ID_Asignacion = ?";
    private final String SQL_DELETE = "DELETE FROM asignacion WHERE ID_Asignacion = ?";
    private final String SQL_SELECT = "SELECT a.ID_Asignacion, e.Nombre, c.Nombre, a.FechaLimite, a.Descripcion FROM asignacion a INNER JOIN empleado e ON e.ID_Empleado = a.ID_Empleado INNER JOIN caso c ON c.ID_Caso = a.ID_Caso";

    public int insert(AsignacionBeans asignacionBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, asignacionBeans.getIdAsignacion());
            stmt.setInt(index++, asignacionBeans.getIdEmpleado());
            stmt.setInt(index++, asignacionBeans.getIdCaso());
            stmt.setString(index++, asignacionBeans.getFechaLimite());
            stmt.setString(index, asignacionBeans.getDescripcionAsignacion());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int update(AsignacionBeans asignacionBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, asignacionBeans.getIdEmpleado());
            stmt.setInt(index++, asignacionBeans.getIdCaso());
            stmt.setString(index++, asignacionBeans.getFechaLimite());
            stmt.setString(index++, asignacionBeans.getDescripcionAsignacion());
            stmt.setInt(index, asignacionBeans.getIdAsignacion());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int delete(int idAsignacion) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idAsignacion);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public DefaultTableModel selectAsignacion() {
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
}