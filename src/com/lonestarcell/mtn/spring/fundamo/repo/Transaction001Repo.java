package com.lonestarcell.mtn.spring.fundamo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface Transaction001Repo extends JpaRepository< Transaction001, Long >{
	public Page< Transaction001 > findByPayerAccountNumber( @Param( "payerAccountNumber" ) String payer, Pageable pageable );
    // public Page< Transaction001 > getSubscriberTransactionHistory();
	
}
