package USA;

import JavaAPI.*;

public class TestUSAResUpdatePinless
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String data_key = "AhcyWhamRPNnhyU8RYPxM3saK";
		String pan = "4242424242424242";
		String expdate = "1911"; //YYMM format
		String phone = "0000000000";
		String email = "bob@smith.com";
		String note = "my note";
		String cust_id = "customer1";
		String presentation_type = "W";
		String p_account_number = "23456789";
		String processing_country_code = "US";
		boolean status_check = false;

		ResUpdatePinless resUpdatePinless = new ResUpdatePinless();
		resUpdatePinless.setDataKey(data_key);
		resUpdatePinless.setCustId(cust_id);
		resUpdatePinless.setPan(pan);
		resUpdatePinless.setExpdate(expdate);
		resUpdatePinless.setPhone(phone);
		resUpdatePinless.setEmail(email);
		resUpdatePinless.setNote(note);
		resUpdatePinless.setPresentationType(presentation_type);
		resUpdatePinless.setPAccountNumber(p_account_number);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resUpdatePinless);
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
			System.out.println("Presentation Type = " + receipt.getResPresentationType());
			System.out.println("P Account Number = " + receipt.getResPAccountNumber());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
