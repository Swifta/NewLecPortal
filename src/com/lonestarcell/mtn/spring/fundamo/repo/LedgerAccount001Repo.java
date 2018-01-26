package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.fundamo.entity.Entry001;
import com.lonestarcell.mtn.spring.fundamo.entity.LedgerAccount001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface LedgerAccount001Repo extends JpaRepository< LedgerAccount001, Long >{
	
	@Query( "SELECT DISTINCT l.ledgerAccountNumber AS accNo, l.name, SUM( e.amount ) AS amount FROM LedgerAccount001 l JOIN l.entry001s e WHERE e.entryDate < :tDate GROUP BY l.ledgerAccountNumber, l.name" )
	public Page< Object[] > getAllSum( Pageable pageable, @Param( "tDate" ) Date tDate );
	
	@Query( "SELECT DISTINCT l.ledgerAccountNumber AS accNo, l.name, SUM( e.amount ) AS amount FROM LedgerAccount001 l JOIN l.entry001s e WHERE e.entryDate BETWEEN :fDate AND :tDate GROUP BY l.ledgerAccountNumber, l.name" )
	public List< Object[] > getAllSum( @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
	@Query( "SELECT DISTINCT l.ledgerAccountNumber AS accNo, l.name, SUM( e.amount ) AS amount FROM LedgerAccount001 l JOIN l.entry001s e GROUP BY l.ledgerAccountNumber, l.name" )
	public Page< Object[] > getAllSum( Pageable page );
	
	@Query( "SELECT DISTINCT l.ledgerAccountNumber AS accNo, l.name, SUM( e.amount ) AS amount, MAX( e.entryDate ) FROM LedgerAccount001 l JOIN l.entry001s e WHERE e.entryDate BETWEEN :fDate AND :tDate GROUP BY l.ledgerAccountNumber, l.name" )
	public Page< Object[] > getAllSumByDateRange( Pageable page, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
	@Query( "SELECT DISTINCT l.ledgerAccountNumber AS accNo, l.name, e.entryDate FROM LedgerAccount001 l JOIN l.entry001s e WHERE e.entryDate > :fDate" )
	public Page< Object[] > getFirstPageAllSumByDateRange( Pageable page, @Param( "fDate" ) Date fDate );
	
	@Query( "SELECT MIN( e.entryDate ) FROM LedgerAccount001 l JOIN l.entry001s e" )
	public Date findEarliestDate();
	
}
