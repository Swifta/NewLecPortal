package com.lonestarcell.mtn.controller.util;

import org.apache.commons.validator.routines.UrlValidator;

import com.vaadin.data.validator.AbstractStringValidator;

/**
 * @author vijay
 * @author Live [ customized ]
 *
 */
public class UrlValidatorCustom extends AbstractStringValidator{

	private static final long serialVersionUID = 1L;
	protected String[] schemes = {"http","https"};
	protected UrlValidator urlValidator = new UrlValidator(schemes);

	public UrlValidatorCustom(String errorMessage) {
	        super(errorMessage);
	}

	@Override
	protected boolean isValidValue(String value) {
		if (value == null || value.trim().isEmpty() ) {
			this.setErrorMessage( "Field is required." );
            return false;
        }
		
		value = value.trim();
		
        String url = (String)value;
        if (!url.startsWith("http")){
        	url = "http://" + url;
        }
        
        boolean status = urlValidator.isValid(url);
        if( !status ){
        	this.setErrorMessage( "Invalid URL Format should be http://example.com/ or https://example.com" );
        }
        
        return status;
	}
	
	
   
    

}