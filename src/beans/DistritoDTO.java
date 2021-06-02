package beans;

public class DistritoDTO {

	private int id;
	private String descripcion;

	@Override
	public String toString() {
		return "DistritoDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
