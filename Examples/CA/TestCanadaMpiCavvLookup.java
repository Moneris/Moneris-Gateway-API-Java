package Canada;

import JavaAPI.*;

public class TestCanadaMpiCavvLookup
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		
		//BASE64 Encoded CRes value returned from response at completion of challenge flow.
        String cres = "eyJhY3NUcmFuc0lEIjoiNzQ0ZDI2NjUtNjU2Yy00ZGNiLTg3MWUtYTBkYmMwODA0OTYzIiwibWVzc2FnZVR5cGUiOiJDUmVzIiwiY2hhbGxlbmdlQ29tcGxldGlvbkluZCI6IlkiLCJtZXNzYWdlVmVyc2lvbiI6IjIuMS4wIiwidHJhbnNTdGF0dXMiOiJZIiwidGhyZWVEU1NlcnZlclRyYW5zSUQiOiJlMTFkNDk4NS04ZDI1LTQwZWQtOTlkNi1jMzgwM2ZlNWU2OGYifQ==";
        
		MpiCavvLookup mpiCavvLookup = new MpiCavvLookup();    
		mpiCavvLookup.setCRes(cres);

		//************************OPTIONAL VARIABLES***************************

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mpiCavvLookup);
		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("Message = " + receipt.getMessage());
			
			System.out.println("ThreeDSServerTransId = " + receipt.getMpiThreeDSServerTransId());
			System.out.println("TransStatus = " + receipt.getMpiTransStatus());
			System.out.println("ChallengeCompletionIndicator = " + receipt.getMpiChallengeCompletionIndicator());
			System.out.println("Cavv = " + receipt.getMpiCavv());
			System.out.println("ECI = " + receipt.getMpiEci());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
