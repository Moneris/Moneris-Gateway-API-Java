package Canada;
import java.io.*;

import JavaAPI.*;

public class TestCanadaEncContactlessPreauth
{
	public static void main(String args[]) throws Exception
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "moneris";
		String api_token = "hurgle";
		String amount = "1.00";
		String enc_track2 = "D2hOIdtikJATjZ41F496w6+onEjcirU8rmdY+mFDu5IjA6LeRiqyGOV4cb1Ds7nRr5Z4fABACblOe9Ed0TBWtnR65GibqAxTir+qMvzftv/OuDpq3iL5922FHAK4jvJ7MWn3reOrin+OpjOaCMX1fxAom4B2v5mNN+Ip9Om1bmk=";
		String pan = "";
		String expdate = "";
		String pos_code = "00";
		String device_type = "public";
		String processing_country_code = "CA";

		EncContactlessPreauth enc_contactless_preauth = new EncContactlessPreauth();
		enc_contactless_preauth.setOrderId(order_id);
		enc_contactless_preauth.setAmount(amount);
		enc_contactless_preauth.setEncTrack2(enc_track2);
		enc_contactless_preauth.setPan(pan);
		enc_contactless_preauth.setExpdate(expdate);
		enc_contactless_preauth.setPosCode(pos_code);
		enc_contactless_preauth.setDeviceType(device_type);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(enc_contactless_preauth);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
