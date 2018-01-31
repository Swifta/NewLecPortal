package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lonestarcell.mtn.spring.fundamo.entity.Subscriber001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface Subscriber001Repo extends JpaRepository< Subscriber001, Long >{
	
	String joinStr = " JOIN sub.person001 p ";
	String conStrDateRange = "  sub.lastUpdate BETWEEN :fDate AND :tDate ";
	String conStrDate = "  sub.lastUpdate BETWEEN :fDate AND :tDate ";
	String conStr = " ";
	/*
	public Page< Transaction001 > findByPayerAccountNumber( @Param( "payerAccountNumber" ) String payer, Pageable pageable );
    @Query( "SELECT SUM( t.payeeAmount ) FROM Transaction001 t" )
	public long getTotalPayeeAmount();
    
    @Query( "SELECT t FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate" )
	public Page< Transaction001 > findPageByDateRange( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
	*/
	
    @Query( "SELECT sub FROM Subscriber001 sub JOIN sub.person001 p WHERE sub.lastUpdate BETWEEN :fDate AND :tDate" )
	public Page< Subscriber001 > findPageByDateRange( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
    
    @Query( "SELECT sub FROM Subscriber001 sub JOIN sub.person001 p WHERE sub.name LIKE %:name% AND "+conStrDateRange )
	public Page< Subscriber001 > findPageByName( Pageable pageable, @Param( "name" ) String name, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
}
