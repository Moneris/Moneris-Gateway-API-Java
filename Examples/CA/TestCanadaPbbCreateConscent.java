package Canada;

import JavaAPI.*;

import java.util.ArrayList;
import java.util.List;

public class TestCanadaPbbCreateConscent
{
	public static void main(String[] args)
	{
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String processing_country_code = "CA";
		String pbb_merchant_id = "CAM0000001";

		PBB_CreateConsent pbbCreateConsent = new PBB_CreateConsent();
		pbbCreateConsent.setpbb_merchant_id(pbb_merchant_id);

		RequiredContactInfo requiredContactInfo= new RequiredContactInfo();
		requiredContactInfo.addcontactField("NAME");
		requiredContactInfo.addcontactField("PHONE");
		requiredContactInfo.addcontactField("EMAIL");
		requiredContactInfo.addcontactField("SHIPPING_ADDRESS");

		pbbCreateConsent.setRequiredContactInfo(requiredContactInfo);

		SupportedMethodInfo supportedMethodInfo= new SupportedMethodInfo();
		supportedMethodInfo.addPaymentMethod("MASTERCARD_DEBIT");
		supportedMethodInfo.addPaymentMethod("VISA_DEBIT");
		supportedMethodInfo.addPaymentMethod("AMEX_CREDIT");
		supportedMethodInfo.addPaymentMethod("VISA_CREDIT");
		supportedMethodInfo.addPaymentMethod("MASTERCARD_CREDIT");

		pbbCreateConsent.setSupportedMethodInfo(supportedMethodInfo);


		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setShipmentType("NONE");
		orderInfo.setMerchantOrderRef("1695401825L1CLwAJ98p7JwAc");
		orderInfo.setPlacementMode("STANDARD");

		PBBItems pbbItems= new PBBItems();
		PBBItem pbbItem1= new PBBItem();
        pbbItem1.setItemName("Java");
        pbbItem1.setQuantity("1");
        pbbItem1.setItemPaymentType("SINGLE");
        pbbItem1.setDescription("This is a test item 1");
        pbbItem1.setAmount("50.00");
        pbbItem1.setItemRef("123456");

        RecurringInfo recurringInfo= new RecurringInfo();
        recurringInfo.setAmountCapped("100");
        recurringInfo.setAmountVariance("2.5");
        recurringInfo.setDayOfMonth("1");
        recurringInfo.setDayOfWeek("MON");
        recurringInfo.setStartDate("2023-10-01T00:00:00.000Z");
        recurringInfo.setEndDate("2025-12-31T23:59:59.000Z");
        recurringInfo.setFrequencyRate("50");
        recurringInfo.setFrequencyType("DAY");
        recurringInfo.setWeekOfMonth("FIRST");
        pbbItem1.setRecurringInfo(recurringInfo);
        pbbItems.addItem(pbbItem1);

        PBBItem pbbItem2= new PBBItem();
        pbbItem2.setItemName("Dotnet");
        pbbItem2.setQuantity("1");
        pbbItem2.setItemPaymentType("SINGLE");
        pbbItem2.setDescription("This is a test item 2");
        pbbItem2.setAmount("20.00");
        pbbItem2.setItemRef("654321");
        RecurringInfo recurringInfo2= new RecurringInfo();
        recurringInfo2.setAmountCapped("200");
        recurringInfo2.setAmountVariance("5.0");
        recurringInfo2.setDayOfMonth("15");
        recurringInfo2.setDayOfWeek("FRI");
        pbbItem2.setRecurringInfo(recurringInfo2);

        pbbItems.addItem(pbbItem2);

		orderInfo.setPbbItems(pbbItems);
		pbbCreateConsent.setOrderInfo(orderInfo);

		PaymentInfo paymentInfo= new PaymentInfo();
		paymentInfo.setCurrency("CAD");
		paymentInfo.setAmount("72.00");
		paymentInfo.setsubTotal("2.00");
		paymentInfo.setTax("0.00");
		paymentInfo.setShippingCost("0.00");

		AdditionalFees additionalFees = new AdditionalFees();
		additionalFees.setAdditionalfee("java","50.00");
		additionalFees.setAdditionalfee("Dotnet","20.00");

		paymentInfo.setAdditionalFees(additionalFees);
		pbbCreateConsent.setPaymentInfo(paymentInfo);


		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(pbbCreateConsent);
		mpgReq.send();


		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();
			if((receipt.getConsentId()!=null)&& !(receipt.getConsentId().isEmpty()))
			{
				System.out.println("ConsentId = " + receipt.getConsentId());
				System.out.println("RequestId = " + receipt.getRequestId());
				System.out.println("CorrelationId = " + receipt.getCorrelationId());
				System.out.println("DeviceProfileSessionId= " + receipt.getDeviceProfileSessionId());
			}
			else
			{
				System.out.println("CODE" + receipt.getCode());
				System.out.println("Type" + receipt.getType());
				System.out.println("Message" + receipt.getMessage());
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
