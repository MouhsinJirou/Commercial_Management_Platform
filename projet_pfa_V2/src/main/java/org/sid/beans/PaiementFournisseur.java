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
public class PaiementFournisseur {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPaiementFournisseur;
	private double montantVerse;
	@Temporal(TemporalType.DATE)
	private Date date;

	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idBonAchat")
	  @JsonIgnoreProperties("paiementFournisseurs")
	private BonAchat bonAchat;




	public PaiementFournisseur(double montantVerse, Date date, BonAchat bonAchat) {
		super();
		this.montantVerse = montantVerse;
		this.date = date;
		this.bonAchat = bonAchat;
	}


	public PaiementFournisseur(Long idPaiementFournisseur, double montantVerse, Date date, BonAchat bonAchat) {
		super();
		this.idPaiementFournisseur = idPaiementFournisseur;
		this.montantVerse = montantVerse;
		this.date = date;
		this.bonAchat = bonAchat;
	}


	public PaiementFournisseur() {
		super();
	}




	public Long getIdPaiementFournisseur() {
		return idPaiementFournisseur;
	}


	public void setIdPaiementFournisseur(Long idPaiementFournisseur) {
		this.idPaiementFournisseur = idPaiementFournisseur;
	}


	public double getMontantVerse() {
		return montantVerse;
	}


	public void setMontantVerse(double montantVerse) {
		this.montantVerse = montantVerse;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public BonAchat getBonAchat() {
		return bonAchat;
	}


	public void setBonAchat(BonAchat bonAchat) {
		this.bonAchat = bonAchat;
	}
	
	
	

	
	
}
