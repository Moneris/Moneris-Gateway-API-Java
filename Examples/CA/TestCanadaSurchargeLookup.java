package Canada;

import JavaAPI.HttpsPostRequest;
import JavaAPI.SurchargeLookup;
import JavaAPI.Receipt;

public class TestCanadaSurchargeLookup
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "moneris";
		String api_token = "hurgle";
		String cust_id = "my customer id";
		String amount = "20.00";
		String pan = "4761260000000134";
		String expdate = "2012"; //YYMM
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		SurchargeLookup surchargeLookup = new SurchargeLookup();

		surchargeLookup.setAmount(amount);
		surchargeLookup.setPan(pan);


		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(surchargeLookup);
		mpgReq.setStatusCheck(status_check);
		System.out.println(mpgReq.toXML());
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("IsSurchargeEligible = " + receipt.getIsSurchargeEligible());
			System.out.println("MaxSurchargeRate = " + receipt.getMaxSurchargeRate());
			System.out.println("MaxSurchargeAmount = " + receipt.getMaxSurchargeAmount());
			System.out.println("ServiceType = " + receipt.getServiceType());


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
