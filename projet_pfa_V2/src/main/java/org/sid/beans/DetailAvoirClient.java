package org.sid.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class DetailAvoirClient {
	//association
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetailAvoirClient;
	
	private int qte;
	private double prix;

	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idAvoirClient")
	private AvoirClient avoirClient;
	

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idProduit")
	@JsonIgnoreProperties("detailsAvoirClient")
	private Produit produit;

	
	public DetailAvoirClient() {
		super();
	}

	public DetailAvoirClient(AvoirClient avoirClient, Produit produit, int qte, double prix) {
		super();
		this.avoirClient = avoirClient;
		this.produit = produit;
		this.qte = qte;
		this.prix = prix;
	}

	public Long getIdDetailAvoirClient() {
		return idDetailAvoirClient;
	}

	public void setIdDetailAvoirClient(Long idDetailAvoirClient) {
		this.idDetailAvoirClient = idDetailAvoirClient;
	}

	public AvoirClient getAvoirClient() {
		return avoirClient;
	}

	public void setAvoirClient(AvoirClient avoirClient) {
		this.avoirClient = avoirClient;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
}