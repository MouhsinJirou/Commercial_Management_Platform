package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.beans.Produit;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {
	
	@Autowired
	private ProduitRepository produitRepository;

	public List<Produit> findAll() {
		
		return produitRepository.findAll();
	}

	public Optional<Produit> findById(Long id) {
		
		return produitRepository.findById(id);
	}

	public Produit save(Produit produit) {
		
		return produitRepository.save(produit);
	}

	public void deleteById(Long id) {
		produitRepository.deleteById(id);
		
	}


	public Page<Produit> chercherParDesignation(String des, int page, int size) {
			return produitRepository.chercherParDesignation("%"+ des+"%",PageRequest.of(page, size));
		
	}
	
	//Alert des produits
		public Page<Produit> alerterProduit(String des,int page, int size){
			return produitRepository.alerterProduit("%"+des+"%",PageRequest.of(page, size));
		}
		
        public int nombreAlerteProduit() {
        	return produitRepository.nombreAlerteProduit();
        }
	
	public int nombreProduit() {
		return produitRepository.nombreProduit();
	}
	
	public int nombreProduitRupture() {
		return produitRepository.nombreProduitRupture();
	}


}
