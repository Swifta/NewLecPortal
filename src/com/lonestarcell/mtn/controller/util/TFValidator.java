package com.lonestarcell.mtn.controller.util;

public class TFValidator extends UserTFUtil{

	private static final long serialVersionUID = 1L;

	public TFValidator(String errorMessage){
		super( errorMessage );
	}

	@Override
	protected boolean isValidValue(String value) {
		
		boolean status = true;
		String msg = "";
		if( value == null || value.trim().isEmpty() ){
			status = true;
		}else if ( value.length() < 3 ||  value.length() > 20 ){
			status = false;
			msg = "Enter More than 2 and less than 21 characters.";
		} else {
			status = value.matches( "^[a-zA-Z0-9._-]{3,20}$" );
			msg = "Enter only Alpha numeric characters A-Z, 0 - 9 and '_'.";
		} 
		
		if( !status )
			this.setErrorMessage( msg );
		
		return status;
	}
	
	
}
