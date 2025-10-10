package Canada;

import java.util.Hashtable;
import java.util.Set;

import JavaAPI.*;

public class TestCanadaVdotMeInfo
{
	public static void main(String[] args)
	{
		String store_id = "store2";
		String api_token = "yesguy";
		String call_id = "8620484083629792701";
		String processing_country_code = "CA";
		boolean status_check = false;

		VdotMeInfo vmeinfo = new VdotMeInfo();
		vmeinfo.setCallId(call_id);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(vmeinfo);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();
		
		

		try
		{
			Receipt receipt = mpgReq.getReceipt();
			
			System.out.println("dump of vmeDataHash variables:");
			Hashtable<String, String>vmeDataHash = new Hashtable<String, String>();
			vmeDataHash = receipt.getVmeDataHash();
			
			 Set<String> keys = vmeDataHash.keySet();
		        for(String key: keys){
		            System.out.println("Value of "+key+" is: "+vmeDataHash.get(key));
		        }

			System.out.println("Response Code: " + receipt.getResponseCode());
			System.out.println("Response Message: " + receipt.getMessage());
			System.out.println("Currency Code: " + receipt.getCurrencyCode());
			System.out.println("Payment Totals: " + receipt.getPaymentTotal());
			System.out.println("User First Name: " + receipt.getUserFirstName());
			System.out.println("User Last Name: " + receipt.getUserLastName());
			System.out.println("Username: " + receipt.getUserName());
			System.out.println("User Email: " + receipt.getUserEmail());
			System.out.println("Encrypted User ID: " + receipt.getEncUserId());
			System.out.println("Creation Time Stamp: " + receipt.getCreationTimeStamp());
			System.out.println("Name on Card: " + receipt.getNameOnCard());
			System.out.println("Expiration Month: " + receipt.getExpirationDateMonth());
			System.out.println("Expiration Year: " + receipt.getExpirationDateYear());
			System.out.println("Last 4 Digits: " + receipt.getLastFourDigits());
			System.out.println("Bin Number (6 Digits): " + receipt.getBinSixDigits());
			System.out.println("Card Brand: " + receipt.getCardBrand());
			System.out.println("Card Type: " + receipt.getVdotMeCardType());
			System.out.println("Billing Person Name: " + receipt.getPersonName());
			System.out.println("Billing Address Line 1: " + receipt.getBillingAddressLine1());
			System.out.println("Billing City: " + receipt.getBillingCity());
			System.out.println("Billing State/Province Code: " + receipt.getBillingStateProvinceCode());
			System.out.println("Billing Postal Code: " + receipt.getBillingPostalCode());
			System.out.println("Billing Country Code: " + receipt.getBillingCountryCode());
			System.out.println("Billing Phone: " + receipt.getBillingPhone());
			System.out.println("Billing ID: " + receipt.getBillingId());
			System.out.println("Billing Verification Status: " + receipt.getBillingVerificationStatus());
			System.out.println("Partial Shipping Country Code: " + receipt.getPartialShippingCountryCode());
			System.out.println("Partial Shipping Postal Code: " + receipt.getPartialShippingPostalCode());
			System.out.println("Shipping Person Name: " + receipt.getShippingPersonName());
			System.out.println("Shipping Address Line 1: " + receipt.getShipAddressLine1());
			System.out.println("Shipping City: " + receipt.getShippingCity());
			System.out.println("Shipping State/Province Code: " + receipt.getShippingStateProvinceCode());
			System.out.println("Shipping Postal Code: " + receipt.getShippingPostalCode());
			System.out.println("Shipping Country Code: " + receipt.getShippingCountryCode());
			System.out.println("Shipping Phone: " + receipt.getShippingPhone());
			System.out.println("Shipping Default: " + receipt.getShippingDefault());
			System.out.println("Shipping ID: " + receipt.getShippingId());
			System.out.println("Shipping Verification Status: " + receipt.getShippingVerificationStatus());
			System.out.println("isExpired: " + receipt.getIsExpired());
			System.out.println("Base Image File Name: " + receipt.getBaseImageFileName());
			System.out.println("Height: " + receipt.getHeight());
			System.out.println("Width: " + receipt.getWidth());
			System.out.println("Issuer Bid: " + receipt.getIssuerBid());
			System.out.println("Risk Advice: " + receipt.getRiskAdvice());
			System.out.println("Risk Score: " + receipt.getRiskScore());
			System.out.println("AVS Response Code: " + receipt.getAvsResponseCode());
			System.out.println("CVV Response Code: " + receipt.getCvvResponseCode());
			System.out.println("\r\nPress the enter key to exit");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
