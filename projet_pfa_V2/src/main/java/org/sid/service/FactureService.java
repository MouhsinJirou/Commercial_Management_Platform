package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.sid.beans.BonAchat;
import org.sid.beans.DetailFacture;
import org.sid.beans.Facture;
import org.sid.beans.PaiementClient;
import org.sid.dao.DetailFactureRepository;
import org.sid.dao.FactureRepository;
import org.sid.dao.PaiementClientRepository;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FactureService {

	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private DetailFactureRepository detailFactureRepository;
	
	@Autowired
	private PaiementClientRepository paiementClientRepository;

	@Autowired
	private ProduitRepository produitRepository;

	public List<Facture> findAll() {
		
		return factureRepository.findAll();
	}

	public Optional<Facture> findById(Long id) {
		
		return factureRepository.findById(id);
	}

	public Facture save(Facture facture) {
		
		return factureRepository.save(facture);
	}
	//ajouter une facture et decrementer le stock
	
	public Facture ajouterFacture(Facture facture) {
		
		Facture facturereturn=factureRepository.save(facture);
			
		for (DetailFacture a : facture.getDetailFactures()) {
			DetailFacture ach=new DetailFacture();
			ach.setPrix(a.getPrix());
			ach.setQte(a.getQte());
			ach.setFacture(facture);
			ach.setProduit(a.getProduit());
			detailFactureRepository.save(ach);
		}
		for (DetailFacture a : facture.getDetailFactures()) {
			Long idProduitLong = a.getProduit().getIdProduit();
			int oldQte=produitRepository.findQte(idProduitLong);
			int newQte= oldQte-a.getQte();
			produitRepository.newQte(idProduitLong,newQte);
		}
		return facturereturn;
	}
	public void deleteById(Long id) {
		Optional<Facture> facture=factureRepository.findById(id);
		Set<DetailFacture> details=facture.get().getDetailFactures();
		Set<PaiementClient> paiements=facture.get().getPaiementClients();

		
		for(DetailFacture d : details) {
			int oldQte=d.getProduit().getNivStock();
			d.getProduit().setNivStock(oldQte+d.getQte());
		}
		
		
		for(DetailFacture d : details) {
			detailFactureRepository.deleteById(d.getIdDetailFacture());
		}
		
		for(PaiementClient p : paiements) {
			paiementClientRepository.deleteById(p.getIdPaiementClient());
		}
		
		
		factureRepository.deleteById(id);
	}
	
	public Page<Facture> chercherAllFacture(int page,int size){
		return factureRepository.chercherAllFacture(PageRequest.of(page, size));
	}
	
	public Double totalVente() {
		return factureRepository.totalVente();
	}
	
	public Double totalVenteDate(Date debut, Date fin) {
		return factureRepository.totalVenteDate(debut, fin);
	}
	public int nombreVenteDate(Date debut, Date fin) {
		return factureRepository.nombreVenteDate(debut, fin);
	}
	
	public Page<Object> rapportVente(Date debut, Date fin,int page,int size){
		return factureRepository.rapportVente(debut, fin,PageRequest.of(page, size));
	}
	
	//Les ventes du jour
		public List<Object> venteJour(Date debut,Date fin){
			return factureRepository.venteJour(debut, fin);
		}
		//ventes de mois
		public List<Object> venteMois(Date debut,Date fin){
			return factureRepository.venteMois(debut, fin);
		}
		//traçabilité
				public List<Object> tracerFacture(Long id) {
					return factureRepository.tracerFacture(id);
				}
}
