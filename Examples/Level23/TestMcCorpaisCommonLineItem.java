package Level23;
import JavaAPI.*;

public class TestMcCorpaisCommonLineItem
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
        String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="Test1485206444761";
		String txn_number="39777-1_11";

		String customer_code1_c ="CustomerCode123";
		String card_acceptor_tax_id_c ="UrTaxId";//Merchant tax id which is mandatory
		String corporation_vat_number_c ="cvn123";
		String freight_amount_c ="1.23";
		String duty_amount_c ="2.34";
		String ship_to_pos_code_c ="M1R 1W5";
		String order_date_c ="141211";
		String customer_vat_number_c ="customervn231";
		String unique_invoice_number_c ="uin567";
		String authorized_contact_name_c ="John Walker";

		//Tax Details
		String[] tax_amount_c = { "1.19", "1.29"};
		String[] tax_rate_c = { "6.0", "7.0"};
		String[] tax_type_c = { "GST", "PST"};
		String[] tax_id_c = { "gst1298", "pst1298"};
		String[] tax_included_in_sales_c = { "Y", "N"};

		//Item Details
		String[] customer_code1_l = {"customer code", "customer code2"};
		String[] line_item_date_l = {"150114", "150114"};
		String[] ship_date_l = {"150120", "150122"};
		String[] order_date1_l = {"150114", "150114"};
		String[] medical_services_ship_to_health_industry_number_l = {"", ""};
		String[] contract_number_l = {"", ""};
		String[] medical_services_adjustment_l = {"", ""};
		String[] medical_services_product_number_qualifier_l = {"", ""};
		String[] product_code1_l = {"pc11", "pc12"};
		String[] item_description_l = {"Good item", "Better item"};
		String[] item_quantity_l = {"4", "5"};
		String[] unit_cost_l ={"1.25", "10.00"};
		String[] item_unit_measure_l = {"EA", "EA"};
		String[] ext_item_amount_l ={"5.00", "50.00"};
		String[] discount_amount_l ={"1.00", "50.00"};
		String[] commodity_code_l ={"cCode11", "cCode12"};
		String[] type_of_supply_l = {"", ""};
		String[] vat_ref_num_l = {"", ""};

		//Tax Details for Items
		String[] tax_amount_l = {"0.52", "1.48"};
		String[] tax_rate_l = {"13.0", "13.0"};
		String[] tax_type_l = {"HST", "HST"};
		String[] tax_id_l = {"hst1298", "hst1298"};
		String[] tax_included_in_sales_l = {"Y", "Y"};

		//Create and set Tax for McCorpac
		McTax tax_c = new McTax();
		tax_c.setTax(tax_amount_c[0], tax_rate_c[0], tax_type_c[0], tax_id_c[0], tax_included_in_sales_c[0]);
		tax_c.setTax(tax_amount_c[1], tax_rate_c[1], tax_type_c[1], tax_id_c[1], tax_included_in_sales_c[1]);

		//Create and set McCorpac for common data - only set values that you know
		McCorpac mcCorpac = new McCorpac();
		mcCorpac.setCustomerCode1(customer_code1_c);
		mcCorpac.setCardAcceptorTaxTd(card_acceptor_tax_id_c);
		mcCorpac.setCorporationVatNumber(corporation_vat_number_c);
		mcCorpac.setFreightAmount1(freight_amount_c);
		mcCorpac.setDutyAmount1(duty_amount_c);
		mcCorpac.setShipToPosCode(ship_to_pos_code_c);
		mcCorpac.setOrderDate(order_date_c);
		mcCorpac.setCustomerVatNumber(customer_vat_number_c);
		mcCorpac.setUniqueInvoiceNumber(unique_invoice_number_c);
		mcCorpac.setAuthorizedContactName(authorized_contact_name_c);
		mcCorpac.setTax(tax_c);

		//Create and set Tax for McCorpal
		McTax[] tax_l = new McTax[2];
		tax_l[0] = new McTax();
		tax_l[0].setTax(tax_amount_l[0], tax_rate_l[0], tax_type_l[0], tax_id_l[0], tax_included_in_sales_l[0]);
		tax_l[1] = new McTax();
		tax_l[1].setTax(tax_amount_l[1], tax_rate_l[1], tax_type_l[1], tax_id_l[1], tax_included_in_sales_l[1]);

		//Create and set McCorpal for each item
		McCorpal mcCorpal = new McCorpal();
		mcCorpal.setMcCorpal(customer_code1_l[0], line_item_date_l[0], ship_date_l[0], order_date1_l[0], medical_services_ship_to_health_industry_number_l[0], contract_number_l[0],
					medical_services_adjustment_l[0], medical_services_product_number_qualifier_l[0], product_code1_l[0], item_description_l[0], item_quantity_l[0],
					unit_cost_l[0], item_unit_measure_l[0], ext_item_amount_l[0], discount_amount_l[0], commodity_code_l[0], type_of_supply_l[0], vat_ref_num_l[0], tax_l[0]);
		mcCorpal.setMcCorpal(customer_code1_l[1], line_item_date_l[1], ship_date_l[1], order_date1_l[1], medical_services_ship_to_health_industry_number_l[1], contract_number_l[1],
					medical_services_adjustment_l[1], medical_services_product_number_qualifier_l[1], product_code1_l[1], item_description_l[1], item_quantity_l[1],
					unit_cost_l[1], item_unit_measure_l[1], ext_item_amount_l[1], discount_amount_l[1], commodity_code_l[1], type_of_supply_l[1], vat_ref_num_l[1], tax_l[1]);

		McCorpais mcCorpais = new McCorpais();
		mcCorpais.setOrderId(order_id);
		mcCorpais.setTxnNumber(txn_number);
		mcCorpais.setMcCorpac(mcCorpac);
		mcCorpais.setMcCorpal(mcCorpal);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(mcCorpais);
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
