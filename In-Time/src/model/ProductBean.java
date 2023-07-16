package model;

public class ProductBean {
    private int id;
    private String nome;
    private String descrizione;
    private float  prezzo;
    private String materiale;
    private String misura;
    private String marca;
    private String genere;
    private String tipo;
    private float sconto;
    private int quantita;
    
    
   

	public ProductBean(int id, String nome, String descrizione, float prezzo, String materiale, String misura,
            String marca, String genere,String tipo, float sconto, int quantita) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.materiale = materiale;
        this.misura = misura;
        this.marca = marca;
        this.genere = genere;
        this.tipo =tipo;
        this.sconto = sconto;
        this.quantita = quantita;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public float getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    public String getMateriale() {
        return materiale;
    }
    public void setMateriale(String materiale) {
        this.materiale = materiale;
    }
    public String getMisura() {
        return misura;
    }
    public void setMisura(String misura) {
        this.misura = misura;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getGenere() {
        return genere;
    }
    public void setGenere(String genere) {
        this.genere = genere;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    public float getSconto() {
        return sconto;
    }
    public void setSconto(float sconto) {
        this.sconto = sconto;
    }
    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
     
    
    
}
