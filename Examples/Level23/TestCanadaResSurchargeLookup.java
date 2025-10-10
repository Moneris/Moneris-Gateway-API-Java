package Canada;

import JavaAPI.HttpsPostRequest;
import JavaAPI.Receipt;
import JavaAPI.ResSurchargeLookup;


public class TestCanadaResSurchargeLookup
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "moneris";
		String api_token = "hurgle";
		String cust_id = "my customer id";
		String amount = "50.00";
		String data_key = "ot-Z40iTM8rWF4vWwTo6daXCJtj4";
		String processing_country_code = "CA";
		boolean status_check = false;

		ResSurchargeLookup resSurchargeLookup = new ResSurchargeLookup();
		resSurchargeLookup.setDataKey(data_key);
		resSurchargeLookup.setAmount(amount);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resSurchargeLookup);
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
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Masked Pan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());



		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
