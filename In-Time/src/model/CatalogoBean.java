package model;

public class CatalogoBean {
     private int Id;
     private String Nome;
     private float Prezzo;
     
     
     
	public CatalogoBean(int id, String nome, float prezzo) {
		Id = id;
		Nome = nome;
		Prezzo = prezzo;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public float getPrezzo() {
		return Prezzo;
	}
	public void setPrezzo(float prezzo) {
		Prezzo = prezzo;
	}
     
     
}