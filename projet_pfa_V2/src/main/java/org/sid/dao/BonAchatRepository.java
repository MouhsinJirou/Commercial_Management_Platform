package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.BonAchat;
import org.sid.beans.Depot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BonAchatRepository extends JpaRepository<BonAchat,Long>{


	@Query("SELECT a FROM BonAchat a ORDER BY a.date DESC")
	public Page<BonAchat> chercherAllAchat(Pageable pageable);
	
	@Query("SELECT sum(b.montantTotal) FROM BonAchat b")
	public Double totalAchat();
	
	@Query("SELECT sum(b.montantTotal) FROM BonAchat b WHERE b.date BETWEEN :debut AND :fin")
	public Double totalAchatDate(@Param("debut") Date debut,@Param("fin") Date fin);
	
	@Query("SELECT count(b) FROM BonAchat b WHERE b.date BETWEEN :debut AND :fin")
	public int nombreAchatDate(@Param("debut") Date debut,@Param("fin") Date fin);

	
	//Achat jour
		@Query("SELECT date,SUM(montantTotal) as total,(select SUM(montantTotal) from BonAchat ba WHERE ba.date BETWEEN :debut AND :fin ) FROM BonAchat ba WHERE ba.date BETWEEN :debut AND :fin GROUP BY ba.date ORDER BY ba.date ASC")
		public List<Object> achatJour(@Param("debut") Date debut,@Param("fin") Date fin);
		//Rapport des mois
		@Query("SELECT YEAR(date),MONTH(date),SUM(montantTotal) as total,(select SUM(montantTotal) from BonAchat ba WHERE ba.date >= :debut AND ba.date< :fin ) FROM BonAchat ba WHERE ba.date >= :debut AND ba.date< :fin GROUP BY 1,2 ORDER BY ba.date ASC")
		public List<Object> achatMois(@Param("debut") Date debut,@Param("fin") Date fin);
		//traçabilité
		@Query("SELECT c.utilisateur.nomUtilisateur,c.utilisateur.prenomUtilisateur from BonAchat c WHERE c.idBonAchat=:id")
		public List<Object> tracerBon(@Param("id")Long id);
}
