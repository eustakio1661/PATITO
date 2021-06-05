package interfaces;

import java.util.ArrayList;

import beans.ClienteDTO;

public interface ClienteDAO {

	public ArrayList<ClienteDTO> listarCliente();
	public int registrarCliente(ClienteDTO cli);
	public int actualizarCliente(ClienteDTO cli);
	public ClienteDTO buscarCliente(int cod);
	public ArrayList<ClienteDTO> listarClientexDistrito();
	public int eliminarCliente(ClienteDTO c);
	
}
