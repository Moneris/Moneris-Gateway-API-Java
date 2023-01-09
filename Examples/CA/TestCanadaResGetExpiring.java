package Canada;

import JavaAPI.*;

public class TestCanadaResGetExpiring
{
	public static void main(String[] args)
	{
		String store_id = "monca02932";
		String api_token = "CG8kYzGgzVU5z23irgMx";
		String processing_country_code = "CA";
		boolean status_check = false;

		ResGetExpiring resGetExpiring = new ResGetExpiring();

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resGetExpiring);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());

			//ResolveData
			for (int index =0; index < receipt.getExpiredCardCount(); index++)
			{
				System.out.println("\nDataKey = " + index);
				System.out.println("Payment Type = " + receipt.getExpPaymentType(index));
				System.out.println("Cust ID = " + receipt.getExpCustId(index));
				System.out.println("Phone = " + receipt.getExpPhone(index));
				System.out.println("Email = " + receipt.getExpEmail(index));
				System.out.println("Note = " + receipt.getExpNote(index));
				System.out.println("Masked Pan = " + receipt.getExpMaskedPan(index));
				System.out.println("Exp Date = " + receipt.getExpExpdate(index));
				System.out.println("Crypt Type = " + receipt.getExpCryptType(index));
				System.out.println("Avs Street Number = " + receipt.getExpAvsStreetNumber(index));
				System.out.println("Avs Street Name = " + receipt.getExpAvsStreetName(index));
				System.out.println("Avs Zipcode = " + receipt.getExpAvsZipCode(index));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
