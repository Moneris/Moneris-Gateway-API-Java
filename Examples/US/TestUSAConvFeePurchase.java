package USA;

import JavaAPI.*;

public class TestUSAConvFeePurchase
{
	public static void main(String args[])
	{
		String store_id = "monusqa138";
		String api_token = "qatoken";
		String processing_country_code = "US";

		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String amount = "10.00";
		String pan = "4242424242424242";
		String expiry_date = "1911";
		String crypt = "7";
		String commcard_invoice = "";
		String commcard_tax_amount = "";

		AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
		CvdInfo cvd = new CvdInfo ("1", "099");

		Purchase purchase = new Purchase (order_id, amount, pan, expiry_date, crypt, commcard_invoice, commcard_tax_amount);

		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("5.00");
		purchase.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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

			System.out.println("CfSuccess = " + receipt.getCfSuccess());
			System.out.println("CfStatus = " + receipt.getCfStatus());
			System.out.println("FeeAmount = " + receipt.getFeeAmount());
			System.out.println("FeeRate = " + receipt.getFeeRate());
			System.out.println("FeeType = " + receipt.getFeeType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
