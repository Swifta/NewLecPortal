package com.lonestarcell.mtn.model.subsys;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
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
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;

public class MTNMomoClient {

	public boolean setSendAXIOMAxis2Request(Map<String, String> mp)
			throws AxisFault {

		SOAPFactory sFac = OMAbstractFactory.getSOAP11Factory();
		SOAPEnvelope soapEnv = sFac.getDefaultEnvelope();

		OMNamespace omnS = sFac.createOMNamespace(
				"http://schemas.xmlsoap.org/soap/envelope/", "soapenv");
		OMNamespace omnM = sFac.createOMNamespace(
				"http://b2b.mobilemoney.mtn.zm_v1.0", "b2b");
		soapEnv.setNamespace(omnM);
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
				XMLConstants.NULL_NS_URI, "processPayment", "b2b"));
		omProcessRequest.setNamespace(omnM);

		OMElement omServiceId2 = sFac.createOMElement(new QName("serviceId"));
		omServiceId2.setText("0000000000000000");
		omProcessRequest.addChild(omServiceId2);

		OMElement omParameter = sFac.createOMElement(new QName("parameter"));
		OMElement omPN = sFac.createOMElement(new QName("name"));
		OMElement omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("DueAmount");
		omPV.setText(mp.get("dueAmt"));
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);

		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("MSISDNNum");
		omPV.setText(mp.get("msisdnNum"));
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);

		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("ProcessingNumber");
		omPV.setText(mp.get("processingNo"));
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);

		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("AcctRef");
		omPV.setText(mp.get("acctRef"));
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);

		omParameter = sFac.createOMElement(new QName("parameter"));
		omPN = sFac.createOMElement(new QName("name"));
		omPV = sFac.createOMElement(new QName("value"));
		omPN.setText("Narration");
		omPV.setText(mp.get("narration"));
		omParameter.addChild(omPN);
		omParameter.addChild(omPV);
		omProcessRequest.addChild(omParameter);

		System.out.println("SOAPHeader: " + omRSH.toString());
		System.out.println("Payload: " + omProcessRequest.toString());

		sHeader.addChild(omRSH);
		sBody.addChild(omProcessRequest);

		return sendAXIOMRequest(soapEnv, "");
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

			// target = "http://127.0.0.1:8280/services/EaglePaymentProxy";

			// target = "http://54.209.44.17:8280/services/eagleairpayment";
			// target =
			// "https://portal.mfisa.com/services/t/ug.com/eagleairpayment";
			target = "https://portal.mfisa.com/services/t/ug.com/eagleairpayment";
			// target = "http://127.0.0.1:8280/services/eagleairpayment";
			// target = "http://127.0.0.1:8280/services/hyperswift";

			EndpointReference targetEPR = new EndpointReference(target);
			// EndpointReference targetEPR = new
			// EndpointReference("http://54.209.44.17:9764/services/hyperswift");
			Options opts = new Options();

			opts.setTo(targetEPR);

			KeyStore keyStore = null;

			keyStore = KeyStore.getInstance("JKS");
			instream = new FileInputStream(new File(
					"C:/Users/pective/Desktop/store/mykeystore.jks"));

			keyStore.load(instream, "l!v3@L!v3".toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			// Initialise the TMF as you normally would, for example:
			tmf.init((KeyStore) keyStore);

			TrustManager[] trustManagers = tmf.getTrustManagers();
			final X509TrustManager origTrustmanager = (X509TrustManager) trustManagers[0];

			TrustManager[] wrappedTrustManagers = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return origTrustmanager.getAcceptedIssuers();
				}

				public void checkClientTrusted(X509Certificate[] certs,
						String authType) {
					try {
						origTrustmanager.checkClientTrusted(certs, authType);
					} catch (CertificateException e) {

					}
				}

				public void checkServerTrusted(X509Certificate[] certs,
						String authType) {
					try {
						origTrustmanager.checkServerTrusted(certs, authType);
					} catch (CertificateException e) {
					}
				}
			} };

			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, "l!v3@L!v3".toCharArray())
					.build();

			sslcontext.init(null, wrappedTrustManagers, null);

			HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext
					.getSocketFactory());

			SSLContext.setDefault(sslcontext);

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
								new QName("http://ws.wso2.org/dataservice",
										"persittransactionrecordresponses"))
						.getFirstChildWithName(
								new QName("http://ws.wso2.org/dataservice",
										"persittransactionrecordresponse"))
						.getChildElements();

				String responseCode = null;
				while (omList.hasNext()) {
					OMElement om = omList.next();
					if (om.getLocalName().equals("responseCode")) {
						responseCode = om.getText();
					}

					System.err.println(om.getLocalName() + " :" + om.getText());
				}

				System.out.println("Initiation Response Code: " + responseCode);
				return responseCode.equals("01");

			}

			httpclient.close();

			return true;

		} catch (Exception e) {
			if (httpclient != null)
				try {
					httpclient.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			e.printStackTrace();

			return false;

		} finally {
			if (instream != null)
				try {
					instream.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
		}
	}

	private static void createJKS() {

		try {

			// System.err.println("Status: " +
			// momo.setSendAXIOMAxis2Request(mp));

			String keypass = "l!v3@L!v3"; // this is a new password, you need to
											// come
											// up with to protect your java key
											// store file
			String defaultalias = "importkey";
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");

			// this section does not make much sense to me,
			// but I will leave it intact as this is how it was in the original
			// example I found on internet:
			ks.load(null, keypass.toCharArray());
			ks.store(new FileOutputStream("mykeystore"), keypass.toCharArray());
			ks.load(new FileInputStream("mykeystore"), keypass.toCharArray());
			// end of section..

			// read the key file from disk and create a PrivateKey

			FileInputStream fis = new FileInputStream(
					"C:/Users/pective/Desktop/store/matsng.der");
			DataInputStream dis = new DataInputStream(fis);
			byte[] bytes = new byte[dis.available()];
			dis.readFully(bytes);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

			byte[] key = new byte[bais.available()];
			KeyFactory kf = KeyFactory.getInstance("RSA");
			bais.read(key, 0, bais.available());
			bais.close();

			PKCS8EncodedKeySpec keysp = new PKCS8EncodedKeySpec(key);
			PrivateKey ff = kf.generatePrivate(keysp);

			// read the certificates from the files and load them into the key
			// store:

			Collection<?> col_crt1 = CertificateFactory
					.getInstance("X509")
					.generateCertificates(
							new FileInputStream(
									"C:/Users/pective/Desktop/store/portal.mfisa.com.pem"));

			Certificate crt1 = (Certificate) col_crt1.iterator().next();

			Certificate[] chain = new Certificate[] { crt1 };

			String alias1 = ((X509Certificate) crt1).getSubjectX500Principal()
					.getName();

			ks.setCertificateEntry(alias1, crt1);

			// store the private key
			ks.setKeyEntry(defaultalias, ff, keypass.toCharArray(), chain);

			// save the key store to a file
			ks.store(new FileOutputStream(
					"C:/Users/pective/Desktop/store/mykeystore.jks"), keypass
					.toCharArray());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		MTNMomoClient momo = new MTNMomoClient();

		String narration = "Paying Eagle Air for: Amanda Rose. Ref:BU20160417AW01 Fair: $600.00. Ex. Rate: 3320/= ";
		String msisdn = "256709999568";
		String msisdnNum = msisdn;
		String bf = "BU20160513SAS002";
		String acctRef = "FRI:" + bf + "@EagleAir.sp/SP";

		BigDecimal dueAmt = BigDecimal.valueOf(724);

		HashMap<String, String> mp = new HashMap<String, String>();

		mp.put("narration", narration);
		mp.put("msisdnNum", msisdnNum);
		mp.put("acctRef", acctRef);
		mp.put("dueAmt", String.valueOf(dueAmt));
		mp.put("processingNo", "6752");

		try {

			System.err.println("Status: " + momo.setSendAXIOMAxis2Request(mp));

			// createJKS();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
