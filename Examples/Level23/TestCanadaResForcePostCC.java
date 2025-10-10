package Canada;

import JavaAPI.*;

public class TestCanadaResForcePostCC
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String data_key = "uroyVNSxzjk5hHoT0kpQDBCw4";
		String amount = "1.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String crypt_type = "7";
		String auth_code = "124424"; 
		String descriptor = "my descriptor";
		String processing_country_code = "CA";
		boolean status_check = false;

		ResForcePostCC resForcePostCC = new ResForcePostCC();
		resForcePostCC.setOrderId(order_id);
		resForcePostCC.setCustId(cust_id);
		resForcePostCC.setAmount(amount);
		resForcePostCC.setDataKey(data_key);
		resForcePostCC.setAuthCode(auth_code);
		resForcePostCC.setCryptType(crypt_type);
		resForcePostCC.setDynamicDescriptor(descriptor);

		//NT Response Option
		boolean get_nt_response = true;
		resForcePostCC.setGetNtResponse(get_nt_response);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resForcePostCC);
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
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
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
			System.out.println("IssuerId = " + receipt.getIssuerId());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());

			if(get_nt_response) {
				System.out.println("\nNTResponseCode = " + receipt.getNTResponseCode());
				System.out.println("NTMessage = " + receipt.getNTMessage());
				System.out.println("NTUsed = " + receipt.getNTUsed());
				System.out.println("NTTokenBin = " + receipt.getNTTokenBin());
				System.out.println("NTTokenLast4 = " + receipt.getNTTokenLast4());
				System.out.println("NTTokenExpDate = " + receipt.getNTTokenExpDate());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
