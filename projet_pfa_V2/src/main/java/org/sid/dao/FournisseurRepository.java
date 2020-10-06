package org.sid.dao;

import org.sid.beans.Fournisseur;

import java.util.Date;
import java.util.List;

import org.sid.beans.AvoirFournisseur;
import org.sid.beans.BonAchat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long>{

	@Query("SELECT f FROM Fournisseur f WHERE f.nomSociete like :x")
	public Page<Fournisseur> chercherParNomSociete(@Param("x")String mc,Pageable pageable);
	
	@Query("SELECT ba FROM BonAchat ba WHERE ba.fournisseur.idFournisseur = :idFournisseur ORDER BY ba.date DESC")
	public Page<BonAchat> getBonAchatFromFournisseur(@Param("idFournisseur")Long idFournisseur,Pageable pageable);

 

	@Query("SELECT ba FROM BonAchat ba WHERE ba.fournisseur.idFournisseur = :id AND ba.date BETWEEN :debut AND :fin ORDER BY ba.date DESC")
	public Page<BonAchat> bonParFournisseur(@Param("id")Long id,@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);
	
	//avoirs d'un fournisseur
		@Query("SELECT af FROM AvoirFournisseur af WHERE af.bonAchat.fournisseur.idFournisseur = :idFournisseur ORDER BY af.dateAvoirFr DESC")
		public Page<AvoirFournisseur> avoirFournisseurId(@Param("idFournisseur")Long idFournisseur,Pageable pageable);

		@Query("SELECT af FROM AvoirFournisseur af WHERE af.bonAchat.fournisseur.idFournisseur = :id AND af.dateAvoirFr BETWEEN :debut AND :fin ORDER BY af.dateAvoirFr DESC ")
		public Page<AvoirFournisseur> avoirFournisseurDate(@Param("id")Long id,@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);

		//Total Montant bonachat d'un fournisseur
		@Query("SELECT sum(b.montantTotal) FROM BonAchat b WHERE b.fournisseur.idFournisseur = :idFournisseur")
		public Double totalBonAchatsFournisseur(@Param("idFournisseur")Long idFournisseur);
		
		//Total Montant versé d'un fournisseur
		@Query("SELECT sum(p.montantVerse) FROM PaiementFournisseur p WHERE p.bonAchat.fournisseur.idFournisseur = :idFournisseur")
		public Double totalPayementsFournisseur(@Param("idFournisseur")Long idFournisseur);
}


