package USA;

import JavaAPI.*;

public class TestUSAResPreAuthCC
{
	public static void main(String args[])
	{

		/********************** Request Variables ****************************/

		String store_id = "monusqa002";
		String api_token = "qatoken";

		/********************** Transaction Variables ************************/
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String data_key = "1P5C4C6bNPGg5xGb4ZFfaOTt8";
		String cust_id = "Hilton_1";
		String amount = "1.00";
		String crypt = "7";
		//String dynamic_descriptor = "123456";
		String processing_country_code = "US";

		/************************ Request Object ******************************/

		ResPreauthCC res_preauth_cc = new ResPreauthCC();
		res_preauth_cc.setOrderId(order_id);
		res_preauth_cc.setAmount(amount);
		res_preauth_cc.setData(data_key);
		res_preauth_cc.setCryptType(crypt);		
		res_preauth_cc.setCustId(cust_id);
		//usResPreauthCC.setDynamicDescriptor(dynamic_descriptor);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(res_preauth_cc);
		mpgReq.send();

		/************************ Receipt Object ******************************/

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
			System.out.println("PaymentType = " + receipt.getPaymentType() + "\n");

			//Contents of ResolveData
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("MaskedPan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpDate());
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
