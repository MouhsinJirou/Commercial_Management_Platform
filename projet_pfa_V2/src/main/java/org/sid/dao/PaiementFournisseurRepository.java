package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.PaiementFournisseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaiementFournisseurRepository extends JpaRepository<PaiementFournisseur, Long>{
	@Query("SELECT p FROM PaiementFournisseur p WHERE p.bonAchat.fournisseur.nomSociete like :nom ORDER BY p.date DESC")
    public Page<PaiementFournisseur> chercherPaiementParFournisseur(@Param("nom")String nom,Pageable pageable);
	
	@Query("SELECT p FROM PaiementFournisseur p WHERE p.bonAchat.idBonAchat = :idBonAchat ORDER BY p.date DESC")
    public List<PaiementFournisseur> chercherPaiementParBon(@Param("idBonAchat")Long idBonAchat);
	
	@Query("SELECT sum(p.montantVerse) FROM PaiementFournisseur p WHERE p.bonAchat.idBonAchat = :idBonAchat")
	public Long totalPaiementsBon(@Param("idBonAchat")Long idBonAchat);
	
	//total paiement Fournisseur
		@Query("SELECT sum(pf.montantVerse) FROM PaiementFournisseur pf WHERE pf.date BETWEEN :debut AND :fin")
		public Double totalPaiementFournisseur(@Param("debut") Date debut,@Param("fin") Date fin);

}
