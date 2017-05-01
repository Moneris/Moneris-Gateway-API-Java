package USA;

import JavaAPI.*;

public class TestUSAEncPreauthEfraud
{
	public static void main(String[] args)
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String amount = "5.00";
		String crypt = "7";
		String processing_country_code = "US";
		boolean status_check = false;
		String device_type = "idtech";
		String enc_track2 = "02840085000000000416EA7637C390587C593F32E83AFC176194B43F34B8F898BCF7715270F280B3804F25340C85C7C3D36138780D880431393035FFFF3141594047A0009B04AE03";
		
		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");

		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");

		EncPreauth encpreauth = new EncPreauth();
		encpreauth.setOrderId(order_id);
		encpreauth.setAmount(amount);
		encpreauth.setEncTrack2(enc_track2);
		encpreauth.setDeviceType(device_type);
		encpreauth.setCryptType(crypt);
		encpreauth.setAvsInfo(avsCheck);
		encpreauth.setCvdInfo(cvdCheck);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(encpreauth);
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
			System.out.println("Avs Response = " + receipt.getAvsResultCode());
			System.out.println("Cvd Response = " + receipt.getCvdResultCode());
			System.out.println("CardLevelResult = " + receipt.getCardLevelResult());
			System.out.println("MaskedPan = " + receipt.getMaskedPan());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
