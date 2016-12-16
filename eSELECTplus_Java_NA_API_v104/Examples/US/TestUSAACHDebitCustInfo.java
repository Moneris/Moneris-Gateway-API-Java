package USA;
                                                                            
import java.util.*;
import JavaAPI.*;

public class TestUSAACHDebitCustInfo
{
	public static void main(String[] args)
	{
		String order_id = "dotnetachdessbitcusti33nfotest1";
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String amount = "1.00";

		//ACHInfo Variables
		String sec = "ppd";
		String cust_first_name = "Bob";
		String cust_last_name = "Smith";
		String cust_address1 = "3300 Bloor St W";
		String cust_address2 = "4th floor west tower";
		String cust_city = "Toronto";
		String cust_state = "ON";
		String cust_zip = "M1M1M1";
		String routing_num = "490000018";
		String account_num = "222222";
		String check_num = "11";
		String account_type = "checking";
		String micr = "t071000013t742941347o127";
		String dl_num = "CO-12312312";
		String magstripe = "no";
		String image_front = "";
		String image_back = "";
		String processing_country_code = "US";
		boolean status_check = false;

		ACHInfo achinfo = new ACHInfo(sec, cust_first_name, cust_last_name,
				cust_address1, cust_address2, cust_city, cust_state, cust_zip,
				routing_num, account_num, check_num, account_type, micr);


		achinfo.setImgFront(image_front);
		achinfo.setImgBack(image_back);
		achinfo.setDlNum(dl_num);
		achinfo.setMagstripe(magstripe);

		ACHDebit achdebit = new ACHDebit();
		achdebit.setOrderId(order_id);
		achdebit.setAmount(amount);
		achdebit.setAchInfo(achinfo);

		//************************OPTIONAL VARIABLES***************************

		//Cust_id Variable
		String cust_id = "customer1";
		achdebit.setCustId(cust_id);

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

		achdebit.setCustInfo(custInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(achdebit);
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
			System.out.println("Message = " + receipt.getMessage());
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
