package com.lonestarcell.mtn.controller.util;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InUserDetails;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutUserDetails;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.model.admin.MUserDetails;
import com.lonestarcell.mtn.model.admin.MUserSelfCare;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.UI;

public class UsernameTFValidator extends UserTFUtil {

	private static final long serialVersionUID = 1L;
	
	public UsernameTFValidator(String errorMessage){
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
				Out out = this.checkUsernameUnique( value, newValue );
				if( out.getStatusCode() == 1 ){
					msg = out.getMsg();
					if( isInitCalled ){
						lbNormalMsg.removeStyleName("sn-display-none");
						lbErrorMsg.addStyleName("sn-display-none");
					}
					
					status = true;
				}else{
					
					if( isInitCalled ){
						lbErrorMsg.removeStyleName("sn-display-none");
						lbNormalMsg.addStyleName("sn-display-none");
						lbErrorMsg.setValue( out.getMsg() );
					}
					msg = out.getMsg();
					
					status = false;
				}
			}
			
			if( !status )
				this.setErrorMessage( msg );
		
		return status;
	}
	
	
	private Out checkUsernameUnique( String username, String newUsername ){
		
		
		InUserDetails inData = new InUserDetails();
		setAuth( inData );
		
		OutUserDetails outUserDetails = new OutUserDetails();
		outUserDetails.setUsername( username );
		outUserDetails.setNewUsername( newUsername );
		Item record = new BeanItem<>( outUserDetails );
		
		inData.setRecord( record );
		
		BData<InUserDetails> bData = new BData<>();
		bData.setData( inData );
		
		In in = new In();
		in.setData( bData );
		
		
		if( getCurrentUserProfileId() == 1 ){
			return new MUserDetails( getCurrentUserId(), getCurrentUserSession() ).checkUsernameUnique( in );
		}else{
			return new MUserSelfCare().checkUsernameUnique( in );
			
		}
		
	}
	
	private int getCurrentUserProfileId( ){
		return ( int )UI.getCurrent().getSession().getAttribute( DLoginUIController.PROFILE_ID );
	}
	
	
	
	
	
}


