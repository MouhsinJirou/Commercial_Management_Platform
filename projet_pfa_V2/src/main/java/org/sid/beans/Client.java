package org.sid.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Client implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idClient;
	private String nomClient;
	private String  prenomClient;
	private String adresse;
	private String tel;
	private String email;
	
	//Whit this annotation we'll avoid infinite loop probleme 
	
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	//  @JsonIgnoreProperties("client")
	@JsonIgnore
	Set<Devis> listDevis=new HashSet<>();
	
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="client")
	@JsonIgnore
	Set<Facture> factures=new HashSet<>();
	
//	@OneToMany(mappedBy="client")
//	Set<AvoirClient> listAvoirClient=new HashSet<>();
	
	public Client(Long idClient, String nomClient, String prenomClient, String adresse, String tel, String email) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}
	public Client(String nomClient, String prenomClient, String adresse, String tel, String email) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}
	public Client() {
		super();
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Devis> getListDevis() {
		return listDevis;
	}
	public void setListDevis(Set<Devis> listDevis) {
		this.listDevis = listDevis;
	}
	public Set<Facture> getFactures() {
		return factures;
	}
	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	
		
}

