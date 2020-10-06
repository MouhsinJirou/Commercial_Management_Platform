package org.sid.dao;



import java.util.List;
import java.util.Optional;

import org.sid.beans.DetailDevis;
import org.sid.beans.Devis;
import org.sid.beans.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DetailDevisRepository extends JpaRepository<DetailDevis,Long>{

	@Query("SELECT idDetailDevis FROM DetailDevis WHERE devis = :idDevis AND produit = :idProduit ")
	public Long findByDevisProduit(@Param("idDevis")Optional<Devis> devis,@Param("idProduit") Optional<Produit> produit);

	@Query("SELECT d.produit.designation,d.prix,d.qte,d.prix*d.qte as total FROM DetailDevis d WHERE d.devis.idDevis = :idDevis")
	public List<Object> getProduitsFromDevis(Long idDevis);

	
	
	



}