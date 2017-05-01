package USA;
import JavaAPI.*;

public class TestUSAConvFeeACHDebit
{
	public static void main(String args[])
	{
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String processing_country_code = "US";

		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String cust_id = "moneristest";
		String amount = "10.00";

		String sec = "web";
		String cust_first_name = "Moneris";
		String cust_last_name = "Solutions";
		String cust_address1 = "3300 Bloor St W";
		String cust_address2 = "4th floor west tower";
		String cust_city = "Toronto";
		String cust_state = "ON";
		String cust_zip = "M1M1M1";
		String routing_num = "071000013";
		String account_num = "742941347";
		String check_num = "9995";
		String account_type = "checking";
		String micr = "";

		ACHInfo achinfo = new ACHInfo (sec,cust_first_name,cust_last_name,cust_address1,cust_address2,cust_city,cust_state,
				cust_zip,routing_num,account_num,check_num,account_type,micr);

		ACHDebit ach_debit = new ACHDebit (order_id, cust_id, amount, achinfo);

		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("5.00");
		ach_debit.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(ach_debit);
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
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("CfSuccess = " + receipt.getCfSuccess());
			System.out.println("CfStatus = " + receipt.getCfStatus());
			System.out.println("FeeAmount = " + receipt.getFeeAmount());
			System.out.println("FeeRate = " + receipt.getFeeRate());
			System.out.println("FeeType = " + receipt.getFeeType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} 
