package org.sid.beans;

import java.io.Serializable;
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
public class PaiementClient implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPaiementClient;
	private Double montantVerse;
	@Temporal(TemporalType.DATE)
	private Date  date;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="idFacture")
	@JsonIgnoreProperties("paiementClients")
	//@JsonIgnore
	private Facture facture;
	
	
	public PaiementClient(Double montantVerse, Date date, Facture facture) {
		super();
		this.montantVerse = montantVerse;
		this.date = date;
		this.facture = facture;
	}
	public PaiementClient() {
		super();
	}

	
	public PaiementClient(Long idPaiementClient, Double montantVerse, Date date, Facture facture) {
		super();
		this.idPaiementClient = idPaiementClient;
		this.montantVerse = montantVerse;
		this.date = date;
		this.facture = facture;
	}
	public Long getIdPaiementClient() {
		return idPaiementClient;
	}
	public void setIdPaiementClient(Long idPaiementClient) {
		this.idPaiementClient = idPaiementClient;
	}

	public Double getMontantVerse() {
		return montantVerse;
	}
	public void setMontantVerse(Double montantVerse) {
		this.montantVerse = montantVerse;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Facture getFacture() {
		return facture;
	}
	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
	
	

}
