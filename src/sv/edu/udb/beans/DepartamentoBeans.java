package sv.edu.udb.beans;

public class DepartamentoBeans {
    private int idDepartamento;
    private String nombreDepartamento;
    private String prefijo;

    //Constructor vacío
    public DepartamentoBeans() {

    }

    //Constructor con argumentos
    public DepartamentoBeans(int idDepartamento, String nombreDepartamento, String prefijo) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.prefijo = prefijo;
    }

    //Getters y Setters
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
}
