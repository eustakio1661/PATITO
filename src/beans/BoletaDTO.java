package beans;

public class BoletaDTO {
    private int id_pe;
    private String id_bol;
    private double precioTotal;
    private double descuento;
    
    public BoletaDTO() {
        
    }
    
    public int getId_pe() {
        return id_pe;
    }
    public void setId_pe(int id_pe) {
        this.id_pe = id_pe;
    }
    public String getId_bol() {
        return id_bol;
    }
    public void setId_bol(String id_bol) {
        this.id_bol = id_bol;
    }
    public double getPrecioTotal() {
        return precioTotal;
    }
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
}
