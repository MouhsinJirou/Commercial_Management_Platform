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
public class Achat {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAchat;
    
//	//Whit this annotation we'll avoid infinite loop probleme 
   @JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idBonAchat")
	//  @JsonIgnoreProperties("achats")
	private BonAchat bonAchat;
	
//	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idProduit")
	@JsonIgnoreProperties("achats")
	private Produit produit;
	
	private double prixAchat;
	private int qte_achat;
	
	public Achat() {
		super();
	}

	public Achat(BonAchat bonAchat, Produit produit, double prixAchat, int qte_achat) {
		super();
		this.bonAchat = bonAchat;
		this.produit = produit;
		this.prixAchat = prixAchat;
		this.qte_achat = qte_achat;
	}

	public Long getIdAchat() {
		return idAchat;
	}

	public void setIdAchat(Long idAchat) {
		this.idAchat = idAchat;
	}

	public BonAchat getBonAchat() {
		return bonAchat;
	}

	public void setBonAchat(BonAchat bonAchat) {
		this.bonAchat = bonAchat;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public int getQte_achat() {
		return qte_achat;
	}

	public void setQte_achat(int qte_achat) {
		this.qte_achat = qte_achat;
	}
    
	
	
}
