package org.sid.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Utilisateur implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUtilisateur;
	private String nomUtilisateur;
	private String prenomUtilisateur;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<AppRole> roles=new HashSet<>();
	
//	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="utilisateur")
	 Set<Charge> charges=new HashSet<>();
	
//	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)	
	@OneToMany(mappedBy="utilisateur")
	Set<AvoirClient> listAvoirClient=new HashSet<>();
	
		
//	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)	
	@OneToMany(mappedBy="utilisateur")
	Set<AvoirFournisseur> listAvoirFournisseur=new HashSet<>();
	
		
//	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="utilisateur")
	Set<BonAchat> bonsAchat=new HashSet<>();
	
	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="utilisateur")
	Set<Facture> factures=new HashSet<>();
	
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Utilisateur(String nomUtilisateur, String prenomUtilisateur, String username, String password,
			String email) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public Utilisateur(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String username,
			String password, String email, Set<AppRole> roles, Set<Charge> charges, Set<AvoirClient> listAvoirClient,
			Set<AvoirFournisseur> listAvoirFournisseur, Set<BonAchat> bonsAchat, Set<Facture> factures) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.charges = charges;
		this.listAvoirClient = listAvoirClient;
		this.listAvoirFournisseur = listAvoirFournisseur;
		this.bonsAchat = bonsAchat;
		this.factures = factures;
	}
	public Utilisateur(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String username,
			String password, String email, Set<AppRole> roles) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
	public Utilisateur() {
		super();
	}
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}
	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Charge> getCharges() {
		return charges;
	}
	public void setCharges(Set<Charge> charges) {
		this.charges = charges;
	}
	public Set<AvoirClient> getListAvoirClient() {
		return listAvoirClient;
	}
	public void setListAvoirClient(Set<AvoirClient> listAvoirClient) {
		this.listAvoirClient = listAvoirClient;
	}
	public Set<AvoirFournisseur> getListAvoirFournisseur() {
		return listAvoirFournisseur;
	}
	public void setListAvoirFournisseur(Set<AvoirFournisseur> listAvoirFournisseur) {
		this.listAvoirFournisseur = listAvoirFournisseur;
	}
	
	public Set<BonAchat> getBonsAchat() {
		return bonsAchat;
	}
	public void setBonsAchat(Set<BonAchat> bonsAchat) {
		this.bonsAchat = bonsAchat;
	}
	public Set<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<AppRole> roles) {
		this.roles = roles;
	}
	public Set<Facture> getFactures() {
		return factures;
	}
	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}
}
