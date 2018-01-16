package com.lonestarcell.mtn.spring.fundamo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
@Transactional( propagation = Propagation.MANDATORY )
public interface Transaction001Repo extends JpaRepository< Transaction001, Short >{
	
}
