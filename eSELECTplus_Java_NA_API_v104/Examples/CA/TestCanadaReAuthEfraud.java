package Canada;
import JavaAPI.*;

public class TestCanadaReAuthEfraud
{
	public static void main(String args[])
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
	
		String store_id = "moneris";
		String api_token = "hurgle";
		String cust_id = "Hilton_1";
		String amount = "1.00";
		String orig_order_id = "mvt3215289259";
		String txn_number = "200152-0_10";
		String crypt = "7";
		String processing_country_code = "CA";

		AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
		CvdInfo cvd = new CvdInfo ("1", "099");

		/*********************** Transaction Object *******************************/
		ReAuth reauth = new ReAuth ();
		reauth.setOrderId(order_id);
		reauth.setCustId(cust_id);
		reauth.setAmount(amount);
		reauth.setOrigOrderId(orig_order_id);
		reauth.setTxnNumber(txn_number);
		reauth.setCryptType(crypt);

		reauth.setAvsInfo(avs);
		reauth.setCvdInfo(cvd);


		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(reauth);
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
