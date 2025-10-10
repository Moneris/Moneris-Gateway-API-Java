package Canada;

import JavaAPI.GooglePayPreauth;
import JavaAPI.GooglePayTokenTempAdd;
import JavaAPI.HttpsPostRequest;
import JavaAPI.Receipt;

public class TestCanadaGooglePayTokenTempAdd
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String amount = "1.00";
		String cust_id = "nqa-cust_id";
		String network = "MASTERCARD";
		String signature = "MEYCIQCdjfGZ1k/8h+eH9Ue5UxJsgDEFimp6YIrWhtpte+W3kAIhAOKikmz1B/C4WB5g3mXy139euOHHnSQ7bQWl2chgwole";
		String protocol_version = "ECv1";
		String signed_message =  "{\"encryptedMessage\":\"nAEP5f0pzvU+cJHwxCwrCVPRrl96NugevgfrdidPOB5B+WG7+yrsYoUVA7HopRD5y5GCldQwrKnP2h2w/Qc2HBfn+G/g2IXqPBzMjguhpGItr6lV0tRLaYimxrgrbh/Xn8DxfW++pTHHoo+0xJiON6o3JC4vM6wmAuhjjwEOgiDeKpgxJKEl8NULR2RK1OtvongkR80K8Et7CT+W0lXoMCoYrH3tJDKMtovyFNfHPMAXLeV4NfVV+ZwhwD3F+tGm7bQkPFMy2xUQxzdj7/H03vmyxwsblSKXhVG3hWKPmnY/+Gkb2K0pAicOHaB/SZuwaxHQll30jaNafUIm96R9T2Yc3p5gmnGiR03R9H5R8JqLL9Wb7LncvfIwuQppgbAKa6HdbuSjbehNOtW8S34VqxvpeSfQFUNYgkQ+fVEU/VaClE17PyF8AMZKN10ZIZ1jj7jntqoD\",\"ephemeralPublicKey\":\"BD5snQM3HF2gdCyERaF9XBPDGOXL8fNyTM9QY/xNTi9VkWTtq5sg7dYgPXdLmQuwIhBN9OyLULAMsNcmsv2TT7k\\u003d\",\"tag\":\"hyG7Ty/qQAZe1t2INIMtDQPMAfoDVhUinW451hJrcP4\\u003d\"}";
		String dynamic_descriptor = "nqa-dd";
		String processing_country_code = "CA";
		boolean status_check = false;

		GooglePayTokenTempAdd googlePayTokenTempAdd = new GooglePayTokenTempAdd();
		googlePayTokenTempAdd.setPaymentToken(signature, protocol_version, signed_message);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(googlePayTokenTempAdd);
		mpgReq.setStatusCheck(status_check);

		//Optional - Proxy
		mpgReq.setProxy(false); //true to use proxy
		mpgReq.setProxyHost("proxyURL");
		mpgReq.setProxyPort("proxyPort"); 
		mpgReq.setProxyUser("proxyUser"); //optional - domainName\User
		mpgReq.setProxyPassword("proxyPassword"); //optional
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Payment Type = " + receipt.getPaymentType());
			System.out.println("GooglepayPaymentMethod = " + receipt.getGooglepayPaymentMethod());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
