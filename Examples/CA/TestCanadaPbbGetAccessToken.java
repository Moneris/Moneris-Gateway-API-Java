package Canada;

import JavaAPI.HttpsPostRequest;
import JavaAPI.PBB_GetAccessToken;
import JavaAPI.Receipt;

import java.util.Hashtable;

public class TestCanadaPbbGetAccessToken
{
	public static void main(String[] args)
	{
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String processing_country_code = "CA";


		PBB_GetAccessToken pbbGetAccessToken = new PBB_GetAccessToken();


		HttpsPostRequest mpgReq = new HttpsPostRequest();

		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(pbbGetAccessToken);
		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();
			System.out.println("TokenType = "+ receipt.getTokenType());
			System.out.println("ExpiresIn = "+receipt.getExpiresIn());
			System.out.println("ExtExpiresIn = "+receipt.getExtExpiresIn());
			System.out.println("AccessToken = "+receipt.getAccessToken());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
