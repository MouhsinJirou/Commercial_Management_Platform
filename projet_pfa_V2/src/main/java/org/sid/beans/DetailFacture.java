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
public class DetailFacture {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetailFacture;

//	//Whit this annotation we'll avoid infinite loop probleme 
    @JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idFacture")
//	  @JsonIgnoreProperties("detailFactures")
	private Facture facture;
	
//	//Whit this annotation we'll avoid infinite loop probleme 
    //@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idProduit")
    @JsonIgnoreProperties("detailFacutres")
	private Produit produit;
	
	private double prix;
	private int qte;
	
	
	public DetailFacture() {
		super();
	}


	public DetailFacture(Facture facture, Produit produit, double prix, int qte) {
		super();
		this.facture = facture;
		this.produit = produit;
		this.prix = prix;
		this.qte = qte;
	}


	public Long getIdDetailFacture() {
		return idDetailFacture;
	}


	public void setIdDetailFacture(Long idDetailFacture) {
		this.idDetailFacture = idDetailFacture;
	}


	public Facture getFacture() {
		return facture;
	}


	public void setFacture(Facture facture) {
		this.facture = facture;
	}


	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}
	
	


}
