package Canada;
import JavaAPI.*;

public class TestCanadaMCPPurchase
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";            
		String amount = "5.00";
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM format
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		MCPPurchase mcpPurchase = new MCPPurchase();
		mcpPurchase.setOrderId(order_id);
		mcpPurchase.setAmount(amount);
		mcpPurchase.setPan(pan);
		mcpPurchase.setExpdate(expdate);
		mcpPurchase.setCryptType(crypt);
		mcpPurchase.setDynamicDescriptor("123456");
		//mcpPurchase.setWalletIndicator(""); //Refer documentation for possible values

		//optional - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("U");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9"); 
		
		//mcpPurchase.setCofInfo(cof);
		
		//MCP Fields
		mcpPurchase.setMCPVersion("1.0");
		mcpPurchase.setCardholderAmount("500");
		mcpPurchase.setCardholderCurrencyCode("840");
		mcpPurchase.setMCPRateToken("P1622647021248255");
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpPurchase);
		mpgReq.setStatusCheck(status_check);
		
		//Optional - Proxy
		mpgReq.setProxy(false); //true to use proxy
		mpgReq.setProxyHost("proxyURL");
		mpgReq.setProxyPort("proxyPort"); 
		mpgReq.setProxyUser("proxyUser"); //optional - domainName\User
		mpgReq.setProxyPassword("proxyPassword"); //optional
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
			System.out.println("HostId = " + receipt.getHostId());
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
