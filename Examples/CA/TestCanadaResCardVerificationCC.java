package Canada;
import java.io.*;

import JavaAPI.*;

public class TestCanadaResCardVerificationCC
{
  public static void main(String args[]) throws IOException
  {

	  	String store_id = "monca00597";
		String api_token = "O27AbCbxQorPggMQe6hU";
		String data_key = "m5FubAMXr8IK4lC0eTv0c9zA0";
        java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
        String crypt_type = "7";
        String processing_country_code = "CA";
        boolean status_check = false;
        
        /********************** Efraud Variables ************************/
		AvsInfo avs = new AvsInfo ();
		avs.setAvsStreetName("test ave");
		avs.setAvsStreetNumber("123");
		avs.setAvsZipcode("123456");

        CvdInfo cvd = new CvdInfo ("1", "099");

        /*********************** Transaction Object *******************************/

        ResCardVerificationCC resCardVerificationCC = new ResCardVerificationCC();
        resCardVerificationCC.setDataKey(data_key);
        resCardVerificationCC.setOrderId(order_id);
        resCardVerificationCC.setCryptType(crypt_type);
        resCardVerificationCC.setAvsInfo(avs);
        resCardVerificationCC.setCvdInfo(cvd);

		//NT Response Option
		boolean get_nt_response = true;
		resCardVerificationCC.setGetNtResponse(get_nt_response);

        //resCardVerificationCC.setExpdate("1412"); //For Temp Tokens only
        
		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("Z");
		cof.setPaymentInformation("2");
		//cof.setIssuerId("139X3130ASCXAS9");
		
		resCardVerificationCC.setCofInfo(cof);
        
        HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resCardVerificationCC);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

/************************ Receipt Object ******************************/

	    try
        {
	    	Receipt resreceipt = mpgReq.getReceipt();

            System.out.println("DataKey = " + resreceipt.getDataKey());
            System.out.println("ReceiptId = " + resreceipt.getReceiptId());
            System.out.println("ReferenceNum = " + resreceipt.getReferenceNum());
    		System.out.println("ResponseCode = " + resreceipt.getResponseCode());
    		System.out.println("AuthCode = " + resreceipt.getAuthCode());
    		System.out.println("ISO = " + resreceipt.getISO());
    		System.out.println("Message = " + resreceipt.getMessage());
    		System.out.println("TransDate = " + resreceipt.getTransDate());
    		System.out.println("TransTime = " + resreceipt.getTransTime());
    		System.out.println("TransType = " + resreceipt.getTransType());
    		System.out.println("Complete = " + resreceipt.getComplete());
    		System.out.println("TransAmount = " + resreceipt.getTransAmount());
    		System.out.println("CardType = " + resreceipt.getCardType());
    		System.out.println("TxnNumber = " + resreceipt.getTxnNumber());
    		System.out.println("TimedOut = " + resreceipt.getTimedOut());
    		System.out.println("ResSuccess = " + resreceipt.getResSuccess());
    		System.out.println("PaymentType = " + resreceipt.getPaymentType() + "\n");
    		System.out.println("IssuerId = " + resreceipt.getIssuerId());
			System.out.println("SourcePanLast4 = " + resreceipt.getSourcePanLast4());
			System.out.println();
			
    		//Contents of ResolveData
    		System.out.println("Cust ID = " + resreceipt.getResCustId());
			System.out.println("Phone = " + resreceipt.getResPhone());
			System.out.println("Email = " + resreceipt.getResEmail());
			System.out.println("Note = " + resreceipt.getResNote());
			System.out.println("Masked Pan = " + resreceipt.getResMaskedPan());
			System.out.println("Exp Date = " + resreceipt.getResExpdate());
			System.out.println("Crypt Type = " + resreceipt.getResCryptType());
			System.out.println("Avs Street Number = " + resreceipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + resreceipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + resreceipt.getResAvsZipcode());

			if(get_nt_response) {
				System.out.println("NTResponseCode = " + resreceipt.getNTResponseCode());
				System.out.println("NTMessage = " + resreceipt.getNTMessage());
				System.out.println("NTUsed = " + resreceipt.getNTUsed());
				System.out.println("NTMaskedToken = " + receipt.getNTMaskedToken());
			}
        }
	    catch (Exception e)
        {
            e.printStackTrace();
        }
  	}
} // end TestResCardVerificationCC



