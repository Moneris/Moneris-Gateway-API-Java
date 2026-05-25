package Canada;

import JavaAPI.HttpsPostRequest;
import JavaAPI.PBB_GetTransactionData;
import JavaAPI.Receipt;

public class TestCanadaPbbGetTransactionData
{
	public static void main(String[] args)
	{
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String processing_country_code = "CA";
		String ConsentId = "b52a7af4-4b2c-4b23-b039-246b0fd6e780 ";
		String amount = "21.00";
		String currency = "CAD";
		String transcation_type = "PURCHASE";
		String payment_type= "SINGLE";


		PBB_GetTransactionData pbbGetTransactionData = new PBB_GetTransactionData();
		pbbGetTransactionData.setConsentId(ConsentId);
		pbbGetTransactionData.setAmount(amount);
		pbbGetTransactionData.setCurrency(currency);
		pbbGetTransactionData.setPaymentType(payment_type);
		pbbGetTransactionData.setTransactionType(transcation_type);


		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(pbbGetTransactionData);

		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();
			System.out.println("TransactionRef = "+ receipt.getTransactionRef());
			System.out.println("Transaction Data - PaymentMethod = "+ receipt.getPaymentMethod());
            System.out.println("Transaction Data - ConsentId = "+ receipt.getTransactionDataConsentId());
		//	System.out.println("Transaction Data - TransactionType = "+ receipt.getTransactionType());
            System.out.println("Transaction Data - PaymentType = "+ receipt.getPBBPaymentType());
			System.out.println("Transaction Data - Currency = "+ receipt.getCurrency());
			System.out.println("Transaction Data - Amount = "+ receipt.getAmount());
			System.out.println("Transaction Data - TransactionRef = "+ receipt.getTransactionReference());
			System.out.println("Transaction Data - TokenExpiry = "+ receipt.getTokenExpiry());
			System.out.println("Transaction Data - PaymentToken = "+ receipt.getPaymentToken());
			System.out.println("Transaction Data - Cryptogram = "+ receipt.getCryptogram());
			System.out.println("Transaction Data - CryptogramExpiry = "+ receipt.getCryptogramExpiry());
			System.out.println("PaymentMethod = "+ receipt.getPaymentMethod2());
			System.out.println("TokenPanLastDigits = "+ receipt.getTokenPanLastDigits());
            System.out.println("ECI = "+ receipt.getEci());
            System.out.println("Par = "+ receipt.getPar());


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
