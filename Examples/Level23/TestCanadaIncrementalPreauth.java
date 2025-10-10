package Canada;

import JavaAPI.*;

public class TestCanadaIncrementalPreauth
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String order_id = "Test1683565649745";
		String amount = "8.00";
		String txn_number = "85401-0_473";
		String processing_country_code = "CA";



		IncrementalPreauth incrementalPreauth = new IncrementalPreauth();
		incrementalPreauth.setOrderId(order_id);
		incrementalPreauth.setAmount(amount);
		incrementalPreauth.setTxnNumber(txn_number);




		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(incrementalPreauth);
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
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());

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
