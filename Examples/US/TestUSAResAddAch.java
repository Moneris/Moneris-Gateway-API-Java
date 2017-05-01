package USA;

import JavaAPI.*;

public class TestUSAResAddAch
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String phone = "0000000000";
		String email = "bob.smith@moneris.com";
		String note = "my note";
		String cust_id = "customer1";

		//ACHInfo Variables
		String sec = "ppd";
		String cust_first_name = "Christian";
		String cust_last_name = "M";
		String cust_address1 = "3300 Bloor St W";
		String cust_address2 = "4th floor west tower";
		String cust_city = "Toronto";
		String cust_state = "ON";
		String cust_zip = "M1M1M1";
		String routing_num = "490000018";
		String account_num = "222222";
		String check_num = "12";
		String account_type = "checking";
		String processing_country_code = "US";
		boolean status_check = false;


		ACHInfo achinfo = new ACHInfo(sec, cust_first_name, cust_last_name,
				cust_address1, cust_address2, cust_city, cust_state, cust_zip,
				routing_num, account_num, check_num, account_type);


		//alternatively, each field of ACHInfo can be set individually
		/*ACHInfo achinfo = new ACHInfo();

		//************************MANDATORY ACH VARIABLES***************************
		achinfo.setSec(sec);
		achinfo.setRoutingNum(routing_num);
		achinfo.setAccountNum(account_num);
		achinfo.setAccountType(account_type);

		//************************OPTIONAL ACH VARIABLES***************************
		achinfo.setCustFirstName(cust_first_name);
		achinfo.setCustLastName(cust_last_name);
		achinfo.setCustAddress1(cust_address1);
		achinfo.setCustAddress2(cust_address2);
		achinfo.setCustCity(cust_city);
		achinfo.setCustState(cust_state);
		achinfo.setCustZip(cust_zip);
		achinfo.setCheckNum(check_num);
		 */
		ResAddAch ressaddach = new ResAddAch();
		ressaddach.setAchInfo(achinfo);
		ressaddach.setCustId(cust_id);
		ressaddach.setPhone(phone);
		ressaddach.setEmail(email);
		ressaddach.setNote(note);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(ressaddach);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Sec = " + receipt.getResSec());
			System.out.println("Cust First Name = " + receipt.getResCustFirstName());
			System.out.println("Cust Last Name = " + receipt.getResCustLastName());
			System.out.println("Cust Address 1 = " + receipt.getResCustAddress1());
			System.out.println("Cust Address 2 = " + receipt.getResCustAddress2());
			System.out.println("Cust City = " + receipt.getResCustCity());
			System.out.println("Cust State = " + receipt.getResCustState());
			System.out.println("Cust Zip = " + receipt.getResCustZip());
			System.out.println("Routing Num = " + receipt.getResRoutingNum());
			System.out.println("Masked Account Num = " + receipt.getResMaskedAccountNum());
			System.out.println("Check Num = " + receipt.getResCheckNum());
			System.out.println("Account Type = " + receipt.getResAccountType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
