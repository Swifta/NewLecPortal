package com.lonestarcell.mtn.model.util;

import org.springframework.data.domain.Pageable;

public interface IPager  {
	public Pageable getPageRequest( int pgNo );
	public Pageable getPageRequest( int pgNo, int pgLen );
}
