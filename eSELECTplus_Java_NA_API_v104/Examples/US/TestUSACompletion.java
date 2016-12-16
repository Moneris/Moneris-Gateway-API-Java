package USA;

import JavaAPI.*;

public class TestUSACompletion
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String amount = "1.00";
		String crypt = "7";
		String commcard_invoice = "INVC090";
		String commcard_tax_amount = "1.00";
		String dynamic_descriptor = "123456";
		String custid = "mycustomerid";
		String order_id = "Test1437167057352";
		String txn_number = "138748-0_25";
		String processing_country_code = "US";
		boolean status_check = false;

		Completion completion = new Completion();
		completion.setOrderId(order_id);
		completion.setCompAmount(amount);
		completion.setTxnNumber(txn_number);
		completion.setCryptType(crypt);
		completion.setCommcardInvoice(commcard_invoice);
		completion.setCommcardTaxAmount(commcard_tax_amount);
		completion.setCustId(custid);
		completion.setDynamicDescriptor(dynamic_descriptor);

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
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			//System.out.println("CardLevelResult = " + receipt.getCardLevelResult());
			//System.out.println("StatusCode = " + receipt.getStatusCode());
			//System.out.println("StatusMessage = " + receipt.getStatusMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
