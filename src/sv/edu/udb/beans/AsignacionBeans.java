package sv.edu.udb.beans;

public class AsignacionBeans {
    private int idAsignacion;
    private int idEmpleado;
    private int idCaso;
    private String fechaLimite;
    private String descripcionAsignacion;

    //Constructor vacío
    public AsignacionBeans() {

    }

    // Constructor con argumentos
    public AsignacionBeans(int idAsignacion, int idEmpleado, int idCaso, String fechaLimite, String descripcionAsignacion) {
        this.idAsignacion = idAsignacion;
        this.idEmpleado = idEmpleado;
        this.idCaso = idCaso;
        this.fechaLimite = fechaLimite;
        this.descripcionAsignacion = descripcionAsignacion;
    }

    //Getters y Setters
    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getDescripcionAsignacion() {
        return descripcionAsignacion;
    }

    public void setDescripcionAsignacion(String descripcionAsignacion) {
        this.descripcionAsignacion = descripcionAsignacion;
    }
}