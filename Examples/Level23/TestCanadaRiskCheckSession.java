package Canada;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import JavaAPI.*;

public class TestCanadaRiskCheckSession
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String session_id = "abc123";
		String service_type = "session";
		//String event_type = "LOGIN";
		String processing_country_code = "CA";
		boolean status_check = false;

		SessionQuery sq = new SessionQuery();
		sq.setOrderId(order_id);
		sq.setSessionId(session_id);
		sq.setServiceType(service_type);
		sq.setEventType(service_type);
		//sq.setPolicy("");
		//sq.setDeviceId("4EC40DE5-0770-4fa0-BE53-981C067C598D");
		sq.setAccountLogin("13195417-8CA0-46cd-960D-14C158E4DBB2");
		sq.setPasswordHash("489c830f10f7c601d30599a0deaf66e64d2aa50a");
		sq.setAccountNumber("3E17A905-AC8A-4c8d-A417-3DADA2A55220");
		sq.setAccountName("4590FCC0-DF4A-44d9-A57B-AF9DE98B84DD");
		sq.setAccountEmail("3CAE72EF-6B69-4a25-93FE-2674735E78E8@test.threatmetrix.com");
		
		//sq.setAccountTelephone("5556667777");
		sq.setPan("4242424242424242");
		//sq.setAccountAddressStreet1("3300 Bloor St W");
		//sq.setAccountAddressStreet2("4th Flr West Tower");
		//sq.setAccountAddressCity("Toronto");
		//sq.setAccountAddressState("Ontario");
		//sq.setAccountAddressCountry("Canada");
		//sq.setAccountAddressZip("M8X2X2");
		//sq.setShippingAddressStreet1("3300 Bloor St W");
		//sq.setShippingAddressStreet2("4th Flr West Tower");
		//sq.setShippingAddressCity("Toronto");
		//sq.setShippingAddressState("Ontario");
		//sq.setShippingAddressCountry("Canada");
		//sq.setShippingAddressZip("M8X2X2");
		//sq.setLocalAttrib1("a");
		//sq.setLocalAttrib2("b");
		//sq.setLocalAttrib3("c");
		//sq.setLocalAttrib4("d");
		//sq.setLocalAttrib5("e");
		//sq.setTransactionAmount("1.00");
		//sq.setTransactionCurrency("840");
		//set SessionAccountInfo
		sq.setTransactionCurrency("CAN");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(sq);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			String[] rules;
			Hashtable<String, String> results = new Hashtable<String, String>();
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());

			results = receipt.getRiskResult();

			Iterator<Map.Entry<String, String>> response = results.entrySet().iterator();
			while (response.hasNext())
			{
				Map.Entry<String, String> entry = response.next();
				System.out.println(entry.getKey().toString() + " = " + entry.getValue().toString());
			}


			rules = receipt.getRiskRules();

			for (int i = 0; i < rules.length; i++)
			{
				System.out.println("RuleName = " + rules[i]);
				System.out.println("RuleCode = " + receipt.getRuleCode(rules[i]));
				System.out.println("RuleMessageEn = " + receipt.getRuleMessageEn(rules[i]));
				System.out.println("RuleMessageFr = " + receipt.getRuleMessageFr(rules[i]));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
