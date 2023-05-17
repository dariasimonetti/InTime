package model;

public class ProductBean {
    private int id;
    private String Nome;
    private String Descrizione;
    private float  Prezzo;
    private String Materiale;
    private String Misura;
    private String Marca;
    private String Genere;
    private String Tipo;
    private float Sconto;
    private int Quantita;
    
    
   

	public ProductBean(int id, String nome, String descrizione, float prezzo, String materiale, String misura,
            String marca, String genere,String tipo, float sconto, int quantita) {
        this.id = id;
        Nome = nome;
        Descrizione = descrizione;
        Prezzo = prezzo;
        Materiale = materiale;
        Misura = misura;
        Marca = marca;
        Genere = genere;
        Tipo =tipo;
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
    public String getMisura() {
        return Misura;
    }
    public void setMisura(String misura) {
        Misura = misura;
    }
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public String getGenere() {
        return Genere;
    }
    public void setGenere(String genere) {
        Genere = genere;
    }
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String tipo) {
        Tipo = tipo;
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
