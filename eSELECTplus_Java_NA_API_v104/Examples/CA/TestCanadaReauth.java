package Canada;

import JavaAPI.*;

public class TestCanadaReauth
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String order_id = "mvt271355ss7ssss839ssdfsdfsdf";
		String orig_order_id = "mvt3213820409";
		String amount = "4.00";
		String txn_number = "200069-0_10";
		String crypt = "8";
		String dynamic_descriptor = "123456";
		String cust_id = "my customer id";
		String processing_country_code = "CA";
		boolean status_check = false;

		ReAuth reauth = new ReAuth();
		reauth.setOrderId(order_id);
		reauth.setCustId(cust_id);
		reauth.setOrigOrderId(orig_order_id);
		reauth.setTxnNumber(txn_number);
		reauth.setAmount(amount);
		reauth.setCryptType(crypt);
		reauth.setDynamicDescriptor(dynamic_descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(reauth);
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
