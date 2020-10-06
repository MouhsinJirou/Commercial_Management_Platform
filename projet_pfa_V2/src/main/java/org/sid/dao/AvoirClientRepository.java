package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.AvoirClient;
import org.sid.beans.DetailAvoirClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvoirClientRepository extends JpaRepository<AvoirClient,Long>{
	
	@Query("SELECT a FROM AvoirClient a WHERE a.facture.client.nomClient like :nom  ORDER BY a.dateAvoirClient DESC")
    public Page<AvoirClient> chercherAvoirParClient(@Param("nom")String nom,Pageable pageable);

	@Query("SELECT a.detailsAvoirClient FROM AvoirClient a WHERE a.idAvoirClient = :id")
	public List<DetailAvoirClient> chercherDetailsAvoirClient(@Param("id")Long id);
	
	//total avoir Client
	@Query("SELECT sum(ac.montantAvoirCl) FROM AvoirClient ac WHERE ac.dateAvoirClient BETWEEN :debut AND :fin")
	public Double totalAvoirClient(@Param("debut") Date debut,@Param("fin") Date fin);
}
