package Canada;

import JavaAPI.*;

public class TestCanadaMCPIndependentRefund
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String cust_id = "my customer id";
		String amount = "20.00";
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		MCPIndependentRefund mcpIndrefund = new MCPIndependentRefund();
		mcpIndrefund.setOrderId(order_id);
		mcpIndrefund.setCustId(cust_id);
		mcpIndrefund.setAmount(amount);
		mcpIndrefund.setPan(pan);
		mcpIndrefund.setExpdate(expdate);
		mcpIndrefund.setCryptType(crypt);
		mcpIndrefund.setDynamicDescriptor("123456");
		
		//MCP Fields
		mcpIndrefund.setMCPVersion("1.0");
		mcpIndrefund.setCardholderAmount("500");
		mcpIndrefund.setCardholderCurrencyCode("840");
		mcpIndrefund.setMCPRateToken("R1538679861330690");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpIndrefund);
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
