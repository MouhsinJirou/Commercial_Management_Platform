package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.PaiementClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaiementClientRepository extends JpaRepository<PaiementClient, Long>{

	@Query("SELECT p FROM PaiementClient p WHERE p.facture.client.nomClient like :nom  ORDER BY p.date DESC")
    public Page<PaiementClient> chercherPaiementParClient(@Param("nom")String nom,Pageable pageable);

	//Liste des payements d'une facture selon son id
	@Query("SELECT p FROM PaiementClient p WHERE p.facture.idFacture = :id  ORDER BY p.date DESC")
    public List<PaiementClient> chercherPaiementParId(@Param("id")Long id);
	
	//calculer le credit restant
	@Query("SELECT p.facture.montantFacture-SUM(p.montantVerse) from PaiementClient p WHERE p.facture.idFacture = :id ")
	public Long calculerCredit(@Param("id")Long id);
	
	
	
	//total paiement Client
	@Query("SELECT sum(pc.montantVerse) FROM PaiementClient pc WHERE pc.date BETWEEN :debut AND :fin")
	public Double totalPaiementClient(@Param("debut") Date debut,@Param("fin") Date fin);

}
