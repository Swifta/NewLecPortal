package com.lonestarcell.mtn.model.agent;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.eagleairug.onlinepayment.ws.ds.DataServiceFaultException;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Addnewplace;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Addnewroute;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookflightdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookflightresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.BookflightresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookingdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookeddetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookedflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Cancelbookedresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.CancelbookedresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getbookingreport;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getplaces;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Getroutes;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Gettodaybookingreport;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Gettodayflightreport;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newplacedata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newplaceresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.NewplaceresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newroutedata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Newrouteresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.NewrouteresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Placesdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Placesresults;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.PlacesresultsE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Results3;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Results4;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Routesdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Routesresults;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.RoutesresultsE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Todaybookingdata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Todaybookingresults;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.TodaybookingresultsE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Todayflightdata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Todayflightresults;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.TodayflightresultsE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatebookedflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatedbookedflightdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatedbookedflightresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.UpdatedbookedflightresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateplace;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateplacedata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateplaceresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.UpdateplaceresponseE;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateroute;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updateroutedata;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updaterouteresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.UpdaterouteresponseE;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.model.admin.MBackendClient;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.UI;

public class MBookingMan extends MBackendClient {

	public static Bookflightdetails bookFlight(Bookflight booking)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		booking.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		booking.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		BookflightresponseE responseE = stub.bookflight(booking);

		Bookflightresponse response = responseE.getBookflightresponse();
		Bookflightdetails[] details = response.getBookflightdetails();

		if (details == null || details.length == 0)
			return null;

		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Updatedbookedflightdetails updateFlight(
			Updatebookedflight ubooking) throws RemoteException,
			DataServiceFaultException, IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		ubooking.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		ubooking.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		UpdatedbookedflightresponseE responseE = stub
				.updatebookedflight(ubooking);

		Updatedbookedflightresponse response = responseE
				.getUpdatedbookedflightresponse();
		Updatedbookedflightdetails[] details = response
				.getUpdatedbookedflightdetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static BeanItemContainer<Bookingdetails> getBookingReport(
			Getbookingreport report) throws RemoteException,
			DataServiceFaultException, IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		report.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		report.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		Results4 responseE = stub.getbookingreport(report);

		Results3 response = responseE.getResults();
		Bookingdetails[] details = response.getBookingdetails();

		if (details == null || details.length == 0) {

			System.out.println("Total fetched Response: 0");

			Collection<Bookingdetails> list = Collections.emptyList();
			BeanItemContainer<Bookingdetails> bookings = new BeanItemContainer<>(
					Bookingdetails.class);
			bookings.addAll(list);
			return bookings;
		}
		System.out.println("Total fetched Response: " + details.length);

		Collection<Bookingdetails> list = Arrays.asList(details);
		BeanItemContainer<Bookingdetails> bookings = new BeanItemContainer<Bookingdetails>(
				Bookingdetails.class);
		bookings.addAll(list);

		return bookings;

	}

	public static BeanItemContainer<Placesdetails> getPlaces(Getplaces places)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		places.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		places.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		PlacesresultsE responseE = stub.getplaces(places);

		Placesresults response = responseE.getPlacesresults();
		Placesdetails[] details = response.getPlacesdetails();

		if (details == null || details.length == 0) {

			System.out.println("Total fetched Response: 0");

			Collection<Placesdetails> list = Collections.emptyList();
			BeanItemContainer<Placesdetails> bookings = new BeanItemContainer<>(
					Placesdetails.class);
			bookings.addAll(list);
			return bookings;
		}
		System.out.println("Total fetched Response: " + details.length);

		Collection<Placesdetails> list = Arrays.asList(details);
		BeanItemContainer<Placesdetails> bookings = new BeanItemContainer<>(
				Placesdetails.class);
		bookings.addAll(list);

		return bookings;

	}

	public static BeanItemContainer<Routesdetails> getRoutes(Getroutes routes)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		routes.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		routes.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		RoutesresultsE responseE = stub.getroutes(routes);

		Routesresults response = responseE.getRoutesresults();
		Routesdetails[] details = response.getRoutesdetails();

		if (details == null || details.length == 0) {

			System.out.println("Total fetched Response: 0");

			Collection<Routesdetails> list = Collections.emptyList();
			BeanItemContainer<Routesdetails> bookings = new BeanItemContainer<>(
					Routesdetails.class);
			bookings.addAll(list);
			return bookings;
		}
		System.out.println("Total fetched Response: " + details.length);

		Collection<Routesdetails> list = Arrays.asList(details);
		BeanItemContainer<Routesdetails> bookings = new BeanItemContainer<>(
				Routesdetails.class);
		bookings.addAll(list);

		return bookings;

	}

	public static BeanItemContainer<Todaybookingdata> getTodayBookingReport(
			Gettodaybookingreport report) throws RemoteException,
			DataServiceFaultException, IllegalAccessException {

		/*
		 * Object profile =
		 * UI.getCurrent().getSession().getAttribute(DLoginUIController
		 * .PROFILE_ID);// != "1"; Object un =
		 * UI.getCurrent().getSession().getAttribute
		 * (DLoginUIController.USERNAME);
		 * 
		 * if((un == null || profile == null)) throw new
		 * IllegalAccessException("Illigal access error");
		 * 
		 * if(!(profile.equals("1") || profile.equals("3"))) throw new
		 * IllegalAccessException("Illigal access error");
		 * 
		 * 
		 * 
		 * 
		 * report.setUsername((String)
		 * UI.getCurrent().getSession().getAttribute(
		 * DLoginUIController.USERNAME)); report.setLoginSession((String)
		 * UI.getCurrent
		 * ().getSession().getAttribute(DLoginUIController.SESSION_VAR));
		 */

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		TodaybookingresultsE responseE = stub.gettodaybookingreport(report);

		Todaybookingresults response = responseE.getTodaybookingresults();
		Todaybookingdata[] details = response.getTodaybookingdata();

		if (details == null || details.length == 0) {

			System.out.println("Total fetched Response: 0");

			Collection<Todaybookingdata> list = Collections.emptyList();
			BeanItemContainer<Todaybookingdata> bookings = new BeanItemContainer<>(
					Todaybookingdata.class);
			bookings.addAll(list);
			return bookings;
		}
		System.out.println("Total fetched Response: " + details.length);

		Collection<Todaybookingdata> list = Arrays.asList(details);
		BeanItemContainer<Todaybookingdata> bookings = new BeanItemContainer<>(
				Todaybookingdata.class);
		bookings.addAll(list);

		return bookings;

	}

	public static BeanItemContainer<Todayflightdata> getTodayFlightsReport(
			Gettodayflightreport report) throws RemoteException,
			DataServiceFaultException, IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");
		if (report == null)
			throw new IllegalAccessException("Client request object missing!");

		report.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		report.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		TodayflightresultsE responseE = stub.gettodayflightreport(report);

		Todayflightresults response = responseE.getTodayflightresults();
		Todayflightdata[] details = response.getTodayflightdata();

		if (details == null || details.length == 0) {

			System.out.println("Total fetched Response: 0");

			Collection<Todayflightdata> list = Collections.emptyList();
			BeanItemContainer<Todayflightdata> bookings = new BeanItemContainer<>(
					Todayflightdata.class);
			bookings.addAll(list);
			return bookings;
		}

		System.out.println("Total fetched Response: " + details.length);

		Collection<Todayflightdata> list = Arrays.asList(details);
		BeanItemContainer<Todayflightdata> bookings = new BeanItemContainer<>(
				Todayflightdata.class);
		bookings.addAll(list);

		return bookings;

	}

	public static Cancelbookeddetails cancelBookedFlight(
			Cancelbookedflight booking) throws RemoteException,
			DataServiceFaultException, IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		booking.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		booking.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		CancelbookedresponseE responseE = stub.cancelbookedflight(booking);

		Cancelbookedresponse response = responseE.getCancelbookedresponse();
		Cancelbookeddetails[] details = response.getCancelbookeddetails();

		if (details == null || details.length == 0)
			return null;
		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Total fetched Response: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Newroutedata addNewRoute(Addnewroute route)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		route.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		route.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		NewrouteresponseE responseE = stub.addnewroute(route);

		Newrouteresponse response = responseE.getNewrouteresponse();
		Newroutedata[] details = response.getNewroutedata();
		if (details == null || details.length == 0)
			return null;

		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Newplacedata addNewPlace(Addnewplace place)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		place.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		place.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		NewplaceresponseE responseE = stub.addnewplace(place);

		Newplaceresponse response = responseE.getNewplaceresponse();
		Newplacedata[] details = response.getNewplacedata();
		if (details == null || details.length == 0)
			return null;

		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Updateplacedata updatePlace(Updateplace place)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		place.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		place.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		UpdateplaceresponseE responseE = stub.updateplace(place);

		Updateplaceresponse response = responseE.getUpdateplaceresponse();
		Updateplacedata[] details = response.getUpdateplacedata();
		if (details == null || details.length == 0)
			return null;

		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static Updateroutedata updateRoute(Updateroute route)
			throws RemoteException, DataServiceFaultException,
			IllegalAccessException {

		Object profile = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.PROFILE_ID);// != "1";
		Object un = UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME);

		if ((un == null || profile == null))
			throw new IllegalAccessException("Illigal access error");

		if (!(profile.equals("1") || profile.equals("3")))
			throw new IllegalAccessException("Illigal access error");

		route.setUsername((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USERNAME));
		route.setLoginSession((String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR));

		if (stub == null) {
			System.err.println("DS failed to initialize not initialized.");
			return null;
		}

		System.out.println("DSS Successfully initialed with class: "
				+ stub.getClass().toString());

		UpdaterouteresponseE responseE = stub.updateroute(route);

		Updaterouteresponse response = responseE.getUpdaterouteresponse();
		Updateroutedata[] details = response.getUpdateroutedata();
		if (details == null || details.length == 0)
			return null;

		System.out.println("Total fetched Response: " + details.length);
		System.out.println("Fetched Response Desc: "
				+ details[0].getResponseMsg());

		return details[0];

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Bookflight booking = new HyperswiftStub.Bookflight();
			booking.setUsername("admin");
			booking.setLoginSession("sehnqtd3tuim06qblbd44ph8rb");
			booking.setBookingRef("020");
			booking.setClientName("Mr. Zepha Best");
			booking.setDateAdded(Calendar.getInstance().getTime());
			booking.setFlightDate(sdf.format(Calendar.getInstance().getTime()));
			booking.setCost(109);

			// MBookingMan.bookFlight(booking);

			Updatebookedflight ubooking = new HyperswiftStub.Updatebookedflight();
			ubooking.setUsername("admin");
			ubooking.setLoginSession("sehnqtd3tuim06qblbd44ph8rb");
			ubooking.setBookingRef("020");
			ubooking.setClientName("Mr. Zepha Best");
			ubooking.setDateAdded(Calendar.getInstance().getTime());
			ubooking.setBookingStatus("0");
			ubooking.setFlightDate(Calendar.getInstance().getTime());
			ubooking.setCost("109");

			// MBookingMan.updateFlight(ubooking);

			Getbookingreport report = new HyperswiftStub.Getbookingreport();
			report.setUsername("admin");
			report.setLoginSession("");

			// MBookingMan.getBookingReport(report);

			Cancelbookedflight cbooking = new HyperswiftStub.Cancelbookedflight();
			cbooking.setUsername("admin");
			cbooking.setLoginSession("sehnqtd3tuim06qblbd44ph8rb");
			cbooking.setBookingRef("016");
			// MBookingMan.cancelBookedFlight(cbooking);

			Calendar cal = Calendar.getInstance();

			Date dEndTime = cal.getTime();
			System.out.println("TD: " + sdf.format(dEndTime));
			Gettodayflightreport tFreport = new HyperswiftStub.Gettodayflightreport();
			tFreport.setFDate(sdf.format(dEndTime));
			// MBookingMan.getTodayFlightsReport(tFreport);

			Addnewplace place = new HyperswiftStub.Addnewplace();
			place.setUsername("admin");
			place.setLoginSession("it3gqi7vgdpoonv6qo335t9tln");
			place.setPlaceName("YEIRUBA");
			place.setPlaceAbbrv("YEI");

			// MBookingMan.addNewPlace(place);

			Addnewroute route = new HyperswiftStub.Addnewroute();
			route.setUsername("admin");
			route.setLoginSession("it3gqi7vgdpoonv6qo335t9tln");
			route.setOriginId("6");
			route.setDestId("6");
			route.setCostOW("499");
			route.setCostRTN("759");

			// MBookingMan.addNewRoute(route);

			Updateplace uplace = new HyperswiftStub.Updateplace();
			uplace.setUsername("admin");
			uplace.setPlaceId("6");
			uplace.setLoginSession("it3gqi7vgdpoonv6qo335t9tln");
			uplace.setPlaceName("YEIRUBAX");
			uplace.setPlaceAbbrv("YEIB");

			// MBookingMan.updatePlace(uplace);

			Updateroute uroute = new HyperswiftStub.Updateroute();
			uroute.setUsername("admin");
			uroute.setLoginSession("it3gqi7vgdpoonv6qo335t9tln");
			uroute.setRouteId("8");
			uroute.setOriginId("6");
			uroute.setDestId("6");
			uroute.setCostOW("499");
			uroute.setCostRTN("759");

			// MBookingMan.updateRoute(uroute);

			// MBookingMan.getPlaces(new HyperswiftStub.Getplaces());

			// MBookingMan.getRoutes(new HyperswiftStub.Getroutes());

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
