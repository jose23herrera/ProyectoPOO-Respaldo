package sv.edu.udb.beans;

import com.toedter.calendar.JDateChooser;

import java.util.Date;

public class CasoBeans {
        private int idCaso;
        private String codigo;
        private int idDepartamento;
        private String nombre;
        private String descripcion;
        private String fechaInicio;
        private String fechaFin;
        private String fechaLimiteEntrega;
        private String infoExtra;
        private String estado;
        private String motivo;

        //Constructor vacío
        public CasoBeans() {

        }

        //Constructor con todos los parámetros
    public CasoBeans(int idCaso, String codigo, int idDepartamento, String nombre, String descripcion, String fechaInicio, String fechaFin, String fechaLimiteEntrega, String infoExtra, String estado, String motivo) {
        this.idCaso = idCaso;
        this.codigo = codigo;
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteEntrega = fechaLimiteEntrega;
        this.infoExtra = infoExtra;
        this.estado = estado;
        this.motivo = motivo;
    }

    //Constructor para Insert a la base de datos
    public CasoBeans(int idDepartamento, String nombre, String descripcion, String fechaInicio, String fechaFin, String fechaLimiteEntrega, String infoExtra, String estado) {
        this.idCaso = idCaso;
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteEntrega = fechaLimiteEntrega;
        this.infoExtra = infoExtra;
        this.estado = estado;
    }

    public CasoBeans(int idCaso, int idDepartamento, String nombre, String descripcion, String fechaInicio, String fechaFin, String fechaLimiteEntrega, String infoExtra, String estado) {
        this.idCaso = idCaso;
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaLimiteEntrega = fechaLimiteEntrega;
        this.infoExtra = infoExtra;
        this.estado = estado;
        }


    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaLimiteEntrega() {
        return fechaLimiteEntrega;
    }

    public void setFechaLimiteEntrega(String fechaLimiteEntrega) {
        this.fechaLimiteEntrega = fechaLimiteEntrega;
    }

    public String getInfoExtra() {
        return infoExtra;
    }

    public void setInfoExtra(String infoExtra) {
        this.infoExtra = infoExtra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
