package interfaces;

import java.util.ArrayList;

import beans.EmpleadoDTO;

public interface EmpleadoDAO {
    
    public int registrar(EmpleadoDTO e);
    public ArrayList<EmpleadoDTO> listado();
    public int actualizar(EmpleadoDTO e);
    public int eliminar(EmpleadoDTO e);
    public EmpleadoDTO validarAcceso(String correo, String usuario, String clave);
}
