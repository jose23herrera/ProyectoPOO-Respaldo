package sv.edu.udb.datos;

import sv.edu.udb.beans.BitacoraBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BitacoraDatos {
    private final String SQL_INSERT = "INSERT INTO bitacora (ID_Bitacora, ID_Empleado, ID_Caso, Descripcion, Fecha, PorcentajeProgreso) VALUES (?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE bitacora SET ID_Empleado = ?, ID_Caso = ?, Descripcion = ?, Fecha  = ?, PorcentajeProgreso  = ? WHERE ID_Bitacora = ?";
    private final String SQL_DELETE = "DELETE FROM bitacora WHERE ID_Bitacora = ?";
    private final String SQL_SELECT = "SELECT b.ID_Bitacora, e.Nombre, c.Nombre, b.Descripcion, b.Fecha, b.PorcentajeProgreso FROM bitacora b INNER JOIN empleado e ON e.ID_Empleado = b.ID_Empleado INNER JOIN caso c ON c.ID_Caso = b.ID_Caso";

    public int insert(BitacoraBeans bitacoraBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, bitacoraBeans.getIdBitacora());
            stmt.setInt(index++, bitacoraBeans.getIdEmpleado());
            stmt.setInt(index++, bitacoraBeans.getIdCaso());
            stmt.setString(index++, bitacoraBeans.getDescripcion());
            stmt.setString(index++, bitacoraBeans.getFecha());
            stmt.setInt(index, bitacoraBeans.getPorcentajeProgreso());
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

    public int update(BitacoraBeans bitacoraBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, bitacoraBeans.getIdEmpleado());
            stmt.setInt(index++, bitacoraBeans.getIdCaso());
            stmt.setString(index++, bitacoraBeans.getDescripcion());
            stmt.setString(index++, bitacoraBeans.getFecha());
            stmt.setInt(index++, bitacoraBeans.getPorcentajeProgreso());
            stmt.setInt(index, bitacoraBeans.getIdBitacora());
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

    public int delete(int idBitacora) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idBitacora);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    public DefaultTableModel selectBitacora() {
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
                for(int i = 0; i < numberOfColumns; i++) {
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
}
