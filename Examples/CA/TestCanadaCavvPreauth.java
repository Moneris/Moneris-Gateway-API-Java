package Canada;

import JavaAPI.*;

public class TestCanadaCavvPreauth
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "CUS887H67";
		String amount = "10.42";
		String pan = "4242424242424242";
		String expdate = "1911"; //YYMM format
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA=";
		String dynamic_descriptor = "123456";
		String processing_country_code = "CA";
		String crypt_type = "5";
		boolean status_check = false;

		CavvPreAuth cavvPreauth = new CavvPreAuth();
		cavvPreauth.setOrderId(order_id);
		cavvPreauth.setCustId(cust_id);
		cavvPreauth.setAmount(amount);
		cavvPreauth.setPan(pan);
		cavvPreauth.setExpdate(expdate);
		cavvPreauth.setCavv(cavv);
		cavvPreauth.setCryptType(crypt_type); //Mandatory for AMEX only
		cavvPreauth.setDynamicDescriptor(dynamic_descriptor);
		//cavvPreauth.setWalletIndicator("APP"); //set only for wallet transactions. e.g APPLE PAY

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(cavvPreauth);
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
			System.out.println("CavvResultCode = " + receipt.getCavvResultCode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
