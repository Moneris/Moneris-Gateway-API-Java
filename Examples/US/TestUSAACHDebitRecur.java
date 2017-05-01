package USA;

import JavaAPI.*;

public class TestUSAACHDebitRecur
{
	public static void main(String[] args)
	{
		String order_id = "dotnetaschdebitssrecurtest12323";
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String amount = "1.00";

		//ACHInfo Variables
		String sec = "ppd";
		String cust_first_name = "Bob";
		String cust_last_name = "Smith";
		String cust_address1 = "3300 Bloor St W";
		String cust_address2 = "4th floor west tower";
		String cust_city = "Toronto";
		String cust_state = "ON";
		String cust_zip = "M1M1M1";
		String routing_num = "490000018";
		String account_num = "222222";
		String check_num = "11";
		String account_type = "checking";
		String micr = "t071000013t742941347o128";
		String dl_num = "CO-12312312";
		String magstripe = "no";
		String image_front = "";
		String image_back = "";
		String processing_country_code = "US";
		boolean status_check = false;

		ACHInfo achinfo = new ACHInfo(sec, cust_first_name, cust_last_name,
				cust_address1, cust_address2, cust_city, cust_state, cust_zip,
				routing_num, account_num, check_num, account_type, micr);


		achinfo.setImgFront(image_front);
		achinfo.setImgBack(image_back);
		achinfo.setDlNum(dl_num);
		achinfo.setMagstripe(magstripe);

		ACHDebit achdebit = new ACHDebit();
		achdebit.setOrderId(order_id);
		achdebit.setAmount(amount);
		achdebit.setAchInfo(achinfo);

		//************************OPTIONAL VARIABLES***************************

		//Cust_id Variable
		String cust_id = "customer1";
		achdebit.setCustId(cust_id);

		//Recur Variables
		//hard coded values for testing
		String recur_unit = "month";   //valid values are (day,week,month)
		String start_now = "true";	   //valid values are (true,false)
		String start_date = "2018/10/01";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "1.01";

		Recur recurInfo = new Recur(recur_unit, start_now, start_date, num_recurs, period, recur_amount);
		achdebit.setRecurInfo(recurInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(achdebit);
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
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
