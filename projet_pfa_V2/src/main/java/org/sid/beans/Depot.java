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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Depot implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDepot;
	private String responsable;
	private String emlpacementString;

	//Whit this annotation we'll avoid infinite loop probleme 
// This annotation will spress not found exception
	//@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="depot")
	  @JsonIgnoreProperties("depot")
	private Set<Produit> listProduits=new HashSet<>();
	
	
	public Depot(Long idDepots, String responsable, String emlpacementString) {
		super();
		this.idDepot = idDepots;
		this.responsable = responsable;
		this.emlpacementString = emlpacementString;
	}
	
	public Depot(String responsable, String emlpacementString) {
		super();
		this.responsable = responsable;
		this.emlpacementString = emlpacementString;
	}
	
	public Depot(String responsable, String emlpacementString, Set<Produit> listProduits) {
		super();
		this.responsable = responsable;
		this.emlpacementString = emlpacementString;
		this.listProduits = listProduits;
	}

	public Depot() {
		super();
	}
	

	
	public Long getIdDepot() {
		return idDepot;
	}
	public void setIdDepot(Long idDepot) {
		this.idDepot = idDepot;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getEmlpacementString() {
		return emlpacementString;
	}
	public void setEmlpacementString(String emlpacementString) {
		this.emlpacementString = emlpacementString;
	}
//	@JsonIgnore
	public Set<Produit> getListProduits() {
		return listProduits;
	}
	public void setListProduits(Set<Produit> listProduits) {
		this.listProduits = listProduits;
	}
}

