package Canada;

import JavaAPI.*;

public class TestCanadaForcePost
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "my customer id";
		String store_id = "moneris";
		String api_token = "hurgle";
		String amount = "1.00";
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM format
		String auth_code = "88864";
		String crypt = "7";
		String dynamic_descriptor = "my descriptor";
		String processing_country_code = "CA";
		boolean status_check = false;

		ForcePost forcepost = new ForcePost();
		forcepost.setOrderId(order_id);
		forcepost.setCustId(cust_id);
		forcepost.setAmount(amount);
		forcepost.setPan(pan);
		forcepost.setExpdate(expdate);
		forcepost.setAuthCode(auth_code);
		forcepost.setCryptType(crypt);
		forcepost.setDynamicDescriptor(dynamic_descriptor);		
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(forcepost);
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
			System.out.println("CorporateCard = " + receipt.getCorporateCard());
			//System.out.println("MessageId = " + receipt.getMessageId());
			System.out.println("IssuerId = " + receipt.getIssuerId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
