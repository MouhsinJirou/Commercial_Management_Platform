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
public class Charge {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCharge;
	private String libelle;
	private String type;
	@Temporal(TemporalType.DATE)
	private Date date;
	private double montant;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnoreProperties("charges")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idUtilisateur")
	private Utilisateur utilisateur;
	
	public Long getIdCharge() {
		return idCharge;
	}
	public void setIdCharge(Long idCharge) {
		this.idCharge = idCharge;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Charge(Long idCharge, String libelle, String type, Date date, double montant) {
		super();
		this.idCharge = idCharge;
		this.libelle = libelle;
		this.type = type;
		this.date = date;
		this.montant = montant;
	}
	public Charge() {
		super();
	}
	public Charge(String libelle, String type, Date date, double montant) {
		super();
		this.libelle = libelle;
		this.type = type;
		this.date = date;
		this.montant = montant;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
		
}
