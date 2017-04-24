package com.lonestarcell.mtn.controller.main;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeSet;

import org.apache.axis2.AxisFault;

import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Persittransactionrecord;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Persittransactionrecordresponse;
import com.eagleairug.onlinepayment.ws.ds.HyperswiftStub.Validbookingresponse;
import com.lonestarcell.mtn.design.main.DDetailsUIDesign;
import com.lonestarcell.mtn.model.main.MInitiatePayment;
import com.lonestarcell.mtn.model.subsys.MTNMomoClient;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;

public class DDetailsUI extends DUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Validbookingresponse details;

	DDetailsUI(DUI duic, Validbookingresponse details) {
		this.details = details;
		init(duic);
	}

	private class DDetailsUIController extends DDetailsUIDesign implements
			DUIControllable {

		DDetailsUIController() {
			init();
		}

		private static final long serialVersionUID = 1L;

		@Override
		public void attachCommandListeners() {
			attachCancel();
			attachBack();
			attachNext();

		}

		@Override
		public void init() {

			setUIState();
			setDetails(details);
			attachCommandListeners();

		}

		private void attachCancel() {
			this.btnCancel.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 8256254316884293815L;

				@Override
				public void buttonClick(ClickEvent event) {

					new DUI();

				}

			});
		}

		private void attachBack() {

			this.btnBack.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 8256254316884293815L;

				@Override
				public void buttonClick(ClickEvent event) {

					new DBookingReferenceUI(duic, details.getBookingrefid());

				}

			});

		}

		private void attachNext() {

			this.fMSISDN.setImmediate(true);
			/*
			 * this.fMSISDN.addValueChangeListener(new ValueChangeListener(){
			 *//**
				 * 
				 */
			/*
			 * private static final long serialVersionUID =
			 * -8274762229961767899L;
			 * 
			 * @Override public void valueChange(ValueChangeEvent event) {
			 * String no = event.getProperty().getValue().toString();
			 * 
			 * validateMTNPhoneNumber(no);
			 * 
			 * }
			 * 
			 * });
			 */
			this.btnNext.addClickListener(new ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 8256254316884293815L;

				@Override
				public void buttonClick(ClickEvent event) {

					lbErrorMsg.removeStyleName("sn-invisible");

					if (!validateMTNPhoneNumber(fMSISDN.getValue().toString()))
						return;

					String dueAmt = lbCostUGX.getValue().replace("UGX", "")
							.replace(",", "");
					dueAmt = dueAmt.trim();

					String msisdn = fMSISDN.getValue();

					if (msisdn.charAt(0) != '0')
						msisdn = "256" + msisdn;
					else
						msisdn = msisdn.replaceFirst("0", "256");

					System.out.println("MSISDN: " + msisdn);

					String msisdnNum = msisdn;
					String bf = lbBookingRef.getValue();
					String acctRef = "FRI:" + bf + "@EagleAir.sp/SP";

					String narration = "Paying Eagle Air for: "
							+ lbName.getValue() + ". Ref: " + bf
							+ " Fair: USD " + details.getRealamount()
							+ " Ex. Rate(UGX): " + details.getFxrate()
							+ ", amount UGX " + dueAmt + ".";

					String txnID = persistInitialRequest(bf, dueAmt, msisdn);
					if (txnID == null) {
						lbErrorMsg
								.setValue("Oops! Something is not right. Please try again.");
						lbErrorMsg.setVisible(true);
						lbNormalMsg.setVisible(false);

						return;
					}

					HashMap<String, String> mapNamVal = new HashMap<String, String>();
					mapNamVal.put("narration", narration);
					mapNamVal.put("msisdnNum", msisdnNum);
					mapNamVal.put("acctRef", acctRef);
					mapNamVal.put("dueAmt", dueAmt);
					mapNamVal.put("processingNo", txnID);

					try {
						if (!new MTNMomoClient()
								.setSendAXIOMAxis2Request(mapNamVal)) {
							lbErrorMsg
									.setValue("Oops! Something is not right. Please try again.");
							lbErrorMsg.setVisible(true);
							lbNormalMsg.setVisible(false);
							return;
						} else {

							lbErrorMsg.setVisible(false);
							lbNormalMsg.setVisible(true);
							new DPaymentStateUIController(bf, msisdn, dueAmt);
						}
					} catch (AxisFault e) {

						if (e.getFaultReasonElement() != null
								&& e.getFaultReasonElement().getText() != null) {

							lbErrorMsg.setValue(e.getFaultReasonElement()
									.getText());
							lbErrorMsg.setVisible(true);
							lbNormalMsg.setVisible(false);

						} else {

							lbErrorMsg
									.setValue("Oops! Something is not right. Please try again.");
							lbErrorMsg.setVisible(true);
							lbNormalMsg.setVisible(false);

						}
						e.printStackTrace();

						return;
					}

				}

			});
		}

		private boolean validateMTNPhoneNumber(String curVal) {

			this.lbErrorMsg.setVisible(true);
			this.lbNormalMsg.setVisible(false);

			HashMap<Integer, String> wordNos = new HashMap<Integer, String>();
			wordNos.put(1, "One");
			wordNos.put(2, "Two");
			wordNos.put(3, "Three");
			wordNos.put(4, "Four");
			wordNos.put(5, "Five");
			wordNos.put(6, "Six");
			wordNos.put(7, "Seven");
			wordNos.put(8, "Eight");
			wordNos.put(9, "Nine");

			if (curVal.trim().equals(null)) {

				System.out.println("State: Invalid character.");
				System.out.println("\nMessage: Only digits allowed.");
				lbErrorMsg.setValue("Please enter phone number.");

				return false;
			}

			System.out.println("Old val: " + curVal);

			curVal = curVal.replace(" ", "").replace("	", "").replace(".", "");
			this.fMSISDN.setValue(curVal);

			Double no = null;
			try {
				no = Double.parseDouble(curVal);
			} catch (Exception e) {

				lbErrorMsg
						.setValue("Invalid character(s). Only Digits allowed.");
				System.out.println("Only Digits allowed.");
				return false;
			}

			if (no == null || no == 0) {
				System.out.println("State: Invalid character.");
				System.out.println("\nMessage: Only digits allowed.");
				lbErrorMsg.setValue("Please enter phone number.");
				return false;
			}

			System.out.println("New val: " + curVal);

			int curValLen = curVal.length();

			boolean startsWithZero = curVal.indexOf("0") == 0;
			if (!startsWithZero) {
				curVal = "0" + curVal;
				curValLen = curVal.length();
			}

			if (curValLen < 10) {
				System.out.println("\nState: MTN Phone number has less digits");
				System.out.println("Help: about " + wordNos.get(10 - curValLen)
						+ " digit(s) missing!");

				lbErrorMsg
						.setValue("MTN Phone number has less digits. Hint: about "
								+ wordNos.get(10 - curValLen)
								+ " digit(s) missing!");
				return false;
			}

			if (curValLen > 10) {
				String excess = wordNos.get(curValLen - 10);
				if (excess == null) {

					System.out
							.println("\nState: MTN Phone number has excess digits");
					System.out.println("Help: about too many excess digit(s)!");

					lbErrorMsg
							.setValue("MTN Phone number has excess digits. Hint: about too many excess digit(s)!");

				} else {

					System.out
							.println("\nState: MTN Phone number has excess digits");
					System.out
							.println("Help: remove about "
									+ wordNos.get(curValLen - 10)
									+ " excess digit(s)!");

					lbErrorMsg.setValue("MTN Phone number has excess digits "
							+ "Hint: remove about "
							+ wordNos.get(curValLen - 10) + " digit(s)!");

				}
				return false;
			}

			boolean secondDigitValid = curVal.indexOf("7") == 1;
			char[] chars = curVal.toCharArray();
			if (!secondDigitValid) {
				System.out.println("State: Invalid second digit");
				System.out.println("Message: Should be 7, but you have "
						+ chars[1]);
				lbErrorMsg.setValue("Invalid second digit "
						+ "Hint: should be 7, but you have " + chars[1]);

				return false;
			}

			TreeSet<String> tsFormats = new TreeSet<String>();
			tsFormats.add("7");
			tsFormats.add("8");
			String thirdDigit = curVal.substring(2, 3);
			boolean thirdDigitValid = tsFormats.contains(thirdDigit);
			if (!thirdDigitValid) {
				System.out.println("State: Invalid third digit");
				System.out.println("\nMessage: should be 7 or 8, you have "
						+ thirdDigit);
				lbErrorMsg.setValue("Invalid third digit "
						+ "Hint: should be 7 or 8, you have " + thirdDigit);
				return false;
			}

			System.out
					.println("\nState: Great! Seems your mtn phone number has valid format");
			System.out
					.println("Message: You may proceed to submit your request.");

			return true;

		}

		private String persistInitialRequest(String bf, String dueAmount,
				String msisdn) {
			Persittransactionrecord transactionPayload = new HyperswiftStub.Persittransactionrecord();
			transactionPayload.setIbookingrefid(bf);
			transactionPayload.setIpartnerstatusid("0");
			transactionPayload.setIpartnertxnid("0");
			transactionPayload.setIratetxnid("0");
			transactionPayload.setIamount(Double.parseDouble(dueAmount));
			transactionPayload.setImsisdn(msisdn);

			Persittransactionrecordresponse response = MInitiatePayment
					.initiatePayment(transactionPayload);
			if (response == null)
				return null;
			else {
				System.out.println("Message: " + response.getStatusmessage());
				System.out.println("Code: " + response.getResponseCode());
				System.out.println("Txn ID: " + response.getPartnertxnid());
				if (response.getResponseCode().equals("01"))
					return response.getTrxnid();
				else
					return null;

			}

		}

		private void setDetails(Validbookingresponse details) {
			if (details == null) {
				System.err.println("Null details things in DDetails.");
				return;
			}

			if (!details.getResponseCode().equals("01")) {
				System.err.println("Invalid booking reference.");
				return;
			}

			NumberFormat fm = NumberFormat.getCurrencyInstance(Locale.US);
			DecimalFormat dfm = (DecimalFormat) fm;
			DecimalFormatSymbols dfms = dfm.getDecimalFormatSymbols();
			dfms.setCurrencySymbol("USD ");
			dfm.setDecimalFormatSymbols(dfms);

			this.lbName.setValue(details.getClientname());
			this.lbBookingRef.setValue(details.getBookingrefid());
			this.lbCostUSD.setValue(dfm.format(BigDecimal.valueOf(Double
					.valueOf(details.getRealamount()))));

			dfms = dfm.getDecimalFormatSymbols();
			dfms.setCurrencySymbol("UGX ");
			dfm.setDecimalFormatSymbols(dfms);

			this.lbRate.setValue(dfm.format(BigDecimal.valueOf(Double
					.valueOf(details.getFxrate()))));
			this.lbCostUGX.setValue(dfm.format(BigDecimal.valueOf(Double
					.parseDouble(details.getConvamount()))));
			UI.getCurrent().getSession()
					.setAttribute("rate_id", details.getFxrate());

		}

		@Override
		public void setUIState() {
			this.lbErrorMsg.setVisible(false);
			this.lbNormalMsg.setVisible(true);
			this.fMSISDN.setImmediate(true);
		}

	}

	@Override
	public void setContent() {
		swap(duic, new DDetailsUIController());
	}

	@Override
	public void init(DUI duic) {
		this.duic = duic;
		setContent();
	}

	public static void main(String[] args) {
		/*
		 * String curVal = null;
		 * 
		 * Scanner scanner = new Scanner(System.in); curVal =
		 * String.valueOf(scanner.nextLine());
		 * while(validateMTNPhoneNumber(curVal)){
		 * 
		 * main(null); }
		 */

	}

}
