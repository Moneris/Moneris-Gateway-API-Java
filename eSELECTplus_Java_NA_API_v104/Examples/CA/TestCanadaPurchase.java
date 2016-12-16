package Canada;
import JavaAPI.*;

public class TestCanadaPurchase
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

		Purchase purchase = new Purchase();
		purchase.setOrderId(order_id);
		purchase.setAmount(amount);
		purchase.setPan(pan);
		purchase.setExpdate(expdate);
		purchase.setCryptType(crypt);
		purchase.setDynamicDescriptor("123456");
		//purchase.setWalletIndicator(""); //Refer documentation for possible values
		
		//Optional - Set for Multi-Currency only
		//setAmount must be 0.00 when using multi-currency
		//purchase.setMCPAmount("500"); //penny value amount 1.25 = 125
		//purchase.setMCPCurrencyCode("840"); //ISO-4217 country currency number

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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
			System.out.println("MCPAmount = " + receipt.getMCPAmount());
			System.out.println("MCPCurrencyCode = " + receipt.getMCPCurrencyCode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
