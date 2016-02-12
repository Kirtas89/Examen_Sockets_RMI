package servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;

import entidades.ManejadoraMensajes;
import entidades.Mensaje;

public class LeoPOP3 implements LeoPOP3Interface {
	private ManejadoraMensajes listaMensajesPendientes;
	
	public LeoPOP3(ManejadoraMensajes listaMensajesPendientes) {
		this.listaMensajesPendientes = listaMensajesPendientes;
	}
	
	/* ArrayList<Mensaje> obtenerMensajes(String emailCliente)
	 * 
	 * Comentario: Función sincronizada que dado un email devuelve una lista de mensajes
	 * 				dirigidos a ese email.
	 * Precondición: Nada
	 * Entrada: Un String que representa un email
	 * Salida: Una lista de mensajes
	 * Postcondición: Se devuelve una lista asociada al nombre con los mensajes cuyo destinatario
	 * 					es el email proporcionado.
	 * 
	 */
	@Override
	public synchronized ArrayList<Mensaje> obtenerMensajes(String emailCliente) throws RemoteException {
		ArrayList<Mensaje> listaMensajesCliente = new ArrayList<>();
		for (Mensaje mensajito:listaMensajesPendientes.leerMensajes()) {
			if (comprobarDestinatario(mensajito, emailCliente)){
				listaMensajesCliente.add(mensajito);
			}
		}
		return listaMensajesCliente;		
	}
	
	/* boolean comprobarDestinatario(Mensaje mensajito, String emailCliente)
	 * 
	 * Comentario: Función que recibe un mensaje (objeto Mensaje) y un email (String) y comprueba
	 * 				si dicho mensaje va destinado a ese email.
	 * Precondición: Nada
	 * Entrada: Un objeto Mensaje y un String que representa un email
	 * Salida: Un boolean
	 * Postcondición: Se devuelve un boolean asociado al nombre que indica true si el mensaje es para 
	 * 					ese email y false si no.
	 */
	private boolean comprobarDestinatario(Mensaje mensajito, String emailCliente) {
		boolean destinarioCoincide = false;
		if (mensajito.getDestinatario().equals(emailCliente)) {
			destinarioCoincide = true;
		}		
		return destinarioCoincide;
	}
}
