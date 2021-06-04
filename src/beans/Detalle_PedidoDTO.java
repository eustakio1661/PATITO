package beans;

public class Detalle_PedidoDTO {
    private int id_pe;
    private int id_pro;
    private double precio;
    private int cantidad;
    private String nombreProd;
    private double importe;
    
    public Detalle_PedidoDTO() {
        
    }
    
    public int getId_pe() {
        return id_pe;
    }
    public void setId_pe(int id_pe) {
        this.id_pe = id_pe;
    }
    public int getId_pro() {
        return id_pro;
    }
    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getNombreProd() {
        return nombreProd;
    }
    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
    
}
