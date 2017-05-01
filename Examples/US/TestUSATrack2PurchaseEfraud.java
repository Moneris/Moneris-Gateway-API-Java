package USA;

import JavaAPI.*;

public class TestUSATrack2PurchaseEfraud
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "LBriggs";
		String amount = "1.00";
		String track2 = ";5258968987035454=06061015454001060101?";
		String pan = "";
		String exp = "";		//must send '0000' if swiped
		String pos_code = "00";
		String commcard_invoice = "INV98798";
		String commcard_tax_amount = "1.00";
		String processing_country_code = "US";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");

		Track2Purchase track2purchase = new Track2Purchase();
		track2purchase.setOrderId(order_id);
		track2purchase.setCustId(cust_id);
		track2purchase.setAmount(amount);
		track2purchase.setTrack2(track2);
		track2purchase.setPan(pan);
		track2purchase.setExpdate(exp);
		track2purchase.setPosCode(pos_code);
		track2purchase.setCommcardInvoice(commcard_invoice);
		track2purchase.setCommcardTaxAmount(commcard_tax_amount);
		track2purchase.setAvsInfo(avsCheck);
		track2purchase.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(track2purchase);
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
