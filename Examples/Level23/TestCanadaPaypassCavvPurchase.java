package Canada;

import JavaAPI.*;

public class TestCanadaPaypassCavvPurchase
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cavv = "AAABBJg0VhI0VniQEjRWAAAAAAA";
		String cust_id = "customer1";
		String amount = "1.00";
		String mp_request_token = "5f10925ab82fee9824b5b2ac142a4b5a3acc3034";
		String crypt_type = "7";
		String dynamic_descriptor = "paypass1";
		String processing_country_code = "CA";

		PaypassCavvPurchase paypassPurchase = new PaypassCavvPurchase();
		paypassPurchase.setOrderId(order_id);
		paypassPurchase.setCavv(cavv);
		paypassPurchase.setCustId(cust_id);
		paypassPurchase.setAmount(amount);
		paypassPurchase.setMpRequestToken(mp_request_token);
		paypassPurchase.setCryptType(crypt_type);
		paypassPurchase.setDynamicDescriptor(dynamic_descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(paypassPurchase);
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
