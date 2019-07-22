package Canada;

import JavaAPI.*;

public class TestCanadaMCPPreauth
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "5.00";
		String pan = "4242424242424242";
		String expdate = "1902";
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		MCPPreAuth mcpPreauth = new MCPPreAuth();
		mcpPreauth.setOrderId(order_id);
		mcpPreauth.setAmount(amount);
		mcpPreauth.setPan(pan);
		mcpPreauth.setExpdate(expdate);
		mcpPreauth.setCryptType(crypt);
		//mcpPreauth.setWalletIndicator(""); //Refer documentation for possible values

		//optional - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("U");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9"); 
		
		//mcpPreauth.setCofInfo(cof);
		
		//MCP Fields
		mcpPreauth.setMCPVersion("1.0");
		mcpPreauth.setCardholderAmount("500");
		mcpPreauth.setCardholderCurrencyCode("840");
		mcpPreauth.setMCPRateToken("P1538681661706745");
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpPreauth);
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
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
			//System.out.println("StatusCode = " + receipt.getStatusCode());
			//System.out.println("StatusMessage = " + receipt.getStatusMessage());
			System.out.println("IssuerId = " + receipt.getIssuerId());
			
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
