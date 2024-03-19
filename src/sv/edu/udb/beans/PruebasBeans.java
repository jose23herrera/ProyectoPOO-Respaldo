// Clase Bean
package sv.edu.udb.beans;

public class PruebasBeans {
    private int idPrueba;
    private int idEmpleado;
    private int idCaso;
    private String fecha;
    private String observaciones;

    // Constructor vacío
    public PruebasBeans() {

    }

    // Constructor con argumentos
    public PruebasBeans(int idPrueba, int idEmpleado, int idCaso, String fecha, String observaciones) {
        this.idPrueba = idPrueba;
        this.idEmpleado = idEmpleado;
        this.idCaso = idCaso;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
