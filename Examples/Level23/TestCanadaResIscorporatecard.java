package Canada;

import JavaAPI.*;

public class TestCanadaResIscorporatecard
{
	public static void main(String[] args)
	{
		String store_id = "store1";
		String api_token = "yesguy";
		String data_key = "eLqsADfwqHDxIpJG9vLnELx01";
		String processing_country_code = "CA";
		boolean status_check = false;

		ResIscorporatecard resIscorporatecard = new ResIscorporatecard();
		resIscorporatecard.setDataKey(data_key);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resIscorporatecard);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("CorporateCard = " + receipt.getCorporateCard());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
