package USA;

import JavaAPI.*;

public class TestUSAConvFeePurchaseCustInfo
{
	public static void main(String args[])
	{
		String store_id = "monusqa138";
		String api_token = "qatoken";
		String processing_country_code = "US";

		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String amount = "10.00";
		String pan = "4242424242424242";
		String expiry_date = "1911";
		String crypt = "7";
		String commcard_invoice = "";
		String commcard_tax_amount = "";

		/********************* Billing/Shipping Variables ********************/

		String last_name = "Harris";
		String first_name = "Tommie";
		String company_name = "Da Bears";
		String address = "454 Michigan Ave";
		String city = "Chicago";
		String province = "Illinois";
		String zip_code = "99879";
		String country = "USA";
		String phone_number = "764-908-9989";
		String fax = "764-908-9990";
		String tax1 = "1.00";
		String tax2 = "1.00";
		String tax3 = "1.00";
		String shipping_cost = "2.00";

		/************************* Line Item Variables *************************/

		String[] name = new String[]{"Mini Bears Helmet", "Mini Bills Helmet"};
		String[] quantity = new String[]{"1", "2"};
		String[] product_code = new String[] {"BEOOOWS9", "BUFD099D"};
		String[] extended_amount = new String[] {"4.00", "6.00"};

		/************************ Miscellaneous Variables **********************/

		String email = "T.Harris@ChicagoBears.com";
		String instructions = "Must arrive before opening day at Lambeau";

		/*********************** Transaction Object *******************************/

		Purchase purchase = new Purchase (order_id, amount, pan, expiry_date, crypt, commcard_invoice, commcard_tax_amount);

		/*********************** Billing/Shipping Object **************************/

		BillingLocation billingAddress =
				new BillingLocation  (last_name, first_name, company_name, address,
						city, province, zip_code, country, phone_number,
						fax, tax1, tax2, tax3, shipping_cost);

		ShippingLocation shippingAddress =
				new ShippingLocation  (last_name, first_name, company_name, address,
						city, province, zip_code, country, phone_number,
						fax, tax1, tax2, tax3, shipping_cost);

		/************************ Line Item Object ************************************/

		Item[] lineItems = new Item[]{new Item(name[0], quantity[0], product_code[0], extended_amount[0]),
				new Item(name[1], quantity[1], product_code[1], extended_amount[1])};


		/************************* Customer Information Object *************************/

		CustomerInfo custData =
				new CustomerInfo (billingAddress, shippingAddress, email, instructions, lineItems);


		purchase.setCustInfo (custData);

		//Convenience Fee
		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("5.00");
		purchase.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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
			System.out.println("CfSuccess = " + receipt.getCfSuccess());
			System.out.println("CfStatus = " + receipt.getCfStatus());
			System.out.println("FeeAmount = " + receipt.getFeeAmount());
			System.out.println("FeeRate = " + receipt.getFeeRate());
			System.out.println("FeeType = " + receipt.getFeeType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
