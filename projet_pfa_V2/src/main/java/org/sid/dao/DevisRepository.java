package org.sid.dao;

import org.sid.beans.BonAchat;
import org.sid.beans.Devis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DevisRepository extends JpaRepository<Devis,Long>{
	@Query("SELECT d FROM Devis d ORDER BY d.dateDevis DESC")
	public Page<Devis> chercherAllDevis(Pageable pageable);
}
