package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirClient;
import org.sid.beans.AvoirFournisseur;
import org.sid.beans.DetailAvoirFr;
import org.sid.dao.AvoirFournisseurRepository;
import org.sid.dao.DetailAvoirFrRepository;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AvoirFournisseurService {
	
	@Autowired
	private AvoirFournisseurRepository avoirFournisseurRepository;
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private DetailAvoirFrRepository detailAvoirFrRepository;
	
	public List<AvoirFournisseur> findAll() {
		return avoirFournisseurRepository.findAll();
		
	}

	public Optional<AvoirFournisseur> findById(Long id) {
		return avoirFournisseurRepository.findById(id);
		 
	}

	public AvoirFournisseur save(AvoirFournisseur avoirFournisseur) {
		return avoirFournisseurRepository.save(avoirFournisseur);
		
	}

	public void deleteById(Long id) {
		avoirFournisseurRepository.deleteById(id);
	}
	
	public Page<AvoirFournisseur> chercherAvoirParFournisseur(String nom, int page, int size){
		return avoirFournisseurRepository.chercherAvoirParFournisseur("%"+nom+"%",PageRequest.of(page, size));
	}
	
	public List<DetailAvoirFr> chercherDetailAvoirFournisseur(Long id){
		return avoirFournisseurRepository.chercherDetailAvoirFournisseur(id);
	}

	//total avoir Fournisseur
	public Double totalAvoirFournisseur(Date debut,Date fin) {
		return avoirFournisseurRepository.totalAvoirFournisseur(debut,fin);
	}
	public boolean ajouterAvoirFr(AvoirFournisseur avoirFournisseur) {
		
		avoirFournisseurRepository.save(avoirFournisseur);
			
		for (DetailAvoirFr a : avoirFournisseur.getDetailAvoirFournisseur()) {
			DetailAvoirFr ach=new DetailAvoirFr();
			ach.setPrix(a.getPrix());
			ach.setQte(a.getQte());
			ach.setAvoirFournisseur(avoirFournisseur);
			ach.setProduit(a.getProduit());
			detailAvoirFrRepository.save(ach);
		}
		for (DetailAvoirFr a : avoirFournisseur.getDetailAvoirFournisseur()) {
			Long idProduitLong = a.getProduit().getIdProduit();
			int oldQte=produitRepository.findQte(idProduitLong);
			int newQte= oldQte-a.getQte();
			produitRepository.newQte(idProduitLong,newQte);
		}
		return true;
	}

}
