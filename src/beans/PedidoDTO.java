package beans;

public class PedidoDTO {
    private int id_pe;
    private int id_em;
    private int id_cli;
    private String fechaPedido;
    private int cantidadTotal;
    
    private String nombreCliente;
    private String nombreEmpleado;
    private int estado;
    
    public PedidoDTO() {
        
    }
    
    public int getId_pe() {
        return id_pe;
    }
    public void setId_pe(int id_pe) {
        this.id_pe = id_pe;
    }
    public int getId_em() {
        return id_em;
    }
    public void setId_em(int id_em) {
        this.id_em = id_em;
    }
    public int getId_cli() {
        return id_cli;
    }
    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }
    public String getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public int getCantidadTotal() {
        return cantidadTotal;
    }
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
