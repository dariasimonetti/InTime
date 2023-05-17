package model;

public class OrderBean {
	int id;
	int idCliente;
	double prezzo;
	
	
	public OrderBean(int id, int idCliente, double prezzo) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.prezzo = prezzo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
		

}
