package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.AvoirFournisseur;
import org.sid.beans.DetailAvoirFr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvoirFournisseurRepository extends JpaRepository<AvoirFournisseur,Long>{

	@Query("SELECT a FROM AvoirFournisseur a WHERE a.bonAchat.fournisseur.nomSociete like :nom  ORDER BY a.dateAvoirFr DESC")
    public Page<AvoirFournisseur> chercherAvoirParFournisseur(@Param("nom")String nom,Pageable pageable);
	
	@Query("SELECT a.detailAvoirFournisseur FROM AvoirFournisseur a WHERE a.idAvoirFr = :id")
	public List<DetailAvoirFr> chercherDetailAvoirFournisseur(@Param("id")Long id);
	
	//total avoir Fournisseur
	@Query("SELECT sum(af.montantAvoirFr) FROM AvoirFournisseur af WHERE af.dateAvoirFr BETWEEN :debut AND :fin")
	public Double totalAvoirFournisseur(@Param("debut") Date debut,@Param("fin") Date fin);
}
