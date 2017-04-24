package com.lonestarcell.mtn.controller.agent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Bookflightdetails;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatebookedflight;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Updatedbookedflightdetails;
import com.lonestarcell.mtn.controller.admin.DDashboardUI;
import com.lonestarcell.mtn.controller.admin.DManUI;
import com.lonestarcell.mtn.controller.admin.DManUIController;
import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.lonestarcell.mtn.design.agent.DBookingFormUIDesign;
import com.lonestarcell.mtn.model.agent.MBookingMan;
import com.vaadin.data.Item;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class DBookingFormUI extends DManUI {

	private Item iFormData;

	public DBookingFormUI() {
		init(duic);
	}

	public DBookingFormUI(DManUIController duic, Item data) {
		setiFormData(data);

		init(duic);
	}

	private class DBookingFormUIController extends DBookingFormUIDesign
			implements DUIControllable {

		private static final long serialVersionUID = 1L;

		DBookingFormUIController(DManUIController duic) {
			init(duic);
		}

		@Override
		public void attachCommandListeners() {
			attachCancel();
			attachSave();

		}

		@Override
		public void init(DManUIController duic) {
			attachCommandListeners();
			setContent();
		}

		private void attachCancel() {
			this.btnCancel.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 3061729744640691786L;

				@Override
				public void buttonClick(ClickEvent event) {
					if (iFormData != null)
						new DBookingReportsUI(duic);
					else
						new DDashboardUI(duic);

				}

			});
		}

		private void attachSave() {

			this.btnAdd.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1161782940713725548L;

				@Override
				public void buttonClick(ClickEvent event) {
					/*
					 * TODO Extra validation
					 * 
					 * @Live
					 */

					tFClient.setComponentError(null);
					tFBookingRef.setComponentError(null);
					tFCost.setComponentError(null);
					tFDate.setComponentError(null);

					String client = tFClient.getValue();

					if (client == null || client.trim().isEmpty()) {
						tFClient.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					if (client.trim().length() < 5
							|| client.trim().length() > 15) {
						tFClient.setComponentError(new UserError(
								"Client name should be between 5 and 15 characters"));
						return;
					}

					String brf = tFBookingRef.getValue();
					if (brf == null || brf.trim().isEmpty()) {
						tFBookingRef.setComponentError(new UserError(
								"Field value required."));
						return;
					}
					if (brf.trim().length() < 10 || brf.trim().length() > 20) {
						tFBookingRef
								.setComponentError(new UserError(
										"Booking Ref should be between 10 and 15 Characters."));
						return;
					}
					String scost = tFCost.getValue();
					if (scost == null || scost.trim().isEmpty()) {
						tFCost.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					Double cost = 0D;
					try {
						cost = Double.valueOf(scost);
					} catch (Exception e) {
						tFCost.setComponentError(new UserError(
								"Invalid cost, no letter allowed."));
						return;
					}

					if (cost <= 0) {
						tFCost.setComponentError(new UserError(
								"Flight cost should be greater than 0."));
						return;

					}

					if (cost > 500000) {
						tFCost.setComponentError(new UserError(
								"Flight cost should be greater than USD 500,000"));
						return;
					}

					Date flightDate = tFDate.getValue();

					if (flightDate == null) {
						tFDate.setComponentError(new UserError(
								"Field value required."));
						return;
					}

					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -1);

					if (flightDate.getTime() < cal.getTime().getTime()) {
						tFDate.setComponentError(new UserError(
								"Flight date should be today's date or later"));
						return;
					}

					try {

						if (iFormData != null) {
							Updatebookedflight ubooking = new Updatebookedflight();
							ubooking.setBookingRef(brf);
							ubooking.setClientName(client);
							ubooking.setCost(scost);
							ubooking.setFlightDate(flightDate);
							ubooking.setBookingStatus("0");

							Updatedbookedflightdetails response = MBookingMan
									.updateFlight(ubooking);

							if (response != null
									&& !response.getResponseCode().equals("01")) {
								lbFormHeader.setValue(response.getResponseMsg());
								lbFormHeader.addStyleName("sn-error");
								return;
							}

							lbFormHeader.setValue("SUCCESS! Details updated");
							lbFormHeader.removeStyleName("sn-error");
							new DBookingReportsUI(duic);

							return;

						} else {

							DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

							Bookflight booking = new Bookflight();
							System.out.println("UI FD: "
									+ sdf.format(flightDate));

							booking.setBookingRef(brf);
							booking.setCost(cost);
							booking.setClientName(client);
							booking.setFlightDate(sdf.format(flightDate));

							Bookflightdetails response = MBookingMan
									.bookFlight(booking);

							if (response != null
									&& !response.getResponseCode().equals("01")) {
								lbFormHeader.setValue(response.getResponseMsg());
								lbFormHeader.addStyleName("sn-error");
								return;
							}

							lbFormHeader.setValue(response.getResponseMsg()
									+ "! Client booking details have been added. Enter new booking details if required.");

						}

						lbFormHeader.removeStyleName("sn-error");

						tFClient.clear();
						tFBookingRef.clear();
						tFCost.clear();
						tFDate.clear();

					} catch (Exception e) {
						lbFormHeader
								.setValue("Oops... something is wrong. Please check your connection/Try again.");
						lbFormHeader.addStyleName("sn-error");
						e.printStackTrace();
					}

				}

			});

		}

		private void setContent() {

			this.tFDate.setDateFormat("dd-MM-yyyy");

			if (iFormData != null) {
				String bookingRef = iFormData.getItemProperty("bookingRef")
						.getValue().toString();
				this.tFBookingRef.setValue(bookingRef);
				this.tFBookingRef.setEnabled(false);
				this.tFBookingRef.setReadOnly(true);

				this.tFClient.setValue(iFormData.getItemProperty("clientName")
						.getValue().toString());
				this.tFCost.setValue(iFormData.getItemProperty("cost")
						.getValue().toString());

				String sDate = iFormData.getItemProperty("flightDate")
						.getValue().toString();
				this.tFDate.setDateFormat("dd-MM-yyyy");
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = sdf.parse(sDate);
					this.tFDate.setValue(date);
				} catch (ParseException e) {

					e.printStackTrace();
					this.tFDate.setValue(new Date());
				}

				this.lbFormHeader.setValue("Edit Client Booking Details");
				this.btnAdd.setCaption("Save");

			}
		}

	}

	@Override
	public void setContent() {
		swap(duic, new DBookingFormUIController(duic));
	}

	@Override
	public void init(DManUIController duic) {
		setDUI(duic);
		setContent();

	}

	public Item getiFormData() {
		return iFormData;
	}

	public void setiFormData(Item iFormData) {
		this.iFormData = iFormData;
	}

}
