package Canada;
import JavaAPI.*;


public class TestCanadaResInstallmentLookup
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String amount = "600.00";
		String data_key = "8cwY6hotzkM362ygNiyt1BtY0";
		String expdate = "2212"; //For Temp Token
		
		ResInstallmentLookup resInstallmentLookup = new ResInstallmentLookup();
		resInstallmentLookup.setOrderId(order_id);
		resInstallmentLookup.setAmount(amount);
		resInstallmentLookup.setDataKey(data_key);
		//resInstallmentLookup.setExpdate(expdate); //Temp Tokens only

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resInstallmentLookup);
		
		//Optional - Proxy
		mpgReq.setProxy(false); //true to use proxy
		mpgReq.setProxyHost("proxyURL");
		mpgReq.setProxyPort("proxyPort");
		mpgReq.setProxyUser("proxyUser"); //optional - domainName\User
		mpgReq.setProxyPassword("proxyPassword"); //optional
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

			EligibleInstallmentPlans eligibleInstallmentPlans = receipt.getEligibleInstallmentPlans();
			
			int planCount = eligibleInstallmentPlans.getPlanCount();
			PlanDetails [] installmentPlans = eligibleInstallmentPlans.getInstallmentPlans();
						
			for (int i = 0; i < planCount; i++)
			{
			    System.out.println("\nPlanId = " + installmentPlans[i].getPlanId() + "\n");
			    System.out.println("PlanIdRef = " + installmentPlans[i].getPlanIdRef());
			    System.out.println("Name = " + installmentPlans[i].getName());
			    System.out.println("Type = " + installmentPlans[i].getType());
			    System.out.println("NumInstallments = " + installmentPlans[i].getNumInstallments());
			    System.out.println("InstallmentFrequency = " + installmentPlans[i].getInstallmentFrequency());
			
			    
			    TAC tac = installmentPlans[i].getTac();
			    
			    TACDetails [] tacDetailsList = tac.getTacDetailsList();
			    int tacCount = tac.getTacCount();
			
			    for (int j = 0; j < tacCount; j++)
			    {
			    	TACDetails tacDetails = tacDetailsList[j];
			    	
			        System.out.println("\nText = " + tacDetails.getText() +"\n");
			        System.out.println("Url = " + tacDetails.getUrl());
			        System.out.println("Version = " + tacDetails.getVersion());
			        System.out.println("LanguageCode = " + tacDetails.getLanguageCode());
			    }
			    
			    PromotionInfo promotionInfo = installmentPlans[i].getPromotionInfo();
			
			    System.out.println("\nPromotionCode = " + promotionInfo.getPromotionCode() +"\n");
			    System.out.println("PromotionId = " + promotionInfo.getPromotionId());
			
			    
			    FirstInstallment firstInstallment = installmentPlans[i].getFirstInstallment();
			
			    System.out.println("\nUpfrontFee = " + firstInstallment.getUpfrontFee() +"\n");
			    System.out.println("InstallmentFee = " + firstInstallment.getInstallmentFee());
			    System.out.println("Amount = " + firstInstallment.getAmount());
			    
			    LastInstallment lastInstallment = installmentPlans[i].getLastInstallment();
			
			    System.out.println("\nInstallmentFee = " + lastInstallment.getInstallmentFee());
			    System.out.println("Amount = " + lastInstallment.getAmount());
			    
			    System.out.println("\nAPR = " + installmentPlans[i].getAPR());
			    System.out.println("\nTotalFees = " + installmentPlans[i].getTotalFees());
			    System.out.println("\nTotalPlanCost = " + installmentPlans[i].getTotalPlanCost());
			}
			
			//Resolve Data
			System.out.println("\ncust_id = " + receipt.getResCustId());
			System.out.println("phone = " + receipt.getResPhone());
			System.out.println("email = " + receipt.getResEmail());
			System.out.println("note = " + receipt.getResNote());
			System.out.println("expdate = " + receipt.getResExpdate());
			System.out.println("masked_pan = " + receipt.getResMaskedPan());
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	}
}
 


