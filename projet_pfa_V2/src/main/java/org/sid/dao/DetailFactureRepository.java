package org.sid.dao;

import java.util.List;

import org.sid.beans.DetailFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DetailFactureRepository extends JpaRepository<DetailFacture, Long>{

	@Query("SELECT df.produit.designation,df.prix,df.qte,df.prix*df.qte as total FROM DetailFacture df WHERE df.facture.idFacture = :idFacture")
	public List<Object> getProduitsFromFacture(Long idFacture);
	
	//detail qui retourne le produit aussi (utilisé dans AjouterAvoirClient)
		@Query("SELECT df.produit,df.prix,df.qte,df.prix*df.qte as total FROM DetailFacture df WHERE df.facture.idFacture = :idFacture")
		public List<Object> getProduitsFromFacture2(Long idFacture);
}
