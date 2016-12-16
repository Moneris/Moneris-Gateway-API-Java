package Canada;

import JavaAPI.*;

public class TestCanadaRefund
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String amount = "2.00";
		String crypt = "7";
		String dynamic_descriptor = "123456";
		String custid = "mycust9";
		String order_id = "Test1471030990821";
		String txn_number = "115270-0_10";
		String processing_country_code = "CA";
		boolean status_check = false;

		Refund refund = new Refund();
		refund.setTxnNumber(txn_number);
		refund.setOrderId(order_id);
		refund.setAmount(amount);
		refund.setCryptType(crypt);
		refund.setCustId(custid);
		refund.setDynamicDescriptor(dynamic_descriptor);
		
		//Optional - Set for Multi-Currency only
		//setAmount must be 0.00 when using multi-currency
		//refund.setMCPAmount("200"); //penny value amount 1.25 = 125
		//refund.setMCPCurrencyCode("840"); //ISO-4217 country currency number

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(refund);
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
			System.out.println("MCPAmount = " + receipt.getMCPAmount());
			System.out.println("MCPCurrencyCode = " + receipt.getMCPCurrencyCode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
