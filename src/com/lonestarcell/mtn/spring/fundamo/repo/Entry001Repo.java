package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface Entry001Repo extends JpaRepository< Entry001, Long >{
	
	//@Query( "SELECT e FROM Entry001 e JOIN e.userAccount001 ua JOIN ua.accountIdentifier001 ui  WHERE ui.typeName = :typeName" )
	// public Page< Entry001 > findByUserAccount001AccountIdentifier001TypeName( @Param( "typeName" ) String typeName, Pageable pageable );
	// public Page< Transaction001 > getSubscriberTransactionHistory();
	
    @Query( "SELECT e FROM Entry001 e WHERE e.entryDate BETWEEN :fDate AND :tDate" )
	public Page< Entry001 > findPageByDateRange( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
    
    @Query( "SELECT e FROM Entry001 e WHERE e.entryDate = :date" )
	public Page< Entry001 > findPageByDate( Pageable pageable, @Param( "date" ) Date date );
	
}
