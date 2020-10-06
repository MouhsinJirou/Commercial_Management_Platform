package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirClient;
import org.sid.beans.DetailAvoirClient;
import org.sid.dao.AvoirClientRepository;
import org.sid.dao.DetailAvoirClientRepository;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AvoirClientService {
	
	@Autowired
	private AvoirClientRepository avoirClientRepository;
	@Autowired
	private DetailAvoirClientRepository detailAvoirClientRepository;
	@Autowired
	private ProduitRepository produitRepository;
	
	public List<AvoirClient> findAll() {
		return avoirClientRepository.findAll();
		
	}

	public Optional<AvoirClient> findById(Long id) {
		return avoirClientRepository.findById(id);
		 
	}

	public AvoirClient save(AvoirClient avoirClient) {
		return avoirClientRepository.save(avoirClient);
		
	}

	public void deleteById(Long id) {
		avoirClientRepository.deleteById(id);
		
	}
	
	public Page<AvoirClient> chercherAvoirParClient(String nom, int page, int size){
		return avoirClientRepository.chercherAvoirParClient("%"+nom+"%",PageRequest.of(page, size));
	}
	
	public List<DetailAvoirClient> chercherDetailsAvoirClient(Long id){
		return avoirClientRepository.chercherDetailsAvoirClient(id);
	}
	
	//total avoir Client
	public Double totalAvoirClient(Date debut,Date fin) {
		return avoirClientRepository.totalAvoirClient(debut,fin);
	}
public boolean ajouterAvoirClient(AvoirClient avoirClient) {
		
		avoirClientRepository.save(avoirClient);
			
		for (DetailAvoirClient a : avoirClient.getDetailsAvoirClient()) {
			DetailAvoirClient ach=new DetailAvoirClient();
			ach.setPrix(a.getPrix());
			ach.setQte(a.getQte());
			ach.setAvoirClient(avoirClient);
			ach.setProduit(a.getProduit());
			detailAvoirClientRepository.save(ach);
		}
		for (DetailAvoirClient a : avoirClient.getDetailsAvoirClient()) {
			Long idProduitLong = a.getProduit().getIdProduit();
			int oldQte=produitRepository.findQte(idProduitLong);
			int newQte= oldQte+a.getQte();
			produitRepository.newQte(idProduitLong,newQte);
		}
		return true;
	}
}
