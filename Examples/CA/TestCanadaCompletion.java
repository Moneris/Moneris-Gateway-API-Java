package Canada;

import JavaAPI.*;

public class TestCanadaCompletion
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String order_id = "Test1471032121580";
		String amount = "2.00";
		String txn_number = "115309-0_10";
		String crypt = "7";
		String cust_id = "my customer id";
		String dynamic_descriptor = "my descriptor";
		String ship_indicator = "F" ;
		String processing_country_code = "CA";
		boolean status_check = false;

		Completion completion = new Completion();
		completion.setOrderId(order_id);
		completion.setCompAmount(amount);
		completion.setTxnNumber(txn_number);
		completion.setCryptType(crypt);
		completion.setCustId(cust_id);
		completion.setDynamicDescriptor(dynamic_descriptor);
		//completion.setShipIndicator(ship_indicator); //optional

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(completion);
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
