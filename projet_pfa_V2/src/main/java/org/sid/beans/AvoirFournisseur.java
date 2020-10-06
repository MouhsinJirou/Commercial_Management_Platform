package org.sid.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class AvoirFournisseur {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAvoirFr;
	@Temporal(TemporalType.DATE)
	private Date dateAvoirFr;
	private double montantAvoirFr;
	
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idBonAchat")
	@JsonIgnoreProperties("avoirsFournisseur")
	private BonAchat bonAchat;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnoreProperties("listAvoirFournisseur")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idUtilisateur")
	private Utilisateur utilisateur;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="avoirFournisseur")
	@JsonIgnoreProperties("avoirFournisseur")
	//@JsonIgnore
	private Set<DetailAvoirFr> detailAvoirFournisseur=new HashSet<>();
	
	public AvoirFournisseur(Long idAvoirFr, Date dateAvoirFr,double montantAvoirFr) {
		super();
		this.idAvoirFr = idAvoirFr;
		this.dateAvoirFr = dateAvoirFr;
		this.montantAvoirFr=montantAvoirFr;
	}
	public AvoirFournisseur(Date dateAvoirFr) {
		super();
		this.dateAvoirFr = dateAvoirFr;
	}
	public AvoirFournisseur() {
		super();
	}
	public Long getIdAvoirFr() {
		return idAvoirFr;
	}
	public void setIdAvoirFr(Long idAvoirFr) {
		this.idAvoirFr = idAvoirFr;
	}
	public Date getDateAvoirFr() {
		return dateAvoirFr;
	}
	public void setDateAvoirFr(Date dateAvoirFr) {
		this.dateAvoirFr = dateAvoirFr;
	}

	
	public double getMontantAvoirFr() {
		return montantAvoirFr;
	}
	public void setMontantAvoirFr(double montantAvoirFr) {
		this.montantAvoirFr = montantAvoirFr;
	}
	public BonAchat getBonAchat() {
		return bonAchat;
	}
	public void setBonAchat(BonAchat bonAchat) {
		this.bonAchat = bonAchat;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Set<DetailAvoirFr> getDetailAvoirFournisseur() {
		return detailAvoirFournisseur;
	}
	public void setDetailAvoirFournisseur(Set<DetailAvoirFr> avoirFournisseur) {
		this.detailAvoirFournisseur = avoirFournisseur;
	}

	
}
