package Level23;
import JavaAPI.*;

public class TestVsCompletion
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="ord-210916-15:14:46";
		String comp_amount="5.00";
		String txn_number = "19002-0_11";
		String crypt="7";
		String national_tax = "1.23";
		String merchant_vat_no = "gstno111";
		String local_tax = "2.34";
		String customer_vat_no = "gstno999";
		String cri = "CUST-REF-002";
		String customer_code="ccvsfp";
		String invoice_number="invsfp";
		String local_tax_no="ltaxno";

		VsCompletion vsCompletion = new VsCompletion();
		vsCompletion.setOrderId(order_id);
		vsCompletion.setCompAmount(comp_amount);
		vsCompletion.setTxnNumber(txn_number);
		vsCompletion.setCryptType(crypt);
		vsCompletion.setNationalTax(national_tax);
		vsCompletion.setMerchantVatNo(merchant_vat_no);
		vsCompletion.setLocalTax(local_tax);
		vsCompletion.setCustomerVatNo(customer_vat_no);
		vsCompletion.setCri(cri);
		vsCompletion.setCustomerCode(customer_code);
		vsCompletion.setInvoiceNumber(invoice_number);
		vsCompletion.setLocalTaxNo(local_tax_no);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(vsCompletion);
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
