package org.sid.service;

import java.util.List;

import org.sid.dao.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchatService {
	
	@Autowired
	private AchatRepository achatRepository;
	
	public List<Object> getProduitsFromBonAchat(Long idFacture) {
		List<Object> produits= achatRepository.getProduitsFromBonAchat(idFacture);
		return produits;
	}
	//Prix d'achat moyenne
	public Double getPrixAchat(Long id){
		return achatRepository.getPrixAchat(id);
	}
	
	//prix achat total d'un produit
	public Double prixAchatTotal(Long id) {
		return achatRepository.prixAchatTotal(id);
	}
	//produits d'un bon2
	public List<Object> getProduitsFromBonAchat2(Long idFacture) {
		List<Object> produits= achatRepository.getProduitsFromBonAchat2(idFacture);
		return produits;
	}
}