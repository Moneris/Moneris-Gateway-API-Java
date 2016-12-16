package Canada;

import JavaAPI.*;

public class TestCanadaPreauthEfraud
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String amount = "10.01";
		String pan = "4242424242424242";
		String expdate = "1902"; //YYMM format
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		avsCheck.setAvsEmail("test@host.com");
		avsCheck.setAvsHostname("hostname");
		avsCheck.setAvsBrowser("Mozilla");
		avsCheck.setAvsShiptoCountry("CAN");
		avsCheck.setAvsShipMethod("G");
		avsCheck.setAvsMerchProdSku("123456");
		avsCheck.setAvsCustIp("192.168.0.1");
		avsCheck.setAvsCustPhone("5556667777");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");

		PreAuth preauth = new PreAuth();
		preauth.setOrderId(order_id);
		preauth.setAmount(amount);
		preauth.setPan(pan);
		preauth.setExpdate(expdate);
		preauth.setCryptType(crypt);
		preauth.setAvsInfo(avsCheck);
		preauth.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(preauth);
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
			System.out.println("Avs Response = " + receipt.getAvsResultCode());
			System.out.println("Cvd Response = " + receipt.getCvdResultCode());
			System.out.println("ITD Response = " + receipt.getITDResponse());
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
