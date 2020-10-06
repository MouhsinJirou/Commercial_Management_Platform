package org.sid.dao;

import java.util.List;

import org.sid.beans.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AchatRepository extends JpaRepository<Achat,Long>{

	@Query("SELECT a.produit.designation,a.prixAchat,a.qte_achat,a.prixAchat*a.qte_achat as total FROM Achat a WHERE a.bonAchat.idBonAchat = :idBonAchat")
	public List<Object> getProduitsFromBonAchat(Long idBonAchat);
	//Prix d'achat moyenne
		@Query("select sum(a.prixAchat*a.qte_achat)/sum(a.qte_achat) FROM Achat a where a.produit.idProduit=:id")
		public Double getPrixAchat(Long id);
		
		//prix achat total d'un produit
		@Query("select sum(a.prixAchat*a.qte_achat) FROM Achat a where a.produit.idProduit=:id")
		public Double prixAchatTotal(Long id);
		
		//produits d'un bon qui retourne un bon
		@Query("SELECT a.produit,a.prixAchat,a.qte_achat,a.prixAchat*a.qte_achat as total FROM Achat a WHERE a.bonAchat.idBonAchat = :idBonAchat")
		public List<Object> getProduitsFromBonAchat2(Long idBonAchat);
}