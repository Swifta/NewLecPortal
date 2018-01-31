package com.lonestarcell.mtn.spring.fundamo.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lonestarcell.mtn.spring.fundamo.entity.RegistrationRequestData001;
import com.lonestarcell.mtn.spring.fundamo.entity.Subscriber001;
import com.lonestarcell.mtn.spring.fundamo.entity.Transaction001;


@Repository
// @Transactional( propagation = Propagation.MANDATORY )
public interface RegistrationRequestData001Repo extends JpaRepository< RegistrationRequestData001, Long >{
	
	   
    // @Query( "SELECT reg FROM RegistrationRequestData001 req" )
	// public Page< RegistrationRequestData001 > findPageByDateRange( Pageable pageable );
    
}
