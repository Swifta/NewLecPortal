package com.lonestarcell.mtn.model.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.vaadin.spring.annotation.SpringComponent;


@SpringComponent
@Scope( ConfigurableBeanFactory.SCOPE_SINGLETON )
public class Pager implements IPager {

	
	// @Value( "${sacco.pagination.backoffice.length}" )
	private int pgLen = 15;
	
	@Override
	public Pageable getPageRequest( int pgNo ) {

		if( pgNo <= 0 )
			pgNo = 1;
		if( pgLen <= 0 )
			pgLen = 5;
		return new PageRequest( pgNo - 1, pgLen );

	}

	@Override
	public Pageable getPageRequest(int pgNo, int pgLen ) {
		if( pgNo <= 0 )
			pgNo = 1;
		if( pgLen <= 0 )
			pgLen = 5;
		return new PageRequest( pgNo - 1, pgLen );
	}

	
}
