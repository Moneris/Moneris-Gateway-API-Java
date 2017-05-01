package Canada;

import JavaAPI.*;

public class TestCanadaOpenTotals
{
	public static void main(String[] args)
	{
		String store_id = "store5";
		String api_token = "yesguy";
		String ecr_no = "66013455";
		//String ecr_no = "66011091";
		String processing_country_code = "CA";

		OpenTotals opentotals = new OpenTotals();
		opentotals.setEcrno(ecr_no);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(opentotals);
		mpgReq.send();
		
		try
		{
			Receipt receipt = mpgReq.getReceipt();

			if ((receipt.getReceiptId()).equals("Global Error Receipt") || 
					 receipt.getReceiptId().equals("null") || 
					 receipt.getReceiptId().equals(""))
			{
				System.out.println("CardType = null");
				System.out.println("TransAmount = " + receipt.getTransAmount());
				System.out.println("TxnNumber = " + receipt.getTxnNumber());
				System.out.println("ReceiptId = " + receipt.getReceiptId());
				System.out.println("TransType = " + receipt.getTransType());
				System.out.println("ReferenceNum = " + receipt.getReferenceNum());
				System.out.println("ResponseCode = " + receipt.getResponseCode());
				System.out.println("ISO = " + receipt.getISO());
				System.out.println("BankTotals = null");
				System.out.println("Message = " + receipt.getMessage());
				System.out.println("AuthCode = " + receipt.getAuthCode());
				System.out.println("Complete = " + receipt.getComplete());
				System.out.println("TransDate = " + receipt.getTransDate());
				System.out.println("TransTime = " + receipt.getTransTime());
				System.out.println("Ticket = " + receipt.getTicket());
				System.out.println("TimedOut = " + receipt.getTimedOut());
			}
			else
			{
				for (String ecr : receipt.getTerminalIDs())
				{
					System.out.println("ECR: " + ecr);
					
					for (String cardType : receipt.getCreditCards(ecr))
					{
						System.out.println("\tCard Type: " + cardType);

						System.out.println("\t\tPurchase: Count = "
								+ receipt.getPurchaseCount(ecr, cardType)
								+ " Amount = "
								+ receipt.getPurchaseAmount(ecr,
									cardType));

						System.out.println("\t\tRefund: Count = "
								+ receipt.getRefundCount(ecr, cardType)
								+ " Amount = "
								+ receipt.getRefundAmount(ecr, cardType));

						System.out.println("\t\tCorrection: Count = "
								+ receipt.getCorrectionCount(ecr, cardType)
								+ " Amount = "
								+ receipt.getCorrectionAmount(ecr,
									cardType));
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
