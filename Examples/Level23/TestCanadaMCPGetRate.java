package Canada;

import JavaAPI.*;

public class TestCanadaMCPGetRate
{
	public static void main(String[] args)
	{
        String store_id = "store1";
		String api_token = "yesguy1";
//		String store_id = "intuit_sped";
//		String api_token = "spedguy"; 
		
        String processing_country_code = "CA";
        
        MCPGetRate getRate = new MCPGetRate();
        getRate.setMCPVersion("1.0");   //MCP Version number.  Should always be 1.0
        getRate.setRateTxnType("P");    //P or R are valid values (Purchase or Refund)
        
        MCPRate rate = new MCPRate();
        rate.addCardholderAmount("500", "840");   //penny value amount 1.25 = 125. Foreign amount and SO-4217 country currency number    
        //rate.addMerchantSettlementAmount("200", "826");   //penny value amount 1.25 = 125. Domestic(CAD) amount and SO-4217 country currency number
        //rate.addMerchantSettlementAmount("300", "036");   //penny value amount 1.25 = 125. Domestic(CAD) amount and SO-4217 country currency number
               
        getRate.setMCPRateInfo(rate);
        
        HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true);   //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(getRate);
        mpgReq.send();
        
        try
        {
            Receipt receipt = mpgReq.getReceipt();
            
            System.out.println("RateTxnType = " + receipt.getRateTxnType());
            System.out.println("MCPRateToken = " + receipt.getMCPRateToken());
            
            System.out.println("RateInqStartTime = " + receipt.getRateInqStartTime());  //The time (unix UTC) of when the rate is requested
            System.out.println("RateInqEndTime = " + receipt.getRateInqEndTime());  //The time (unix UTC) of when the rate is returned
            System.out.println("RateValidityStartTime = " + receipt.getRateValidityStartTime());    //The time (unix UTC) of when the rate is valid from
            System.out.println("RateValidityEndTime = " + receipt.getRateValidityEndTime());    //The time (unix UTC) of when the rate is valid until
            System.out.println("RateValidityPeriod = " + receipt.getRateValidityPeriod());  //The time in minutes this rate is valid for
            
            System.out.println("ResponseCode = " + receipt.getResponseCode());
            System.out.println("Message = " + receipt.getMessage());
            System.out.println("Complete = " + receipt.getComplete());
            System.out.println("TransDate = " + receipt.getTransDate());
            System.out.println("TransTime = " + receipt.getTransTime());
            System.out.println("TimedOut = " + receipt.getTimedOut());
            
            //RateData
			for (int index = 0; index < receipt.getRatesCount(); index++)
			{
                System.out.println("MCPRate = " + receipt.getMCPRate(index));
                System.out.println("MerchantSettlementCurrency = " + receipt.getMerchantSettlementCurrency(index));
                System.out.println("MerchantSettlementAmount = " + receipt.getMerchantSettlementAmount(index));    //Domestic(CAD) amount
                System.out.println("CardholderCurrencyCode = " + receipt.getCardholderCurrencyCode(index));
                System.out.println("CardholderAmount = " + receipt.getCardholderAmount(index));    //Foreign amount
                
                System.out.println("MCPErrorStatusCode = " + receipt.getMCPErrorStatusCode(index));
                System.out.println("MCPErrorMessage = " + receipt.getMCPErrorMessage(index));
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
}
