package USA;

import JavaAPI.*;

public class TestUSAMpiAcs
{
	public static void main(String[] args)
	{
		String store_id = "monusqa006";
		String api_token = "qatoken";
		String amount = "1.00";
		String xid = "12345678910111214005";
		String MD = xid + "mycardinfo" + amount;
		String PaRes = "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"
					+ "YIw==";
		String processing_country_code = "US";
		boolean status_check = false;

		MpiAcs mpiAcs = new MpiAcs();
		mpiAcs.setPaRes(PaRes);
		mpiAcs.setMD(MD);

		//************************OPTIONAL VARIABLES***************************

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mpiAcs);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("MpiMessage = " + receipt.getMpiMessage());
			System.out.println("MpiSuccess = " + receipt.getMpiSuccess());
			System.out.println("Message = " + receipt.getMessage());

			if (receipt.getMpiSuccess().equals("true"))
			{
				System.out.println("Cavv = " + receipt.getMpiCavv());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
