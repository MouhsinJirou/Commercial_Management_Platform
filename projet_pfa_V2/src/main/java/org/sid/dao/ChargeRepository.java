package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.Charge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChargeRepository extends JpaRepository<Charge,Long>{

	@Query("SELECT c FROM Charge c WHERE c.libelle like :x ORDER BY c.date DESC")
	public Page<Charge> chercherParLibelle(@Param("x")String mc,Pageable pageable);
	
	@Query("SELECT sum(c.montant) FROM Charge c")
	public Double totalCharge();
	
	@Query("SELECT sum(c.montant) FROM Charge c WHERE c.date BETWEEN :debut AND :fin")
	public Double totalChargeDate(@Param("debut") Date debut,@Param("fin") Date fin);
	
	@Query("SELECT count(c) FROM Charge c WHERE c.date BETWEEN :debut AND :fin")
	public int nombreChargeDate(@Param("debut") Date debut,@Param("fin") Date fin);
	
	@Query("SELECT c FROM Charge c WHERE c.date BETWEEN :debut AND :fin")
	public Page<Charge> chercherParDate(@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);
	
	//charge du jour
		@Query("SELECT date,SUM(montant) as total,(select SUM(montant) from Charge c WHERE c.date BETWEEN :debut AND :fin ) FROM Charge c WHERE c.date BETWEEN :debut AND :fin GROUP BY c.date ORDER BY c.date ASC")
		public List<Object> chargeJour(@Param("debut") Date debut,@Param("fin") Date fin);

		//charge du mois
		@Query("SELECT YEAR(date),MONTH(date),SUM(montant) as total,(select SUM(montant) from Charge c WHERE c.date >= :debut AND c.date< :fin ) FROM Charge c WHERE c.date >= :debut AND c.date< :fin GROUP BY 1,2 ORDER BY c.date ASC")
		public List<Object> chargeMois(@Param("debut") Date debut,@Param("fin") Date fin);
		//traçabilité
				@Query("SELECT c.utilisateur.nomUtilisateur,c.utilisateur.prenomUtilisateur from Charge c WHERE c.idCharge=:id")
				public List<Object> tracerCharge(@Param("id")Long id);
}