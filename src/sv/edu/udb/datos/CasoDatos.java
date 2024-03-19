package sv.edu.udb.datos;

import sv.edu.udb.beans.CasoBeans;
import sv.edu.udb.util.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CasoDatos {

    //Consultas SQL
    private final String SQL_INSERT = "INSERT INTO caso (ID_Departamento, Nombre, Descripcion, FechaInicio, FechaFin, FechaLimite, InfoExtra, Estado, Codigo) VALUES (?,?,?,?,?,?,?, ?, CONCAT((SELECT Prefijo FROM Departamento WHERE ID_Departamento = ?), DATE_FORMAT(NOW(), '%y%m%d'), LPAD(FLOOR(RAND() * 1000), 3, '0')))";
    private final String SQL_UPDATE = "UPDATE caso SET ID_Departamento = ?, Nombre = ?, Descripcion = ?, FechaInicio = ?, FechaFin = ?, FechaLimite  = ?, InfoExtra = ?, Estado = ? WHERE ID_Caso = ?";
    private final String SQL_DELETE = "DELETE FROM caso WHERE ID_Caso = ?";
    private final String SQL_SELECT = "SELECT c.ID_Caso, c.Codigo, d.NombreDepartamento, c.Nombre, c.Descripcion, c.FechaInicio, c.FechaFin, c.FechaLimite, c.InfoExtra, c.Estado FROM caso c INNER JOIN departamento d ON c.ID_Departamento = d.ID_Departamento";
    private final String SQL_SELECT_CASOS = "SELECT ID_Caso, Nombre FROM caso ORDER BY ID_Caso";
    private final String SQL_SELECT_IDCASO = "SELECT ID_Caso FROM caso WHERE Nombre = ?";
    private final String SQL_SELECT_EN_ESPERA = "SELECT c.ID_Caso, c.Codigo, d.NombreDepartamento, c.Nombre, c.Descripcion, c.FechaInicio, c.FechaLimite, c.Estado, c.Motivo FROM caso c INNER JOIN departamento d ON c.ID_Departamento = d.ID_Departamento WHERE c.Estado = 'En Espera'";
    private final String SQL_SELECT_ACEPTADOS = "SELECT c.ID_Caso, c.Codigo, d.NombreDepartamento, c.Nombre, c.Descripcion, c.FechaInicio, c.FechaLimite, c.Estado, c.Motivo FROM caso c INNER JOIN departamento d ON c.ID_Departamento = d.ID_Departamento WHERE c.Estado = 'Aceptado'";

    public int insert(CasoBeans casoBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, casoBeans.getIdDepartamento());
            stmt.setString(index++, casoBeans.getNombre());
            stmt.setString(index++, casoBeans.getDescripcion());
            stmt.setString(index++, casoBeans.getFechaInicio());
            stmt.setString(index++, casoBeans.getFechaFin());
            stmt.setString(index++, casoBeans.getFechaLimiteEntrega());
            stmt.setString(index++, casoBeans.getInfoExtra());
            stmt.setString(index++, casoBeans.getEstado());
            stmt.setInt(index, casoBeans.getIdDepartamento()); // Aquí se pasa el ID_Departamento para la subconsulta para generar el código del caso
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }


    public int update(CasoBeans casoBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, casoBeans.getIdDepartamento());
            stmt.setString(index++, casoBeans.getNombre());
            stmt.setString(index++, casoBeans.getDescripcion());
            stmt.setString(index++, casoBeans.getFechaInicio());
            stmt.setString(index++, casoBeans.getFechaFin());
            stmt.setString(index++, casoBeans.getFechaLimiteEntrega());
            stmt.setString(index++, casoBeans.getInfoExtra());
            stmt.setString(index++, casoBeans.getEstado());
            stmt.setInt(index, casoBeans.getIdCaso());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }


    public int delete(int idCaso) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idCaso);
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

    public DefaultTableModel selectCaso() {
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
                    if(rs.getObject((i+1)) == null) {
                        fila[i] = "Vacío";
                    }
                    else {
                        fila[i] = rs.getObject(i+1);
                    }
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

    public DefaultComboBoxModel selectCasos() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_CASOS);
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

    public int getIdCaso(String caso) {
        int idCaso = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_IDCASO);
            stmt.setString(1, caso);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idCaso = rs.getInt(1);
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
        return idCaso;
    }

    //Método para actualizar el estado de un caso
    public int actualizarEstadoCaso(int idCaso, String estado, String motivo) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            if (estado.equals("Aceptado")) {
                stmt = con.prepareStatement("UPDATE caso SET Estado = ? WHERE ID_Caso = ?");
                stmt.setString(1, estado);
                stmt.setInt(2, idCaso);
            } else if (estado.equals("Rechazado")) {
                stmt = con.prepareStatement("UPDATE caso SET Estado = ?, Motivo = ? WHERE ID_Caso = ?");
                stmt.setString(1, estado);
                stmt.setString(2, motivo);
                stmt.setInt(3, idCaso);
            }
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    //Método para generar una tabla solo con los casos en espera
    public DefaultTableModel selectCasosEnEspera() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_EN_ESPERA);
            rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            for(int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for(int i = 0; i < numberOfColumns; i++) {
                    if(rs.getObject((i+1)) == null) {
                        fila[i] = "Vacío";
                    }
                    else {
                        fila[i] = rs.getObject(i+1);
                    }
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

    //Método para generar un combobox solo con los casos aceptados
    public DefaultComboBoxModel selectCasosAceptados() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_ACEPTADOS);
            rs = stmt.executeQuery();
            while (rs.next()){
                dtm.addElement(rs.getObject(4));
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
