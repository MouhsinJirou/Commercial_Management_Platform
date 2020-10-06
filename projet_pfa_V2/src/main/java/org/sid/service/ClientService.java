package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirClient;
import org.sid.beans.BonAchat;
import org.sid.beans.Client;
import org.sid.beans.Facture;
import org.sid.beans.Produit;
import org.sid.dao.ClientRepository;
import org.sid.dao.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> findAll() {
		
			return clientRepository.findAll();
	}

	public Optional<Client> findById(Long id) {
		
		return clientRepository.findById(id);
	}

	public Client save(Client client) {
		
		return clientRepository.save(client);
	}

	public void deleteById(Long id) {
		clientRepository.deleteById(id);
		
	}

	public Page<Client> chercherParNom(String nom, int page, int size) {
		return clientRepository.chercherParNom("%"+nom+"%",PageRequest.of(page, size));
	
}
	public List<Facture> factureParClient(Long id){
		return clientRepository.factureParClient(id);
	}
	
	public Page<Facture> factureClientId(Long idClient,int page, int size) {
		Page<Facture> factures= clientRepository.factureClientId(idClient,PageRequest.of(page, size));
		return factures;
	}
	

	public Page<Facture> factureClientDate(Long id, Date debut, Date fin, int page, int size) {
		return clientRepository.factureClientDate(id, debut, fin,PageRequest.of(page, size));
	}
	
	//Lez avoirs d'un client
		public Page<AvoirClient> avoirClientId(Long idClient,int page, int size) {
			Page<AvoirClient> avoirs= clientRepository.avoirClientId(idClient,PageRequest.of(page, size));
			return avoirs;
		}
		public Page<AvoirClient> avoirClientDate(Long id, Date debut, Date fin, int page, int size) {
			return clientRepository.avoirClientDate(id, debut, fin,PageRequest.of(page, size));
		}
		
	//Total Montant factures d'un client
    public Double totalFacturesClient(Long id) {
    	return clientRepository.totalFacturesClient(id);
    }
	//Total Montant versé d'un client
    public Double totalPayementsClient(Long id) {
    	return clientRepository.totalPayementsClient(id);
    }
    
	//Clients du mois
    public Page<Object> clientDuMois(Date debut, Date fin, int page, int size){
    	return clientRepository.clientDuMois(debut,fin,PageRequest.of(page, size));
    }
}