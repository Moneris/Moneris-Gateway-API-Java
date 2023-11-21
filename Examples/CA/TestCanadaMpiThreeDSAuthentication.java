package Canada;

import JavaAPI.*;

import java.util.Hashtable;

public class TestCanadaMpiThreeDSAuthentication
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
//		String store_id = "monca02932";
		String api_token = "hurgle";
//		String api_token = "CG8kYzGgzVU5z23irgMx";

		String processing_country_code = "CA";

		MpiThreeDSAuthentication mpiThreeDSAuthentication = new MpiThreeDSAuthentication();
        mpiThreeDSAuthentication.setOrderId("Test159787ssa3215193");	//must be the same one that was used in MpiCardLookup call
        mpiThreeDSAuthentication.setCardholderName("Moneris Test");
        mpiThreeDSAuthentication.setPan("347668693641199");
//        mpiThreeDSAuthentication.setDataKey("xRl9O4FgrZUYdGkmqHTqiEw97"); //Optional - For Moneris Vault and Hosted Tokenization tokens in place of setPan
        mpiThreeDSAuthentication.setExpdate("2310");
        mpiThreeDSAuthentication.setAmount("1.00");
        mpiThreeDSAuthentication.setThreeDSCompletionInd("Y"); //(Y|N|U) indicates whether 3ds method MpiCardLookup was successfully completed
        mpiThreeDSAuthentication.setRequestType("01"); //(01=payment|02=recur)
        mpiThreeDSAuthentication.setPurchaseDate("20200819035249"); //(YYYYMMDDHHMMSS)
        mpiThreeDSAuthentication.setNotificationURL("https://yournotificationurl.com"); //(Website where response from RRes or CRes after challenge will go)
        mpiThreeDSAuthentication.setChallengeWindowSize("03"); //(01 = 250 x 400, 02 = 390 x 400, 03 = 500 x 600, 04 = 600 x 400, 05 = Full screen)
		mpiThreeDSAuthentication.setEmail("test@email.com");

		mpiThreeDSAuthentication.setBrowserUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36\\");
        mpiThreeDSAuthentication.setBrowserJavaEnabled("true"); //(true|false)
        mpiThreeDSAuthentication.setBrowserScreenHeight("1000"); //(pixel height of cardholder screen)
        mpiThreeDSAuthentication.setBrowserScreenWidth("1920"); //(pixel width of cardholder screen)
        mpiThreeDSAuthentication.setBrowserLanguage("en-GB"); //(defined by IETF BCP47)
        
        //Optional Methods
        mpiThreeDSAuthentication.setBillAddress1("3300 Bloor St W");
        mpiThreeDSAuthentication.setBillProvince("ON");
        mpiThreeDSAuthentication.setBillCity("Toronto");
        mpiThreeDSAuthentication.setBillPostalCode("M8X 2X2");
        mpiThreeDSAuthentication.setBillCountry("124");
        
        mpiThreeDSAuthentication.setShipAddress1("3300 Bloor St W");
        mpiThreeDSAuthentication.setShipProvince("ON");
        mpiThreeDSAuthentication.setShipCity("Toronto");
        mpiThreeDSAuthentication.setShipPostalCode("M8X 2X2");
        mpiThreeDSAuthentication.setShipCountry("124");
        
//        mpiThreeDSAuthentication.setRequestChallenge("Y"); //(Y|N Requesting challenge regardless of outcome)
		//**********************3DS2.2*****************************************
//		mpiThreeDSAuthentication.setMessageCategory("02");
//		mpiThreeDSAuthentication.setDeviceChannel("03");
//		mpiThreeDSAuthentication.setDecoupledRequestIndicator("Y");
//		mpiThreeDSAuthentication.setDecoupledRequestMaxTime("00010");
//		mpiThreeDSAuthentication.setDecoupledRequestAsyncUrl("https://yourasyncnotificationurl.com");
//		mpiThreeDSAuthentication.setRiIndicator("03");
//		mpiThreeDSAuthentication.setRecurringExpiry("20221230");
//		mpiThreeDSAuthentication.setRecurringFrequency("031");

//		Hashtable<String, String> priorParams = new Hashtable<>();
//		priorParams.put("prior_request_auth_data","fc8a0fbd-a686-4ac2-bd2b-1be7a9e7808b");
//		priorParams.put("prior_request_ref","cb0b027b-6f1e-4591-ae94-81859037c033");
//		priorParams.put("prior_request_auth_method","01");
//		priorParams.put("prior_request_auth_timestamp","202308151640");
//		PriorAuthenticationInfo pai = new PriorAuthenticationInfo(priorParams);
//		mpiThreeDSAuthentication.setPriorRequestAuthInfo(pai);

		//************************OPTIONAL VARIABLES***************************

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mpiThreeDSAuthentication);
		System.out.println(mpiThreeDSAuthentication.toXML());
		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("Message = " + receipt.getMessage());
			
			System.out.println("MessageType = " + receipt.getMpiMessageType());
			System.out.println("TransStatus = " + receipt.getMpiTransStatus());
			System.out.println("TransStatusReason = " + receipt.getMpiTransStatusReason());
			System.out.println("ChallengeURL = " + receipt.getMpiChallengeURL());
			System.out.println("ChallengeData = " + receipt.getMpiChallengeData());
			System.out.println("ThreeDSServerTransId = " + receipt.getMpiThreeDSServerTransId());

			System.out.println("ThreeDSVersion = " + receipt.getThreeDSVersion());
			System.out.println("ThreeDSAcsTransID = " + receipt.getMpiThreeDSAcsTransID());
			System.out.println("ThreeDSDSTransID = " + receipt.getMpiDSTransId());
			System.out.println("ThreeDSAuthTimeStamp = " + receipt.getMpiThreeDSAuthTimeStamp());
			System.out.println("CardholderInfo = " + receipt.getMpiCardholderInfo());
			System.out.println("AuthenticationType = " + receipt.getMpiAuthenticationType());

			//In Frictionless flow, you may receive TransStatus as "Y", 
		    //in which case you can then proceed directly to Cavv Purchase/Preauth with values below
		    if(receipt.getMpiTransStatus().equals("Y"))
		    {
		    	System.out.println("Cavv = " + receipt.getMpiCavv());
		    	System.out.println("ECI = " + receipt.getMpiEci());
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
