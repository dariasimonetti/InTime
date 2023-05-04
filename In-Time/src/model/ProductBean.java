package model;

public class ProductBean {
    private int id;
    private String Nome;
    private String Descrizione;
    private float  Prezzo;
    private String Materiale;
    private int Misura;
    private String Marca;
    private int Uomo;
    private int Donna;
    private int Quadrante;
    private int Cinturino;
    private float Sconto;
    private int Quantita;
    public ProductBean() {
           
    }
    

    @Override
	public String toString() {
		return "ProductBean [Nome=" + Nome + ", Descrizione=" + Descrizione + ", Quadrante=" + Quadrante + "]";
	}


	public ProductBean(String nome, String descrizione, int quadrante) {
		super();
		Nome = nome;
		Descrizione = descrizione;
		Quadrante = quadrante;
	}


	public ProductBean(int id, String nome, String descrizione, float prezzo, String materiale, int misura,
            String marca, int uomo, int donna, int quadrante, int cinturino, float sconto, int quantita) {
        this.id = id;
        Nome = nome;
        Descrizione = descrizione;
        Prezzo = prezzo;
        Materiale = materiale;
        Misura = misura;
        Marca = marca;
        Uomo = uomo;
        Donna = donna;
        Quadrante = quadrante;
        Cinturino = cinturino;
        Sconto = sconto;
        Quantita = quantita;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getDescrizione() {
        return Descrizione;
    }
    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }
    public float getPrezzo() {
        return Prezzo;
    }
    public void setPrezzo(float prezzo) {
        Prezzo = prezzo;
    }
    public String getMateriale() {
        return Materiale;
    }
    public void setMateriale(String materiale) {
        Materiale = materiale;
    }
    public int getMisura() {
        return Misura;
    }
    public void setMisura(int misura) {
        Misura = misura;
    }
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public int getUomo() {
        return Uomo;
    }
    public void setUomo(int uomo) {
        Uomo = uomo;
    }
    public int getDonna() {
        return Donna;
    }
    public void setDonna(int donna) {
        Donna = donna;
    }
    public int getQuadrante() {
        return Quadrante;
    }
    public void setQuadrante(int quadrante) {
        Quadrante = quadrante;
    }
    public int getCinturino() {
        return Cinturino;
    }
    public void setCinturino(int cinturino) {
        Cinturino = cinturino;
    }
    public float getSconto() {
        return Sconto;
    }
    public void setSconto(float sconto) {
        Sconto = sconto;
    }
    public int getQuantita() {
        return Quantita;
    }
    public void setQuantita(int quantita) {
        Quantita = quantita;
    }
     
    
    
}
