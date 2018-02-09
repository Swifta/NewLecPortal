package com.lonestarcell.mtn.spring.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.user.entity.ProfilePermissionMap;


@Repository
@Transactional( propagation = Propagation.REQUIRED )
public interface ProfilePermissionMapRepo extends JpaRepository< ProfilePermissionMap, Short >{
	public List< ProfilePermissionMap > findByProfileProfileId( @Param( value = "profileId" ) Short id );
	public ProfilePermissionMap findByProfileProfileIdAndPermissionId( @Param( value = "profileId" ) Short profileId, @Param( value = "id" ) Short permissionId );
	
}
