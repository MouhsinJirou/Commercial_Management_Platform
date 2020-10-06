package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.beans.DetailDevis;
import org.sid.beans.Devis;
import org.sid.beans.Produit;
import org.sid.dao.DetailDevisRepository;
import org.sid.dao.DevisRepository;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailDevisService {
	
	@Autowired
	private DetailDevisRepository detailDevisRepository;
	
	@Autowired
	private DevisRepository devisRepository;
	
	@Autowired
	private ProduitRepository produitRepository;

	public List<DetailDevis> findAll() {	
		return detailDevisRepository.findAll();
	}

	public Optional<DetailDevis> findByDevisProduit(Long idDevis, Long idProduit) {
	
		Optional<Devis> devis=devisRepository.findById(idDevis);
		Optional<Produit> produit=produitRepository.findById(idProduit);
		
		if( devis!=null && produit!=null)
		{
			Long id=detailDevisRepository.findByDevisProduit(devis,produit);
			
			return detailDevisRepository.findById(id);
		}
		else {
			return null;
		}
		
	}
	public List<Object> getProduitsFromDevis(Long idDevis) {
		List<Object> produits= detailDevisRepository.getProduitsFromDevis(idDevis);
		return produits;
	}


}