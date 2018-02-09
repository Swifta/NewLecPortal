package com.lonestarcell.mtn.spring.email;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public class ClassResourceLoader implements IResourceLoader {

	private static Logger log = LogManager.getLogger();

	@Override
	public String loadAsString(String filename) {
		try {
			
			
			String filePath = this.getClass().getResource( filename ).getPath();
			if ( filePath == null) {
				log.debug("File path is null.");
				return null;
			}

			log.info( "File path: "+filePath );
			File file = new File( filePath );
			String content = FileUtils.readFileToString( file );
			return content;
			
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		
		log.info( "Resource content is null" );
		return null;
	}

}
