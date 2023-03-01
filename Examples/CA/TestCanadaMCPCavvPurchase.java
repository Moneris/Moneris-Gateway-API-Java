package Canada;

import JavaAPI.*;

public class TestCanadaMCPCavvPurchase
{
	public static void main(String[] args)
	{
//		String store_id = "monca02760";
//        String api_token = "ibnNzGEEphi0wdlivn9l";
		String store_id = "monca02932";
		String api_token = "CG8kYzGgzVU5z23irgMx";
		//String store_id = "store5";
        //String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String cust_id = "customer1";
		String amount = "5.00";
		String pan = "4242424242424242";
		String expdate = "2301"; //YYMM
		String cavv = "BwABApFSYyd4l2eQQFJjAAAAAAA=";
		String dynamic_descriptor = "ABC Comp";
		String processing_country_code = "CA";
		String crypt_type = "7";
		boolean status_check = false;

		MCPCavvPurchase mcpCavvPurchase = new MCPCavvPurchase();
		mcpCavvPurchase.setOrderId(order_id);
		mcpCavvPurchase.setCustId(cust_id);
		mcpCavvPurchase.setAmount(amount);
		mcpCavvPurchase.setPan(pan);
		mcpCavvPurchase.setExpdate(expdate);
		mcpCavvPurchase.setCavv(cavv);
		mcpCavvPurchase.setCryptType(crypt_type); //Mandatory for AMEX only
		mcpCavvPurchase.setDynamicDescriptor(dynamic_descriptor);
		//mcpCavvPurchase.setWalletIndicator("APP"); //set only for wallet transactions. e.g APPLE PAY
		//mcpCavvPurchase.setNetwork("Interac"); //set only for Interac e-commerce
		//mcpCavvPurchase.setDataType("3DSecure"); //set only for Interac e-commerce
		mcpCavvPurchase.setCmId("8nAK8712sGaAkls56"); //set only for usage with Offlinx - Unique max 50 alphanumeric characters transaction id generated by merchant

		mcpCavvPurchase.setThreeDSVersion("2"); //Mandatory for 3DS Version 2.0+
		mcpCavvPurchase.setThreeDSServerTransId("e11d4985-8d25-40ed-99d6-c3803fe5e68f"); //Mandatory for 3DS Version 2.0+ - obtained from MpiCavvLookup or MpiThreeDSAuthentication
		mcpCavvPurchase.setDsTransId("12345"); //Optional - to be used only if you are using 3rd party 3ds 2.0 service

		//MCP Fields
		mcpCavvPurchase.setMCPVersion("1.0");
		mcpCavvPurchase.setCardholderAmount("240000");
		mcpCavvPurchase.setCardholderCurrencyCode("008");
		mcpCavvPurchase.setMCPRateToken("P1637855535621490");

		//optional - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("C");
		cof.setPaymentInformation("0");
		mcpCavvPurchase.setCofInfo(cof);

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		mcpCavvPurchase.setAvsInfo(avsCheck);

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");
		mcpCavvPurchase.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest(); 
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpCavvPurchase);
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
			System.out.println("CavvResultCode = " + receipt.getCavvResultCode());
			System.out.println("IssuerId = " + receipt.getIssuerId());
			System.out.println("ThreeDSVersion = " + receipt.getThreeDSVersion());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());
			
			System.out.println("MerchantSettlementAmount = " + receipt.getMerchantSettlementAmount());
			System.out.println("CardholderAmount = " + receipt.getCardholderAmount());
			System.out.println("CardholderCurrencyCode = " + receipt.getCardholderCurrencyCode());
			System.out.println("MCPRate = " + receipt.getMCPRate());
			System.out.println("MCPErrorStatusCode = " + receipt.getMCPErrorStatusCode());
			System.out.println("MCPErrorMessage = " + receipt.getMCPErrorMessage());
			System.out.println("HostId = " + receipt.getHostId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}