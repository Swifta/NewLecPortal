package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lonestarcell.mtn.spring.fundamo.entity.CorporateAccountHolder001;
import com.lonestarcell.mtn.spring.fundamo.entity.Subscriber001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface CorporateAccountHolder001Repo extends JpaRepository< CorporateAccountHolder001, Long >{
	
	@Query("SELECT COUNT( * ) FROM CorporateAccountHolder001 corp JOIN corp.userAccount001 ua JOIN ua.systemCode s WHERE s.code = 'STS001' ")
	public long countActive();
	
	@Query("SELECT COUNT( * ) FROM CorporateAccountHolder001 corp JOIN corp.userAccount001 ua JOIN ua.systemCode s WHERE s.code != 'STS001' ")
	public long countOther();
		
}
