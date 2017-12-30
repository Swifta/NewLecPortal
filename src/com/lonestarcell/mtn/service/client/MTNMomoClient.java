package com.lonestarcell.mtn.service.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.TransportUtils;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.InMo;
import com.lonestarcell.mtn.model.sms.MTNLECPaymentServiceStub;
import com.lonestarcell.mtn.model.sms.MTNLECPaymentServiceStub.ReceivePaymentConfirmation;
import com.lonestarcell.mtn.model.sms.MTNLECPaymentServiceStub.ReceivePaymentConfirmationResponse;
import com.lonestarcell.mtn.model.sms.MTNLECPaymentServiceStub.TransactionResponse;

public class MTNMomoClient {

	private Logger log = LogManager.getLogger( MTNMomoClient.class.getName() );
	public boolean setSendAXIOMAxis2Request( InMo in, String epr )
			throws AxisFault {

		SOAPFactory sFac = OMAbstractFactory.getSOAP11Factory();
		SOAPEnvelope soapEnv = sFac.getDefaultEnvelope();

		OMNamespace omnS = sFac.createOMNamespace(
				"http://schemas.xmlsoap.org/soap/envelope/", "soapenv");
		
		//soapEnv.setNamespace(omnM);
		soapEnv.setNamespace(omnS);

		SOAPBody sBody = soapEnv.getBody();
		SOAPHeader sHeader = soapEnv.getHeader();

		QName qnRSH = new QName("http://www.huawei.com.cn/schema/common/v2_1",
				"RequestSOAPHeader", XMLConstants.DEFAULT_NS_PREFIX);
		OMElement omRSH = sFac.createOMElement(qnRSH);
		OMElement omSpId = sFac.createOMElement(new QName("spId"));
		omSpId.setText("2560110004722");
		OMElement omSPassword = sFac.createOMElement(new QName("spPassword"));
		omSPassword.setText("00000000");
		OMElement omServiceId = sFac.createOMElement(new QName("serviceId"));
		omServiceId.setText("00000000");
		OMElement omTimeStamp = sFac.createOMElement(new QName("timeStamp"));
		omTimeStamp.setText("20160520224642");
		OMElement omBundleID = sFac.createOMElement(new QName("BundleID"));
		// omBundleID.setText("");

		omRSH.addChild(omSpId);
		omRSH.addChild(omSPassword);
		omRSH.addChild(omBundleID);
		omRSH.addChild(omServiceId);
		omRSH.addChild(omTimeStamp);

		OMElement omProcessRequest = sFac.createOMElement(new QName(
				XMLConstants.NULL_NS_URI, "processRequest", "b2b"));
		OMNamespace omnM = sFac.createOMNamespace(
				"http://b2b.mobilemoney.mtn.zm_v1.0/", "b2b");
		omProcessRequest.setNamespace(omnM);

		OMElement omServiceId2 = sFac.createOMElement(new QName("serviceId"));
		omServiceId2.setText("0000000000000000");
		omProcessRequest.addChild(omServiceId2);

		OMElement omParameter = sFac.createOMElement(new QName("parameter"));
		OMElement omPN = sFac.createOMElement(new QName("name"));
		OMElement omPV = sFac.createOMElement(new QName("value"));
		
		omPN.setText("ProcessingNumber");
		omPV.setText( in.getMmoId() );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText( "senderID" );
		omPV.setText( "MOM" );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("AcctRef");
		omPV.setText( in.getAcctRef() );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("RequestAmount");
		omPV.setText( in.getAmount() );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("paymentRef");
		omPV.setText( "0" );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("ThirdPartyTransactionID");
		omPV.setText( "0" );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		

		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("MOMAcctNum");
		omPV.setText( in.getMsisdn() );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText( "CustName" );
		omPV.setText( in.getMsisdn()  );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText( "TXNType" );
		omPV.setText( "" );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText( "StatusCode" );
		omPV.setText( "" );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText( "OpCoID" );
		omPV.setText( "" );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);
		
	

		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("Cur");
		omPV.setText( in.getCurrency() );
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);

		System.out.println("SOAPHeader: " + omRSH.toString());
		System.out.println("Payload: " + omProcessRequest.toString());

		sHeader.addChild(omRSH);
		sBody.addChild(omProcessRequest);

		return sendAXIOMRequest(soapEnv, epr );
	}
	

	public boolean sendSMS( InMo in, String epr ){
			
			
			try {
				
				// Disable port TODO 
				MTNLECPaymentServiceStub stub = new MTNLECPaymentServiceStub( epr );
				
				ReceivePaymentConfirmation comfirm = new MTNLECPaymentServiceStub.ReceivePaymentConfirmation();
				comfirm.setAccountRef( in.getAcctRef() );
				comfirm.setAmount( Double.valueOf( in.getAmount() ) );
				comfirm.setCur( in.getCurrency() );
				comfirm.setCustomerName( in.getMsisdn() );
				comfirm.setFundamoTransactionID( in.getMmoId() );
				comfirm.setFundamoUserId( "modupe" );
				comfirm.setFundamoPassword( "modupel" );
				comfirm.setPayerAccountIdentifier( in.getMsisdn() );
				comfirm.setPaymentRef( "Paying LEC bills." );
				
				comfirm.setStatusCode( "04" );
				
				ReceivePaymentConfirmationResponse comfirmResponse = stub.receivePaymentConfirmation( comfirm );
				
				TransactionResponse transactionResponse = comfirmResponse.get_return();
				log.debug( transactionResponse.getResponseCode() );
				return transactionResponse.getResponseCode().equals( "01" );
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return false;
		
	}

	@SuppressWarnings("unchecked")
	private boolean sendAXIOMRequest(SOAPEnvelope soapEnv, String target)
			throws AxisFault {
		/*
		 * TODO Delete test endpoint
		 * 
		 * @Live
		 */

		FileInputStream instream = null;
		CloseableHttpClient httpclient = null;

		try {

			//target = "http://127.0.0.1:8280/services/lecpaymentservice";
			//target = "http://127.0.0.1:8280/services/lecproxyxxxx";

			EndpointReference targetEPR = new EndpointReference(target);
			Options opts = new Options();

			opts.setTo(targetEPR);


			opts.setAction("requestPaymentRequest");
			opts.setTransportInProtocol(Constants.TRANSPORT_HTTPS);

			System.out.println("Target endpoint used: " + target);
			System.out.println("SOAPContent: " + soapEnv.toString());

			SOAPEnvelope sEnv = TransportUtils.createSOAPEnvelope(soapEnv);
			MessageContext mCxt = new MessageContext();

			mCxt.setEnvelope(sEnv);

			ServiceClient client = new ServiceClient();
			client.setOptions(opts);

			OperationClient opClient = client
					.createClient(ServiceClient.ANON_OUT_IN_OP);

			opClient.addMessageContext(mCxt);

			opClient.execute(true);

			System.err.println("Request sent successfully.");
			SOAPEnvelope sEnvResponse = opClient.getMessageContext(
					WSDLConstants.MESSAGE_LABEL_IN_VALUE).getEnvelope();

			if (sEnvResponse == null)
				System.err.println("No response received");
			else {
				System.out.println("Response successfully received TRUE.");
				System.out.println("");
				System.out.println(sEnvResponse.toString());

				Iterator<OMElement> omList = sEnvResponse
						.getBody()
						.getFirstChildWithName(
								new QName("http://b2b.mobilemoney.mtn.zm_v1.0/",
										"processRequestResponse"))
						.getChildElements();

				String responseCode = null;
				while (omList.hasNext()) {
					OMElement om = omList.next();
					log.debug( om.getFirstElement().getText() );
					if ( om.getFirstElement().getText().equals( "StatusCode" ) ) {
						responseCode = om.getFirstChildWithName( new QName("value") ).getText();
						
					}

				}

				log.debug( "Initiation Response Code: " + responseCode );
				if( responseCode == null )
					return false;
				
				return responseCode.equals("1000");

			}

			return true;

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;

		} finally {
			if( httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (instream != null)
				try {
					instream.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
		}
	}

}
