package com.lonestarcell.mtn.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.spring.entity.User;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public interface UserRepo extends JpaRepository< User, Long >{
	
}
