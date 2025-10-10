package Canada;

import java.util.HashMap;
import java.util.Map;

import JavaAPI.*;

public class TestCanadaResMpiTxn
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String data_key = "ot-7hkuLdmybbHdUD0y2gCXQQx6J";
		String amount = "1.00";
		java.util.Date createDate = new java.util.Date();
		String xid = "TEMPXID"+ createDate.getTime();
		String MD = xid + "mycardinfo" + amount;
		String merchantUrl = "www.mystoreurl.com";
		String accept = "true";
		String userAgent = "Mozilla";
		String processing_country_code = "CA";
		String expdate = "1712";
		boolean status_check = false;

		ResMpiTxn resMpiTxn = new ResMpiTxn();
		resMpiTxn.setDataKey(data_key);
		resMpiTxn.setXid(xid);
		resMpiTxn.setAmount(amount);
		resMpiTxn.setMD(MD);
		resMpiTxn.setMerchantUrl(merchantUrl);
		resMpiTxn.setAccept(accept);
		resMpiTxn.setUserAgent(userAgent);
		resMpiTxn.setExpDate(expdate);

		//************************OPTIONAL VARIABLES***************************

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resMpiTxn);
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

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
