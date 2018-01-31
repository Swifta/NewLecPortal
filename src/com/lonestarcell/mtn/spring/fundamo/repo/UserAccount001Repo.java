package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lonestarcell.mtn.spring.fundamo.entity.UserAccount001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface UserAccount001Repo extends JpaRepository< UserAccount001, Long >{
	// public Page< Entry001 > findByAccountIdentifier001( @Param( "payerAccountNumber" ) String payer, Pageable pageable );
	// public Page< Transaction001 > getSubscriberTransactionHistory();
	
    // @Query( "SELECT p.oid, p.firstName FROM UserAccount001 u JOIN u.subscriber001 s JOIN s.person001 p JOIN p.registrationRequestData001 r WHERE s.lastUpdate BETWEEN :fDate AND :tDate GROUP BY p.oid, p.firstName" )
	// public Page< Object[] > findPageByDateRangeRaw( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );

    
    @Query( "SELECT u FROM UserAccount001 u JOIN u.subscriber001 s WHERE s.lastUpdate BETWEEN :fDate AND :tDate" )
	public Page< UserAccount001 > findPageByDateRange( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
    
    // @Query( "SELECT u, s, r FROM UserAccount001 u JOIN u.subscriber001 s JOIN s.registrationRequestData001 r WHERE s.lastUpdate BETWEEN :fDate AND :tDate" )
	// public Page< Object > findPageByDateRangeObj( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );

	
}
