package sv.edu.udb.datos;

import sv.edu.udb.beans.PruebasBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PruebasDatos {
    private final String SQL_INSERT = "INSERT INTO pruebas (ID_Prueba, ID_Empleado, ID_Caso, Fecha, Observaciones) VALUES (?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE pruebas SET ID_Empleado = ?, ID_Caso = ?, Fecha = ?, Observaciones = ? WHERE ID_Prueba = ?";
    private final String SQL_DELETE = "DELETE FROM pruebas WHERE ID_Prueba = ?";
    private final String SQL_SELECT = "SELECT p.ID_Prueba, e.Nombre, c.Nombre, p.Fecha, p.Observaciones FROM pruebas p INNER JOIN empleado e ON e.ID_Empleado = p.ID_Empleado INNER JOIN caso c ON c.ID_Caso = p.ID_Caso";

    public int insert(PruebasBeans pruebaBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, pruebaBeans.getIdPrueba());
            stmt.setInt(index++, pruebaBeans.getIdEmpleado());
            stmt.setInt(index++, pruebaBeans.getIdCaso());
            stmt.setString(index++, pruebaBeans.getFecha());
            stmt.setString(index, pruebaBeans.getObservaciones());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int update(PruebasBeans pruebaBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, pruebaBeans.getIdEmpleado());
            stmt.setInt(index++, pruebaBeans.getIdCaso());
            stmt.setString(index++, pruebaBeans.getFecha());
            stmt.setString(index++, pruebaBeans.getObservaciones());
            stmt.setInt(index, pruebaBeans.getIdPrueba());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public int delete(int idPrueba) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPrueba);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public DefaultTableModel selectPrueba() {
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
