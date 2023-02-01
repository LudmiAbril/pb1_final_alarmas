package interfaz;

import java.util.Scanner;

import dominio.Alarma;
import dominio.Sensor;
import dominio.Zona;

public class PruebaAlarma {

	public static void main(String[] args) {
		Scanner teclado= new Scanner(System.in);
		System.out.println("ingrese el nombre de la alarma");
		String nombre=teclado.next();
		System.out.println("ingrese la cantidad de sensores que se pueden agregar a la alarma");
		int cantidad=teclado.nextInt();
		String codConfig;
		do {
			System.out.println("ingrese el codigo de configuracion, el mismo debe tener un minimo de 4 caracteres y comenzar con 'C' mayuscula");
			codConfig=teclado.next();
		}while(codConfig.length()<4 || codConfig.charAt(0)!='C');
		System.out.println("ingrese el codigo de activacion");
		String codAct=teclado.next();
		Alarma alarma=new Alarma(nombre, cantidad, codConfig, codAct); 
		
		String codigo;
		int opcion;
		do {
		System.out.println("bienvenido al la central de alarma " + alarma.getNombreAlarma() + ", ingrese el codigo de configuracion,activacion o presione s para salir");
		codigo=teclado.next();
		opcion=alarma.codConf(codigo);
		
		switch(opcion) {
		case 1: 
			int op;
			
			do {
			op=menuConfig(teclado);
			elegirOpcion(op,teclado,alarma);
			}while(op!=0);
			
			break;
			
		case 2:
			alarma.activarDesactivar();
			if(alarma.isActivo()) {
				System.out.println("la alarma se encuentra activada");
			}else {
				System.out.println("la alarma se encuentra desactivada");
			}
			
			
			break;
		case 3: System.out.println("adios!");
			
		    break;
		default: System.out.println("opcion incorrecta");
			
			break;
		}
	
		}while(opcion!=3);
		
		
		

	}
	
	
	public static int menuConfig(Scanner teclado) {
		System.out.println("** MENU CONFIGURACION **"
				+ "\n1 - registrar sensor "
				+ "\n2 - habilitar/deshabilitar sensor "
				+ "\n3 - habilitar todos los sensores de una zona"
				+ "\n4 - obtener un sensor por nombre"
				+ "\n5 - obtener todos los sensores deshabillitados de una zona"
				+ "\n0 - volver");
		
		int opcion=teclado.nextInt();
		return opcion;
		
	}
	
	public static void elegirOpcion(int opcion,Scanner teclado,Alarma alarma) {
		
		switch(opcion) {
		case 1: registrarSensor(teclado,alarma);
			break;
			
		case 2: habilitarSensor(teclado,alarma);
			break;
			
		case 3: habilitarSensoresDeUnaZona(teclado,alarma);
			break;
			
		case 4: obtenerSensorPorNombre(teclado,alarma);
			break;
			
		case 5: obtenerSensoresDeshabilitadosDeUnaZona(teclado, alarma);
			break;
			
		case 0: 
			break;
			
		default: System.out.println("opcion incorrecta");
            break;		
		}
		
		
		
	}
	

	public static void registrarSensor(Scanner teclado, Alarma alarma) {
		System.out.println("ingrese el id del sensor");
		String id=teclado.next();
		System.out.println("ingrese el nombre del sensor");
		String nombre=teclado.next();
		int opcion;
		do {
		System.out.println("seleccione la zona:"
				+ "\n1 - ENTRADA"
				+ "\n2 - PUERTA_TRASERA"
				+ "\n3 - INTERIOR");
		opcion=teclado.nextInt();
		}while(opcion<0 || opcion>3);
		Zona zona=Zona.values()[opcion-1];
		
		Sensor sensor=new Sensor(id,nombre,zona);
		if(alarma.registrarSensor(sensor)) {
			System.out.println("se registro el sensor");
		}else {
			System.out.println("no se pudo registrar el sensor");
		}
	}
	
	public static void habilitarSensor(Scanner teclado,Alarma alarma) {
		System.out.println("ingrese el id del sensor");
		String id=teclado.next();
		if(alarma.buscarSensorPorID(id)!=null) {
			alarma.buscarSensorPorID(id).habilitarDeshabilitar();
			System.out.println("se cambio el estado del sensor");
		}else {
			System.out.println("no se encontro el sensor");
		}
	}
	
	public static void habilitarSensoresDeUnaZona(Scanner teclado,Alarma alarma) {
		int opcion;
		do {
		System.out.println("seleccione la zona:"
				+ "\n1 - ENTRADA"
				+ "\n2 - PUERTA_TRASERA"
				+ "\n3 - INTERIOR");
		opcion=teclado.nextInt();
		}while(opcion<0 || opcion>3);
		Zona zona=Zona.values()[opcion-1];
		alarma.habilitarSensoresZona(zona);
		System.out.println("se habilitaron los sensores en: " + zona);
		
	}
	
	public static void obtenerSensorPorNombre(Scanner teclado, Alarma alarma) {
		System.out.println("ingrese el nombre del sensor que quiere buscar:");
		String nombre=teclado.next();
		if(alarma.buscarSensorNombre(nombre)!=null) {
			System.out.println(alarma.buscarSensorNombre(nombre).toString()); 
		}else{
			System.out.println("no se encontro el sensor");
		}
		
		
	}
	
	public static void obtenerSensoresDeshabilitadosDeUnaZona(Scanner teclado,Alarma alarma) {
		Sensor sensores[];
		
		int opcion;
		do {
		System.out.println("seleccione la zona:"
				+ "\n1 - ENTRADA"
				+ "\n2 - PUERTA_TRASERA"
				+ "\n3 - INTERIOR");
		opcion=teclado.nextInt();
		}while(opcion<0 || opcion>3);
		Zona zona=Zona.values()[opcion-1];
		
		sensores=alarma.getSensoresDeshabilitadosZona(zona);
		for(int i=0;i<sensores.length;i++) {
			if(sensores[i]!=null) {
				System.out.println(sensores[i].toString());
			}
		}
		
		if(sensores==null) {
			System.out.println("no se encontraron sensores deshabilitados en esa zona");
		}
		
	}

}
