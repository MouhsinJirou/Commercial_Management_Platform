package org.sid.service;

import java.util.List;
import java.util.Optional;

import org.sid.beans.Depot;
import org.sid.beans.Produit;
import org.sid.dao.DepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DepotService {

	@Autowired
	private DepotRepository depotRepository;
	
	public List<Depot> findAll() {
		return depotRepository.findAll();
		
	}

	public Optional<Depot> findById(Long id) {
		return depotRepository.findById(id);
		 
	}

	public Depot save(Depot depot) {
		return depotRepository.save(depot);
		
	}

	public void deleteById(Long id) {
		depotRepository.deleteById(id);
		
	}
	public Page<Depot> chercherParEmplacement(String emp, int page, int size) {
		return depotRepository.chercherParEmplacement("%"+emp+"%",PageRequest.of(page, size));
	
}
	
public Page<Produit> getProduitsByDesignation(Long id,String designation,int page, int size) {
		
		return depotRepository.getProduitsByDesignation(id,"%"+designation+"%",PageRequest.of(page, size));
	}

	public Page<Produit> getProduitsFromDepot(Long id, int page, int size) {
		
		return depotRepository.getProduitsFromDepot(id,PageRequest.of(page, size));
	}
	
	
}
