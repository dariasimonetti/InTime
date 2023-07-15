package model;

import java.sql.Date;

public class OrderBean {
	int id;
	int idCliente;
	float prezzo;
	Date dataOrd;
	
	
	public OrderBean(int id, int idCliente, float prezzo, Date dataOrd) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.prezzo = prezzo;
		this.dataOrd = dataOrd;
	}


	public Date getDataOrd() {
		return dataOrd;
	}


	public void setDataOrd(Date dataOrd) {
		this.dataOrd = dataOrd;
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


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
		

}
