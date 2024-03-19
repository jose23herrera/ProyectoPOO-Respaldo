package sv.edu.udb.beans;

public class EmpleadoBeans {
    //Campos de la Tabla Empleado
    private int idEmpleado;
    private int idDepartamento;
    private int idRol;
    private String nombre;
    private int edad;
    private String telefono;

    // Constructor vacío
    public EmpleadoBeans() {
    }

    // Constructor con argumentos
    public EmpleadoBeans(int idEmpleado,int idDepartamento, int idRol, String nombre, int edad, String telefono) {
        this.idEmpleado  = idEmpleado;
        this.idDepartamento = idDepartamento;
        this.idRol = idRol;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    // Getters y setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
