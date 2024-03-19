package sv.edu.udb.beans;

import java.util.Date;

public class BitacoraBeans {
    private int idBitacora;
    private int idEmpleado;
    private int idCaso;
    private String descripcion;
    private String fecha;
    private int porcentajeProgreso;

    //Constructor vacío
    public BitacoraBeans() {

    }

    // Constructor con argumentos
    public BitacoraBeans(int idBitacora, int idEmpleado, int idCaso, String descripcion, String fecha, int porcentajeProgreso) {
        this.idBitacora = idBitacora;
        this.idEmpleado = idEmpleado;
        this.idCaso = idCaso;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.porcentajeProgreso = porcentajeProgreso;
    }

    //Getters y Setters
    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPorcentajeProgreso() {
        return porcentajeProgreso;
    }

    public void setPorcentajeProgreso(int porcentajeProgreso) {
        this.porcentajeProgreso = porcentajeProgreso;
    }
}
