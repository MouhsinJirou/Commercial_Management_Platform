package org.sid.service;

import java.util.List;

import org.sid.dao.DetailFactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailFactureService {
	@Autowired
	private DetailFactureRepository detailFactureRepository;
	
	public List<Object> getProduitsFromFacture(Long idFacture) {
		List<Object> produits= detailFactureRepository.getProduitsFromFacture(idFacture);
		return produits;
	}
	public List<Object> getProduitsFromFacture2(Long idFacture) {
		List<Object> produits= detailFactureRepository.getProduitsFromFacture2(idFacture);
		return produits;
	}
}
