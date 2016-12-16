package Canada;

import JavaAPI.*;

public class TestCanadaConvFeeCavvPurchase
{
	public static void main(String[] args)
	{
		String store_id = "monca00392";
		String api_token = "qYdISUhHiOdfTr1CLNpN";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String cust_id = "CUS887H67";
		String amount = "10.42";
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA=";
		String dynamic_descriptor = "123456";
		String processing_country_code = "CA";
		String crypt_type = "5";
		boolean status_check = false;

		/*************** Convenience Fee *******************/
		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("1.00");
		
		CavvPurchase cavvPurchase = new CavvPurchase();
		cavvPurchase.setOrderId(order_id);
		cavvPurchase.setCustId(cust_id);
		cavvPurchase.setAmount(amount);
		cavvPurchase.setPan(pan);
		cavvPurchase.setExpdate(expdate);
		cavvPurchase.setCavv(cavv);
		cavvPurchase.setCryptType(crypt_type); //Mandatory for AMEX only
		cavvPurchase.setDynamicDescriptor(dynamic_descriptor);
		cavvPurchase.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest(); 
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(cavvPurchase);
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
			
			System.out.println("CfSuccess = " + receipt.getCfSuccess());
			System.out.println("CfStatus = " + receipt.getCfStatus());
			System.out.println("FeeAmount = " + receipt.getFeeAmount());
			System.out.println("FeeRate = " + receipt.getFeeRate());
			System.out.println("FeeType = " + receipt.getFeeType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
