package USA;

import JavaAPI.*;

public class TestUSAResPurchaseAchRecur
{
	public static void main(String args[])
	{

		/********************** Request Variables ****************************/

		String store_id = "monusqa002";
		String api_token = "qatoken";

		/********************** Transaction Variables ************************/

		String data_key = "QMlFZodHBk5K102EKnoyobs1N";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String cust_id = "Hilton_1";
		String amount = "1.00";
		String processing_country_code = "US";

		/*********************** Recur Varables ******************************/

		String recur_unit = "month";
		String start_now = "true";
		String start_date = "2019/12/12";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "1.00";

		/************************** Recur Object ******************************/

		Recur monthlyPayment = new Recur (recur_unit, start_now, start_date,
				num_recurs, period, recur_amount);

		/************************ Request Object ******************************/

		ResPurchaseAch resPurchaseAch = new ResPurchaseAch();
		resPurchaseAch.setDataKey(data_key);
		resPurchaseAch.setOrderId(order_id);
		resPurchaseAch.setCustId(cust_id);
		resPurchaseAch.setAmount(amount);
		resPurchaseAch.setRecur(monthlyPayment);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resPurchaseAch);
		mpgReq.send();

		/************************ Receipt Object ******************************/

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
			System.out.println("PaymentType = " + receipt.getPaymentType() + "\n");

			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Sec = " + receipt.getResSec());
			System.out.println("Cust First Name = " + receipt.getResCustFirstName());
			System.out.println("Cust Last Name = " + receipt.getResCustLastName());
			System.out.println("Cust Address1 = " + receipt.getResCustAddress1());
			System.out.println("Cust Address2 = " + receipt.getResCustAddress2());
			System.out.println("Cust City = " + receipt.getResCustCity());
			System.out.println("Cust State = " + receipt.getResCustState());
			System.out.println("Cust Zip = " + receipt.getResCustZip());
			System.out.println("Routing Num = " + receipt.getResRoutingNum());
			System.out.println("Account Num = " + receipt.getResAccountNum());
			System.out.println("Masked Account Num = " + receipt.getResMaskedAccountNum());
			System.out.println("Check Num = " + receipt.getResCheckNum());
			System.out.println("Account Type = " + receipt.getResAccountType());	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}  
}
