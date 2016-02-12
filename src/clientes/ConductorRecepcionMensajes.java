package clientes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import entidades.Mensaje;
import servidor.LeoPOP3Interface;

public class ConductorRecepcionMensajes {
	private final static String HOST = "127.0.0.1";
    private final static int PORT = 28888;
    private final static String OBJECT_NAME = "POP";
    
	public static void main(String[] args) {		
		LeoPOP3Interface servidor = null;
		//Lista de destinatario que van a comprobar su correo
		String destinatario1 = "hola@hola.com";
		String destinatario2 = "111@111.com";
		//Instancio el objeto remoto LeoPOP3
		try {
            Registry registry = LocateRegistry.getRegistry(HOST,PORT);
            servidor = (LeoPOP3Interface) registry.lookup(OBJECT_NAME);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
		//Uso el método del objeto remoto para comprobar el correo de destinatario 1
		try {
			ArrayList<Mensaje> mensajes = servidor.obtenerMensajes(destinatario1);
			for(Mensaje mensajito:mensajes) {
				System.out.println("Destinatario: " + mensajito.getDestinatario() 
									+ " - Remitente: " + mensajito.getRemitente() 
										+ " - Asunto: " + mensajito.getAsunto() 
											+ " - Texto: " + mensajito.getTexto());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----------------------------------");
		
		//Uso el método del objeto remoto para comprobar el correo de destinatario 2
				try {
					ArrayList<Mensaje> mensajes = servidor.obtenerMensajes(destinatario2);
					for(Mensaje mensajito:mensajes) {
						System.out.println("Destinatario: " + mensajito.getDestinatario() 
											+ " - Remitente: " + mensajito.getRemitente() 
												+ " - Asunto: " + mensajito.getAsunto() 
													+ " - Texto: " + mensajito.getTexto());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		//Comprobacion
		System.out.println("He llegado al final");
	}
}
