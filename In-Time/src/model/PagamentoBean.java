package model;

public class PagamentoBean {
	
	String nomeT;
	String cognomeT;
	String nCarta;
	String scadenza;
	
	

	public PagamentoBean(String nomeT, String cognomeT, String nCarta, String scadenza) {
		super();
		this.nomeT = nomeT;
		this.cognomeT = cognomeT;
		this.nCarta = nCarta;
		this.scadenza = scadenza;
	}

	public String getnCarta() {
		return nCarta;
	}

	public void setnCarta(String nCarta) {
		this.nCarta = nCarta;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public String getNomeT() {
		return nomeT;
	}

	public void setNomeT(String nomeT) {
		this.nomeT = nomeT;
	}

	public String getCognomeT() {
		return cognomeT;
	}

	public void setCognomeT(String cognomeT) {
		this.cognomeT = cognomeT;
	}
	
	

}
