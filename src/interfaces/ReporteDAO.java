package interfaces;

import java.util.ArrayList;

import beans.ListadoEntreFechasDTO;

public interface ReporteDAO {
    public ArrayList<ListadoEntreFechasDTO> reporte(String fecha1, String fecha2);
    public ArrayList<ListadoEntreFechasDTO> lista();

}
