package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface Transaction001Repo extends JpaRepository< Transaction001, Long >{
	public Page< Transaction001 > findByPayerAccountNumber( @Param( "payerAccountNumber" ) String payer, Pageable pageable );
    @Query( "SELECT SUM( t.payeeAmount ) FROM Transaction001 t" )
	public long getTotalPayeeAmount();
    
    @Query( "SELECT t FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate" )
	public Page< Transaction001 > findPageByDateRange( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
    @Query( "SELECT t FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate" )
	public List< Transaction001 > findAllByDateRange( @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );

}
