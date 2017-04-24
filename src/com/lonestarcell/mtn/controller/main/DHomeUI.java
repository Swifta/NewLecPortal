package com.lonestarcell.mtn.controller.main;

import com.lonestarcell.mtn.design.main.DHomeUIDesign;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class DHomeUI extends DUI {

	private static final long serialVersionUID = 1L;

	DHomeUI(DUI duic) {
		System.out.println("DHomeUI Contructor called.");
		init(duic);
	}

	private class DHomeUIController extends DHomeUIDesign implements
			DUIControllable {

		private static final long serialVersionUID = 1L;

		DHomeUIController() {
			System.out.println("Controller called.");
			init();
		}

		@Override
		public void attachCommandListeners() {
			System.out.println("Controls attachedx.");
			this.btnGetStarted.addClickListener(new ClickListener() {
				private static final long serialVersionUID = -2145343495982704951L;

				@Override
				public void buttonClick(ClickEvent event) {
					System.out.println("Button click active.");
					new DPaymentUI(duic);

				}

			});

		}

		@Override
		public void init() {
			attachCommandListeners();
		}

		@Override
		public void setUIState() {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void setContent() {
		System.out.println("Swap is called.");
		swap(duic, new DHomeUIController());
	}

	@Override
	public void init(DUI duic) {
		this.duic = duic;
		setContent();
		
	}

}
