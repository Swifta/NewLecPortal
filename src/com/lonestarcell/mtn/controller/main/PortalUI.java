package com.lonestarcell.mtn.controller.main;

import javax.servlet.annotation.WebServlet;

import com.lonestarcell.mtn.controller.admin.DMainUI;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("lec_portal")
@Push
public class PortalUI extends UI {
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = PortalUI.class, widgetset = "com.lonestarcell.mtn.controller.main.widgetset.Lec_portalWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		Navigator nav = UI.getCurrent().getNavigator();
		if ( nav == null )
			nav = new Navigator(this, this);

		DUI dui = new DUI();
		dui.init(dui);
		nav.addView("", dui);
		nav.addView("login", DLoginUIController.class);
		//nav.addView("management", DManUIController.class);
		nav.addView("management", DMainUI.class);

	}

}