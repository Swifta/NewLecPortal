package com.lonestarcell.mtn.spring.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class EmailTemplate {
	
	private IResourceLoader loader;
	private EmailServiceImpl sender;
	@Autowired
	public EmailTemplate( IResourceLoader loader, EmailServiceImpl sender ){
		this.loader = loader;
		this.sender = sender;
	}
	private String build( String filename, String templatePart  ){
		String msg  = loader.loadAsString( filename+"_head.html" )
				+templatePart
				+loader.loadAsString( filename+"_foot.html" );
		return msg;
		
	}
	
	public boolean sendCredentials(String username, String password, String email ) {

		try {
			
			
			String templatePart = String.format( getCredentialsTemplatePart(), username, password );
			String content = build( "acc_creation", templatePart );
			if( content == null || content.isEmpty() ) {
				return false;
			}
			return sender.sendSimpleMessage( email, "Account Credentials", content );
		} catch (Exception ex) {
			// ex.printStackTrace();

		}

		return false;

	}
	
	private String getCredentialsTemplatePart(){
		return "<td colspan='2'><br/>Credentials <br/>Username: <code>%s</code><br/>Password: <code>%s</code></td>";
	}

}
