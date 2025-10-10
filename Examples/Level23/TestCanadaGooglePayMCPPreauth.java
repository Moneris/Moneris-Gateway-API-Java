package Canada;
import JavaAPI.*;

public class TestCanadaGooglePayMCPPreauth
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "intuit_sped";
		String api_token = "spedguy";            
		String amount = "1.00";
		String cust_id = "nqa-cust_id";
		String network = "MASTERCARD";
		String signature = "MEQCIHqTNrWj16DTwJUKi/AHbcp12n7hWgLYpcZeIL4YE6v2AiBabiGJla15MuHs0irVOXX2/jW/YxID1aUmaHSBRddy5Q==";
		String protocol_version = "ECv1";
		String signed_message =  "{\"encryptedMessage\":\"UC+YsDq+MDGAbMS+8liNngnEnHHsh/LueiqZlkCaVz3fYuWqL3i70Xk72yEg+Riu6erDA49nth7F/VkAH8Lun6ZvKC+r7AHLc7kScIAhRJf/muPYas9Zwr5sHV7WdmKLNoPi5Ni5YYGH8jXry7byJCXU0fbVYvFcR1zqrVL+IgdIRxu0hjpyP2NVhv9lNCaCP4Qk7bQf5jN0XhBYVzHwWc42knGkV8oHLBQ199IXl+ARjizax1zcZsa10+dPySBYQD2rcu6THp/aIRfHbW5feux/ifn4sbv4Xq3SNHJluo2L2MfKEiPLwFV6NyyMLRqXNIoLLB/5l8IjEqBgSkEXh4oBbyZcKsWw9udnzwf/K3Mat7lfu2xSPB9eLRJvwOtg3pgkYf8o+gZTW4UEbuBJwOtDDVtmZQeLVOFGTGZSX+tSn5Ua6unWEgwXkH9XTYXYtHlgGjOb\",\"ephemeralPublicKey\":\"BPoyXz2b9VdlFfcnsJ0pbu57wxNIrTxkVHDKstNmxTXKu0rE+5S6BcT9m5zPU8WR/CZ/H+lbXgAp9USPL3ZdRMY\\u003d\",\"tag\":\"dN9RJUDMztNgUkvPE/ys8ZLNpCUkpKi+ZLB6SoGx6Ww\\u003d\"}";
		String dynamic_descriptor = "nqa-dd";
		String processing_country_code = "CA";
		boolean status_check = false;

		GooglePayMCPPreauth googlePayMCPPreauth = new GooglePayMCPPreauth();
		googlePayMCPPreauth.setOrderId(order_id);
		googlePayMCPPreauth.setCustId(cust_id);
		googlePayMCPPreauth.setAmount(amount);
		googlePayMCPPreauth.setNetwork(network);
		googlePayMCPPreauth.setPaymentToken(signature, protocol_version, signed_message);
		googlePayMCPPreauth.setDynamicDescriptor(dynamic_descriptor);
		
		//MCP Fields
		googlePayMCPPreauth.setMCPVersion("1.0");
		googlePayMCPPreauth.setCardholderAmount("500");
		googlePayMCPPreauth.setCardholderCurrencyCode("840");
		googlePayMCPPreauth.setMCPRateToken("P1645492718628533");
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(googlePayMCPPreauth);
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
