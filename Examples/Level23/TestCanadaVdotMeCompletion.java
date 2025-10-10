package Canada;

import JavaAPI.*;

public class TestCanadaVdotMeCompletion
{
	public static void main(String[] args)
	{
		String store_id = "store2";
		String api_token = "yesguy";
		String order_id = "Test1432134710264";
		String txn_number = "724379-0_10";
		String comp_amount = "1.00";
		String ship_indicator = "P";
		String crypt_type = "7";
		String cust_id = "mycustomerid";
		String dynamic_descriptor = "inv 123";
		String processing_country_code = "CA";
		boolean status_check = false;

		VdotMeCompletion vmecompletion = new VdotMeCompletion();
		vmecompletion.setOrderId(order_id);
		vmecompletion.setTxnNumber(txn_number);
		vmecompletion.setAmount(comp_amount);
		vmecompletion.setCryptType(crypt_type);
		vmecompletion.setDynamicDescriptor(dynamic_descriptor);
		vmecompletion.setCustId(cust_id);
		vmecompletion.setShipIndicator(ship_indicator);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(vmecompletion);
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
			System.out.println("StatusCode = " + receipt.getStatusCode());
			System.out.println("StatusMessage = " + receipt.getStatusMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}	
