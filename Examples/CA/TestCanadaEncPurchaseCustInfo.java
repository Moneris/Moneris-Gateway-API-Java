package Canada;

import JavaAPI.*;

public class TestCanadaEncPurchaseCustInfo
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "5.00";
		String crypt = "7";
		String device_type = "idtech_bdk";
		String enc_track2 = "02840085000000000416EA7637C390587C593F32E83AFC176194B43F34B8F898BCF7715270F280B3804F25340C85C7C3D36138780D880431393035FFFF3141594047A0009B04AE03";
		
		String processing_country_code = "CA";
		boolean status_check = false;


		/********************* Billing/Shipping Variables ****************************/

		String first_name = "Bob";
		String last_name = "Smith";
		String company_name = "ProLine Inc.";
		String address = "623 Bears Ave";
		String city = "Chicago";
		String province = "Illinois";
		String postal_code = "M1M2M1";
		String country = "Canada";
		String phone = "777-999-7777";
		String fax = "777-999-7778";
		String tax1 = "10.00";
		String tax2 = "5.78";
		String tax3 = "4.56";
		String shipping_cost = "10.00";

		/********************* Order Line Item Variables *****************************/

		String[] item_description = new String[] { "Chicago Bears Helmet", "Soldier Field Poster" };
		String[] item_quantity = new String[] { "1", "1" };
		String[] item_product_code = new String[] { "CB3450", "SF998S" };
		String[] item_extended_amount = new String[] { "150.00", "19.79" };

		/********************** Customer Information Object **************************/

		CustInfo customer = new CustInfo();

		/********************** Set Customer Billing Information **********************/

		customer.setBilling(first_name, last_name, company_name, address, city,
				province, postal_code, country, phone, fax, tax1, tax2,
				tax3, shipping_cost);

		/******************** Set Customer Shipping Information ***********************/

		customer.setShipping(first_name, last_name, company_name, address, city,
				province, postal_code, country, phone, fax, tax1, tax2,
				tax3, shipping_cost);

		/***************************** Order Line Items  ******************************/

		customer.setItem(item_description[0], item_quantity[0],
				item_product_code[0], item_extended_amount[0]);

		customer.setItem(item_description[1], item_quantity[1],
				item_product_code[1], item_extended_amount[1]);

		
		EncPurchase encpurchase = new EncPurchase();
		encpurchase.setOrderId(order_id);
		encpurchase.setAmount(amount);
		encpurchase.setEncTrack2(enc_track2);
		encpurchase.setDeviceType(device_type);
		encpurchase.setCryptType(crypt);
		encpurchase.setCustInfo(customer);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(encpurchase);
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
			System.out.println("CardLevelResult = " + receipt.getCardLevelResult());
			System.out.println("MaskedPan = " + receipt.getMaskedPan());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
