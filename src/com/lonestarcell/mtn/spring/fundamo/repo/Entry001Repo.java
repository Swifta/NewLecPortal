package com.lonestarcell.mtn.spring.fundamo.repo;

import java.math.BigDecimal;
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
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface Entry001Repo extends JpaRepository< Entry001, Long >{
	
	//@Query( "SELECT e FROM Entry001 e JOIN e.userAccount001 ua JOIN ua.accountIdentifier001 ui  WHERE ui.typeName = :typeName" )
	// public Page< Entry001 > findByUserAccount001AccountIdentifier001TypeName( @Param( "typeName" ) String typeName, Pageable pageable );
	// public Page< Transaction001 > getSubscriberTransactionHistory();
	
	String joinStr = "  JOIN e.transaction001 t JOIN e.userAccount001 uAcc JOIN uAcc.corporateAccountHolder001 corp ";
	String conditionStrDateRange = " ( e.entryDate BETWEEN :fDate AND :tDate ) ";
	String conditionStrNotNull = " e.userAccount001 IS NOT NULL  AND uAcc.corporateAccountHolder001 IS NOT NULL ";
	String conditionStr = conditionStrDateRange+" AND "+conditionStrNotNull;
			
	
	public List< Entry001 > findByOid( long oid );
    @Query( "SELECT e FROM Entry001 e "+joinStr+" WHERE "+conditionStr )
	public Page< Entry001 > findPageByDateRange( Pageable pageable, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
    
    @Query( "SELECT SUM( e.amount ) FROM Entry001 e "+joinStr+" WHERE "+conditionStr )
	public double findPageByDateRangeAmount( @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );

    
    // @Query( "SELECT COUNT( e ) FROM Entry001 e WHERE e.entryDate BETWEEN :fDate AND :tDate" )
	// public long findPageByDateRangeCount( @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
    
    //  @Query( "SELECT SUM( e.amount ), COUNT( e ) FROM Entry001 e" )
	// public List< Object[] > getTotalAmountAndCountAll();
	
    // @Query( "SELECT SUM( e.amount ), COUNT( e ) FROM Entry001 e WHERE e.entryDate BETWEEN :fDate AND :tDate" )
	// public List< Object[] > findByDateRangeAmountAndCount( @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
	@Query( "SELECT MIN( e.entryDate ) FROM Entry001 e" )
	public Date findEarliestDate();
    
    @Query( "SELECT e FROM Entry001 e "+joinStr+" WHERE e.entryDate = :date AND "+conditionStrNotNull )
	public Page< Entry001 > findPageByDate( Pageable pageable, @Param( "date" ) Date date );
    
    @Query( "SELECT e FROM Entry001 e  "+joinStr+"  WHERE e.entryDate > :date AND "+conditionStrNotNull+" ORDER BY e.entryDate" )
	public Page< Entry001 > findFirstPageByDate( Pageable pageable, @Param( "date" ) Date date );
    
    
   //  @Query( "SELECT COUNT( e ) FROM Entry001 e WHERE e.entryDate = :date" )
	// public long findPageByDateCount( @Param( "date" ) Date date );
    
    
	// Search by something
	
	@Query("SELECT e FROM Entry001 e  "+joinStr+"  WHERE t.payerAccountNumber LIKE %:payerAccNo% AND "+conditionStr)
	public Page<Entry001> findPageByPayerAccountNumber(Pageable pageable, @Param("payerAccNo") String payerAccNo, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate);
	@Query("SELECT SUM( e.amount ) FROM Entry001 e  "+joinStr+"  WHERE t.payerAccountNumber LIKE %:payerAccNo% AND "+conditionStr)
	public double findPageByPayerAccountNumberAmount( @Param("payerAccNo") String payerAccNo, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );

	@Query("SELECT e FROM Entry001 e  "+joinStr+" WHERE t.payeeAccountNumber LIKE %:payeeAccNo% AND "+conditionStr)
	public Page<Entry001> findPageByPayeeAccountNumber(Pageable pageable, @Param("payeeAccNo") String payeeAccNo, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate);
	@Query("SELECT SUM( e.amount ) FROM Entry001 e  "+joinStr+" WHERE t.payeeAccountNumber LIKE %:payeeAccNo% AND "+conditionStr)
	public double findPageByPayeeAccountNumberAmount( @Param("payeeAccNo") String payeeAccNo, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );

	@Query("SELECT e FROM Entry001 e  "+joinStr+"  WHERE t.transactionNumber = :tNo AND "+conditionStrNotNull)
	public Page<Entry001> findPageByTransactionNumber( Pageable pageable, @Param("tNo") BigDecimal tNo );
	@Query("SELECT SUM( e.amount ) FROM Entry001 e  "+joinStr+"  WHERE t.transactionNumber = :tNo AND "+conditionStrNotNull)
	public double findPageByTransactionNumberAmount( @Param("tNo") BigDecimal tNo );
	
	@Query("SELECT e FROM Entry001 e "+joinStr+" WHERE corp.name LIKE %:name% AND "+conditionStr)
	public Page<Entry001> findPageByCorpName( Pageable pageable, @Param("name") String name, @Param( "fDate" ) Date fDate, @Param( "tDate" ) Date tDate );
	
	
	
}
