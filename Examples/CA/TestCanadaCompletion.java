package Canada;

import JavaAPI.*;

public class TestCanadaCompletion
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String order_id = "Test1683565649745";
		String amount = "12.00";
		String txn_number = "85401-0_473";
		String crypt = "7";
		String cust_id = "my customer id";
		String dynamic_descriptor = "my descriptor";
		String ship_indicator = "F" ;
		String processing_country_code = "CA";
		boolean status_check = false;

		Completion completion = new Completion();
		completion.setOrderId(order_id);
		completion.setCompAmount(amount);
		completion.setTxnNumber(txn_number);
		completion.setCryptType(crypt);
		completion.setCustId(cust_id);
		completion.setDynamicDescriptor(dynamic_descriptor);
		//completion.setShipIndicator(ship_indicator); //optional

		//Optional
		InstallmentInfo installmentInfo = new InstallmentInfo();
		installmentInfo.setPlanId("ae859ef1-eb91-b708-8b80-1dd481746401");
		installmentInfo.setPlanIdRef("0000000065");
		installmentInfo.setTacVersion("2");
		completion.setInstallmentInfo(installmentInfo);

		PBBInfo pbbInfo= new PBBInfo();
		String consentId="1b5ee10a-5356-4a71-b2cf-874ab134661f";
		String cryptogram="eyJraWQiOiJpZGlyZWN0LXRva2VuLWp3cy0wMDEiLCJhbGciOiJFUzI1NiJ9..0I37qRcPh0lzhTupEwGgkqE_6PD6Nv6UOe37lUlStg0oH6ELp67BLHg8T8HzEwMWxVXk8qvISYvCu3XwNw_ADg";
		String cryptogramExpiry= "2024-08-28T17:54:48.000Z";
		String paymentMethod ="BANK_ACCOUNT_CHEQUING";
		String channel="DESKTOP_WEB";
		pbbInfo.setConsentId(consentId);
		pbbInfo.setCryptogram(cryptogram);
		pbbInfo.setCryptogramExpiry(cryptogramExpiry);
		pbbInfo.setPaymentMethod(paymentMethod);
		pbbInfo.setchannel(channel);
		completion.setPbbInfo(pbbInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(completion);
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
