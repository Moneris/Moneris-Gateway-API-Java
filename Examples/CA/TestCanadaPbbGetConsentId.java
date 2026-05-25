package Canada;

import JavaAPI.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.StreamSupport;

public class TestCanadaPbbGetConsentId
{
	public static void main(String[] args)
	{
		String store_id = "monca03650";
		String api_token = "7Yw0MPTlhjBRcZiE6837";
		String processing_country_code = "CA";
		String ConsentId = "b4e029fb-ee8a-49ca-b5d2-16e7ef4d880f";

		PBB_GetConsentId pbbGetConsentId = new PBB_GetConsentId();
		pbbGetConsentId.setConsentId(ConsentId);


		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(pbbGetConsentId);

		mpgReq.send();

		/**********************   REQUEST  ************************/

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("CreatedOn = "+ receipt.getCreatedOn());
			System.out.println("ConsentId = "+ receipt.getConsentId());
			System.out.println("\nShippingAddress");
			System.out.println("StreetNumber = "+ receipt.getStreetNumber());
			System.out.println("StreetName = "+ receipt.getStreetName());
			System.out.println("City = "+ receipt.getCity());
			System.out.println("Province = "+ receipt.getProvince());
			System.out.println("PostalCode = "+ receipt.getPostalCode());
			System.out.println("Country = "+ receipt.getCountry());
			System.out.println("RecipientName = "+ receipt.getRecipientName());
			System.out.println("ShippingAddressRef = "+receipt.getShippingAddressRef());

			System.out.println("\nCustomerInfo Name =  "+ receipt.getName());
			System.out.println("CustomerInfo Email =  "+ receipt.getEmail());
			System.out.println("CustomerInfo PhoneNumber =  "+ receipt.getPhoneNumber());
			System.out.println("CustomerInfo AuthMethod =  "+ receipt.getAuthMethod());
			System.out.println("CustomerInfo UserType =  "+ receipt.getUserType());
			System.out.println("Status =  "+ receipt.getStatus());
			System.out.println("\nOrder MerchantOrderRef = "+ receipt.getMerchantOrderRef());
			System.out.println("Order MerchantCustomerRef = "+ receipt.getMerchantCustomerRef());
			System.out.println("Order PlacementMode = "+ receipt.getPlacementMode());
			System.out.println("Order ShipmentType = "+ receipt.getShipmentType());

			PBBItems pbbItems = receipt.getPBBItems();
			int totalItems= pbbItems.getItemsCount();
			PBBItem [] pbbitem=pbbItems.getItems();
			System.out.println("Total Items: "+ totalItems);

			for(int i=0;i<totalItems;i++)
			{
			System.out.println("\nItem: "+(i+1));
			System.out.println("ItemRef = "+pbbitem[i].getItemRef());
			System.out.println("ItemPaymentType = "+pbbitem[i].getItemPaymentType());
			System.out.println("Amount = "+pbbitem[i].getAmount());
			System.out.println("Quantity = "+pbbitem[i].getQuantity());
			System.out.println("Description = "+pbbitem[i].getDescription());
			System.out.println("ItemType = "+pbbitem[i].getItemType());
            System.out.println("ItemName = "+pbbitem[i].getItemName());

            if(pbbitem[i].getRecurringInfo()!=null) {
                RecurringInfo recurringInfo = pbbitem[i].getRecurringInfo();
                System.out.println("Recurring Info:");
                System.out.println("    AmountCapped = " + recurringInfo.getAmountCapped());
                System.out.println("    AmountVariance = " + recurringInfo.getAmountVariance());
                System.out.println("    DayOfMonth = " + recurringInfo.getDayOfMonth());
                System.out.println("    DayOfWeek = " + recurringInfo.getDayOfWeek());
                System.out.println("    FrequencyRate = " + recurringInfo.getFrequencyRate());
                System.out.println("    FrequencyType = " + recurringInfo.getFrequencyType());
                System.out.println("    MaxNumberOfPayments = " + recurringInfo.getMaxNumberOfPayments());
                System.out.println("    StartDate = " + recurringInfo.getStartDate());
                System.out.println("    EndDate = " + recurringInfo.getEndDate());
                System.out.println("    WeekOfMonth = " + recurringInfo.getWeekOfMonth());
                System.out.println("    RecurringType = " + recurringInfo.getRecurringType());
                }

			}

			System.out.println("\nPayment Details");

			AdditionalFees additionalFees = receipt.getAdditionalFees();
			int totalAdditionalfee= additionalFees.getAdditionalFeeCount();
			AdditionalFee [] additionalFees2=additionalFees.getItems();

			System.out.println("SubTotal ="+ receipt.getSubTotal());
			System.out.println("Amount ="+ receipt.getAmount2());
			System.out.println("Tax ="+receipt.getTax());
			System.out.println("ShippingCost ="+receipt.getShippingCost());
			System.out.println("Currency ="+receipt.getCurrency2());

			System.out.println("\nAdditional fees");
			System.out.println("Total Additional fees: "+ totalAdditionalfee);
			for(int i=0;i<totalAdditionalfee;i++){
				System.out.println("Additional Fee: "+(i+1));
				System.out.println("Name = "+additionalFees2[i].getName());
				System.out.println("Amount = "+additionalFees2[i].getAmount());
			}

			ArrayList<String>Merchant= receipt.getMerchantRequiredArray();
			System.out.println("\nMerchantRequiredContactFields");
			for(int i=0;i<Merchant.size();i++)
			{
			System.out.println("MerchantRequiredContactField = "+Merchant.get(i));
			}

			ArrayList<String>MerchantSupported= receipt.getMerchantSupportedPaymentMethodArray();
			System.out.println("\nMerchantSupportedPaymentMethods");

			for(int i=0;i<MerchantSupported.size();i++)
			{
				System.out.println("MerchantSupportedPaymentMethod = "+MerchantSupported.get(i));
			}
			System.out.println("\nTokenMetaData");
			System.out.println("AccountType = "+receipt.getAccountType());
			System.out.println("TokenState = "+receipt.getTokenState());
			System.out.println("AccountLastDigits = "+receipt.getAccountLastDigits());
			System.out.println("TokenPanLastDigits = "+receipt.getTokenPanLastDigits2());
			System.out.println("\nAuthOn = "+receipt.getAuthOn());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
} // end TestResMpiTxn
