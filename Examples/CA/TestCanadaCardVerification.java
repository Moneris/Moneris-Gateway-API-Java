package Canada;

import JavaAPI.*;

public class TestCanadaCardVerification
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM format
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");

		CardVerification cardVerification = new CardVerification();
		cardVerification.setOrderId(order_id);
		cardVerification.setPan(pan);
		cardVerification.setExpdate(expdate);
		cardVerification.setCryptType(crypt);
		cardVerification.setAvsInfo(avsCheck);
		cardVerification.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(cardVerification);
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
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
