package Level23;
import JavaAPI.*;
	
public class TestVsForcePost
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
        String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		java.util.Date createDate = new java.util.Date(); 
		String order_id="Test"+createDate.getTime();
		String cust_id="CUST13343";
		String amount="5.00";
		String pan="4242424254545454";
		String expiry_date="2012"; //YYMM
		String auth_code="123456";
		String crypt="7";
		String national_tax = "1.23";
		String merchant_vat_no = "gstno111";
		String local_tax = "2.34";
		String customer_vat_no = "gstno999";
		String cri = "CUST-REF-002";
		String customer_code="ccvsfp";
		String invoice_number="invsfp";
		String local_tax_no="ltaxno";

		VsForcePost vsForcePost = new VsForcePost();
		vsForcePost.setOrderId(order_id);
		vsForcePost.setCustId(cust_id);
		vsForcePost.setAmount(amount);
		vsForcePost.setPan(pan);
		vsForcePost.setExpDate(expiry_date);
		vsForcePost.setAuthCode(auth_code);
		vsForcePost.setCryptType(crypt);
		vsForcePost.setNationalTax(national_tax);
		vsForcePost.setMerchantVatNo(merchant_vat_no);
		vsForcePost.setLocalTax(local_tax);
		vsForcePost.setCustomerVatNo(customer_vat_no);
		vsForcePost.setCri(cri);
		vsForcePost.setCustomerCode(customer_code);
		vsForcePost.setInvoiceNumber(invoice_number);
		vsForcePost.setLocalTaxNo(local_tax_no);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(vsForcePost);
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
