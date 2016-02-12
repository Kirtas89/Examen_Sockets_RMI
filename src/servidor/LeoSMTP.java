package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import entidades.ManejadoraMensajes;
import entidades.Mensaje;

	public class LeoSMTP implements Runnable {
	private Socket clientSocket = null;
	private InputStream socketIS = null;
	private ObjectInputStream socketObjectIS = null;
	private ManejadoraMensajes maneja;
	private Mensaje mensajito;
	private final String DESTINATARIO_CENTINELA = "sauron@mordor.com";
	private final String ASUNTO_CENTINELA = "I'm watching you";

    public LeoSMTP(Socket clientSocket, ManejadoraMensajes maneja) {
        this.clientSocket = clientSocket;
        this.maneja = maneja;
    }

    public void run() {
		try {
			//Abro stream de entrada
			socketIS = clientSocket.getInputStream();
			socketObjectIS = new ObjectInputStream(socketIS);
			//Recibo un mensaje haciendo el cast
			mensajito = (Mensaje) socketObjectIS.readObject();
			//socketObjectIS.reset();
			//Compruebo que el mensaje no es el centinela
			while (!mensajito.getDestinatario().equals(DESTINATARIO_CENTINELA) 
						&& !mensajito.getAsunto().equals(ASUNTO_CENTINELA)) {       //Por motivos que se me escapan, el stream lee todos los objetos
				//Lo almaceno en la lista de mensajes pendientes					//que se le pasan en ConductorEnvioMensajes pero el valor de esos	
				maneja.guardarMensajes(mensajito);									//objetos siempre es el mismo (el del primer objeto enviado).
				//Recibo un nuevo mensaje											//Si se prueba a mandar más o menos objetos, guardará todos esos
				mensajito = null;
				mensajito = (Mensaje) socketObjectIS.readObject();					//objetos con los mismos valores.
				//socketObjectIS.reset();
			}
			//Corto la conexión
			try {
				socketIS.close();
			} catch(IOException e) {
				System.out.println("La conexion se ha cerrado");
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} finally {
			try {
				//Compruebo si el socket no se ha cerrado y lo cierro
				if (socketIS != null) {
					socketIS.close();
				}				
			} catch (IOException e) {
				//System.out.println("Error al cerrar el stream de salida");
				e.printStackTrace();
			}	
		}
    }
}
