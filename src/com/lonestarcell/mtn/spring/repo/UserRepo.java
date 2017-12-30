package com.lonestarcell.mtn.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lonestarcell.mtn.spring.entity.User;

@Repository
public interface UserRepo extends JpaRepository< User, Long >{
	
}
