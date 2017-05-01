package Canada;

import JavaAPI.*;

public class TestCanadaPaypassRetrieveCheckoutData
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";

		String oauth_token = "5f10925ab82fee9824b5b2ac142a4b5a3acc3034";
		String oauth_verifier = "5004cbbf560a3efb37827ccfce85e869c409df1e";
		String checkout_resource_url = "https://sandbox.api.mastercard.com/online/v3/checkout/301049752";
		String processing_country_code = "CA";

		PaypassRetrieveCheckoutData paypassRetrieveCheckoutData = new PaypassRetrieveCheckoutData();
		paypassRetrieveCheckoutData.setOauthToken(oauth_token);
		paypassRetrieveCheckoutData.setOauthVerifier(oauth_verifier);
		paypassRetrieveCheckoutData.setCheckoutResourceUrl(checkout_resource_url);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(paypassRetrieveCheckoutData);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("MPRequestToken = " + receipt.getMPRequestToken());
			System.out.println("MPRedirectUrl = " + receipt.getMPRedirectUrl());

			//PayPassInfo
			System.out.println("\nCardBrandId = " + receipt.getCardBrandId());
			System.out.println("CardBrandName = " + receipt.getCardBrandName());
			System.out.println("CardBillingAddressCity = " + receipt.getCardBillingAddressCity());
			System.out.println("CardBillingAddressCountry = " + receipt.getCardBillingAddressCountry());
			System.out.println("CardBillingAddressCountrySubdivision = " + receipt.getCardBillingAddressCountrySubdivision());
			System.out.println("CardBillingAddressLine1 = " + receipt.getCardBillingAddressLine1());
			System.out.println("CardBillingAddressLine2 = " + receipt.getCardBillingAddressLine2());
			System.out.println("CardBillingAddressPostalCode = " + receipt.getCardBillingAddressPostalCode());
			System.out.println("CardCardHolderName = " + receipt.getCardCardHolderName());
			System.out.println("CardExpiryMonth = " + receipt.getCardExpiryMonth());
			System.out.println("CardExpiryYear = " + receipt.getCardExpiryYear());
			System.out.println("TransactionId = " + receipt.getTransactionId());
			System.out.println("ContactEmailAddress = " + receipt.getContactEmailAddress());
			System.out.println("ContactFirstName = " + receipt.getContactFirstName());
			System.out.println("ContactLastName = " + receipt.getContactLastName());
			System.out.println("ContactPhoneNumber = " + receipt.getContactPhoneNumber());
			System.out.println("ShippingAddressCity = " + receipt.getShippingAddressCity());
			System.out.println("ShippingAddressCountry = " + receipt.getShippingAddressCountry());
			System.out.println("ShippingAddressCountrySubdivision = " + receipt.getShippingAddressCountrySubdivision());
			System.out.println("ShippingAddressLine1 = " + receipt.getShippingAddressLine1());
			System.out.println("ShippingAddressLine2 = " + receipt.getShippingAddressLine2());
			System.out.println("ShippingAddressPostalCode = " + receipt.getShippingAddressPostalCode());
			System.out.println("ShippingAddressRecipientName = " + receipt.getShippingAddressRecipientName());
			System.out.println("ShippingAddressRecipientPhoneNumber = " + receipt.getShippingAddressRecipientPhoneNumber());
			System.out.println("PayPassWalletIndicator = " + receipt.getPayPassWalletIndicator());
			System.out.println("AuthenticationOptionsAuthenticateMethod = " + receipt.getAuthenticationOptionsAuthenticateMethod());
			System.out.println("AuthenticationOptionsCardEnrollmentMethod = " + receipt.getAuthenticationOptionsCardEnrollmentMethod());
			System.out.println("CardAccountNumber = " + receipt.getCardAccountNumber());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
