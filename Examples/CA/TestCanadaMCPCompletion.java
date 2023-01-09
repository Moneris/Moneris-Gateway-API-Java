package Canada;

import JavaAPI.*;

public class TestCanadaMCPCompletion
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String order_id = "Test1538681966167";
		String txn_number = "696294-0_11";
		String crypt = "7";
		String cust_id = "my customer id";
		String dynamic_descriptor = "my descriptor";
		String ship_indicator = "F" ;
		String processing_country_code = "CA";
		boolean status_check = false;

		MCPCompletion mcpCompletion = new MCPCompletion();
		mcpCompletion.setOrderId(order_id);
		mcpCompletion.setTxnNumber(txn_number);
		mcpCompletion.setCryptType(crypt);
		mcpCompletion.setCustId(cust_id);
		mcpCompletion.setDynamicDescriptor(dynamic_descriptor);
		//mcpCompletion.setShipIndicator(ship_indicator); //optional

		//MCP Fields
		mcpCompletion.setMCPVersion("1.0");
		mcpCompletion.setCardholderAmount("500");
		mcpCompletion.setCardholderCurrencyCode("840");
		//mcpCompletion.setMCPRateToken("P1538681661706745"); //optional
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpCompletion);
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
