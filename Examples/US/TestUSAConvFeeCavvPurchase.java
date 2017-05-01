package USA;

import JavaAPI.*;

public class TestUSAConvFeeCavvPurchase
{
	public static void main(String args[])
	{
		String store_id = "monusqa138";
		String api_token = "qatoken";
		String processing_country_code = "US";

		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String cust_id = "B_Griese_06";
		String amount = "10.00";
		String pan = "4005554444400555";
		String expiry_date = "1912";
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA";
		String commcard_invoice = "INV009";
		String commcard_tax_amount = "1.00";

		String street_number = "212";
		String street_name = "Michigan Avenue";
		String zip_code = "87882";

		String cvd_indicator = "1";
		String cvd_code = "890";

		CavvPurchase cavvPurchase =	new CavvPurchase ();
		cavvPurchase.setOrderId(order_id);
		cavvPurchase.setCustId(cust_id);
		cavvPurchase.setAmount(amount);
		cavvPurchase.setPan(pan);
		cavvPurchase.setExpdate(expiry_date);
		cavvPurchase.setCavv(cavv);
		cavvPurchase.setCommcardInvoice(commcard_invoice);
		cavvPurchase.setCommcardTaxAmount(commcard_tax_amount);
		
		AvsInfo avsCheck = new AvsInfo(street_number, street_name, zip_code);
		cavvPurchase.setAvsInfo (avsCheck);

		CvdInfo cvdCheck = new CvdInfo (cvd_indicator, cvd_code);
		cavvPurchase.setCvdInfo (cvdCheck);

		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("1.00");
		cavvPurchase.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(cavvPurchase);
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
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("AVS Response = " + receipt.getAvsResultCode());
			System.out.println("CVD Response = " + receipt.getCvdResultCode());
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
