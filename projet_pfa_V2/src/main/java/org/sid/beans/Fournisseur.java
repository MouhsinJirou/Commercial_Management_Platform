package org.sid.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "idFournisseur")
public class Fournisseur {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFournisseur;
	private String nomSociete;
	private String adresse;
	private String tel;
	private String email;
	

    @JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany(mappedBy="fournisseur")
	Set<BonAchat> bonsAchat=new HashSet<>();
	
//	@OneToMany(mappedBy="fournisseur")
//	Set<AvoirFournisseur> avoirsFournisseur=new HashSet<>();
	
	public Long getIdFournisseur() {
		return idFournisseur;
	}
	public void setIdFournisseur(Long idFournisseur) {
		this.idFournisseur = idFournisseur;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
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
	public Fournisseur(Long idFournisseur, String nomSociete, String adresse, String tel, String email) {
		super();
		this.idFournisseur = idFournisseur;
		this.nomSociete = nomSociete;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}
	public Fournisseur(String nomSociete, String adresse, String tel, String email) {
		super();
		this.nomSociete = nomSociete;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}
	public Fournisseur() {
		super();
	}

	public Set<BonAchat> getBonsAchat() {
		return bonsAchat;
	}
	public void setBonsAchat(Set<BonAchat> bonsAchat) {
		this.bonsAchat = bonsAchat;
	}

	
}