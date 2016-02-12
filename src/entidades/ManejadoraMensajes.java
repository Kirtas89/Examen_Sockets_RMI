package entidades;

import java.util.ArrayList;

public class ManejadoraMensajes {
	private ArrayList<Mensaje> listaMensajesPendientes;
	
	public ManejadoraMensajes(ArrayList<Mensaje> listaMensajesPendientes) {
		this.listaMensajesPendientes = listaMensajesPendientes;
	}
	
	/* void guardarMensajes(Mensaje mensajito)
	 * 
	 * Comentario: Método sincronizado que almacena mensajes en una lista
	 * Precondición: El mensaje no viene vacio
	 * Entrada: Un objeto tipo Mensaje
	 * Salida: Nada
	 * Postcondición: El mensaje queda guardado en la lista
	 * 
	 */
	public synchronized void guardarMensajes(Mensaje mensajito) {
		listaMensajesPendientes.add(mensajito);
	}
	
	/*
	 * Función que devuelve la lista de mensajes. Sirve para poder mostrar los emails en el cliente
	 */
	public ArrayList<Mensaje> leerMensajes() {
		return listaMensajesPendientes;
	}
}
