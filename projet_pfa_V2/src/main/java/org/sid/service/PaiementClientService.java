package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.PaiementClient;
import org.sid.beans.PaiementFournisseur;
import org.sid.dao.AvoirClientRepository;
import org.sid.dao.AvoirFournisseurRepository;
import org.sid.dao.BonAchatRepository;
import org.sid.dao.ChargeRepository;
import org.sid.dao.FactureRepository;
import org.sid.dao.PaiementClientRepository;
import org.sid.dao.PaiementFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PaiementClientService {
	
	@Autowired
	private PaiementClientRepository paiementClientRepository;
	@Autowired
	private FactureRepository factureRepository;

	@Autowired
	private PaiementFournisseurRepository paiementFournisseurRepository;
	
	@Autowired
	private AvoirClientRepository avoirClientRepository;

	@Autowired
	private AvoirFournisseurRepository avoirFournisseurRepository;
	
	@Autowired
	private ChargeRepository chargeRepository;
	
	@Autowired
	private BonAchatRepository bonAchatRepository;

	public List<PaiementClient> findAll() {
		
		return paiementClientRepository.findAll();
	}

	public Optional<PaiementClient> findById(Long id) {
		
		return paiementClientRepository.findById(id);
	}

	public PaiementClient save(PaiementClient paiementclient) {
		PaiementClient paiement;
		paiement=paiementClientRepository.save(paiementclient);
		Long idFact=paiementclient.getFacture().getIdFacture();
		
		double credit=paiementClientRepository.calculerCredit(idFact);
		if(credit==0) {
			paiementclient.getFacture().setStatu("Payé");
			factureRepository.save(paiementclient.getFacture());
		}
		
		return paiement;
	}

	public void deleteById(Long id) {
		paiementClientRepository.deleteById(id);
	}
	
	public Page<PaiementClient> chercherPaiementParClient(String nom, int page, int size){
		return paiementClientRepository.chercherPaiementParClient("%"+nom+"%",PageRequest.of(page, size));
	}
	//paiement par id facture
	public List<PaiementClient> chercherPaiementParId(Long id){
		return paiementClientRepository.chercherPaiementParId(id);
	}
	
	//credit restant
	public Long calculerCredit(Long id) {
		return paiementClientRepository.calculerCredit(id);
	}
	//caisse
	public Double caisse(Date debut,Date fin) {

        Double tpc=paiementClientRepository.totalPaiementClient(debut, fin);
        if(tpc==null) {tpc=(double) 0;}
        Double tpf=paiementFournisseurRepository.totalPaiementFournisseur(debut, fin);
        if(tpf==null) {tpf=(double) 0;}
        Double tac=avoirClientRepository.totalAvoirClient(debut, fin);
        if(tac==null) {tac=(double) 0;}
        Double taf=avoirFournisseurRepository.totalAvoirFournisseur(debut, fin);
        if(taf==null) {taf=(double) 0;}
        Double tc=chargeRepository.totalChargeDate(debut, fin);
        if(tc==null) {tc=(double) 0;}
        
        Double caisse=tpc-tpf-tac+taf-tc;
		return caisse;
	}
	//total paiement Client
	public Double totalPaiementClient(Date debut,Date fin) {
		return paiementClientRepository.totalPaiementClient(debut,fin);
	}
	
	//profit
		public Double profit(Date debut,Date fin) {

	        Double tpc=factureRepository.totalVenteDate(debut, fin);
	        if(tpc==null) {tpc=(double) 0;}
	        Double tpf=bonAchatRepository.totalAchatDate(debut, fin);
	        if(tpf==null) {tpf=(double) 0;}
	        Double tac=avoirClientRepository.totalAvoirClient(debut, fin);
	        if(tac==null) {tac=(double) 0;}
	        Double taf=avoirFournisseurRepository.totalAvoirFournisseur(debut, fin);
	        if(taf==null) {taf=(double) 0;}
	        Double tc=chargeRepository.totalChargeDate(debut, fin);
	        if(tc==null) {tc=(double) 0;}
	        
	        Double caisse=tpc-tpf-tac+taf-tc;
			return caisse;
		}

}
