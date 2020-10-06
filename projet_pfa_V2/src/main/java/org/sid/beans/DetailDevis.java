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
	public class DetailDevis {
		
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetailDevis;
	
//	//Whit this annotation we'll avoid infinite loop probleme 
 	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idDevis")
	//  @JsonIgnoreProperties("detailsDevis")
	private Devis devis;
	
//	//Whit this annotation we'll avoid infinite loop probleme 
	//@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idProduit")
	@JsonIgnoreProperties("detailsDevis")
	private Produit produit;
	
	private double prix;
	private int qte;
	
	public DetailDevis() {
		super();
	}

	public DetailDevis(Devis devis, Produit produit, double prix, int qte) {
		super();
		this.devis = devis;
		this.produit = produit;
		this.prix = prix;
		this.qte = qte;
	}



	public Long getIdDetailDevis() {
		return idDetailDevis;
	}

	public void setIdDetailDevis(Long idDetailDevis) {
		this.idDetailDevis = idDetailDevis;
	}

	public Devis getDevis() {
		return devis;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
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
