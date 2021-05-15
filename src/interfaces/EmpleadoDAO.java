package interfaces;

import java.util.ArrayList;

import beans.EmpleadoDTO;

public interface EmpleadoDAO {
    
    public int registrar(EmpleadoDTO e);
    public ArrayList<EmpleadoDTO> listado();

}
