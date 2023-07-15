package model;

public class ReviewBean {
    private int id;
    private String utente;
    private int idUtente;
    private int idArticolo;
    private double voto;
    private String testo;

 

 
	public ReviewBean(String utente, String testo,double voto) {
		super();
		this.utente = utente;
		this.testo = testo;
		this.voto = voto;
	}
	
	

	public ReviewBean( int idUtente, int idArticolo, double voto, String testo) {
		super();

		this.idUtente = idUtente;
		this.idArticolo = idArticolo;
		this.voto = voto;
		this.testo = testo;
	}



	// Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(int idArticolo) {
        this.idArticolo = idArticolo;
    }

    public double getVoto() {
        return voto;
    }

    public void setVoto(double voto) {
        this.voto = voto;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
