package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.AvoirClient;
import org.sid.beans.Client;
import org.sid.beans.Facture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Long>{

	@Query("SELECT p FROM Client p WHERE p.nomClient like :nom or p.prenomClient like :nom")
	public Page<Client> chercherParNom(@Param("nom")String mc,Pageable pageable);
	
	@Query("SELECT c.factures FROM Client c WHERE c.idClient=:id")
	public List<Facture> factureParClient(@Param("id")Long id);
	
	@Query("SELECT f FROM Facture f WHERE f.client.idClient = :idClient ORDER BY f.dateFacture DESC")
	public Page<Facture> factureClientId(@Param("idClient")Long idClient,Pageable pageable);

 

	@Query("SELECT f FROM Facture f WHERE f.client.idClient = :id AND f.dateFacture BETWEEN :debut AND :fin ORDER BY f.dateFacture DESC")
	public Page<Facture> factureClientDate(@Param("id")Long id,@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);
	
	//Les avoirs d'un client
	
		
	@Query("SELECT ac FROM AvoirClient ac WHERE ac.facture.client.idClient = :idClient ORDER BY ac.dateAvoirClient DESC")
			public Page<AvoirClient> avoirClientId(@Param("idClient")Long idClient,Pageable pageable);
			
	@Query("SELECT ac FROM AvoirClient ac WHERE ac.facture.client.idClient = :id AND ac.dateAvoirClient BETWEEN :debut AND :fin ORDER BY ac.dateAvoirClient DESC")
			public Page<AvoirClient> avoirClientDate(@Param("id")Long id,@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);

	
	//Total Montant factures d'un client
	@Query("SELECT sum(f.montantFacture) FROM Facture f WHERE f.client.idClient = :idClient")
	public Double totalFacturesClient(@Param("idClient")Long idClient);
	
	//Total Montant versé d'un client
	@Query("SELECT sum(p.montantVerse) FROM PaiementClient p WHERE p.facture.client.idClient = :idClient")
	public Double totalPayementsClient(@Param("idClient")Long idClient);
	
	//Clients du mois
	@Query("SELECT f.client,sum(f.montantFacture) FROM Facture f WHERE f.dateFacture BETWEEN :debut AND :fin GROUP BY f.client ORDER BY sum(f.montantFacture) DESC")
	public Page<Object> clientDuMois(@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);

}