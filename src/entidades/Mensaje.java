package entidades;

import java.io.Serializable;

public class Mensaje implements Serializable {
	private static final long serialVersionUID = 8L;
	private String destinatario,remitente,asunto,texto;
	
	public Mensaje() {
		this.destinatario = null;
		this.remitente = null;
		this.asunto = null;
		this.texto = null;
	}

	public Mensaje(String destinatario, String remitente, String asunto, String texto) {
		this.destinatario = destinatario;
		this.remitente = remitente;
		this.asunto = asunto;
		this.texto = texto;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
