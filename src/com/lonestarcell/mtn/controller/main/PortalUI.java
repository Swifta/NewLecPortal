package com.lonestarcell.mtn.controller.main;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.ContextLoaderListener;

import com.lonestarcell.mtn.controller.admin.DMainUI;
import com.lonestarcell.mtn.spring.config.Config;
import com.lonestarcell.mtn.spring.config.DataAccessConfigUser;
import com.lonestarcell.mtn.spring.config.JpaConfig;
import com.lonestarcell.mtn.spring.user.repo.ProfileRepo;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
// import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;

@SpringUI
@SuppressWarnings("serial")
@Theme("lec_portal")
@Push
public class PortalUI extends UI {
	
	@Configuration
	@EnableVaadin
	@ContextConfiguration( classes = { Config.class, DataAccessConfigUser.class, JpaConfig.class } )
	public static class VaadinSpringConfig {

	}
	
	private static Logger log = LogManager.getLogger( PortalUI.class );
	@Autowired
	private Person person;
	
	@Autowired
	private DMainUI defaultUI;
	
	
	@Autowired
	private ProfileRepo profileRepo;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PortalUI.class, widgetset = "com.lonestarcell.mtn.controller.main.widgetset.Lec_portalWidgetset")
	
	/*
	 * Instead of VaadinServlet, extend SpringVaadinServlet
	 */
	public static class UIServlet extends SpringVaadinServlet {
	}
	
	/*
	 * Let spring create & load the context with UI injected
	 */
	@WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {
    }

	@Override
	protected void init(VaadinRequest request) {
		
		if( profileRepo != null ){
			log.debug( "Profile repo is wired.", this );
		} else {
			log.debug( "Profile repo is null.", this );
		}
		
		/*
		if( defaultUI != null ){
			log.debug( "Main UI component is wired.", this );
		} else {
			log.debug( "Main UI component is null.", this );
		} */
		
		
		
		Navigator nav = UI.getCurrent().getNavigator();
		if ( nav == null )
			nav = new Navigator(this, this);

		// DUI dui = new DUI();
		// dui.init(dui);
		// nav.addView("", dui);
		
		// DMainUI defaultUI = new DMainUI();
		nav.addView("", defaultUI );
		nav.addView("login", DLoginUIController.class);
		//nav.addView("management", DManUIController.class);
		nav.addView("management", defaultUI );
		
		log.debug( person.greet(), this );
		
		

	}

}