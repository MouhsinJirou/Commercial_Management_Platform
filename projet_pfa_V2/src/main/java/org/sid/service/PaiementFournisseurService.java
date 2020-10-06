package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.BonAchat;
import org.sid.beans.PaiementClient;
import org.sid.beans.PaiementFournisseur;
import org.sid.dao.BonAchatRepository;
import org.sid.dao.PaiementClientRepository;
import org.sid.dao.PaiementFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaiementFournisseurService {
	
	@Autowired
	private PaiementFournisseurRepository paiementFournisseurRepository;
	@Autowired
	private BonAchatRepository bonAchatRepository;

	public List<PaiementFournisseur> findAll() {
		
		return paiementFournisseurRepository.findAll();
	}

	public Optional<PaiementFournisseur> findById(Long id) {
		
		return paiementFournisseurRepository.findById(id);
	}

	public PaiementFournisseur save(PaiementFournisseur paiementFournisseur) {
		PaiementFournisseur paiement;
		double montantTotal=paiementFournisseur.getBonAchat().getMontantTotal();
        Long idBon=paiementFournisseur.getBonAchat().getIdBonAchat();

		paiement=paiementFournisseurRepository.save(paiementFournisseur);

		double totalPaiementBon=paiementFournisseurRepository.totalPaiementsBon(idBon);
		double credit=montantTotal-totalPaiementBon;
		if(credit==0) {
			paiementFournisseur.getBonAchat().setStatu("Payé");
			bonAchatRepository.save(paiementFournisseur.getBonAchat());

		}

	    return paiement;
	}

	public void deleteById(Long id) {
		
		paiementFournisseurRepository.deleteById(id);
	}
	
	public Page<PaiementFournisseur> chercherPaiementParFournisseur(String nom, int page, int size){
		return paiementFournisseurRepository.chercherPaiementParFournisseur("%"+nom+"%",PageRequest.of(page, size));
	}
	public List<PaiementFournisseur> chercherPaiementParBon(Long id){
		return paiementFournisseurRepository.chercherPaiementParBon(id);
	}
	
	public Long totalPaiementsBon(Long id) {
		return paiementFournisseurRepository.totalPaiementsBon(id);
	}
	
	//total paiement Fournisseur
	public Double totalPaiementFournisseur(Date debut,Date fin) {
		return paiementFournisseurRepository.totalPaiementFournisseur(debut,fin);
	}

}
