package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirFournisseur;
import org.sid.beans.BonAchat;
import org.sid.beans.Fournisseur;
import org.sid.dao.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {

	@Autowired
	private FournisseurRepository fournisseurRepository;
	
public List<Fournisseur> findAll() {
		
		return fournisseurRepository.findAll();
	}

	public Optional<Fournisseur> findById(Long id) {
		
		return fournisseurRepository.findById(id);
	}

	public Fournisseur save(Fournisseur fournisseur) {
		
		return fournisseurRepository.save(fournisseur);
	}

	public void deleteById(Long id) {
		
		fournisseurRepository.deleteById(id);
	}
	
	public Page<Fournisseur> chercherParNomSociete(String nom, int page, int size) {
		return fournisseurRepository.chercherParNomSociete("%"+ nom+"%",PageRequest.of(page, size));
}

	public Page<BonAchat> getBonAchatFromFournisseur(Long idFournisseur,int page, int size) {
		Page<BonAchat> bonAchats= fournisseurRepository.getBonAchatFromFournisseur(idFournisseur,PageRequest.of(page, size));
		return bonAchats;
	}
	

	public Page<BonAchat> bonParFournisseur(Long id, Date debut, Date fin, int page, int size) {
		return fournisseurRepository.bonParFournisseur(id, debut, fin,PageRequest.of(page, size));
	}
	
	//Avoir Fournisseur
	
		public Page<AvoirFournisseur> avoirFournisseurId(Long idFournisseur,int page, int size) {
			Page<AvoirFournisseur> avoirs= fournisseurRepository.avoirFournisseurId(idFournisseur,PageRequest.of(page, size));
			return avoirs;
		}
		

		public Page<AvoirFournisseur> avoirFournisseurDate(Long id, Date debut, Date fin, int page, int size) {
			return fournisseurRepository.avoirFournisseurDate(id, debut, fin,PageRequest.of(page, size));
		}
		
		//Total Montant bonachat d'un fournisseur
		public Double totalBonAchatsFournisseur(Long id) {
			return fournisseurRepository.totalBonAchatsFournisseur(id);
		}
		//Total Montant versé d'un fournisseur
		public Double totalPayementsFournisseur(Long id) {
			return fournisseurRepository.totalPayementsFournisseur(id);
		}
}
