package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.sid.beans.Achat;
import org.sid.beans.BonAchat;
import org.sid.beans.Depot;
import org.sid.beans.PaiementFournisseur;
import org.sid.dao.AchatRepository;
import org.sid.dao.BonAchatRepository;
import org.sid.dao.PaiementFournisseurRepository;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BonAchatService {
	
	@Autowired
	private BonAchatRepository bonAchatRepository;
	@Autowired
	private AchatRepository achatRepository;
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private PaiementFournisseurRepository paiementFournisseurRepository;
	
	
	public List<BonAchat> findAll() {
		return bonAchatRepository.findAll();
		
	}

	public Optional<BonAchat> findById(Long id) {
		return bonAchatRepository.findById(id);
		 
	}

	public BonAchat save(BonAchat bonAchat) {
		return bonAchatRepository.save(bonAchat);
		
	}

	public void deleteById(Long id) {
		Optional<BonAchat> bonAchat=bonAchatRepository.findById(id);
		Set<Achat> achats=bonAchat.get().getAchats();
		Set<PaiementFournisseur> paiements=bonAchat.get().getPaiementFournisseurs();
		for(Achat a : achats) {
			int oldQte=a.getProduit().getNivStock();
			a.getProduit().setNivStock(oldQte-a.getQte_achat());
		}
		for(Achat a : achats) {
			achatRepository.deleteById(a.getIdAchat());
		}
		for(PaiementFournisseur p : paiements) {
			paiementFournisseurRepository.deleteById(p.getIdPaiementFournisseur());
		}
		bonAchatRepository.deleteById(id);
		
	}

	public Page<BonAchat> chercherAllAchat(int page,int size){
		return bonAchatRepository.chercherAllAchat(PageRequest.of(page, size));
	}
	
	public Double totalAchat() {
		return bonAchatRepository.totalAchat();
	}
	
	public Double totalAchatDate(Date debut, Date fin) {
		return bonAchatRepository.totalAchatDate(debut, fin);
	}
	
	public int nombreAchatDate(Date debut, Date fin) {
		return bonAchatRepository.nombreAchatDate(debut, fin);
	}
	
	public List<Object> achatJour(Date debut,Date fin){
		return bonAchatRepository.achatJour(debut, fin);
	}
	public List<Object> achatMois(Date debut,Date fin){
		return bonAchatRepository.achatMois(debut, fin);
	}

	//ajouter un bon achat et incrementer le stock
		// Ajouter les bonachats dans la table bonAchat et les achats dans la table achat
			public BonAchat ajouterAchat(BonAchat bonAchat) {
				
				BonAchat bonachat=bonAchatRepository.save(bonAchat);
					
				for (Achat a : bonAchat.getAchats()) {
					Achat ach=new Achat();
					ach.setPrixAchat(a.getPrixAchat());
					ach.setQte_achat(a.getQte_achat());
					ach.setBonAchat(bonAchat);
					ach.setProduit(a.getProduit());
					achatRepository.save(ach);
				}
				for (Achat a : bonAchat.getAchats()) {
					Long idProduitLong = a.getProduit().getIdProduit();
					int oldQte=produitRepository.findQte(idProduitLong);
					int newQte= oldQte+a.getQte_achat();
					produitRepository.newQte(idProduitLong,newQte);
				}
				return bonachat;
			}
			//traçabilité
			public List<Object> tracerBon(Long id) {
				return bonAchatRepository.tracerBon(id);
			}
}
