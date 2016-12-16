package Canada;

import JavaAPI.*;

public class TestCanadaTrack2PreAuth
{
	public static void main(String[] args)
	{
		String store_id = "store1";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "LBriggs";
		String amount = "5.00";
		String track2 = ";5258968987035454=06061015454001060101?";
		String pan = "";
		String exp = "0000";		//must send '0000' if swiped
		String pos_code = "00";
		String processing_country_code = "CA";
		boolean status_check = false;

		Track2PreAuth track2preauth = new Track2PreAuth();
		track2preauth.setOrderId(order_id);
		track2preauth.setCustId(cust_id);
		track2preauth.setAmount(amount);
		track2preauth.setTrack2(track2);
		track2preauth.setPan(pan);
		track2preauth.setExpdate(exp);
		track2preauth.setPosCode(pos_code);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(track2preauth);
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
			//System.out.println("StatusCode = " + receipt.getStatusCode());
			//System.out.println("StatusMessage = " + receipt.getStatusMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
