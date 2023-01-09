package Canada;

import JavaAPI.*;

public class TestCanadaResPurchaseCC
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String store_id = "monca00597";
		String api_token = "O27AbCbxQorPggMQe6hU";
		String data_key = "4HIme0ZGURXE3NRBXHUj6nSc4";
		String amount = "18.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String crypt_type = "7";
		String descriptor = "my descriptor";
		String processing_country_code = "CA";
		String expdate = "2301"; //For Temp Token
		boolean status_check = false;

		ResPurchaseCC resPurchaseCC = new ResPurchaseCC();
		resPurchaseCC.setDataKey(data_key);
		resPurchaseCC.setOrderId(order_id);
		resPurchaseCC.setCustId(cust_id);
		resPurchaseCC.setAmount(amount);
		resPurchaseCC.setCryptType(crypt_type);
		//resPurchaseCC.setDynamicDescriptor(descriptor);
		resPurchaseCC.setExpDate(expdate); //Temp Tokens only

		//NT Response Option
		boolean get_nt_response = true;
		resPurchaseCC.setGetNtResponse(get_nt_response);

		//optional - Installment Info
		// InstallmentInfo installmentInfo = new InstallmentInfo();
		// installmentInfo.setPlanId("ae859ef1-eb91-b708-8b80-1dd481746401");
		// installmentInfo.setPlanIdRef("0000000065");
		// installmentInfo.setTacVersion("2");
		// resPurchaseCC.setInstallmentInfo(installmentInfo);

		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("C");
		cof.setPaymentInformation("0");
		//cof.setIssuerId("139X3130ASCXAS9");
		resPurchaseCC.setCofInfo(cof);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resPurchaseCC);
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
				System.out.println("NTResponseCode = " + receipt.getNTResponseCode());
				System.out.println("NTMessage = " + receipt.getNTMessage());
				System.out.println("NTUsed = " + receipt.getNTUsed());
				System.out.println("NTMaskedToken = " + receipt.getNTMaskedToken());
			}

			// InstallmentResults installmentResults = receipt.getInstallmentResults();

			// System.out.println("\nPlanId = " + installmentResults.getPlanId());
			// System.out.println("PlanIDRef = " + installmentResults.getPlanIDRef());
			// System.out.println("TacVersion = " + installmentResults.getTacVersion());
			// System.out.println("PlanAcceptanceId = " + installmentResults.getPlanAcceptanceId());
			// System.out.println("PlanStatus = " + installmentResults.getPlanStatus()); 
			// System.out.println("PlanResponse = " + installmentResults.getPlanResponse());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
