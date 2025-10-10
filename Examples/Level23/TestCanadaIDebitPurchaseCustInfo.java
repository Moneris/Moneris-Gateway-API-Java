package Canada;

import JavaAPI.*;

public class TestCanadaIDebitPurchaseCustInfo
{
  public static void main(String args[])
  {
        String store_id = "store5";
        String api_token = "yesguy";
        String processing_country_code = "CA";

        java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
        String cust_id = "Rex_Grossman";
        String amount = "1.00";
        String idebit_track2 = "3728024906540591206=0609AAAAAAAAAAAAA";

        Item[] items = new Item[2];

        items[0] = new Item(
            "item1's name",         //name
            "5",                    //quantity
            "item1's product code", //product code
            "1.01");                //extended amount

        items[1] = new Item(
            "item2's name",         //name
            "5",                    //quantity
            "item2's product code", //product code
            "2.02");                //extended amount

        BillingLocation billing = new BillingLocation(
                                        "Smith",                //last name
                                        "Bob",                  //first name
                                        "Widget Company Inc.",  //company name
                                        "111 Bolts Ave.",       //address
                                        "Toronto",              //city
                                        "Ontario",              //province
                                        "M8T 1T8",              //postal code
                                        "Canada",               //country
                                        "416-555-5555",         //phone
                                        "416-555-5555",         //fax
                                        "123.45",               //federal tax
                                        "12.34",                //prov tax
                                        "15.45",                //luxury tax
                                        "456.23");              //shipping cost

        ShippingLocation shipping = new ShippingLocation(
                                        "Smith",                //last name
                                        "Bob",                  //first name
                                        "Widget Company Inc.",  //company name
                                        "111 Bolts Ave.",       //address
                                        "Toronto",              //city
                                        "Ontario",              //province
                                        "M8T 1T8",              //postal code
                                        "Canada",               //country
                                        "416-555-5555",         //phone
                                        "416-555-5555",         //fax
                                        "123.45",               //federal tax
                                        "12.34",                //prov tax
                                        "15.45",                //luxury tax
                                        "456.23");              //shipping cost

        String email = new String("nick@widget.com");

        String instructions = new String("Make it fast!");

        CustomerInfo cust_info =
                new CustomerInfo(billing,shipping,email,instructions,items);

        IDebitPurchase IOP_Txn = new IDebitPurchase();
		IOP_Txn.setOrderId(order_id);
		IOP_Txn.setCustId(cust_id);
		IOP_Txn.setAmount(amount);
		IOP_Txn.setIdebitTrack2(idebit_track2);
		IOP_Txn.setCustInfo(cust_info);
        
    	HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(IOP_Txn);
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
            System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
  }
}



