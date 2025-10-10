package Canada;

import JavaAPI.*;

public class TestCanadaRecurUpdate
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String order_id = "Test155409282";
		String cust_id = "antonio";
		String recur_amount = "1.50";
		String pan = "4242424242424242";
		String expiry_date = "1902";
		//String add_num = "";
		//String total_num = "";
		//String hold = "";
		//String terminate = "";
		String processing_country_code = "CA";
		boolean status_check = false;

		//Credential on File details
		CofInfo cof = new CofInfo();
		cof.setIssuerId("139X3130ASCXAS9");
		
		RecurUpdate recurUpdate = new RecurUpdate();
		recurUpdate.setOrderId(order_id);
		recurUpdate.setCustId(cust_id);
		recurUpdate.setRecurAmount(recur_amount);
		recurUpdate.setPan(pan);
		recurUpdate.setExpdate(expiry_date);
		//recurUpdate.setAddNumRecurs(add_num);
		//recurUpdate.setTotalNumRecurs(total_num);
		//recurUpdate.setHold(hold);
		//recurUpdate.setTerminate(terminate);
		recurUpdate.setCofInfo(cof);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(recurUpdate);
		mpgReq.setStatusCheck(status_check);
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
