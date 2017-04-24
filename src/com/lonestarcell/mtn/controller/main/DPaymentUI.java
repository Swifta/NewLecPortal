package com.lonestarcell.mtn.controller.main;

import com.lonestarcell.mtn.design.main.DPaymentUIDesign;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.HorizontalLayout;

public class DPaymentUI extends DUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DPaymentUI(DUI duic) {
		init(duic);
	}

	private class DPaymentUIController extends DPaymentUIDesign implements
			DUIControllable {

		DPaymentUIController() {
			System.out.println("Payment opt Controller called.");
			init();
		}

		private static final long serialVersionUID = 1L;

		@Override
		public void attachCommandListeners() {
			attachMTN();

		}

		@Override
		public void init() {
			attachCommandListeners();

		}

		private void attachMTN() {
			HorizontalLayout listItem = this.listItemMTN;

			listItem.addLayoutClickListener(new LayoutClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 4856081838626048379L;

				@Override
				public void layoutClick(LayoutClickEvent event) {
					new DBookingReferenceUI(duic, null);
				}

			});
		}

		@Override
		public void setUIState() {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void setContent() {
		swap(duic, new DPaymentUIController());
	}

	@Override
	public void init(DUI duic) {
		this.duic = duic;
		setContent();
	}

}
