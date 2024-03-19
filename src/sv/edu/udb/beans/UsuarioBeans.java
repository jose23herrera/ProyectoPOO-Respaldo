package sv.edu.udb.beans;

public class UsuarioBeans {
    private String nombreRol;
    private int nivelAcceso;
    private String nombre;
    private String usuario;
    private String password;

    public UsuarioBeans(String nombreRol, int nivelAcceso, String nombre, String usuario, String password) {
        this.nombreRol = nombreRol;
        this.nivelAcceso = nivelAcceso;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodos get y set
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public int getNivelAcceso() {
        return nivelAcceso;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getPassword() {
        return password;
    }
}
