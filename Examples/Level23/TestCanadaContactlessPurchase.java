package Canada;
import java.io.*;

import JavaAPI.*;

public class TestCanadaContactlessPurchase
{
	public static void main(String args[]) throws IOException
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "moneris";
		String api_token = "hurgle";
		String amount = "1.00";
		String track2 = ";4924190000004030=09121214797536211133?";
		String pos_code = "00";
		String processing_country_code = "CA";

		ContactlessPurchase contactless_purchase = new ContactlessPurchase();
		contactless_purchase.setOrderId(order_id);
		contactless_purchase.setAmount(amount);
		contactless_purchase.setTrack2(track2);
		contactless_purchase.setPosCode(pos_code);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(contactless_purchase);
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
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
