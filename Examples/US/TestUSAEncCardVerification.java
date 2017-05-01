package USA;

import JavaAPI.*;

public class TestUSAEncCardVerification
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "customer1";
		
		String processing_country_code = "US";
		boolean status_check = false;
		String device_type = "idtech";
		String enc_track2 = 
			"02D901801F4F2800039B%*4924********4030^TESTCARD/MONERIS^***************************************"
			+ "**?*;4924********4030=********************?*A7150C78335A5024949516FDA9A68A91C4FBAB1279DD1DE2283D"
			+ "BEBB2C6B3FDEACF7B5B314219D76C00890F347A9640EFE90023E31622F5FD95C14C0362DD2EAB28ADEB46B8B577DA1A1"
			+ "8B707BCC7E48068EFF1882CFB4B369BDC4BB646C870D6083239860B23837EA91DB3F1D8AD066DAAACE2B2DA18D563E4F"
			+ "1EF997696337B8999E9C707DEC4CB0410B887291CAF2EE449573D01613484B80760742A3506C31415939320000A00028"
			+ "3C5E03";

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
