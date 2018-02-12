package com.lonestarcell.mtn.spring.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.lonestarcell.mtn.spring.user.entity.AuditLog;


@Repository
@Transactional( propagation = Propagation.REQUIRED )
public interface AuditLogRepo extends JpaRepository< AuditLog, Long >{
}
