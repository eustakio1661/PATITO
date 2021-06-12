package beans;

public class PedidoDTO {
    private int id_pe;
    private int id_em;
    private int id_cli;
    private String fechaSalida;
    private String fechaEntrega;
    private int cantidadTotal;
    
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
    public String getFechaSalida() {
        return fechaSalida;
    }
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public String getFechaEntrega() {
        return fechaEntrega;
    }
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    public int getCantidadTotal() {
        return cantidadTotal;
    }
    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
    
    
}
