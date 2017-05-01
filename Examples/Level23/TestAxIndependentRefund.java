package Level23;
import JavaAPI.*;

public class TestAxIndependentRefund
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
		String amount="62.37";
		String pan="373269005095005";
		String expiry_date="2012"; //YYMM
		String crypt="7";
		
		//Create Table 1 with details
		String n101 = "R6";	//Entity ID Code
		String n102 = "Retailing Inc. International";	//Name
		String n301 = "919 Oriole Rd.";		//Address Line 1
		String n401 = "Toronto";		//City
		String n402 = "On";			//State or Province
		String n403 = "H1T6W3";			//Postal Code

		String[] ref01 = {"4C", "CR"};	//Reference ID Qualifier
		String[] ref02 = {"M5T3A5", "16802309004"}; //Reference ID

		String big04 = "PO7758545";	//Purchase Order Number
		String big05 = "RN0049858";	//Release Number
		String big10 = "INV99870E";      //Invoice Number

		AxRef axRef1 = new AxRef();
		axRef1.setRef(ref01[0], ref02[0]);
		axRef1.setRef(ref01[1], ref02[1]);

		AxN1Loop n1Loop = new AxN1Loop();
		n1Loop.setN1Loop(n101, n102, n301, n401, n402, n403, axRef1);

		AxTable1 table1 = new AxTable1();
		table1.setBig04(big04);
		table1.setBig05(big05);
		table1.setBig10(big10);
		table1.setN1Loop(n1Loop);
		
		//Create Table 2 with details
		//the sum of the extended amount field (pam05) must equal the level 1 amount field				
		String[] it102 = {"1", "1", "1", "1", "1"};	//Line item quantity invoiced
		String[] it103 = {"EA", "EA", "EA", "EA", "EA"};  //Line item unit or basis of measurement code
		String[] it104 = {"10.00", "25.00", "8.62", "10.00", "-10.00"};   //Line item unit price
		String[] it105 = {"", "", "", "", ""};	//Line item basis of unit price code
			
		String[] it10618 = {"MG", "MG", "MG", "MG", "MG"};   //Product/Service ID qualifier
		String[] it10719 = {"DJFR4", "JFJ49", "FEF33", "FEE43", "DISCOUNT"};   //Product/Service ID (corresponds to it10618)
			
		String[] txi01_GST = {"GS", "GS", "GS", "GS", "GS"};	//Tax type code
		String[] txi02_GST = {"0.70", "1.75", "1.00", "0.80","0.00"};	//Monetary amount
		String[] txi03_GST = {"", "", "", "",""};		//Percent
		String[] txi06_GST = {"", "", "", "",""};		//Tax exempt code
			
		String[] txi01_PST = {"PG", "PG", "PG","PG","PG"};	//Tax type code
		String[] txi02_PST = {"0.80", "2.00", "1.00", "0.80","0.00"};	//Monetary amount
		String[] txi03_PST = {"", "", "", "",""};		//Percent
		String[] txi06_PST = {"", "", "", "",""};		//Tax exempt code

		String[] pam05 = {"11.50", "28.75", "10.62", "11.50", "-10.00"};	//Extended line-item amount
		String[] pid05 = {"Stapler", "Lamp", "Bottled Water", "Fountain Pen", "DISCOUNT"};	//Line item description

		AxIt106s[] it106s = {new AxIt106s(), new AxIt106s(), new AxIt106s(), new AxIt106s(), new AxIt106s()};
		
		it106s[0].setIt10618(it10618[0]);
		it106s[0].setIt10719(it10719[0]);
		
		it106s[1].setIt10618(it10618[1]);
		it106s[1].setIt10719(it10719[1]);
		
		it106s[2].setIt10618(it10618[2]);
		it106s[2].setIt10719(it10719[2]);
		
		it106s[3].setIt10618(it10618[3]);
		it106s[3].setIt10719(it10719[3]);
		
		it106s[4].setIt10618(it10618[4]);
		it106s[4].setIt10719(it10719[4]);

		AxTxi[] txi = {new AxTxi(), new AxTxi(), new AxTxi(), new AxTxi(), new AxTxi()};

		txi[0].setTxi(txi01_GST[0], txi02_GST[0], txi03_GST[0], txi06_GST[0]);
		txi[0].setTxi(txi01_PST[0], txi02_PST[0], txi03_PST[0], txi06_PST[0]);

		txi[1].setTxi(txi01_GST[1], txi02_GST[1], txi03_GST[1], txi06_GST[1]);
		txi[1].setTxi(txi01_PST[1], txi02_PST[1], txi03_PST[1], txi06_PST[1]);

		txi[2].setTxi(txi01_GST[2], txi02_GST[2], txi03_GST[2], txi06_GST[2]);
		txi[2].setTxi(txi01_PST[2], txi02_PST[2], txi03_PST[2], txi06_PST[2]);

		txi[3].setTxi(txi01_GST[3], txi02_GST[3], txi03_GST[3], txi06_GST[3]);
		txi[3].setTxi(txi01_PST[3], txi02_PST[3], txi03_PST[3], txi06_PST[3]);

		txi[4].setTxi(txi01_GST[4], txi02_GST[4], txi03_GST[4], txi06_GST[4]);
		txi[4].setTxi(txi01_PST[4], txi02_PST[4], txi03_PST[4], txi06_PST[4]);

		AxIt1Loop it1Loop = new AxIt1Loop();
		it1Loop.setIt1Loop(it102[0], it103[0], it104[0], it105[0], it106s[0], txi[0], pam05[0], pid05[0]);
		it1Loop.setIt1Loop(it102[1], it103[1], it104[1], it105[1], it106s[1], txi[1], pam05[1], pid05[1]);
		it1Loop.setIt1Loop(it102[2], it103[2], it104[2], it105[2], it106s[2], txi[2], pam05[2], pid05[2]);
		it1Loop.setIt1Loop(it102[3], it103[3], it104[3], it105[3], it106s[3], txi[3], pam05[3], pid05[3]);
		it1Loop.setIt1Loop(it102[4], it103[4], it104[4], it105[4], it106s[4], txi[4], pam05[4], pid05[4]);

		AxTable2 table2 = new AxTable2();
		table2.setIt1Loop(it1Loop);

		//Create Table 3 with details
		AxTxi taxTbl3 = new AxTxi();
		taxTbl3.setTxi("GS", "4.25","","");	//sum of GST taxes
		taxTbl3.setTxi("PG", "4.60","","");	//sum of PST taxes
		taxTbl3.setTxi("TX", "8.85","","");	//sum of all taxes

		AxTable3 table3 = new AxTable3();
		table3.setTxi(taxTbl3);
		
		//Create and set Level23 Object
		AxLevel23 level23 = new AxLevel23();
		level23.setTable1(table1);
		level23.setTable2(table2);
		level23.setTable3(table3);

		AxIndependentRefund axIndependentRefund = new AxIndependentRefund();
		axIndependentRefund.setOrderId(order_id);
		axIndependentRefund.setCustId(cust_id);
		axIndependentRefund.setAmount(amount);
		axIndependentRefund.setPan(pan);
		axIndependentRefund.setExpDate(expiry_date);
		axIndependentRefund.setCryptType(crypt);
		axIndependentRefund.setAxLevel23(level23);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
        mpgReq.setProcCountryCode(processing_country_code);
        mpgReq.setTestMode(true); //false or comment out this line for production transactions
        mpgReq.setStoreId(store_id);
        mpgReq.setApiToken(api_token);
        mpgReq.setTransaction(axIndependentRefund);
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

