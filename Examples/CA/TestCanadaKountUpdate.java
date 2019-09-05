package Canada;

import java.util.Map;

import JavaAPI.*;

public class TestCanadaKountUpdate
{
	public static void main(String args[])
	{
		/********************** Request Variables ****************************/

		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		
		KountUpdate kountUpdate = new KountUpdate ();
		
		kountUpdate.setKountTransactionId("PHJS0J2PK1MB"); //kount transaction ID number that is returned in the response of a kount_inquiry request
		kountUpdate.setKountMerchantId("760000"); //6 digit - This is a UNIQUE local identifier used by the merchant to identify the kount inquiry request
		kountUpdate.setKountApiKey("mykey"); //214 character max - This is a UNIQUE local identifier used by the merchant to identify the kount inquiry request
		kountUpdate.setOrderId("nqa-orderidkount-5"); //64 characters max - This is a UNIQUE local identifier used by the merchant to identify the transaction e.g. purchase order number.
		kountUpdate.setFinancialOrderId("nqa-fin-orderid-1"); //64 characters max - This is a local identifier used by the merchant to identify the transaction e.g. purchase order number
		kountUpdate.setDataKey("3B1C19fgfRObNHaQh5qVCpRW2"); //token from moneris vault service to represent pan if previously tokenized
		kountUpdate.setPaymentToken("4242424242424242"); //payment token submitted by merchant (ie: credit card, payer ID)
								/*	Payment Type Must be one of the following values:
								APAY-�Apple Pay
								CARD-�Credit Card
								PYPL-�PayPal
								NONE-�None
								GOOG-�Google Checkout-
								
								GIFT-�Gift Card
								INTERAC-�Interac
								CHEK - Check
								GDMP - Green Dot Money Pack
								BLML - Bill Me Later
								BPAY - BPAY
								NETELLER - Neteller
								GIROPAY - GiroPay
								ELV - ELV
								MERCADE_PAGO - Mercade Pago
								SEPA - Single Euro Payments Area
								CARTE_BLEUE - Carte Bleue
								POLI - POLi
								Skrill/Moneybookers - SKRILL
								SOFORT - Sofort
								*/
		kountUpdate.setPaymentType("CARD"); //payment type submitted by merchant
		kountUpdate.setSessionId("xjudq804i1049jkjakdad");  //unique session id.  Must be unique over a 30-day spa
		kountUpdate.setPaymentResponse("A"); //A - Authorized, D - Declined - payment transaction response
		kountUpdate.setAvsResponse("M"); //M - Match, N - No Match - avs verification response returned from payment request. This can be provided should kount_inquiry be performed after the transaction is complete
		kountUpdate.setCvdResponse("N"); //M - Match, N - No Match, X - Unsupported/Unavailable - cvd response returned to merchant from processor. This can be provided should kount_inquiry be performed after the transaction is complete
		kountUpdate.setLast4("4242"); ////last 4 digits of credit card value
		kountUpdate.setEvaluate("Y"); //Y or N - If set to Y, full re-evaluation will be performed with Kount.  If unset, default value is N
		kountUpdate.setRefundStatus("C"); //R = Refund, C = Chargeback		
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(kountUpdate);
		mpgReq.send();
		
		try
		{
			Receipt receipt = mpgReq.getReceipt();
			
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("KountResult = " + receipt.getKountResult());
			System.out.println("KountTransactionId = " + receipt.getKountTransactionId());
			System.out.println("KountScore = " + receipt.getKountScore());
			System.out.println("KountInfo = " + receipt.getKountInfo());
			
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
        
  	}
} // end TestKountUpdate example
