package com.lonestarcell.mtn.spring.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	
	
}
