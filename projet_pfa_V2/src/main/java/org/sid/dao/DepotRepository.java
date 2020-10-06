package org.sid.dao;


import java.util.Optional;

import org.sid.beans.Depot;
import org.sid.beans.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepotRepository extends JpaRepository<Depot,Long>{

	@Query("SELECT p FROM Depot p WHERE p.emlpacementString like :emp")
	public Page<Depot> chercherParEmplacement(@Param("emp")String mc,Pageable pageable);
	
	@Query("SELECT p FROM Produit p WHERE p.designation like :designation AND p.depot.idDepot = :id")
	public Page<Produit> getProduitsByDesignation(@Param("id")Long id,@Param("designation")String categorie,Pageable pageable);

	@Query("SELECT p from Produit p WHERE p.depot.idDepot = :id")
	public Page<Produit> getProduitsFromDepot(@Param("id")Long id,Pageable pageable);
	
	
}
