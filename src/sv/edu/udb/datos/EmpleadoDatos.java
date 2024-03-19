package sv.edu.udb.datos;
import sv.edu.udb.util.Conexion;
import sv.edu.udb.beans.EmpleadoBeans;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmpleadoDatos {
    //Consultas SQL a utilizar
    private final String SQL_INSERT = "INSERT INTO empleado (ID_Empleado, ID_Rol, ID_Departamento, Nombre, Edad, Telefono) VALUES (?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE empleado SET ID_Rol = ?, ID_Departamento = ?, Nombre = ?, Edad = ?, Telefono = ? WHERE ID_Empleado = ?";
    private final String SQL_DELETE = "DELETE FROM empleado WHERE ID_Empleado = ?";
    private final String SQL_SELECT = "SELECT e.ID_Empleado, d.NombreDepartamento, r.NombreRol, e.Nombre, e.Edad, e.Telefono FROM empleado e INNER JOIN departamento d ON e.ID_Departamento = d.ID_Departamento INNER JOIN rol r ON r.ID_Rol = e.ID_Rol";
    private final String SQL_SELECT_EMPLEADOS = "SELECT ID_Empleado, Nombre FROM empleado ORDER BY ID_Empleado";
    private final String SQL_SELECT_IDEMPLEADO = "SELECT ID_Empleado FROM empleado WHERE Nombre = ?";

    private final String SQL_SELECT_EMPLEADOS_PROGRAMADORES = "SELECT ID_Empleado, Nombre FROM empleado WHERE ID_Rol = 4 ORDER BY ID_Empleado";

    private final String SQL_SELECT_EMPLEADOS_PROBADORES = "SELECT ID_Empleado, Nombre FROM empleado WHERE ID_Rol = 5 ORDER BY ID_Empleado";

    //Método para agregar un nuevo empleado a la base de datos
    public int insert(EmpleadoBeans empleadoBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, empleadoBeans.getIdEmpleado());
            stmt.setInt(index++, empleadoBeans.getIdDepartamento());
            stmt.setInt(index++, empleadoBeans.getIdRol());
            stmt.setString(index++, empleadoBeans.getNombre());
            stmt.setInt(index++, empleadoBeans.getEdad());
            stmt.setString(index, empleadoBeans.getTelefono());

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
    }//Fin del métoodo insert

    //Método para actualizar datos de un empleado
    public int update(EmpleadoBeans empleadoBeans) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, empleadoBeans.getIdDepartamento());
            stmt.setInt(index++, empleadoBeans.getIdRol());
            stmt.setString(index++, empleadoBeans.getNombre());
            stmt.setInt(index++, empleadoBeans.getEdad());
            stmt.setString(index++, empleadoBeans.getTelefono());
            stmt.setInt(index, empleadoBeans.getIdEmpleado());

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
    }//Fin del método update

    //Método para eliminar un empleado de la base de datos
    public int delete(int idEmpleado) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idEmpleado);
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
    }//Fin del método delete

    //Método para mostrar todos los empleados registrados en la base de datos
    public DefaultTableModel selectEmpleado() {
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
    }//Fin del método selectEmpleado

    public DefaultComboBoxModel selectEmpleados() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_EMPLEADOS);
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

    public int getIdEmpleado(String empleado) {
        int idEmpleado = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_IDEMPLEADO);
            stmt.setString(1, empleado);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idEmpleado = rs.getInt(1);
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
        return idEmpleado;
    }

    public DefaultComboBoxModel selectProgramadores() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_EMPLEADOS_PROGRAMADORES);
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

    public DefaultComboBoxModel selectProbadores() {
        DefaultComboBoxModel dtm = new DefaultComboBoxModel();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_EMPLEADOS_PROBADORES);
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
}
