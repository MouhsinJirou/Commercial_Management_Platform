package org.sid.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.Charge;
import org.sid.beans.Produit;
import org.sid.dao.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ChargeService {
	
	@Autowired
	private ChargeRepository chargeRepository;
	
	public List<Charge> findAll() {
		return chargeRepository.findAll();
		
	}

	public Optional<Charge> findById(Long id) {
		return chargeRepository.findById(id);
		 
	}

	public Charge save(Charge charge) {
		return chargeRepository.save(charge);
		
	}

	public void deleteById(Long id) {
		chargeRepository.deleteById(id);
		
	}
	
	public Page<Charge> chercherParLibelle(String libelle, int page, int size) {
		return chargeRepository.chercherParLibelle("%"+ libelle+"%",PageRequest.of(page, size));
}
	
	public Double totalCharge() {
		return chargeRepository.totalCharge();
	}
	
	public Double totalChargeDate(Date debut, Date fin) {
		return chargeRepository.totalChargeDate(debut, fin);
	}
	public int nombreChargeDate(Date debut, Date fin) {
		return chargeRepository.nombreChargeDate(debut, fin);
	}
	
	public Page<Charge> chercherParDate(Date debut, Date fin, int page, int size) {
		return chargeRepository.chercherParDate(debut, fin,PageRequest.of(page, size));
}
	public List<Object> chargeJour(Date debut,Date fin){
		return chargeRepository.chargeJour(debut, fin);
	}
	//charge mois
	public List<Object> chargeMois(Date debut,Date fin){
		return chargeRepository.chargeMois(debut, fin);
	}
	//traçabilité
		public List<Object> tracerCharge(Long id) {
			return chargeRepository.tracerCharge(id);
		}
	
}