package org.sid.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.sid.beans.BonAchat;
import org.sid.beans.DetailDevis;
import org.sid.beans.Devis;
import org.sid.dao.BonAchatRepository;
import org.sid.dao.DetailDevisRepository;
import org.sid.dao.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DevisService {
	@Autowired
	private DevisRepository devisRepository;
	@Autowired
	private DetailDevisRepository detailDevisRepository;
	
	public List<Devis> findAll() {
		return devisRepository.findAll();
		
	}

	public Optional<Devis> findById(Long id) {
		return devisRepository.findById(id);
		 
	}

	public Devis save(Devis devis) {
		return devisRepository.save(devis);
		
	}

	public void deleteById(Long id) {
		Optional<Devis> devis=devisRepository.findById(id);
		Set<DetailDevis> details=devis.get().getDetailsDevis();
		
		for(DetailDevis d : details) {
			detailDevisRepository.deleteById(d.getIdDetailDevis());
		}
		devisRepository.deleteById(id);
		
	}

	public Page<Devis> chercherAllDevis(int page,int size){
		return devisRepository.chercherAllDevis(PageRequest.of(page, size));
	}
	//ajouter dans detail devis et devis
		public boolean ajouterDevis(Devis devis) {
		devisRepository.save(devis);
		
		for (DetailDevis a : devis.getDetailsDevis()) {
			DetailDevis ach=new DetailDevis();
			ach.setPrix(a.getPrix());
			ach.setQte(a.getQte());
			ach.setDevis(devis);
			ach.setProduit(a.getProduit());
			detailDevisRepository.save(ach);
			
		}
		return true;
		}

}
