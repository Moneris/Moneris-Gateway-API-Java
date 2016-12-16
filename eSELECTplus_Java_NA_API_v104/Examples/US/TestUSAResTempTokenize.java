package USA;
import java.io.*;

import JavaAPI.*;

public class TestUSAResTempTokenize
{
	public static void main(String args[]) throws IOException
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";

		String order_id = "mvt3562495927";
		String txn_number = "846740-0_25";
		String crypt_type = "7";
		String duration = "60";
		String processing_country_code = "US";

		ResTempTokenize temp_tokenize = new ResTempTokenize ();
		temp_tokenize.setOrderId(order_id);
		temp_tokenize.setTxnNumber(txn_number);
		temp_tokenize.setDuration(duration);
		temp_tokenize.setCryptType(crypt_type);
		//temp_tokenize.setDataKeyFormat("1"); //1=F6L4 w/ Length preserve, 2=F6L4 w/o Length preserve

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(temp_tokenize);
		mpgReq.send();

		try
		{
			Receipt resreceipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + resreceipt.getDataKey());
			System.out.println("ResponseCode = " + resreceipt.getResponseCode());
			System.out.println("Message = " + resreceipt.getMessage());
			System.out.println("TransDate = " + resreceipt.getTransDate());
			System.out.println("TransTime = " + resreceipt.getTransTime());
			System.out.println("Complete = " + resreceipt.getComplete());
			System.out.println("TimedOut = " + resreceipt.getTimedOut());
			System.out.println("ResSuccess = " + resreceipt.getResSuccess());
			System.out.println("PaymentType = " + resreceipt.getPaymentType() + "\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
