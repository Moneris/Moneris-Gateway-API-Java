package USA;

import JavaAPI.*;

public class TestUSATrack2PurchaseCorrection
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String order_id = "Test1432092066726";
		String txn_number = "837881-0_25";
		String dynamic_descriptor = "123456";
		String cust_id = "my customer id";
		String processing_country_code = "US";
		boolean status_check = false;

		Track2PurchaseCorrection track2void = new Track2PurchaseCorrection();
		track2void.setOrderId(order_id);
		track2void.setTxnNumber(txn_number);
		track2void.setCustId(cust_id);
		track2void.setDynamicDescriptor(dynamic_descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(track2void);
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
