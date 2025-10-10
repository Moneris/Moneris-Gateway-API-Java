package Canada;

import JavaAPI.*;

public class TestCanadaEncCardVerification
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "customer1";
		
		String processing_country_code = "CA";
		boolean status_check = false;
		String device_type = "idtech_bdk";
		String enc_track2 = "02840085000000000416570F44857F2F7867342C66F7CDB57128A48F6E8DD8AD30AC1A6C727B5C400DC3AC8169BF2398B6C664FD3BE40431383131FFFF3141594047A00093031D03";
		String crypt_type = "7";

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");

		EncCardVerification encCardVerification = new EncCardVerification();
		encCardVerification.setOrderId(order_id);
		encCardVerification.setCustId(cust_id);
		encCardVerification.setEncTrack2(enc_track2);
		encCardVerification.setDeviceType(device_type);
		encCardVerification.setCryptType(crypt_type);
		encCardVerification.setAvsInfo(avsCheck);
		encCardVerification.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(encCardVerification);
		mpgReq.setStatusCheck(status_check);
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
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("CardLevelResult = " + receipt.getCardLevelResult());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
