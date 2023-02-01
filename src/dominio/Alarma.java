package dominio;

public class Alarma {
	private String nombreAlarma;
	private int cantidadDeSensoresQueSePuedenAgregar;
	private String codigoConfiguracion;
	private Sensor [] sensores;
	private String codAct;
	private boolean activo;
	
	public Alarma(String nombreAlarma, int cantidadDeSensoresQueSePuedenAgregar, String codigoConfiguracion, String codAct) {
		this.nombreAlarma = nombreAlarma;
		this.sensores = new Sensor[cantidadDeSensoresQueSePuedenAgregar];
		this.codigoConfiguracion = codigoConfiguracion;
		this.codAct=codAct;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int codConf(String cod) {
		if(cod.equals(this.codigoConfiguracion)) {
			return 1;
		}
		if(cod.equals(this.codAct)) {
			return 2;
		}
		if(cod.equals("s")) {
			return 3;
		}
		
		return 9;
	}
	

	public String getCodAct() {
		return codAct;
	}


	public void setCodAct(String codAct) {
		this.codAct = codAct;
	}


	public String getNombreAlarma() {
		return nombreAlarma;
	}

	public void setNombreAlarma(String nombreAlarma) {
		this.nombreAlarma = nombreAlarma;
	}

	public int getCantidadDeSensoresQueSePuedenAgregar() {
		return cantidadDeSensoresQueSePuedenAgregar;
	}

	public void setCantidadDeSensoresQueSePuedenAgregar(int cantidadDeSensoresQueSePuedenAgregar) {
		this.cantidadDeSensoresQueSePuedenAgregar = cantidadDeSensoresQueSePuedenAgregar;
	}

	public String getCodigoConfiguracion() {
		return codigoConfiguracion;
	}

	public void setCodigoConfiguracion(String codigoConfiguracion) {
		this.codigoConfiguracion = codigoConfiguracion;
	}

	public Sensor[] getSensores() {
		return sensores;
	}

	public void setSensores(Sensor[] sensores) {
		this.sensores = sensores;
	}

	public boolean registrarSensor(Sensor sensor) {
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]==null) {
				sensores[i]=sensor;
				return true;
			}
		}
		return false;
	}

	public Sensor buscarSensorPorID(String id) {
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]!=null && sensores[i].getID().equals(id)) {
				return sensores[i];
			}
		}
		return null;
	}

	public void habilitarSensoresZona(Zona zona) {
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]!=null && sensores[i].getZona().equals(zona)) {
				sensores[i].setEstado(true);
			}
		}
		
	}

	public Sensor buscarSensorNombre(String nombre) {
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]!=null && sensores[i].getNombre().equals(nombre)) {
				return sensores[i];
			}
		}
		return null;
	}

	public Sensor[] getSensoresDeshabilitadosZona(Zona zona) {
		Sensor sensoresdeshzona[]=new Sensor[sensores.length];
		int j=0;
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]!=null && sensores[i].isEstado()==false && sensores[i].getZona().equals(zona)) {
				sensoresdeshzona[j]=sensores[i];
				j++;
			}
		}
		return sensoresdeshzona;
	}

	public void activarDesactivar() {
		if(!this.activo && todosLosSensoresHabilitados()) {
			this.setActivo(true);
		}
		
	}
	
	public boolean todosLosSensoresHabilitados() {
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]!=null && sensores[i].isEstado()==false) {
			 return false;
			 }
		} return true;
	}
	
	

}
