package Level23;
import JavaAPI.*;

public class TestMcIndependentRefund
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
            String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		java.util.Date createDate = new java.util.Date(); 
		String order_id="Test"+createDate.getTime();
		String cust_id = "CUST13343";
		String amount = "5.00";
		String pan = "5454545442424242";
		String expiry_date = "1912"; //YYMM
		String crypt = "7";
		String merchant_ref_no = "319038";

		McIndependentRefund mcindrefund = new McIndependentRefund();
		mcindrefund.setOrderId(order_id);
		mcindrefund.setCustId(cust_id);
		mcindrefund.setAmount(amount);
		mcindrefund.setPan(pan);
		mcindrefund.setExpDate(expiry_date);
		mcindrefund.setCryptType(crypt);
		mcindrefund.setMerchantRefNo(merchant_ref_no);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(mcindrefund);
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
