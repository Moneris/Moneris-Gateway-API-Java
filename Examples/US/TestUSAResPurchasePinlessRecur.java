package USA;

import JavaAPI.*;

public class TestUSAResPurchasePinlessRecur
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String data_key = "AhcyWhamRPNnhyU8RYPxM3saK";
		String amount = "1.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String intended_use = "1";
		String p_account_number = "23456789";
		String processing_country_code = "US";

		ResPurchasePinless resPurchasePinless = new ResPurchasePinless();
		resPurchasePinless.setDataKey(data_key);
		resPurchasePinless.setOrderId(order_id);
		resPurchasePinless.setCustId(cust_id);
		resPurchasePinless.setAmount(amount);
		resPurchasePinless.setIntendedUse(intended_use);
		resPurchasePinless.setPAccountNumber(p_account_number);

		/************************* Recur Variables **********************************/

		String recur_unit = "month";
		String start_now = "true";
		String start_date = "2018/12/01";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "30.00";

		/************************* Recur Object Option1 ******************************/

		Recur recurring_cycle = new Recur(recur_unit, start_now, start_date,
				num_recurs, period, recur_amount);

		resPurchasePinless.setRecur(recurring_cycle);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resPurchasePinless);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("RecurSuccess = " + receipt.getRecurSuccess());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Masked Pan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Presentation Type = " + receipt.getResPresentationType());
			System.out.println("P Account Number = " + receipt.getResPAccountNumber());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
