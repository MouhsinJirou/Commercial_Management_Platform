package org.sid.beans;

import java.io.Serializable;
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
public class Facture implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFacture;
	@Temporal(TemporalType.DATE)
	private Date  dateFacture;
	private String modePaiement;
	private double montantFacture;
	private String statu;
	
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idClient")
	  @JsonIgnoreProperties("factures")
	private Client client;

	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="facture")
	Set<AvoirClient> listAvoirClient=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	//@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="facture")
	 @JsonIgnoreProperties("facture")
	private Set<DetailFacture> detailFactures=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	//@JsonIgnore
	@JsonIgnoreProperties("facture")
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="facture")
	Set<PaiementClient> paiementClients=new HashSet<>();
	
	
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idUtilisateur")
	 @JsonIgnoreProperties("factures")
	private Utilisateur utilisateur;
	
	
	
	public Facture(Long idFacture, Date dateFacture, String modePaiement, double montantFacture,String statu) {
		super();
		this.idFacture = idFacture;
		this.dateFacture = dateFacture;
		this.modePaiement = modePaiement;
		this.montantFacture = montantFacture;
		this.statu = statu;
	}
	public Date getDateFacture() {
		return dateFacture;
	}
	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}
	public String getModePaiement() {
		return modePaiement;
	}
	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}
	public double getMontantFacture() {
		return montantFacture;
	}
	public void setMontantFacture(double montantFacture) {
		this.montantFacture = montantFacture;
	}
	public Long getIdFacture() {
		return idFacture;
	}
	public void setIdFacture(Long idFacture) {
		this.idFacture = idFacture;
	}
	public Facture(Date dateFacture, String modePaiement, double montantFacture) {
		super();
		this.dateFacture = dateFacture;
		this.modePaiement = modePaiement;
		this.montantFacture = montantFacture;
	}
	public Facture() {
		super();
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<AvoirClient> getListAvoirClient() {
		return listAvoirClient;
	}
	public void setListAvoirClient(Set<AvoirClient> listAvoirClient) {
		this.listAvoirClient = listAvoirClient;
	}
	
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Set<DetailFacture> getDetailFactures() {
		return detailFactures;
	}
	public void setDetailFactures(Set<DetailFacture> detailFactures) {
		this.detailFactures = detailFactures;
	}
	public Set<PaiementClient> getPaiementClients() {
		return paiementClients;
	}
	public void setPaiementClients(Set<PaiementClient> paiementClients) {
		this.paiementClients = paiementClients;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
	
}
