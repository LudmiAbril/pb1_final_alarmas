package dominio;

public class Sensor {
	private String ID;
	private String nombre;
	private Zona zona;
	private boolean estado;
	
	public Sensor(String iD, String nombre, Zona zona) {
		ID = iD;
		this.nombre = nombre;
		this.zona = zona;
	}
	
	
	public void habilitarDeshabilitar() {
		if(this.estado) {
			this.setEstado(false);
		}else {
			this.setEstado(true);
		}
	}
	
	@Override
	public String toString() {
		return "Sensor [ID=" + ID + ", nombre=" + nombre + ", zona=" + zona + ", estado=" + estado + "]";
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
}
