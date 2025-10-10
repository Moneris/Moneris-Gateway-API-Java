package Canada;

import JavaAPI.*;

public class TestCanadaMpiTxn
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String amount = "1.00";
		String xid = "12345678910111214039";
		String MD = xid + "mycardinfo" + amount;
		String merchantUrl = "www.mystoreurl.com";
		String accept = "true";
		String userAgent = "Mozilla";
		String processing_country_code = "CA";
		String pan = "4242424242424242";
		String expdate = "1905";
		boolean status_check = false;

		MpiTxn mpiTxn = new MpiTxn();
		mpiTxn.setXid(xid);
		mpiTxn.setPan(pan);
		mpiTxn.setExpDate(expdate);
		mpiTxn.setAmount(amount);
		mpiTxn.setMD(MD);
		mpiTxn.setMerchantUrl(merchantUrl);
		mpiTxn.setHttpAccept(accept);
		mpiTxn.setHttpUserAgent(userAgent);

		//************************OPTIONAL VARIABLES***************************

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mpiTxn);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("MpiMessage = " + receipt.getMpiMessage());
			System.out.println("MpiSuccess = " + receipt.getMpiSuccess());

			if (receipt.getMpiSuccess().equals("true"))
			{
				System.out.println(receipt.getMpiInLineForm());
			}
			else 
			{
				System.out.println(receipt.getMessage());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
