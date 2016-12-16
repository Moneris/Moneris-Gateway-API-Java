package Canada;
                                                                           
import java.util.*;
import JavaAPI.*;

public class TestCanadaPreauthCustInfo
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String amount = "1.00";
		String pan = "4242424242424242";
		String expdate = "1901"; //YYMM format
		String crypt = "7";
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

		/*****************************************************************************/
		/*								             */
		/*			Customer Information Option 1			     */
		/*									     */
		/*****************************************************************************/

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


		/*****************************************************************************/
		/*								             */
		/*			Customer Information Option 2			     */
		/*									     */
		/*****************************************************************************/



		/********************** Customer Information Object **************************/

		CustInfo customer2 = new CustInfo();

		/******************************* Billing Hashtable ***************************/


		Hashtable<String, String> b = new Hashtable<String, String>();	//billing hashtable

		b.put("first_name", first_name);
		b.put("last_name", last_name);
		b.put("company_name", company_name);
		b.put("address", address);
		b.put("city", city);
		b.put("province", province);
		b.put("postal_code", postal_code);
		b.put("country", country);
		b.put("phone", phone);
		b.put("fax", fax);
		b.put("tax1", tax1);       //federal tax
		b.put("tax2", tax2);        //prov tax
		b.put("tax3", tax3);        //luxury tax
		b.put("shipping_cost", shipping_cost);   //shipping cost  

		customer2.setBilling(b);

		/****************************** Shipping Hashtable ***************************/

		Hashtable<String, String> s = new Hashtable<String, String>();	//shipping hashtable

		s.put("first_name", first_name);
		s.put("last_name", last_name);
		s.put("company_name", company_name);
		s.put("address", address);
		s.put("city", city);
		s.put("province", province);
		s.put("postal_code", postal_code);
		s.put("country", country);
		s.put("phone", phone);
		s.put("fax", fax);
		s.put("tax1", tax1);       //federal tax
		s.put("tax2", tax2);        //prov tax
		s.put("tax3", tax3);        //luxury tax
		s.put("shipping_cost", shipping_cost);   //shipping cost

		customer2.setShipping(s);

		/************************* Order Line Item1 Hashtable ************************/

		Hashtable<String, String> i1 = new Hashtable<String, String>();		//item hashtable #1

		i1.put("name", item_description[0]);
		i1.put("quantity", item_quantity[0]);
		i1.put("product_code", item_product_code[0]);
		i1.put("extended_amount", item_extended_amount[0]);

		customer2.setItem(i1);

		/************************* Order Line Item2 Hashtable **************************/

		Hashtable<String, String> i2 = new Hashtable<String, String>();		//item hashtable #2

		i2.put("name", "item2's name");
		i2.put("quantity", "7");
		i2.put("product_code", "item2's product code");
		i2.put("extended_amount", "5.01");

		customer2.setItem(i2);

		/*************** Miscellaneous Customer Information Methods *******************/

		customer.setEmail("nick@widget.com");
		customer.setInstructions("Make it fast!");

		/********************** Transactional Request Object **************************/

		PreAuth preauth = new PreAuth();
		preauth.setOrderId(order_id);
		preauth.setAmount(amount);
		preauth.setPan(pan);
		preauth.setExpdate(expdate);
		preauth.setCryptType(crypt);
		preauth.setCustInfo(customer);

		/**************************** Https Post Request ***************************/

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(preauth);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		/******************************* Receipt ***********************************/

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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
