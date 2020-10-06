package org.sid.dao;


import java.util.List;

import org.sid.beans.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProduitRepository extends JpaRepository<Produit, Long>{

	    @Query("SELECT p FROM Produit p WHERE p.designation like :x or p.refProduit like :x")
	    public Page<Produit> chercherParDesignation(@Param("x")String mc,Pageable pageable);
	
	    //alert des produits
		@Query("SELECT p FROM Produit p WHERE p.nivStock<p.seuil AND p.designation like :x")
		public Page<Produit> alerterProduit(@Param("x")String mc,Pageable pageable);
		
		@Query("SELECT count(p) FROM Produit p WHERE p.nivStock<p.seuil")
        public int nombreAlerteProduit();
		
		@Query("SELECT count(p) FROM Produit p WHERE p.nivStock=0")
		public int nombreProduitRupture();
		
		@Query("select p.nivStock FROM Produit p WHERE p.idProduit = :id")
		public int findQte(@Param("id")Long idProduitLong);
		
		//nombre de produits
		@Query("select count(p) from Produit p")
		public int nombreProduit();

		@Transactional
		@Modifying
		@Query("update Produit p set p.nivStock = :stock where p.idProduit = :id")
		public void newQte(@Param("id")Long idProduitLong, @Param("stock")int newQte);
		
}
