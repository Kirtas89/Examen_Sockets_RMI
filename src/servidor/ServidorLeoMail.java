package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import entidades.ManejadoraMensajes;
import entidades.Mensaje;

	public class ServidorLeoMail {
	private final int PUERTO = 40025;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ArrayList<Mensaje> listaMensajes;
	private ManejadoraMensajes maneja;
	private LeoPOP3 leoPOP;
	
	public ServidorLeoMail() {
		listaMensajes = new ArrayList<Mensaje>();
		maneja = new ManejadoraMensajes(listaMensajes);		
	}
	
	public void initServer() {
		//Registra objeto remoto LeoPOP3
		try {
            System.out.println("Creando el registro de objetos remotos");
            Registry reg = null;
            try {
            	//Creo el registro en el puerto 28888
                reg = LocateRegistry.createRegistry(28888);
            } catch (RemoteException ex) {
                System.out.println("ERROR: No se ha podido crear el registro");
                Logger.getLogger(LeoPOP3.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println ("Creando el objeto servidor e instanciándolo en el registro");
            //Instancio la clase remota y la vinculo al registro creado previamente con la clave "POP"
            leoPOP = new LeoPOP3(maneja);
            reg.rebind("POP", (LeoPOP3Interface)UnicastRemoteObject.exportObject(leoPOP,0));
            System.out.println("Objeto remoto registrado con éxito");
        } catch (RemoteException ex) {
            //Logger.getLogger(Conductor.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		//Inicia sockets para lanzar hilos LeoSMTP
		try {
			//Crea socket servidor para escuchar en puerto 40025
			serverSocket = new ServerSocket(PUERTO);
			System.out.println("Esperando una conexión:");
			//Inicia el socket, ahora está esperando una conexión por parte del cliente
			while(true){
				socket = serverSocket.accept();			
				
				Thread a = new Thread(new LeoSMTP(socket,maneja));//CREO THREAD
				a.start();//EJECUTO THREAD	
				
				System.out.println("Cliente conectado");
			}//FIN_WHILE								
		}//FIN_TRY
		catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}
		finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
