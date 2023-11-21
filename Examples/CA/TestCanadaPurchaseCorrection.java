package Canada;

import JavaAPI.*;

public class TestCanadaPurchaseCorrection
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String order_id = "Test1471030762599";
		String txn_number = "115257-0_10";
		String crypt = "7";
		String dynamic_descriptor = "123456";
		String processing_country_code = "CA";
		boolean status_check = false;

		PurchaseCorrection purchasecorrection = new PurchaseCorrection();
		purchasecorrection.setOrderId(order_id);
		purchasecorrection.setTxnNumber(txn_number);
		purchasecorrection.setCryptType(crypt);
		purchasecorrection.setDynamicDescriptor(dynamic_descriptor);
		purchasecorrection.setCustId("my customer id");

		//Optional
		InstallmentInfo installmentInfo = new InstallmentInfo();
		installmentInfo.setPlanId("ae859ef1-eb91-b708-8b80-1dd481746401");
		installmentInfo.setPlanIdRef("0000000065");
		installmentInfo.setTacVersion("2");
		//purchasecorrection.setInstallmentInfo(installmentInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchasecorrection);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());

			// InstallmentResults installmentResults = receipt.getInstallmentResults();

			// System.out.println("\nPlanId = " + installmentResults.getPlanId() +"\n");
			// System.out.println("PlanIDRef = " + installmentResults.getPlanIDRef());
			// System.out.println("TacVersion = " + installmentResults.getTacVersion());
			// System.out.println("PlanAcceptanceId = " + installmentResults.getPlanAcceptanceId());
			// System.out.println("PlanStatus = " + installmentResults.getPlanStatus()); 
			// System.out.println("PlanResponse = " + installmentResults.getPlanResponse());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
