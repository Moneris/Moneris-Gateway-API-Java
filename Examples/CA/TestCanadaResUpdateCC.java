package Canada;

import JavaAPI.*;

public class TestCanadaResUpdateCC
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String data_key = "vthBJyN1BicbRkdWFZ9flyDP2";
		String pan = "4242424242424242";
		String expdate = "1901";
		String phone = "0000000000";
		String email = "bob@smith.com";
		String note = "my note";
		String cust_id = "customer1";
		String crypt_type = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");

		//Credential on File details
		CofInfo cof = new CofInfo();
		cof.setIssuerId("139X3130ASCXAS9");
		
		ResUpdateCC resUpdateCC = new ResUpdateCC();
		resUpdateCC.setDataKey(data_key);
		resUpdateCC.setAvsInfo(avsCheck);
		resUpdateCC.setCustId(cust_id);
		resUpdateCC.setPan(pan);
		resUpdateCC.setExpdate(expdate);
		resUpdateCC.setPhone(phone);
		resUpdateCC.setEmail(email);
		resUpdateCC.setNote(note);
		resUpdateCC.setCryptType(crypt_type);
		resUpdateCC.setCofInfo(cof);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resUpdateCC);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
