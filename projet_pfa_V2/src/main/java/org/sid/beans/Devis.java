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
public class Devis implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDevis;
	@Temporal(TemporalType.DATE)
	private Date  dateDevis;
	private double montantDevis;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne 
	@JoinColumn(name="idClient")
	  @JsonIgnoreProperties("listDevis")
	private Client client;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	//@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="devis")
   @JsonIgnoreProperties("devis")
	private Set<DetailDevis> detailsDevis=new HashSet<>();
	
	public Devis(Long idDevis, Date dateDevis, double montantDevis) {
		super();
		this.idDevis = idDevis;
		this.dateDevis = dateDevis;
		this.montantDevis = montantDevis;
	}
	public Devis(Date dateDevis, double montantDevis) {
		super();
		this.dateDevis = dateDevis;
		this.montantDevis = montantDevis;
	}
	public Devis() {
		super();
	}
	public Long getIdDevis() {
		return idDevis;
	}
	public void setIdDevis(Long idDevis) {
		this.idDevis = idDevis;
	}
	public Date getDateDevis() {
		return dateDevis;
	}
	public void setDateDevis(Date dateDevis) {
		this.dateDevis = dateDevis;
	}
	public double getMontantDevis() {
		return montantDevis;
	}
	public void setMontantDevis(double montantDevis) {
		this.montantDevis = montantDevis;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<DetailDevis> getDetailsDevis() {
		return detailsDevis;
	}
	public void setDetailsDevis(Set<DetailDevis> detailsDevis) {
		this.detailsDevis = detailsDevis;
	}

}

