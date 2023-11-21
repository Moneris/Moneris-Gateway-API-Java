package Level23;
import JavaAPI.*;
	
public class TestAxRaRefund
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="Test1485206346383";
		String amount="62.37";
		String txn_number = "660117311902017023161906704-0_11";
		String crypt="7";
		
		String airline_process_id = "000"; 	//Airline three-digit IATA code, Mandatory, Alphanumberic/3
		String invoice_batch = "580";		//Three-digit code that specifies processing options, Mandatory, Numeric/3
		String establishment_name = "TestEstablishment"; 	//Name of the ticket issuer, Mandatory, Alphanumberic/21
		String carrier_name = "M AIR";		//Name of the ticketing airline, Mandatory, Alphanumberic/8
		String ticket_id = "83060915430001";		//Ticket or document number, Mandatory, Numeric/14
		String issue_city = "Toronto";		//Name of the city, Mandatory, Alphanumberic/13
		String establishment_state = "ON";	//State or province code, Mandatory, Alphanumberic/2
		String number_in_party = "2";		//Number of the people, Optional, Numeric/3
		String passenger_name = "TestPassenger";		//Passenger name, Mandatory, Alphanumberic/20
		String taa_routing = "YYZ";		//Flight stopover and city/airport codes, Mandatory, Alphanumberic/20
		String carrier_code = "ClassA";	//Carrier designator codes, Mandatory, Alphanumberic/8
		String fare_basis = "Regular";	//Primary and secondary discount codes, Mandatory, Alphanumberic/24
		String document_type = "00";		//Airline document type code, Mandatory, Numeric/2
		String doc_number = "5908";		//Number assigned to the airline document, Mandatory, Numeric/4
		String departure_date = "0916";  //Departure date, Mandatory, Numeric/4 (MMDD)
		
		AxRaLevel23 raLevel23 = new AxRaLevel23();
		raLevel23.setAirlineProcessId(airline_process_id);
		raLevel23.setInvoiceBatch(invoice_batch);
		raLevel23.setEstablishmentName(establishment_name);
		raLevel23.setCarrierName(carrier_name);
		raLevel23.setTicketId(ticket_id);
		raLevel23.setIssueCity(issue_city);
		raLevel23.setEstablishmentState(establishment_state);
		raLevel23.setNumberInParty(number_in_party);
		raLevel23.setPassengerName(passenger_name);
		raLevel23.setTaaRouting(taa_routing);
		raLevel23.setCarrierCode(carrier_code);
		raLevel23.setFareBasis(fare_basis);
		raLevel23.setDocumentType(document_type);
		raLevel23.setDocNumber(doc_number);
		raLevel23.setDepartureDate(departure_date);

		AxRaRefund axRaRefund = new AxRaRefund();
		axRaRefund.setOrderId(order_id);
		axRaRefund.setAmount(amount);
		axRaRefund.setTxnNumber(txn_number);
		axRaRefund.setCryptType(crypt);
		axRaRefund.setAxRaLevel23(raLevel23);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(axRaRefund);
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
