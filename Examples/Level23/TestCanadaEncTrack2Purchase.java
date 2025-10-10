package Canada;
import JavaAPI.*;

public class TestCanadaEncTrack2Purchase
{
	public static void main(String args[])
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "moneris";
		String api_token = "hurgle";
		String amount = "1.00";
		String enc_track2 = "02D901801F4F2800039B%*4924********3428^TESTCARD/MONERIS^*****************************************?*;4924********3428=********************?*9AC1D386E03D553ACB8976E58653B76386B5C7ACBFA126D6ADC026018152893229626E03BE0D41673C975B69C773FDE3A890AD2B5452B02739529B85C8231B3798AEE7E702280CC8A3E4B3CE1C098E4CB24A0A7DFF462507E3419EFF3880E9D52C869BA6C970C49B10B3C6E146B626EDBB13F3C6057377E43CD06F7A188D84EA0260832F743E485C0D369929D4840FFAFA12BC3938C4A4DE4FA3FA837D1C2190FFFF314159200420005F345603";
		String pos_code = "00";
		String device_type = "idtech_bdk";
		String processing_country_code = "CA";

		EncTrack2Purchase enc_track2_purchase = new EncTrack2Purchase ();
		enc_track2_purchase.setOrderId(order_id);
		enc_track2_purchase.setAmount(amount);
		enc_track2_purchase.setEncTrack2(enc_track2);
		enc_track2_purchase.setPosCode(pos_code);
		enc_track2_purchase.setDeviceType(device_type);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(enc_track2_purchase);
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
