package org.sid.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;


//To increase speed and save sql statement execution time.
//@DynamicInsert
//@DynamicUpdate
@Entity
public class Produit implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProduit;
	private String refProduit;
	private String designation;
	private String categorie;
	private int seuil;
	private int nivStock;
	private double prixVenteMin;
	
	//Whit this annotation we'll avoid infinite loop probleme 

	// This annotation will spress not found exception
//	@JsonIgnore
//	@NotFound(action=NotFoundAction.IGNORE)
  
	@ManyToOne
	@JoinColumn(name="idDepot")
	  @JsonIgnoreProperties("listProduits")
	private Depot depot;
	

	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="produit")
	//  @JsonIgnoreProperties("produit")
	private Set<Achat> achats=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="produit")
	private Set<DetailAvoirClient> detailsAvoirClient=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	@JsonIgnore
	// This annotation will spress not found exception
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="produit")
	private Set<DetailAvoirFr> detailsAvoirFr=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="produit")
	//  @JsonIgnoreProperties("produit")
	private Set<DetailDevis> detailsDevis=new HashSet<>();
	
	//Whit this annotation we'll avoid infinite loop probleme 
	// This annotation will spress not found exception
	@JsonIgnore
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany (mappedBy="produit")
//	  @JsonIgnoreProperties("produit")
	private Set<DetailFacture> detailFacutres=new HashSet<>();

	public Produit(Long idProduit, String refProduit, String designation, String categorie, int seuil, int nivStock,
			double prixVenteMin) {
		super();
		this.idProduit = idProduit;
		this.refProduit = refProduit;
		this.designation = designation;
		this.categorie = categorie;
		this.seuil = seuil;
		this.nivStock = nivStock;
		this.prixVenteMin= prixVenteMin;
	}
	public Produit(String refProduit, String designation, String categorie, int seuil, int nivStock,
			double prixVenteMin) {
		super();	
		this.refProduit = refProduit;
		this.designation = designation;
		this.categorie = categorie;
		this.seuil = seuil;
		this.nivStock = nivStock;
		this.prixVenteMin= prixVenteMin;
	}
	public Produit() {
		super();
	}
	

	public Produit(Long idProduit, String refProduit, String designation, String categorie, int seuil, int nivStock,
			 double prixVenteMin, Depot depot) {
		super();
		this.idProduit = idProduit;
		this.refProduit = refProduit;
		this.designation = designation;
		this.categorie = categorie;
		this.seuil = seuil;
		this.nivStock = nivStock;
		this.prixVenteMin = prixVenteMin;
		this.depot = depot;
	}
	public Set<Achat> getAchats() {
		return achats;
	}
	public void setAchats(Set<Achat> achats) {
		this.achats = achats;
	}

	public Long getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	public String getRefProduit() {
		return refProduit;
	}
	public void setRefProduit(String refProduit) {
		this.refProduit = refProduit;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public int getSeuil() {
		return seuil;
	}
	public void setSeuil(int seuil) {
		this.seuil = seuil;
	}
	public int getNivStock() {
		return nivStock;
	}
	public void setNivStock(int nivStock) {
		this.nivStock = nivStock;
	}
	
	public Set<DetailAvoirClient> getDetailsAvoirClient() {
		return detailsAvoirClient;
	}
	public void setDetailsAvoirClient(Set<DetailAvoirClient> detailsAvoirClient) {
		this.detailsAvoirClient = detailsAvoirClient;
	}
	public Set<DetailAvoirFr> getDetailsAvoirFr() {
		return detailsAvoirFr;
	}
	public void setDetailsAvoirFr(Set<DetailAvoirFr> detailsAvoirFr) {
		this.detailsAvoirFr = detailsAvoirFr;
	}
	public Set<DetailDevis> getDetailsDevis() {
		return detailsDevis;
	}
	public void setDetailsDevis(Set<DetailDevis> detailsDevis) {
		this.detailsDevis = detailsDevis;
	}
	

	public Set<DetailFacture> getDetailFacutres() {
		return detailFacutres;
	}
	public void setDetailFacutres(Set<DetailFacture> detailFacutres) {
		this.detailFacutres = detailFacutres;
	}
	
	public double getPrixVenteMin() {
		return prixVenteMin;
	}
	public void setPrixVenteMin(double prixVenteMin) {
		this.prixVenteMin = prixVenteMin;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	
}
