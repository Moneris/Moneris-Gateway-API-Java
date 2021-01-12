package Canada;

import JavaAPI.*;

public class TestCanadaResCavvPurchaseCC
{
	public static void main(String[] args)
	{
		String store_id = "store1";
		String api_token = "yesguy";
		String data_key = "4INQR1A8ocxD0oafSz50LADXy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "1.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA";
		String processing_country_code = "CA";
		String exp_date = "1901";
		boolean status_check = false;

		ResCavvPurchaseCC resCavvPurchaseCC = new ResCavvPurchaseCC();
		resCavvPurchaseCC.setOrderId(order_id);
		resCavvPurchaseCC.setDataKey(data_key);
		resCavvPurchaseCC.setCustId(cust_id);
		resCavvPurchaseCC.setAmount(amount);
		resCavvPurchaseCC.setCavv(cavv);
		resCavvPurchaseCC.setExpDate(exp_date);

		resCavvPurchaseCC.setThreeDSVersion("2"); //Mandatory for 3DS Version 2.0+
		resCavvPurchaseCC.setThreeDSServerTransId("e11d4985-8d25-40ed-99d6-c3803fe5e68f"); //Mandatory for 3DS Version 2.0+ - obtained from MpiCavvLookup or MpiThreeDSAuthentication 

		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("U");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9");
		
		resCavvPurchaseCC.setCofInfo(cof);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resCavvPurchaseCC);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("CavvResultCode = " + receipt.getCavvResultCode());
			System.out.println("IssuerId = " + receipt.getIssuerId());
			System.out.println("ThreeDSVersion = " + receipt.getThreeDSVersion());

			//ResolveData
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Masked Pan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
