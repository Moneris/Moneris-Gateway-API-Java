package Level23;
import JavaAPI.*;
	
public class TestAxPurchaseCorrection
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="Test1485206180427";
		String txn_number = "660117311902017023161620759-0_11";
		String crypt="7";

		AxPurchaseCorrection axPurchaseCorrection = new AxPurchaseCorrection();
		axPurchaseCorrection.setOrderId(order_id);
		axPurchaseCorrection.setTxnNumber(txn_number);
		axPurchaseCorrection.setCryptType(crypt);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
            mpgReq.setProcCountryCode(processing_country_code);
            mpgReq.setTestMode(true); //false or comment out this line for production transactions
            mpgReq.setStoreId(store_id);
            mpgReq.setApiToken(api_token);
            mpgReq.setTransaction(axPurchaseCorrection);
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
                System.out.println("CavvResultCode = " + receipt.getCavvResultCode());
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

	}
}
