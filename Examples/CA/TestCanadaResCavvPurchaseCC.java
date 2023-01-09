package Canada;

import JavaAPI.*;

public class TestCanadaResCavvPurchaseCC
{
	public static void main(String[] args)
	{
		String store_id = "monca00597";
		String api_token = "O27AbCbxQorPggMQe6hU";
		String data_key = "m5FubAMXr8IK4lC0eTv0c9zA0";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "4.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA";
		String processing_country_code = "CA";
		String exp_date = "2301";
		String ds_trans_id = "12345";
		boolean status_check = false;

		ResCavvPurchaseCC resCavvPurchaseCC = new ResCavvPurchaseCC();
		resCavvPurchaseCC.setOrderId(order_id);
		resCavvPurchaseCC.setDataKey(data_key);
		resCavvPurchaseCC.setCustId(cust_id);
		resCavvPurchaseCC.setAmount(amount);
		resCavvPurchaseCC.setCavv(cavv);
		resCavvPurchaseCC.setExpDate(exp_date);

		//NT Response Option
		boolean get_nt_response = true;
		resCavvPurchaseCC.setGetNtResponse(get_nt_response);

		//optional - Installment Info
		// InstallmentInfo installmentInfo = new InstallmentInfo();
		// installmentInfo.setPlanId("ae859ef1-eb91-b708-8b80-1dd481746401");
		// installmentInfo.setPlanIdRef("0000000065");
		// installmentInfo.setTacVersion("2");
		// resCavvPurchaseCC.setInstallmentInfo(installmentInfo);

		resCavvPurchaseCC.setThreeDSVersion("2"); //Mandatory for 3DS Version 2.0+
		resCavvPurchaseCC.setThreeDSServerTransId("e11d4985-8d25-40ed-99d6-c3803fe5e68f"); //Mandatory for 3DS Version 2.0+ - obtained from MpiCavvLookup or MpiThreeDSAuthentication 
		resCavvPurchaseCC.setDsTransId(ds_trans_id);//Optional - to be used only if you are using 3rd party 3ds 2.0 service

		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("C");
		cof.setPaymentInformation("0");
		//cof.setIssuerId("139X3130ASCXAS9");
		
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
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());

			if(get_nt_response) {
				System.out.println("NTResponseCode = " + receipt.getNTResponseCode());
				System.out.println("NTMessage = " + receipt.getNTMessage());
				System.out.println("NTUsed = " + receipt.getNTUsed());
				System.out.println("NTMaskedToken = " + receipt.getNTMaskedToken());
			}

			// InstallmentResults installmentResults = receipt.getInstallmentResults();

			// System.out.println("\nPlanId = " + installmentResults.getPlanId() +"\n");
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
