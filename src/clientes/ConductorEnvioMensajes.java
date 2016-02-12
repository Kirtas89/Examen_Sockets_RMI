package clientes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import entidades.Mensaje;


public class ConductorEnvioMensajes {
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 40025;

	public static void main(String[] args) {
		Socket clientSocket = null;
	    OutputStream clienteOS = null;
	    ObjectOutputStream clienteObjectOS = null;
	    Mensaje mensajito = new Mensaje();
	    
	    try {
    		clientSocket = new Socket(HOST,PORT);
    		clienteOS = clientSocket.getOutputStream();
    		clienteObjectOS = new ObjectOutputStream(clienteOS);
    		
    		//Mando un par de mensajes por el socket
    		//Dos de los mensajes van para el mismo destinatario hola@hola.com
    		//El último va dirigido a Sauron
  			mensajito.setDestinatario("hola@hola.com");
      		mensajito.setRemitente("prueba@prueba.com");
       		mensajito.setAsunto("probando");
       		mensajito.setTexto("Probando probando");
       		clienteObjectOS.writeObject(mensajito);
       		System.out.println("Mensaje enviado 1");
       		
       		mensajito = new Mensaje();	
			mensajito.setDestinatario("111@111.com");
			mensajito.setRemitente("prueba@prueba.com");
			mensajito.setAsunto("hola soy 1");
			mensajito.setTexto("1111111111111111111111");
			clienteObjectOS.writeObject(mensajito);
			System.out.println("Mensaje enviado 2");
				
			mensajito = new Mensaje();	
			mensajito.setDestinatario("hola@hola.com");
    		mensajito.setRemitente("333@333.com");
    		mensajito.setAsunto("hola soy 3");
    		mensajito.setTexto("3333333333333333333333");
    		clienteObjectOS.writeObject(mensajito);
    		System.out.println("Mensaje enviado 3");
	    		
    		mensajito = new Mensaje();	
			mensajito.setDestinatario("sauron@mordor.com");
    		mensajito.setRemitente("prueba@prueba.com");
    		mensajito.setAsunto("I'm watching you");
    		mensajito.setTexto("Mordor mola(mentira)");
    		clienteObjectOS.writeObject(mensajito);
    		System.out.println("Mensaje enviado 4");
	    		
    		mensajito = new Mensaje();	
			mensajito.setDestinatario("venga@funciona.com");
    		mensajito.setRemitente("por@que.com");
    		mensajito.setAsunto("No deberia enviarme");
    		mensajito.setTexto("Espero que no me estes leyendo maldito servidor");
    		clienteObjectOS.writeObject(mensajito);
    		System.out.println("Mensaje enviado 5");
    		
    		//Bucle infinito para que no salte una SocketException en el hilo LeoSMTP
			//Por culpa de que la lectura del stream siempre coja los valores del primer objeto
			//la condición del bucle en el hilo nunca se comprueba y el hilo se queda a la espera de leer
			//un nuevo objeto. Sin este bucle, este conducot acaba su ejecución y el hilo a la espera de una nueva lectura
			//provoca una SocketException
    		//while(true) {} 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
