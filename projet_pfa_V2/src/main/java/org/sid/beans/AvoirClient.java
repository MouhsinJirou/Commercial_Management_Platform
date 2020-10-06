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
public class AvoirClient{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAvoirClient;
	@Temporal(TemporalType.DATE)
	private Date  dateAvoirClient;
	private double montantAvoirCl;
	
	
	//Whit this annotation we'll avoid infinite loop probleme 
	//@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idFacture")
	@JsonIgnoreProperties("listAvoirClient")
	private Facture facture;
	
	
	//Whit this annotation we'll avoid infinite loop probleme 
	 @JsonIgnoreProperties("listAvoirClient")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idUtilisateur")
	private Utilisateur utilisateur;
	
	
	//Whit this annotation we'll avoid infinite loop probleme 
	//@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="avoirClient")
	@JsonIgnoreProperties("avoirClient")
	private Set<DetailAvoirClient> detailsAvoirClient=new HashSet<>();
	
	public AvoirClient(Long idAvoirClient, Date dateAvoirClient,double montantAvoirCl) {
		super();
		this.idAvoirClient = idAvoirClient;
		this.dateAvoirClient = dateAvoirClient;
		this.montantAvoirCl=montantAvoirCl;
	}
	public AvoirClient(Date dateAvoirClient, String motif) {
		super();
		this.dateAvoirClient = dateAvoirClient;
	}
	public AvoirClient() {
		super();
	}
	public Long getIdAvoirClient() {
		return idAvoirClient;
	}
	public void setIdAvoirClient(Long idAvoirClient) {
		this.idAvoirClient = idAvoirClient;
	}
	public Date getDateAvoirClient() {
		return dateAvoirClient;
	}
	public void setDateAvoirClient(Date dateAvoirClient) {
		this.dateAvoirClient = dateAvoirClient;
	}

	public Facture getFacture() {
		return facture;
	}
	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public double getMontantAvoirCl() {
		return montantAvoirCl;
	}
	public void setMontantAvoirCl(double montantAvoirCl) {
		this.montantAvoirCl = montantAvoirCl;
	}
	public Set<DetailAvoirClient> getDetailsAvoirClient() {
		return detailsAvoirClient;
	}
	public void setDetailsAvoirClient(Set<DetailAvoirClient> detailsAvoirClient) {
		this.detailsAvoirClient = detailsAvoirClient;
	}
}
