package com.lonestarcell.mtn.spring.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.user.entity.Profile;


@Repository
@Transactional( propagation = Propagation.MANDATORY )
public interface ProfileRepo extends JpaRepository< Profile, Short >{
	
}
