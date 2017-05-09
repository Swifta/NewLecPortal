package com.lonestarcell.mtn.controller.util;

public class PasswordTFValidator extends UserPasswordTFUtil {

	private static final long serialVersionUID = 1L;
	
	public PasswordTFValidator(String errorMessage){
		super( errorMessage );
	}
	

	@Override
	protected boolean isValidValue(String value) {
	
			boolean status = true;
			String msg = "";
			if( value == null || value.trim().isEmpty() ){
				return false;
			}else if ( value.length() < 3 ||  value.length() > 20 ){
				status = false;
				msg = "Enter More than 2 and less than 21 characters.";
			} else {
				status = value.matches("^[a-zA-Z0-9._-]{3,20}$");
				if( !status ){
					msg = "Enter only Alpha numeric characters A-Z, 0 - 9 and '_'.";
				}
			}
			
			if( status ) {
				status = checkPasswordMatch( value, otherFieldDS.getValue().toString() );
				if( !status ){
					msg = "New Password and Confirm Password fields don't match.";
					if( isInitCalled ){
						lbErrorMsg.removeStyleName("sn-display-none");
						lbNormalMsg.addStyleName("sn-display-none");
					}
				}else{
					if( isInitCalled ){
						lbNormalMsg.removeStyleName("sn-display-none");
						lbErrorMsg.addStyleName("sn-display-none");
						
					}
				}
			}
			
			if( !status )
				this.setErrorMessage( msg );
		
		return status;
	}
	
	
	private boolean checkPasswordMatch( String pass, String otherPass ){
		return pass.trim().equals( otherPass.trim() );
	}
	
	
	
	
	
}


