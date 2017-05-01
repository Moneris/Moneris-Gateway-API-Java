package USA;
                                                                            
import java.util.*;

import JavaAPI.*;

public class TestUSAResPurchaseCCCustInfo
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String data_key = "1P5C4C6bNPGg5xGb4ZFfaOTt8";
		String amount = "1.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String crypt_type = "1";
		String processing_country_code = "US";
		boolean status_check = false;

		ResPurchaseCC resPurchaseCC = new ResPurchaseCC();
		resPurchaseCC.setData(data_key);
		resPurchaseCC.setOrderId(order_id);
		resPurchaseCC.setCustId(cust_id);
		resPurchaseCC.setAmount(amount);
		resPurchaseCC.setCryptType(crypt_type);

		//CustInfo Variables
		CustInfo custInfo = new CustInfo();
		custInfo.setEmail("nick@widget.com");
		custInfo.setInstructions("Make it fast!");

		Hashtable<String, String> b = new Hashtable<String, String>();

		b.put("first_name", "Bob");
		b.put("last_name", "Smith");
		b.put("company_name", "Widget Company Inc.");
		b.put("address", "111 Bolts Ave.");
		b.put("city", "Toronto");
		b.put("province", "Ontario");
		b.put("postal_code", "M8T 1T8");
		b.put("country", "Canada");
		b.put("phone", "416-555-5555");
		b.put("fax", "416-555-5555");
		b.put("tax1", "123.45");       //federal tax
		b.put("tax2", "12.34");        //prov tax
		b.put("tax3", "15.45");        //luxury tax
		b.put("shipping_cost", "456.23");   //shipping cost

		custInfo.setBilling(b);

		/* OR you can pass the individual args.
		   custInfo.setBilling(
		   "Bob",                  //first name
		   "Smith",                //last name
		   "Widget Company Inc.",  //company name
		   "111 Bolts Ave.",       //address
		   "Toronto",              //city
		   "Ontario",              //province
		   "M8T 1T8",              //postal code
		   "Canada",               //country
		   "416-555-5555",         //phone
		   "416-555-5555",         //fax
		   "123.45",               //federal tax
		   "12.34",                //prov tax
		   "15.45",                //luxury tax
		   "456.23"                //shipping cost
		   );
		 */

		Hashtable<String, String> s = new Hashtable<String, String>();

		s.put("first_name", "Bob");
		s.put("last_name", "Smith");
		s.put("company_name", "Widget Company Inc.");
		s.put("address", "111 Bolts Ave.");
		s.put("city", "Toronto");
		s.put("province", "Ontario");
		s.put("postal_code", "M8T 1T8");
		s.put("country", "Canada");
		s.put("phone", "416-555-5555");
		s.put("fax", "416-555-5555");
		s.put("tax1", "123.45");       //federal tax
		s.put("tax2", "12.34");        //prov tax
		s.put("tax3", "15.45");        //luxury tax
		s.put("shipping_cost", "456.23");   //shipping cost

		custInfo.setShipping(s);

		/* OR you can pass the individual args.
		   custInfo.setShipping(
		   "Bob",                  //first name
		   "Smith",                //last name
		   "Widget Company Inc.",  //company name
		   "111 Bolts Ave.",       //address
		   "Toronto",              //city
		   "Ontario",              //province
		   "M8T 1T8",              //postal code
		   "Canada",               //country
		   "416-555-5555",         //phone
		   "416-555-5555",         //fax
		   "123.45",               //federal tax
		   "12.34",                //prov tax
		   "15.45",                //luxury tax
		   "456.23"                //shipping cost
		   );
		 */

		Hashtable<String, String> i1 = new Hashtable<String, String>();

		i1.put("name", "item1's name");
		i1.put("quantity", "5");
		i1.put("product_code", "item1's product code");
		i1.put("extended_amount", "1.01");

		custInfo.setItem(i1);

		/* OR you can pass the individual args.
		   custInfo.setItem(
		   "item1's name",         //name
		   "5",                    //quantity
		   "item1's product code", //product code
		   "1.01"                  //extended amount
		   );
		 */

		Hashtable<String, String> i2 = new Hashtable<String, String>();

		i2.put("name", "item2's name");
		i2.put("quantity", "7");
		i2.put("product_code", "item2's product code");
		i2.put("extended_amount", "5.01");

		custInfo.setItem(i2);

		resPurchaseCC.setCustInfo(custInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resPurchaseCC);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Masked Pan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
