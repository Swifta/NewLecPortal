package com.lonestarcell.mtn.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories( basePackages = { "com.lonestarcell.mtn.spring.repo" } )
 //@EnableJpaAuditing
public class JpaConfig {

}
