package model;

public class DatiSpedizioneBean {
	
	String nome;
	String cognome;
	String civico;
	String via;
	int cap;
	String citta;
	
	public DatiSpedizioneBean(String nome, String cognome, String civico, String via, int cap, String citta) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.civico = civico;
		this.via = via;
		this.cap = cap;
		this.citta = citta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	

}
