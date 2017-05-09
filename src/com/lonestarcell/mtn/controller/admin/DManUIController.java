package com.lonestarcell.mtn.controller.admin;

import java.util.Calendar;

import com.lonestarcell.mtn.controller.agent.DBookingFormUI;
import com.lonestarcell.mtn.controller.agent.DBookingReportsUI;
import com.lonestarcell.mtn.controller.agent.DTodayBookingReportsUI;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DManagementUIDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class DManUIController extends DManagementUIDesign implements View,
		DUIControllable, DUIInitializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private DManUIController duic;

	public DManUIController() {

		setDUI(this);
		init(this);

	}

	@Override
	public void init(DManUIController duic) {
		attachCommandListeners();
		setContent();

	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (UI.getCurrent().getSession().getAttribute("username") == null)
			UI.getCurrent().getNavigator().navigateTo("login");
		else
			new DNewUserFormUI(duic);
			//new DDashboardUI(duic);

	}

	private void attachLogout() {
		this.btnLogout.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = -7731164586803534336L;

			@Override
			public void buttonClick(ClickEvent event) {

				UI.getCurrent().getSession().close();
				UI.getCurrent().getPage().reload();

			}

		});
	}

	private void attachDashboard() {
		System.out.println("Dashboard control click active.");
		this.btnDashboard.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 4209289701962087613L;

			@Override
			public void buttonClick(ClickEvent event) {
				new DDashboardUI(duic);
			}

		});
	}

	private void attachNewUser() {

		this.btnNewUser.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4523991192877530736L;

			@Override
			public void buttonClick(ClickEvent event) {
				new DNewUserFormUI(duic);
			}

		});

	}

	private void attachNewReservation() {
		this.btnNewReserv.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2637075833255513552L;

			@Override
			public void buttonClick(ClickEvent event) {
				new DBookingFormUI();

			}

		});
	}

	private void attachUsers() {

		this.btnUsers.addClickListener(new ClickListener() {

			/**
			 * 
			*/
			private static final long serialVersionUID = -4523991192877530736L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DUsersxUI(duic);

			}

		});

	}

	private void attachBookingReports() {
		this.btnBookingReports.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DBookingReportsUI(duic);

			}

		});
	}

	private void attachPaymentReports() {

		this.btnPaymentReports.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = -4523991192877530736L;

			@Override
			public void buttonClick(ClickEvent event) {
				new DPaymentReportsUI(duic);

			}

		});

	}

	private void attachBtnUserOps() {
		System.out.println("Btn Ops attached.");
		this.btnUserOps.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				moreDropDown.removeStyleName("sn-invisible");

			}

		});

	}

	private void attachBtnFx() {
		this.btnFxRate.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DFxFormUI();

			}

		});
	}

	private void attachBtnProfile() {
		this.btnUserProfile.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DProfileFormUI();

			}

		});
	}

	private void attachBtnTodayBooking() {
		this.btnTodaysBooking.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DTodayBookingReportsUI(duic);

			}

		});
	}

	private void attachBtnPlaces() {
		this.btnPlaces.addClickListener(new ClickListener() {

			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DPlacesUI();

			}

		});
	}

	/*private void attachBtnRoutes() {
		this.btnRoutes.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				new DRoutesUI();

			}

		});
	} */

	@Override
	public void attachCommandListeners() {

		attachDashboard();
		attachNewReservation();
		attachNewUser();
		attachUsers();
		attachPaymentReports();
		attachBookingReports();
		attachLogout();
		attachBtnUserOps();
		attachBtnFx();
		attachBtnProfile();
		attachBtnTodayBooking();
		attachBtnPlaces();
		//attachBtnRoutes();

	}

	@Override
	public void setContent() {
		
		setHeader();
		setUIState();
		setFooter();
	}

	@Override
	public void setHeader() {
		this.moreDropDown.addStyleName("sn-invisible");
		lbUsername.setValue((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		
	}

	@Override
	public void swap(DManUIController duic, Component cuid) {
		
		// TODO No swap is necessary here. Or is there?
		
	}

	@Override
	public void setFooter() {
		
		String startYear = "2017";
		
		Calendar cal = Calendar.getInstance();
		String currentYear = cal.get( Calendar.YEAR )+"";
		
		
		this.lbClient.setValue( "&nbspMTN Liberia,&nbsp" );
		this.lbCRYearStart.setValue( startYear );
		this.lbCRYearCurrent.setValue( currentYear );
		
		if( startYear.equals( currentYear )) {
			
			this.lbCRYearCurrent.setVisible(false);
			this.lbYearSeparator.setVisible(false);
			
		}
		
	}

	@Override
	public DManUIController getDUI() {
		return duic;
	}

	@Override
	public void setDUI(DManUIController duic) {
		this.duic = duic;
		
	}
	
	private void setUIState(){
		
		this.moreDropDown.addStyleName("sn-invisible");
		lbUsername.setValue((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		
	}

}