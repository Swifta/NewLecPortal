package com.lonestarcell.mtn.spring.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.user.entity.Profile;


@Repository
@Transactional( propagation = Propagation.REQUIRED )
public interface ProfileRepo extends JpaRepository< Profile, Short >{
	public Profile findByProfileName( @Param( "profileName" ) String name );
	public Profile findByProfileStatus( @Param( "profileStatus" ) Short status );
	
}
