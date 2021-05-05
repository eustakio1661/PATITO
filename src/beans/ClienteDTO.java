package beans;

public class ClienteDTO {

	private int codigoCliente, codigoDistrito;
	private String dniCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente;

	public String toString() {
		return "ClienteDTO [codigoCliente=" + codigoCliente + ", codigoDistrito=" + codigoDistrito + ", dniCliente="
				+ dniCliente + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente
				+ ", direccionCliente=" + direccionCliente + ", telefonoCliente=" + telefonoCliente + "]";
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public int getCodigoDistrito() {
		return codigoDistrito;
	}

	public void setCodigoDistrito(int codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

}
