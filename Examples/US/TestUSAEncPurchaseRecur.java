package USA;

import JavaAPI.*;

public class TestUSAEncPurchaseRecur
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "5.00";
		String crypt = "7";
		String commcard_invoice = "INVC090";
		String commcard_tax_amount = "1.00";
		String processing_country_code = "US";
		boolean status_check = false;
		String device_type = "idtech";
		String enc_track2 = "0284008500000000041608B84D1586DEDBA1F0DB3CECD296C41195AFECE7CA8F3A00E6DBA97A4EFDEF05D3935553E9E24B5AEFC7B2330431393035FFFF3141594047A0009A7FB103";
		

		/************************* Recur Variables **********************************/

		String recur_unit = "month";
		String start_now = "true";
		String start_date = "2018/12/01";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "30.00";

		/************************* Recur Object Option1 ******************************/

		Recur recurring_cycle = new Recur(recur_unit, start_now, start_date,
				num_recurs, period, recur_amount);

		EncPurchase encpurchase = new EncPurchase();
		encpurchase.setOrderId(order_id);
		encpurchase.setAmount(amount);
		encpurchase.setEncTrack2(enc_track2);
		encpurchase.setDeviceType(device_type);
		encpurchase.setCryptType(crypt);
		encpurchase.setCommcardInvoice(commcard_invoice);
		encpurchase.setCommcardTaxAmount(commcard_tax_amount);
		encpurchase.setRecur(recurring_cycle);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(encpurchase);
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
			System.out.println("Recur Success = " + receipt.getRecurSuccess());
			System.out.println("CardLevelResult = " + receipt.getCardLevelResult());
			System.out.println("MaskedPan = " + receipt.getMaskedPan());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
