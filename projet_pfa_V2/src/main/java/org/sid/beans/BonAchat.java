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
public class BonAchat {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idBonAchat;
	@Temporal(TemporalType.DATE)
	private Date date;
	private double montantTotal;
	private String modePaiement;
	private String statu;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnoreProperties("bonsAchat")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idFournisseur")
	private Fournisseur fournisseur;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnoreProperties("bonsAchat")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idUtilisateur")
	private Utilisateur utilisateur;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	//@JsonIgnoreProperties("bonAchat")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="bonAchat")
	@JsonIgnoreProperties("bonAchat")
	private Set<Achat> achats=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="idAvoirFr")
	Set<AvoirFournisseur> avoirsFournisseur=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="bonAchat")
	Set<PaiementFournisseur> paiementFournisseurs=new HashSet<>();
	
	
	
	public BonAchat(Long idBonAchat, Date date, double montantTotal, String modePaiement,String statu) {
		super();
		this.idBonAchat = idBonAchat;
		this.date = date;
		this.montantTotal = montantTotal;
		this.modePaiement = modePaiement;
		this.statu = statu;
		
	}
	public Long getIdBonAchat() {
		return idBonAchat;
	}
	public void setIdBonAchat(Long idBonAchat) {
		this.idBonAchat = idBonAchat;
	}
	public Set<AvoirFournisseur> getAvoirsFournisseur() {
		return avoirsFournisseur;
	}
	public void setAvoirsFournisseur(Set<AvoirFournisseur> avoirsFournisseur) {
		this.avoirsFournisseur = avoirsFournisseur;
	}
	public BonAchat() {
		super();
	}
	public BonAchat(Date date, double montantTotal, String modePaiement) {
		super();
		this.date = date;
		this.montantTotal = montantTotal;
		this.modePaiement = modePaiement;
	}
	
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public Set<Achat> getAchats() {
		return achats;
	}
	public void setAchats(Set<Achat> achats) {
		this.achats = achats;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontantTotal() {
		return montantTotal;
	}
	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}
	public String getModePaiement() {
		return modePaiement;
	}
	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}
	
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Set<PaiementFournisseur> getPaiementFournisseurs() {
		return paiementFournisseurs;
	}
	public void setPaiementFournisseurs(Set<PaiementFournisseur> paiementFournisseurs) {
		this.paiementFournisseurs = paiementFournisseurs;
	}
	
	
}