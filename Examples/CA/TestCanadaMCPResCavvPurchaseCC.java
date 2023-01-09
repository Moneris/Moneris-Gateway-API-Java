package Canada;

import JavaAPI.*;

public class TestCanadaMCPResCavvPurchaseCC
{
	public static void main(String[] args)
	{
//		String store_id = "monca02932";
//		String api_token = "CG8kYzGgzVU5z23irgMx";
		String store_id = "monca02760";
        String api_token = "ibnNzGEEphi0wdlivn9l";
		String data_key = "xRl9O4FgrZUYdGkmqHTqiEw97";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "1.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String cavv = "BwABApFSYyd4l2eQQFJjAAAAAAA=";
		String processing_country_code = "CA";
		String exp_date = "1901";
		String crypt_type = "7";
		String ds_trans_id = "12345";
		boolean status_check = false;

		MCPResCavvPurchaseCC mcpResCavvPurchaseCC = new MCPResCavvPurchaseCC();
		mcpResCavvPurchaseCC.setOrderId(order_id);
		mcpResCavvPurchaseCC.setDataKey(data_key);
		mcpResCavvPurchaseCC.setCustId(cust_id);
		mcpResCavvPurchaseCC.setAmount(amount);
		mcpResCavvPurchaseCC.setCavv(cavv);
		mcpResCavvPurchaseCC.setCryptType(crypt_type);
		mcpResCavvPurchaseCC.setExpDate(exp_date);

		//NT Response Option
		boolean get_nt_response = true;
		mcpResCavvPurchaseCC.setGetNtResponse(get_nt_response);

		mcpResCavvPurchaseCC.setThreeDSVersion("2"); //Mandatory for 3DS Version 2.0+
		mcpResCavvPurchaseCC.setThreeDSServerTransId("e11d4985-8d25-40ed-99d6-c3803fe5e68f"); //Mandatory for 3DS Version 2.0+ - obtained from MpiCavvLookup or MpiThreeDSAuthentication 
		mcpResCavvPurchaseCC.setDsTransId(ds_trans_id);//Optional - to be used only if you are using 3rd party 3ds 2.0 service
		
		//MCP Fields
		mcpResCavvPurchaseCC.setMCPVersion("1.0");
		mcpResCavvPurchaseCC.setCardholderAmount("500");
		mcpResCavvPurchaseCC.setCardholderCurrencyCode("840");
		mcpResCavvPurchaseCC.setMCPRateToken("P1637854635947343");
		
		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("U");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9");
		
		mcpResCavvPurchaseCC.setCofInfo(cof);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpResCavvPurchaseCC);
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
			
			System.out.println("MerchantSettlementAmount = " + receipt.getMerchantSettlementAmount());
			System.out.println("CardholderAmount = " + receipt.getCardholderAmount());
			System.out.println("CardholderCurrencyCode = " + receipt.getCardholderCurrencyCode());
			System.out.println("MCPRate = " + receipt.getMCPRate());
			System.out.println("MCPErrorStatusCode = " + receipt.getMCPErrorStatusCode());
			System.out.println("MCPErrorMessage = " + receipt.getMCPErrorMessage());
			System.out.println("HostId = " + receipt.getHostId());		

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

			if(get_nt_response) {
				System.out.println("NTResponseCode = " + receipt.getNTResponseCode());
				System.out.println("NTMessage = " + receipt.getNTMessage());
				System.out.println("NTUsed = " + receipt.getNTUsed());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
