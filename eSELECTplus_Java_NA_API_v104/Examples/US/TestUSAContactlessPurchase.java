package USA;

import JavaAPI.*;

public class TestUSAContactlessPurchase
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "my customer id";
		String amount = "1.00";
		String track2 = ";4924190000004030=09121214797536211133?";
		String commcard_invoice = "INVC090";
		String commcard_tax_amount = "1.00";
		String dynamic_descriptor = "my descriptor";
		String processing_country_code = "US";
		String pos_code = "00";
		boolean status_check = false;

		ContactlessPurchase purchase = new ContactlessPurchase();
		purchase.setOrderId(order_id);
		purchase.setAmount(amount);
		purchase.setTrack2(track2);
		purchase.setPosCode(pos_code);
		purchase.setCustId(cust_id);
		purchase.setCommcardInvoice(commcard_invoice);
		purchase.setCommcardTaxAmount(commcard_tax_amount);
		purchase.setDynamicDescriptor(dynamic_descriptor);


		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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
