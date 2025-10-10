package Canada;

import JavaAPI.*;

public class TestCanadaPurchaseEfraud
{
	public static void main(String[] args)
	{
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String amount = "6000.00";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String pan = "4622943127023886";
		String expdate = "2212"; //YYMM format
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		avsCheck.setAvsEmail("test@host.com");
		avsCheck.setAvsHostname("hostname");
		avsCheck.setAvsBrowser("Mozilla");
		avsCheck.setAvsShiptoCountry("CAN");
		avsCheck.setAvsShipMethod("G");
		avsCheck.setAvsMerchProdSku("123456");
		avsCheck.setAvsCustIp("192.168.0.1");
		avsCheck.setAvsCustPhone("5556667777");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");
		
		InstallmentInfo installmentInfo = new InstallmentInfo();
		installmentInfo.setPlanId("ae859ef1-eb91-b708-8b80-1dd481746401");
		installmentInfo.setPlanIdRef("0000000065");
		installmentInfo.setTacVersion("2");

		Purchase purchase = new Purchase();
		purchase.setOrderId(order_id);
		purchase.setAmount(amount);
		purchase.setPan(pan);
		purchase.setExpdate(expdate);
		purchase.setCryptType(crypt);
//		purchase.setAvsInfo(avsCheck);
//		purchase.setCvdInfo(cvdCheck);
		purchase.setInstallmentInfo(installmentInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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
			System.out.println("Avs Response = " + receipt.getAvsResultCode());
			System.out.println("Cvd Response = " + receipt.getCvdResultCode());
			System.out.println("ITD Response = " + receipt.getITDResponse());
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());
			
			InstallmentResults installmentResults = receipt.getInstallmentResults();

			System.out.println("\nPlanId = " + installmentResults.getPlanId() +"\n");
			System.out.println("PlanIDRef = " + installmentResults.getPlanIDRef());
			System.out.println("TacVersion = " + installmentResults.getTacVersion());
			System.out.println("PlanAcceptanceId = " + installmentResults.getPlanAcceptanceId());
			System.out.println("PlanStatus = " + installmentResults.getPlanStatus()); 
			System.out.println("PlanResponse = " + installmentResults.getPlanResponse());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
