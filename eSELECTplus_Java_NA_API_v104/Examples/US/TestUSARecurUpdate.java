package USA;

import JavaAPI.*;

public class TestUSARecurUpdate
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String order_id = "Test481881053";
		String cust_id = "bob";
		//String recur_amount = "45.00";
		String pan = "5454545454545454";
		String expiry_date = "1701";
		//String p_account_number = "123123123";
		//String presentation_type = "X";
		//String add_num = "";
		//String total_num = "";
		//String hold = "";
		//String terminate = "false";
		String processing_country_code = "US";

		RecurUpdate recurUpdate = new RecurUpdate();
		recurUpdate.setOrderId(order_id);
		recurUpdate.setCustId(cust_id);
		//recurUpdate.setCustId(cust_id);
		//recurUpdate.setRecurAmount(recur_amount);
		recurUpdate.setPan(pan);
		recurUpdate.setExpdate(expiry_date);
		//recurUpdate.setPAccountNumber(p_account_number);
		//recurUpdate.setPresentationType(presentation_type);
		//recurUpdate.setAddNumRecurs(add_num);
		//recurUpdate.setTotalNumRecurs(total_num);
		//recurUpdate.setHold(hold);
		//recurUpdate.setTerminate(terminate);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(recurUpdate);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("RecurUpdateSuccess = " + receipt.getRecurUpdateSuccess());
			System.out.println("NextRecurDate = " + receipt.getNextRecurDate());
			System.out.println("RecurEndDate = " + receipt.getRecurEndDate());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
