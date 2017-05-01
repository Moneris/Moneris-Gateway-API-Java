package USA;

import JavaAPI.*;

public class TestUSAEncForcepost
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "Ced_Benson32";
		String amount = "1.00";
		String device_type = "idtech";
		String crypt = "7";
		String auth_code = "12345";
		String descriptor = "my descriptor";

		String enc_track2 = "0284008500000000041608B84D1586DEDBA1F0DB3CECD296C41195AFECE7CA8F3A00E6DBA97A4EFDEF05D3935553E9E24B5AEFC7B2330431393035FFFF3141594047A0009A7FB103";
	
		String processing_country_code = "US";
		boolean status_check = false;

		EncForcepost encforcepost = new EncForcepost();
		encforcepost.setOrderId(order_id);
		encforcepost.setCustId(cust_id);
		encforcepost.setAmount(amount);
		encforcepost.setEncTrack2(enc_track2);
		encforcepost.setDeviceType(device_type);
		encforcepost.setAuthCode(auth_code);
		encforcepost.setCryptType(crypt);
		encforcepost.setDynamicDescriptor(descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(encforcepost);
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
			System.out.println("CardLevelResult = " + receipt.getCardLevelResult());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
