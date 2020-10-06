package org.sid.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class DetailAvoirFr {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetailAvoirFr;
	
	private int qte;
	private double prix;
//	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idAvoirFr")
	private AvoirFournisseur avoirFournisseur;
	
//	//Whit this annotation we'll avoid infinite loop probleme 
    @JsonIgnoreProperties("detailsAvoirFr")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idProduit")
	private Produit produit;

	
	public DetailAvoirFr() {
		super();
	}

	public DetailAvoirFr(AvoirFournisseur avoirFournisseur, Produit produit, int qte, double prix) {
		super();
		this.avoirFournisseur = avoirFournisseur;
		this.produit = produit;
		this.qte = qte;
		this.prix = prix;
	}

	public Long getIdDetailAvoirFr() {
		return idDetailAvoirFr;
	}

	public void setIdDetailAvoirFr(Long idDetailAvoirFr) {
		this.idDetailAvoirFr = idDetailAvoirFr;
	}

	public AvoirFournisseur getAvoirFournisseur() {
		return avoirFournisseur;
	}

	public void setAvoirFournisseur(AvoirFournisseur avoirFournisseur) {
		this.avoirFournisseur = avoirFournisseur;
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