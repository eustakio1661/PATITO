package beans;

public class EmpleadoDTO {

    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String usuario;
    private String clave;
    private int idTipo;
    private int estado;
    private String imagen;

    // 0 -> eliminado , 1 -> activo

    public EmpleadoDTO(int id, String dni, String nombre, String apellido, String telefono, String usuario,
            String clave, int idTipo, int estado, String imagen) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
        this.idTipo = idTipo;
        this.estado = estado;
        this.imagen = imagen;
    }

    public EmpleadoDTO(int id, String dni, String nombre, String apellido, String telefono, String usuario,
            String clave, int idTipo, int estado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
        this.idTipo = idTipo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
