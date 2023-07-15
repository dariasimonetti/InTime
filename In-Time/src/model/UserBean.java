package model;
import java.util.*;


public class UserBean {
	private int id;
	private String nome;
	private String cognome;
	private boolean isAdmin;
	private String telefono;
	private String email;
	private String password;
	
	
	
	public UserBean(String nome, String cognome, boolean isAdmin, String telefono, String email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.isAdmin = isAdmin;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}



	public UserBean(int id, String nome, String cognome, boolean isAdmin, String telefono, String email,
			String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.isAdmin = isAdmin;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
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



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getId() {
		return id;
	}
	
	
	
	
}


