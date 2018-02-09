package com.lonestarcell.mtn.spring.user.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.spring.user.entity.User;

@Repository
@Transactional( propagation = Propagation.REQUIRED )
public interface UserRepo extends JpaRepository< User, Long >{
	
	@Override
	public long count();
	@Query( "SELECT COUNT(*) FROM User u WHERE u.status = 1" )
	public long countActive();
	
	@Query( "SELECT COUNT(*) FROM User u WHERE u.status != 1" )
	public long countOther();
	
	public List< User > findByProfileProfileId( short profileId );
	
	@Query( "SELECT u FROM User u WHERE u.userId != :userId AND u.dateAdded BETWEEN :fDate AND :tDate ORDER BY u.dateAdded" )
	public Page< User > findPageByDateRange( Pageable pgR, @Param( "userId" ) Long userId, @Param( "fDate" ) Date fDate,  @Param( "tDate" )Date tDate );
	
	@Query( "SELECT u FROM User u WHERE u.userId != :userId AND  u.dateAdded BETWEEN :fDate AND :tDate  AND u.username LIKE %:username% ORDER BY u.dateAdded" )
	public Page< User > findPageByUsername( Pageable pgR, @Param( "userId" ) Long userId, @Param( "username" ) String username, @Param( "fDate" ) Date fDate,  @Param( "tDate" )Date tDate );
	
	@Query( "SELECT u FROM User u WHERE u.userId != :userId AND u.dateAdded BETWEEN :fDate AND :tDate AND u.email LIKE %:email% ORDER BY u.dateAdded" )
	public Page< User > findPageByEmail( Pageable pgR, @Param( "userId" ) Long userId, @Param( "email" ) String email, @Param( "fDate" ) Date fDate,  @Param( "tDate" )Date tDate );

	@Query( "SELECT u FROM User u JOIN u.organization org WHERE u.userId != :userId AND u.dateAdded BETWEEN :fDate AND :tDate AND org.name LIKE %:org% ORDER BY u.dateAdded" )
	public Page< User > findPageByOrg( Pageable pgR, @Param( "userId" ) Long userId, @Param( "org" ) String org, @Param( "fDate" ) Date fDate,  @Param( "tDate" )Date tDate );

	@Query( "SELECT u FROM User u JOIN u.profile prof WHERE u.userId != :userId AND u.dateAdded BETWEEN :fDate AND :tDate AND prof.profileName LIKE %:profile% ORDER BY u.dateAdded" )
	public Page< User > findPageByProfile( Pageable pgR, @Param( "userId" ) Long userId, @Param( "profile" ) String org, @Param( "fDate" ) Date fDate,  @Param( "tDate" )Date tDate );

	
	@Query( "SELECT u FROM User u WHERE u.userId != :userId AND u.dateAdded BETWEEN :fDate AND :tDate AND u.status = :uStatus ORDER BY u.dateAdded" )
	public Page< User > findPageByStatus( Pageable pgR,@Param( "userId" ) Long userId, @Param( "uStatus" ) short uStatus, @Param( "fDate" ) Date fDate,  @Param( "tDate" )Date tDate  );
	
	
}
