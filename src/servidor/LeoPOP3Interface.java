package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entidades.Mensaje;

public interface LeoPOP3Interface extends Remote {

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
	ArrayList<Mensaje> obtenerMensajes(String emailCliente) throws RemoteException;

}