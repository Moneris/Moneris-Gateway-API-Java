package USA;

import JavaAPI.*;

public class TestUSAACHDebit
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String store_id = "monusqa002";
		String api_token = "qatoken";
		//String status = "true";
		String amount = "1.00";

		//ACHInfo Variables
		String sec = "ppd";
		String cust_first_name = "Christian";
		String cust_last_name = "M";
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

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(achdebit);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		/*Status Check Example
		  ACHHttpsPostRequest mpgReq = new ACHHttpsPostRequest(host, store_id, api_token, status, achdebit);
		 */

		/**********************   REQUEST  ************************/

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
			//System.out.println("StatusCode = " + receipt.getStatusCode());
			//System.out.println("StatusMessage = " + receipt.getStatusMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
