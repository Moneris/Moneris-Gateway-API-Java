package Canada;

import JavaAPI.*;

public class TestCanadaMpiCardLookup
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String pan = "4740611374762707";
		
		String processing_country_code = "CA";

		MpiCardLookup mpiCardLookup = new MpiCardLookup();
		mpiCardLookup.setOrderId(order_id);
		mpiCardLookup.setPan(pan);
		//mpiCardLookup.setDataKey("8OOXGiwxgvfbZngigVFeld9d2"); //Optional - For Moneris Vault and Hosted Tokenization tokens in place of setPan
		mpiCardLookup.setNotificationUrl("https://yournotificationurl.com");  //(Website URL that will receive 3DS Method Completion response from ACS)

		//************************OPTIONAL VARIABLES***************************

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mpiCardLookup);
		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("MessageType = " + receipt.getMpiMessageType());
			System.out.println("ThreeDSMethodURL = " + receipt.getMpiThreeDSMethodURL());
			System.out.println("ThreeDSMethodData = " + receipt.getMpiThreeDSMethodData());
			System.out.println("ThreeDSServerTransId = " + receipt.getMpiThreeDSServerTransId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
