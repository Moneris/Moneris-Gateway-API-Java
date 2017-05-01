package Canada;
                                                                           
import java.util.*;

import JavaAPI.*;

public class TestCanadaRiskCheckAttribute
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String service_type = "session";
		String processing_country_code = "CA";
		boolean status_check = false;

		AttributeQuery aq = new AttributeQuery();
		aq.setOrderId(order_id);
		aq.setServiceType(service_type);
		aq.setDeviceId("");
		aq.setAccountLogin("13195417-8CA0-46cd-960D-14C158E4DBB2");
		aq.setPasswordHash("489c830f10f7c601d30599a0deaf66e64d2aa50a");
		aq.setAccountNumber("3E17A905-AC8A-4c8d-A417-3DADA2A55220");
		aq.setAccountName("4590FCC0-DF4A-44d9-A57B-AF9DE98B84DD");
		aq.setAccountEmail("3CAE72EF-6B69-4a25-93FE-2674735E78E8@test.threatmetrix.com");
		//aq.setCCNumberHash("4242424242424242");
		//aq.setIPAddress("192.168.0.1");
		//aq.setIPForwarded("192.168.1.0");
		aq.setAccountAddressStreet1("3300 Bloor St W");
		aq.setAccountAddressStreet2("4th Flr West Tower");
		aq.setAccountAddressCity("Toronto");
		aq.setAccountAddressState("Ontario");
		aq.setAccountAddressCountry("Canada");
		aq.setAccountAddressZip("M8X2X2");
		aq.setShippingAddressStreet1("3300 Bloor St W");
		aq.setShippingAddressStreet2("4th Flr West Tower");
		aq.setShippingAddressCity("Toronto");
		aq.setShippingAddressState("Ontario");
		aq.setShippingAddressCountry("Canada");
		aq.setShippingAddressZip("M8X2X2");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(aq);
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
