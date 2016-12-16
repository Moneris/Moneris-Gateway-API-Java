package Canada;

import JavaAPI.*;

public class TestCanadaIDebitPurchase
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String cust_id = "Lance_Briggs_55";
		String amount = "5.00";
		String track2 = "5268051119993326=0609AAAAAAAAAAAAA000";
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

		/************************** Request *************************/

		IDebitPurchase IOP_Txn = new IDebitPurchase();
		IOP_Txn.setOrderId(order_id);
		IOP_Txn.setCustId(cust_id);
		IOP_Txn.setAmount(amount);
		IOP_Txn.setIdebitTrack2(track2);

		IOP_Txn.setCustInfo(customer);

		//IOP_Txn.setDynamicDescriptor("dynamicdescriptor1");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(IOP_Txn);
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
