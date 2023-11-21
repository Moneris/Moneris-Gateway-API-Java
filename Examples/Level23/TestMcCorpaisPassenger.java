package Level23;
import JavaAPI.*;
	
public class TestMcCorpaisPassenger
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
            String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="Test1485207824676";
		String txn_number="660117311902017023164345027-0_11";

		//Common Data
		String customer_code1_c ="CustomerCode123";
		String additional_card_acceptor_data_c ="acad1";
		String austin_tetra_number_c ="atn1";
		String naics_code_c ="nc1";
		String card_acceptor_type_c ="0000nnnn";
		String card_acceptor_tax_id_c ="Moneristaxid1";
		String corporation_vat_number_c ="cvn123";
		String card_acceptor_reference_number_c ="carn1";
		String freight_amount1_c ="1.23";
		String duty_amount1_c ="2.34";
		String destination_province_code_c ="ONT";
		String destination_country_code_c ="CAN";
		String ship_from_pos_code_c ="M8X 2X2";
		String ship_to_pos_code_c ="_M1R 1R5";
		String order_date_c ="141211";
		String card_acceptor_vat_number_c ="cavn1";
		String customer_vat_number_c ="customervn231";
		String unique_invoice_number_c ="uin567";
		String commodity_code_c ="paCCC1";
		String authorized_contact_name_c ="John Walker";
		String authorized_contact_phone_c ="416-734-1000";

		//Common Tax Details
		String[] tax_amount_c = {"1.19", "1.29"};
		String[] tax_rate_c = {"6.0", "7.0"};
		String[] tax_type_c = {"GST", "PST"};
		String[] tax_id_c = {"gst1298", "pst1298"};
		String[] tax_included_in_sales_c = {"Y", "N"};

		//General Passenger Ticket Information
		String passenger_name1_i ="MCC Tester";
		String ticket_number1_i ="1234567890001";
		String travel_agency_name_i ="Moneris Travel";
		String travel_agency_code_i ="MC322";
		String issuing_carrier_i ="2R";
		String customer_code1_i ="passengerabc";
		String issue_date_i ="141210";
		String total_fare_i ="129.45";
		String travel_authorization_code_i ="sde-erdsz-452112";
		String total_fee_i ="10.34";
		String total_taxes_i ="11.56";
		String restricted_ticket_indicator_i ="1";
		String exchange_ticket_amount_i ="13.98";
		String exchange_fee_amount_i ="1.78";
		String iata_client_code_i ="icc2";

		//Tax Details for passenger
		String[] tax_amount_i = {"3.28"};
		String[] tax_rate_i = {"13.00"};
		String[] tax_type_i = {"HST"};
		String[] tax_id_i = {"hst1298"};
		String[] tax_included_in_sales_i = {"Y"};

		//Passenger Air Travel Details
		String[] travel_date_s = {"150101", "150102"};
		String[] carrier_code1_s = {"3R", "4R"};
		String[] service_class_s = {"E", "B"};
		String[] orig_city_airport_code_s = {"Toron", "Montr"};
		String[] dest_city_airport_code_s = {"Montr", "Halif"};
		String[] stop_over_code_s = {"", "X"};
		String[] flight_number_s = {"56786", "54386"};
		String[] departure_time_s = {"1920", "1120"};
		String[] arrival_time_s = {"0620", "1620"};
		String[] conjunction_ticket_number1_s = {"123456789054367", ""};
		String[] exchange_ticket_number1_s = {"123456789067892", ""};
		String[] coupon_number1_s = {"1", "2"};
		String[] fare_basis_code1_s = {"FClass", "SClass"};
		String[] fare_s = {"1.69", ""};
		String[] fee_s = {"1.48", ""};
		String[] taxes_s = {"3.91", ""};
		String[] endorsement_restrictions_s = {"er6", ""};

		//Tax Details for Air Travel
		String[] tax_amount_s = {"4.67", "7.43"};
		String[] tax_rate_s = {"5.0", "9.975"};
		String[] tax_type_s = {"GST", "QST"};
		String[] tax_id_s = {"gst1298", "qst1298"};
		String[] tax_included_in_sales_s = {"Y", "Y"};

		//Create and set Tax for McCorpac
		McTax mcTax_c = new McTax();
		mcTax_c.setTax(tax_amount_c[0], tax_rate_c[0], tax_type_c[0], tax_id_c[0], tax_included_in_sales_c[0]);
		mcTax_c.setTax(tax_amount_c[1], tax_rate_c[1], tax_type_c[1], tax_id_c[1], tax_included_in_sales_c[1]);

		//Create and set McCorpac for common data - only set values that you know
		McCorpac mcCorpac = new McCorpac();
		mcCorpac.setCustomerCode1(customer_code1_c);
		mcCorpac.setAdditionalCardAcceptorData(additional_card_acceptor_data_c);
		mcCorpac.setAustinTetraNumber(austin_tetra_number_c);
		mcCorpac.setNaicsCode(naics_code_c);
		mcCorpac.setCardAcceptorType(card_acceptor_type_c);
		mcCorpac.setCardAcceptorTaxTd(card_acceptor_tax_id_c);
		mcCorpac.setCorporationVatNumber(corporation_vat_number_c);
		mcCorpac.setCardAcceptorReferenceNumber(card_acceptor_reference_number_c);
		mcCorpac.setFreightAmount1(freight_amount1_c);
		mcCorpac.setDutyAmount1(duty_amount1_c);
		mcCorpac.setDestinationProvinceCode(destination_province_code_c);
		mcCorpac.setDestinationCountryCode(destination_country_code_c);
		mcCorpac.setShipFromPosCode(ship_from_pos_code_c);
		mcCorpac.setShipToPosCode(ship_to_pos_code_c);
		mcCorpac.setOrderDate(order_date_c);
		mcCorpac.setCardAcceptorVatNumber(card_acceptor_vat_number_c);
		mcCorpac.setCustomerVatNumber(customer_vat_number_c);
		mcCorpac.setUniqueInvoiceNumber(unique_invoice_number_c);
		mcCorpac.setCommodityCode(commodity_code_c);
		mcCorpac.setAuthorizedContactName(authorized_contact_name_c);
		mcCorpac.setAuthorizedContactPhone(authorized_contact_phone_c);
		mcCorpac.setTax(mcTax_c);

		//Create and set Tax for McCorpai
		McTax mcTax_i = new McTax();
		mcTax_i.setTax(tax_amount_i[0], tax_rate_i[0], tax_type_i[0], tax_id_i[0], tax_included_in_sales_i[0]);

		//Create and set McCorpai
		McCorpai mcCorpai = new McCorpai();
		mcCorpai.setPassengerName1(passenger_name1_i);
		mcCorpai.setTicketNumber1(ticket_number1_i);
		mcCorpai.setTravelAgencyName(travel_agency_name_i);
		mcCorpai.setTravelAgencyCode(travel_agency_code_i);
		mcCorpai.setIssuingCarrier(issuing_carrier_i);
		mcCorpai.setCustomerCode1(customer_code1_i);
		mcCorpai.setIssueDate(issue_date_i);
		mcCorpai.setTotalFare(total_fare_i);
		mcCorpai.setTravelAuthorizationCode(travel_authorization_code_i);
		mcCorpai.setTotalFee(total_fee_i);
		mcCorpai.setTotalTaxes(total_taxes_i);
		mcCorpai.setRestrictedTicketIndicator(restricted_ticket_indicator_i);
		mcCorpai.setExchangeTicketAmount(exchange_ticket_amount_i);
		mcCorpai.setExchangeFeeAmount(exchange_fee_amount_i);
		mcCorpai.setIataClientCode(iata_client_code_i);
		mcCorpai.setTax(mcTax_i);

		//Create and set Tax for McCorpas
		McTax[] mcTax_s = new McTax[2];
		mcTax_s[0] = new McTax();
		mcTax_s[0].setTax(tax_amount_s[0], tax_rate_s[0], tax_type_s[0], tax_id_s[0], tax_included_in_sales_s[0]);
		mcTax_s[1] = new McTax();
		mcTax_s[1].setTax(tax_amount_s[1], tax_rate_s[1], tax_type_s[1], tax_id_s[1], tax_included_in_sales_s[1]);

		//Create and set McCorpas for Air Travel Details only
		McCorpas mcCorpas = new McCorpas();
		mcCorpas.setMcCorpas(travel_date_s[0], carrier_code1_s[0], service_class_s[0], orig_city_airport_code_s[0], dest_city_airport_code_s[0], stop_over_code_s[0],
							conjunction_ticket_number1_s[0],exchange_ticket_number1_s[0], coupon_number1_s[0], fare_basis_code1_s[0], flight_number_s[0], departure_time_s[0],
							arrival_time_s[0], fare_s[0], fee_s[0], taxes_s[0], endorsement_restrictions_s[0], mcTax_s[0]);
		mcCorpas.setMcCorpas(travel_date_s[1], carrier_code1_s[1], service_class_s[1], orig_city_airport_code_s[1], dest_city_airport_code_s[1], stop_over_code_s[1],
							conjunction_ticket_number1_s[1],exchange_ticket_number1_s[1], coupon_number1_s[1], fare_basis_code1_s[1], flight_number_s[1], departure_time_s[1],
							arrival_time_s[1], fare_s[1], fee_s[1], taxes_s[1], endorsement_restrictions_s[1], mcTax_s[1]);


		McCorpais mcCorpais = new McCorpais();
		mcCorpais.setOrderId(order_id);
		mcCorpais.setTxnNumber(txn_number);
		mcCorpais.setMcCorpac(mcCorpac);
		mcCorpais.setMcCorpai(mcCorpai);
		mcCorpais.setMcCorpas(mcCorpas);

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
