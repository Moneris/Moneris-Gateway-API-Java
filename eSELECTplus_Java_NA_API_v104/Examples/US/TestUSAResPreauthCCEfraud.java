package USA;

import JavaAPI.*;

public class TestUSAResPreauthCCEfraud
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

		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		avsCheck.setAvsEmail("test@host.com");
		avsCheck.setAvsHostname("hostname");
		avsCheck.setAvsBrowser("Mozilla");
		avsCheck.setAvsShiptoCountry("CAN");
		avsCheck.setAvsShipMethod("G");
		avsCheck.setAvsMerchProdSku("123456");
		avsCheck.setAvsCustIp("192.168.0.1");
		avsCheck.setAvsCustPhone("5556667777");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");

		ResPreauthCC resPreauthCC = new ResPreauthCC();
		resPreauthCC.setData(data_key);
		resPreauthCC.setOrderId(order_id);
		resPreauthCC.setCustId(cust_id);
		resPreauthCC.setAmount(amount);
		resPreauthCC.setCryptType(crypt_type);
		resPreauthCC.setAvsInfo(avsCheck);
		resPreauthCC.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resPreauthCC);
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
			System.out.println("AVSResponse = " + receipt.getAvsResultCode());
			System.out.println("CVDResponse = " + receipt.getCvdResultCode());
			System.out.println("ITDResponse = " + receipt.getITDResponse());
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
