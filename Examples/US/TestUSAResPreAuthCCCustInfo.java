package USA;

import JavaAPI.*;

public class TestUSAResPreAuthCCCustInfo
{
  public static void main(String args[])
  {

/********************** Request Variables ****************************/

        String store_id = "monusqa002";
        String api_token = "qatoken";
        String processing_country_code = "US";
        
/********************** Transaction Variables ************************/

        String data_key = "1P5C4C6bNPGg5xGb4ZFfaOTt8";
        java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
        String cust_id = "Hilton_1";
        String amount = "1.00";
        String crypt = "7";
		
/********************* Billing/Shipping Variables ********************/

        String last_name = "Harris";
        String first_name = "Tommie";
        String company_name = "Da Bears";
        String address = "454 Michigan Ave";
        String city = "Chicago";
        String province = "Illinois";
        String zip_code = "99879";
        String country = "USA";
        String phone_number = "764-908-9989";
        String fax = "764-908-9990";
        String tax1 = "1.00";
        String tax2 = "1.00";
        String tax3 = "1.00";
        String shipping_cost = "2.00";

/************************* Line Item Variables *************************/

		String[] name = new String[]{"Mini Bears Helmet", "Mini Bills Helmet"};
		String[] quantity = new String[]{"1", "2"};
		String[] product_code = new String[] {"BEOOOWS9", "BUFD099D"};
		String[] extended_amount = new String[] {"4.00", "6.00"};

/************************ Miscellaneous Variables **********************/

		String email = "T.Harris@ChicagoBears.com";
		String instructions = "Must arrive before opening day at Lambeau";

/*********************** Transaction Object *******************************/

        ResPreauthCC res_preauth_cc = new ResPreauthCC();
        res_preauth_cc.setData(data_key);
		res_preauth_cc.setOrderId(order_id);
		res_preauth_cc.setCustId(cust_id);
		res_preauth_cc.setAmount(amount);
		res_preauth_cc.setCryptType(crypt);		
		
		

/*********************** Billing/Shipping Object **************************/

		BillingLocation billingAddress =
				new BillingLocation  (last_name, first_name, company_name, address,
									   city, province, zip_code, country, phone_number,
									   fax, tax1, tax2, tax3, shipping_cost);

		ShippingLocation shippingAddress =
				new ShippingLocation  (last_name, first_name, company_name, address,
									   city, province, zip_code, country, phone_number,
									   fax, tax1, tax2, tax3, shipping_cost);

/************************ Line Item Object ************************************/

		Item[] lineItems = new Item[]{new Item(name[0], quantity[0], product_code[0], extended_amount[0]),
									  new Item(name[1], quantity[1], product_code[1], extended_amount[1])};


/************************* Customer Information Object *************************/

		CustomerInfo custData =
				new CustomerInfo (billingAddress, shippingAddress, email, instructions, lineItems);


		res_preauth_cc.setCustInfo (custData);

/************************ Request Object ******************************/

	    
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
