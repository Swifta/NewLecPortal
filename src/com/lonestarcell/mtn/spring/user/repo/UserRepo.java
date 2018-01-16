package com.lonestarcell.mtn.spring.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lonestarcell.mtn.spring.user.entity.User;

@Repository
@Transactional( propagation = Propagation.MANDATORY )
public interface UserRepo extends JpaRepository< User, Long >{
	
}
