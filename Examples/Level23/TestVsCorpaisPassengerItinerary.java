package Level23;
import JavaAPI.*;

public class TestVsCorpaisPassengerItinerary
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="Test1485208038386";
		String txn_number="39790-0_11";

		String ticket_number = "X9831083193";
		String passenger_name = "John Williams";
		String total_fee = "0.23";
		String exchange_ticket_number = "1234567890001";
		String exchange_ticket_amount = "0.24";
		String travel_agency_code = "XH1";
		String travel_agency_name="AIR FLY";
		String internet_indicator = "Y";
		String electronic_ticket_indicator = "Y";
		String vat_ref_num = "XH13983189";

		String[] conjunction_ticket_number = {"1234567890100", "1234567890101"};

		String[] coupon_number = {"1", "3", "2"};
		String[] carrier_code1 = {"2R", "2R", "2R"};
		String[] flight_number = {"1234", "5678", "3456"};
		String[] service_class = {"A", "B", "C"};
		String[] orig_city_airport_code = {"YVR", "BOS", "NYC"};
		String[] stop_over_code = {"O", "O", "X"};
		String[] dest_city_airport_code = {"BOS", "NYC", "EWR"};
		String[] fare_basis_code = {"FClass", "Business", "Business"};
		String[] departure_date1 = {"030113", "030213", "030313"};
		String[] departure_time = {"1110", "1120", "1130"};
		String[] arrival_time = {"1210", "1220", "1230"};

		String[] control_id = {"1234567890300", "1234567890301"};

		//Create and set VsCorpai
		VsCorpai vsCorpai = new VsCorpai();
		vsCorpai.setTicketNumber(ticket_number);
		vsCorpai.setPassengerName1(passenger_name);
		vsCorpai.setTotalFee(total_fee);
		vsCorpai.setExchangeTicketNumber(exchange_ticket_number);
		vsCorpai.setExchangeTicketAmount(exchange_ticket_amount);
		vsCorpai.setTravelAgencyCode(travel_agency_code);
		vsCorpai.setTravelAgencyName(travel_agency_name);
		vsCorpai.setInternetIndicator(internet_indicator);
		vsCorpai.setElectronicTicketIndicator(electronic_ticket_indicator);
		vsCorpai.setVatRefNum(vat_ref_num);

		//Create and set VsCorpais
		//Every Corpas can only have up to 2 TripLegInfo
		VsTripLegInfo[] vsTripLegInfo = {new VsTripLegInfo(), new VsTripLegInfo()};
		vsTripLegInfo[0].setTripLegInfo(coupon_number[0], carrier_code1[0], flight_number[0], service_class[0], orig_city_airport_code[0], stop_over_code[0], dest_city_airport_code[0], fare_basis_code[0], departure_date1[0], departure_time[0], arrival_time[0]);
		vsTripLegInfo[0].setTripLegInfo(coupon_number[1], carrier_code1[1], flight_number[1], service_class[1], orig_city_airport_code[1], stop_over_code[1], dest_city_airport_code[1], fare_basis_code[1], departure_date1[1], departure_time[1], arrival_time[1]);

		vsTripLegInfo[1].setTripLegInfo(coupon_number[2], carrier_code1[2], flight_number[2], service_class[2], orig_city_airport_code[2], stop_over_code[2], dest_city_airport_code[2], fare_basis_code[2], departure_date1[2], departure_time[2], arrival_time[2]);

		VsCorpas vsCorpas = new VsCorpas();
		vsCorpas.setCorpas(conjunction_ticket_number[0], vsTripLegInfo[0], control_id[0]);
		vsCorpas.setCorpas(conjunction_ticket_number[1], vsTripLegInfo[1], control_id[1]);
	
		VsCorpais vsCorpais = new VsCorpais();
		vsCorpais.setOrderId(order_id);
		vsCorpais.setTxnNumber(txn_number);
		vsCorpais.setVsCorpa(vsCorpai, vsCorpas);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(vsCorpais);
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
