package Level23;
import JavaAPI.*;
	
public class TestVsCorpaisInvoice
{
	public static void main(String[] args)
	{
		String store_id = "moneris";
		String api_token = "hurgle";
		String processing_country_code = "CA";
		boolean status_check = false;
		
		String order_id="Test1485208069127";
		String txn_number="39791-0_11";

		String buyer_name = "Buyer Manager";
		String local_tax_rate = "13.00";
		String duty_amount = "0.00";
		String discount_treatment = "0";
		String discount_amt = "0.00";
		String freight_amount = "0.20";
		String ship_to_pos_code = "M8X 2W8";
		String ship_from_pos_code = "M1K 2Y7";
		String des_cou_code = "CAN";
		String vat_ref_num = "VAT12345";
		String tax_treatment = "3";//3 = Gross prices given with tax information provided at invoice level
		String gst_hst_freight_amount = "0.00";
		String gst_hst_freight_rate = "13.00";  

		String[] item_com_code = {"X3101", "X84802"};
		String[] product_code = {"CHR123", "DDSK200"};
		String[] item_description = {"Office Chair", "Disk Drive"};
		String[] item_quantity = {"3", "1"};
		String[] item_uom = {"EA", "EA"};
		String[] unit_cost = {"0.20", "0.40"};
		String[] vat_tax_amt = {"0.00", "0.00"};
		String[] vat_tax_rate = {"13.00", "13.00"};
		String[] discount_treatmentL = {"0", "0"};
		String[] discount_amtL = {"0.00", "0.00"};

		//Create and set VsPurcha
		VsPurcha vsPurcha = new VsPurcha();
		vsPurcha.setBuyerName(buyer_name);
		vsPurcha.setLocalTaxRate(local_tax_rate);
		vsPurcha.setDutyAmount(duty_amount);
		vsPurcha.setDiscountTreatment(discount_treatment);
		vsPurcha.setDiscountAmt(discount_amt);
		vsPurcha.setFreightAmount(freight_amount);
		vsPurcha.setShipToPostalCode(ship_to_pos_code);
		vsPurcha.setShipFromPostalCode(ship_from_pos_code);
		vsPurcha.setDesCouCode(des_cou_code);
		vsPurcha.setVatRefNum(vat_ref_num);
		vsPurcha.setTaxTreatment(tax_treatment);
		vsPurcha.setGstHstFreightAmount(gst_hst_freight_amount);
		vsPurcha.setGstHstFreightRate(gst_hst_freight_rate);

		//Create and set VsPurchl
		VsPurchl vsPurchl = new VsPurchl();
		vsPurchl.setVsPurchl(item_com_code[0], product_code[0], item_description[0], item_quantity[0], item_uom[0], unit_cost[0], vat_tax_amt[0], vat_tax_rate[0], discount_treatmentL[0], discount_amtL[0]);
		vsPurchl.setVsPurchl(item_com_code[1], product_code[1], item_description[1], item_quantity[1], item_uom[1], unit_cost[1], vat_tax_amt[1], vat_tax_rate[1], discount_treatmentL[1], discount_amtL[1]);
		
		VsCorpais vsCorpais = new VsCorpais();
		vsCorpais.setOrderId(order_id);
		vsCorpais.setTxnNumber(txn_number);
		vsCorpais.setVsPurch(vsPurcha, vsPurchl);

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
