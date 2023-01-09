package Canada;
                                                                           
import java.util.*;

import JavaAPI.*;

public class TestCanadaPurchaseRecur
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "10.00";
		String pan = "4242424242424242";
		String expiry_date = "1901"; //YYMM format
		String crypt = "7";

		/************************* Recur Variables **********************************/

		String recur_unit = "month"; //eom = end of month
		String start_now = "true";
		String start_date = "2018/04/01";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "30.00";
		String processing_country_code = "CA";
		boolean status_check = false;

		/************************* Recur Object Option1 ******************************/

		Recur recurring_cycle = new Recur(recur_unit, start_now, start_date,
				num_recurs, period, recur_amount);

		/************************* Recur Object Option2 ******************************/

		Hashtable<String, String> recur_hash = new Hashtable<String, String>();

		recur_hash.put("recur_unit", recur_unit);
		recur_hash.put("start_now", start_now);
		recur_hash.put("start_date", start_date);
		recur_hash.put("num_recurs", num_recurs);
		recur_hash.put("period", period);
		recur_hash.put("recur_amount", recur_amount);

		/************************ Transactional Object *******************************/

		Purchase purchase = new Purchase();
		purchase.setOrderId(order_id);
		purchase.setAmount(amount);
		purchase.setPan(pan);
		purchase.setExpdate(expiry_date);
		purchase.setCryptType(crypt);

		/******************************* Set Recur ***********************************/

		purchase.setRecur(recurring_cycle);

		//Mandatory on Recurs - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("R");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9"); 
		
		purchase.setCofInfo(cof);
		
		/**************************** Https Post Request ***************************/

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		/******************************* Receipt ***********************************/

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
			System.out.println("Recur Success = " + receipt.getRecurSuccess());
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
			System.out.println("IssuerId = " + receipt.getIssuerId());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} 
