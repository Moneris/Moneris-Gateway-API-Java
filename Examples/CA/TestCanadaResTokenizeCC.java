package Canada;

import JavaAPI.*;

public class TestCanadaResTokenizeCC
{
	public static void main(String[] args)
	{
		String store_id = "monca00544";
		String api_token = "5JLbUfpl9JsQzPmbyje7";
		String order_id = "Test1737561056105";
		String txn_number = "5354-0_1023";
		String phone = "0000000000";
		String email = "bob@smith.com";
		String note = "my note";
		String cust_id = "customer1";
		String data_key_format = "0";
		String processing_country_code = "CA";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		
		//Credential on File details
		CofInfo cof = new CofInfo();
		cof.setIssuerId("139X3130ASCXAS9");

		ResTokenizeCC resTokenizeCC = new ResTokenizeCC();
		resTokenizeCC.setOrderId(order_id);
		resTokenizeCC.setTxnNumber(txn_number);
		resTokenizeCC.setCustId(cust_id);
		resTokenizeCC.setPhone(phone);
		resTokenizeCC.setEmail(email);
		resTokenizeCC.setNote(note);
		resTokenizeCC.setAvsInfo(avsCheck);
		resTokenizeCC.setCofInfo(cof);
		//resTokenizeCC.setDataKeyFormat(data_key_format); //optional
		resTokenizeCC.setReturnIssuerId(false);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resTokenizeCC);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());

			//ResolveData
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
			System.out.println("IssuerId = " + receipt.getIssuerId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
