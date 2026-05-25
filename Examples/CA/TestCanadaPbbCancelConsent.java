package Canada;

import JavaAPI.HttpsPostRequest;
import JavaAPI.PBB_CancelConsent;
import JavaAPI.PBB_GetTransactionData;
import JavaAPI.Receipt;

public class TestCanadaPbbCancelConsent
{
	public static void main(String[] args)
	{
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String processing_country_code = "CA";
		String consentId = "e0663091-24b9-4df6-b5e6-9ffccaa9cba4";
		String cancelRef = "e32f9a6b-843c-4fbb-a8ac-2b11bbca7fd3";
		String cancelReason="this is the optional cancel ";



		PBB_CancelConsent pbbCancelConsent = new PBB_CancelConsent();
		pbbCancelConsent.setConsentId(consentId);
		pbbCancelConsent.SetCancelReference(cancelRef);
		pbbCancelConsent.setCancelReason(cancelReason);



		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(pbbCancelConsent);
		System.out.println(mpgReq.toXML());
		mpgReq.send();


		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();
			if((receipt.getCorrelationId()!=null)&& !(receipt.getCorrelationId().isEmpty()))
			{

				System.out.println("RequestId = " + receipt.getRequestId());
				System.out.println("CorrelationId = " + receipt.getCorrelationId());

			}
			else
			{
				System.out.println("CODE" + receipt.getCode());
				System.out.println("Type" + receipt.getType());
				System.out.println("Message" + receipt.getMessage());
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
