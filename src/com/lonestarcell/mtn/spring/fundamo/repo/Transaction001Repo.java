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

import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;

@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface Transaction001Repo extends JpaRepository<Transaction001, Long> {
	public Page<Transaction001> findByPayerAccountNumber(
			@Param("payerAccountNumber") String payer, Pageable pageable);

	@Query("SELECT SUM( t.payeeAmount ) FROM Transaction001 t")
	public double getTotalPayeeAmount();

	@Query("SELECT SUM( t.payeeAmount ) AS amount, COUNT( t.oid ) AS count FROM Transaction001 t")
	public List<Object[]> getTotalAmountAndCountAll();

	@Query("SELECT MIN( t.lastUpdate ) FROM Transaction001 t")
	public Date getInitialDate();

	@Query("SELECT t FROM Transaction001 t WHERE t.lastUpdate >= :fDate ORDER BY t.lastUpdate")
	public Page<Transaction001> findFirstPageByDate(Pageable pageable,
			@Param("fDate") Date fDate);

	@Query("SELECT MIN( t.lastUpdate ) FROM Transaction001 t")
	public Date findEarliestDate();

	@Query("SELECT t FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate")
	public List<Transaction001> findAllByDateRange(@Param("fDate") Date fDate,
			@Param("tDate") Date tDate);

	@Query("SELECT SUM( t.payeeAmount ) FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate")
	public double findByDateRangeAmount(@Param("fDate") Date fDate,
			@Param("tDate") Date tDate);

	@Query("SELECT SUM( t.payeeAmount ) AS a, COUNT( t ) AS t FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate")
	public List<Object[]> findByDateRangeAmountAndCount(
			@Param("fDate") Date fDate, @Param("tDate") Date tDate);

	@Query("SELECT COUNT( t ) FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate")
	public long findByDateRangeCount(@Param("fDate") Date fDate,
			@Param("tDate") Date tDate);

	@Query("SELECT t FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate")
	public Page<Transaction001> findPageByDateRange(Pageable pageable, @Param("fDate") Date fDate, @Param("tDate") Date tDate);

	@Query("SELECT SUM( t.payeeAmount ) FROM Transaction001 t WHERE t.lastUpdate BETWEEN :fDate AND :tDate")
	public double findPageByDateRangeAmount(@Param("fDate") Date fDate, @Param("tDate") Date tDate);

	
	// Search by something
	
	@Query("SELECT t FROM Transaction001 t WHERE t.payerAccountNumber LIKE %:payerAccNo%")
	public Page<Transaction001> findPageByPayerAccountNumber(Pageable pageable, @Param("payerAccNo") String payerAccNo);
	
	@Query("SELECT SUM( t.payeeAmount ) FROM Transaction001 t WHERE t.payerAccountNumber LIKE %:payerAccNo%")
	public double findPageByPayerAccountNumberAmount( @Param("payerAccNo") String payerAccNo );

	@Query("SELECT t FROM Transaction001 t WHERE t.payeeAccountNumber LIKE %:payeeAccNo%")
	public Page<Transaction001> findPageByPayeeAccountNumber(Pageable pageable, @Param("payeeAccNo") String payeeAccNo);
	
	@Query("SELECT SUM( t.payeeAmount ) FROM Transaction001 t WHERE t.payeeAccountNumber LIKE %:payeeAccNo%")
	public double findPageByPayeeAccountNumberAmount( @Param("payeeAccNo") String payeeAccNo);

	@Query("SELECT t FROM Transaction001 t WHERE t.transactionNumber = :tNo")
	public Page<Transaction001> findPageByTransactionNumber( Pageable pageable, @Param("tNo") BigDecimal tNo );
	
	@Query("SELECT SUM( t.payeeAmount ) FROM Transaction001 t WHERE t.transactionNumber = :tNo")
	public double findPageByTransactionNumberAmount( @Param("tNo") BigDecimal tNo );

}
