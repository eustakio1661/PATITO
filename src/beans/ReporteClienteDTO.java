package beans;

public class ReporteClienteDTO {
    private int id_cli;
    private String nombreCompleto;
    private int cantidadCompras;
    private String segmentacion;

    public ReporteClienteDTO() {
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public String getSegmentacion() {
        return segmentacion;
    }

    public void setSegmentacion(String segmentacion) {
        this.segmentacion = segmentacion;
    }

}
