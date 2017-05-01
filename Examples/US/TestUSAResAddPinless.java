package USA;

import JavaAPI.*;

public class TestUSAResAddPinless
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String phone = "0000000000";
		String email = "bob@smith.com";
		String note = "my note";
		String cust_id = "customer1";
		String pan = "4242424242424242";
		String expdate = "1902";        //YYMM format
		String presentation_type = "W";
		String p_account_number = "123123213123213213123123";
		String processing_country_code = "US";
		boolean status_check = false;

		ResAddPinless resAddPinless = new ResAddPinless();
		resAddPinless.setPan(pan);
		resAddPinless.setPresentationType(presentation_type);
		resAddPinless.setPAccountNumber(p_account_number);
		resAddPinless.setCustId(cust_id);
		resAddPinless.setPhone(phone);
		resAddPinless.setEmail(email);
		resAddPinless.setNote(note);
		resAddPinless.setExpdate(expdate);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resAddPinless);
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
