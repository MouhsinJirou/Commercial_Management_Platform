package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.beans.BonAchat;
import org.sid.beans.Facture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FactureRepository extends JpaRepository<Facture,Long>{
	@Query("SELECT f FROM Facture f ORDER BY f.dateFacture DESC")
	public Page<Facture> chercherAllFacture(Pageable pageable);
	
	@Query("SELECT sum(f.montantFacture) FROM Facture f")
	public Double totalVente();
	
	@Query("SELECT sum(f.montantFacture) FROM Facture f WHERE f.dateFacture BETWEEN :debut AND :fin")
	public Double totalVenteDate(@Param("debut") Date debut,@Param("fin") Date fin);
	
	@Query("SELECT count(f) FROM Facture f WHERE f.dateFacture BETWEEN :debut AND :fin")
	public int nombreVenteDate(@Param("debut") Date debut,@Param("fin") Date fin);
	
	@Query("SELECT d.produit,sum(d.qte),sum(d.prix*d.qte) FROM DetailFacture d WHERE d.facture.dateFacture BETWEEN :debut AND :fin GROUP BY d.produit.designation")
	public Page<Object> rapportVente(@Param("debut") Date debut,@Param("fin") Date fin,Pageable pageable);
	
	//rapport du jour
			@Query("SELECT dateFacture,SUM(montantFacture) as total,(select SUM(montantFacture) from Facture f WHERE f.dateFacture BETWEEN :debut AND :fin ) FROM Facture f WHERE f.dateFacture BETWEEN :debut AND :fin GROUP BY f.dateFacture ORDER BY f.dateFacture ASC")
			public List<Object> venteJour(@Param("debut") Date debut,@Param("fin") Date fin);

			//rappot du mois
			@Query("SELECT YEAR(dateFacture),MONTH(dateFacture),SUM(montantFacture) as total,(select SUM(montantFacture) from Facture f WHERE f.dateFacture >= :debut AND f.dateFacture<:fin ) FROM Facture f WHERE f.dateFacture >= :debut AND f.dateFacture<:fin GROUP BY 1,2 ORDER BY f.dateFacture ASC")
			public List<Object> venteMois(@Param("debut") Date debut,@Param("fin") Date fin);
			//traçabilité
			@Query("SELECT c.utilisateur.nomUtilisateur,c.utilisateur.prenomUtilisateur from Facture c WHERE c.idFacture=:id")
			public List<Object> tracerFacture(@Param("id")Long id);
}
