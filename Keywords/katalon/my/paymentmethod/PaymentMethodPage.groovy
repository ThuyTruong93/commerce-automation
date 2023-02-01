package katalon.my.paymentmethod
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.fw.lib.BasePage



public class PaymentMethodPage extends BasePage<PaymentMethodPage>{
	
	public PaymentMethodPage verifyPaymentMethodElement()
	{
		//Payment method
		WebUI.verifyElementPresent(text("Payment Method"), 5)
		WebUI.verifyElementPresent(text("Card Number"), 5)
		WebUI.verifyElementPresent(text("Expiration"), 5)
		WebUI.verifyElementPresent(text("CVC"), 5)
		WebUI.verifyElementPresent(text("Card Holder"), 5)
		WebUI.verifyElementPresent(btn("Save"), 5)
		//Billing information
		WebUI.verifyElementPresent(text("Billing Information"), 5)
		WebUI.verifyElementPresent(text("CC Email"), 5)
		WebUI.verifyElementPresent(text("Address Line 1*"), 5)
		WebUI.verifyElementPresent(text("Address Line 2*"), 5)
		WebUI.verifyElementPresent(text("City*"), 5)
		WebUI.verifyElementPresent(text("State*"), 5)
		WebUI.verifyElementPresent(text("Country*"), 5)
		WebUI.verifyElementPresent(text("Postal Code*"), 5)
		WebUI.verifyElementPresent(text("VAT/GSCID*"), 5)
		WebUI.verifyElementPresent(text("Full Business Name*"), 5)
		
	}
	
	public PaymentMethodPage inputCardNumber(String value)
	{
		WebUI.sendKeys(xpath("//*[text()='Card Number']/following::input"), value)
		return this
	}
	
	public PaymentMethodPage inputExpiration(String value)
	{
		WebUI.sendKeys(xpath("//*[text()='Expiration']/following::input"), value)
		return this
	}
	
	public PaymentMethodPage inputCVC(String value)
	{
		WebUI.sendKeys(xpath("//*[text()='CVC']/following::input"), value)
		return this
	}
	
	public PaymentMethodPage inputCardHolder(String value)
	{
		WebUI.sendKeys(xpath("//*[text()='Card Holder']/following::input"), value)
		return this
	}
	
	public PaymentMethodPage clickSavePayment()
	{
		WebUI.click(btn("Save"))
		return this
	}
	
	public PaymentMethodPage verifyAppearElementAfterAddPayment()
	{
		WebUI.verifyElementPresent(btn("Update"), 5)
		WebUI.verifyElementPresent(btn("Delete"), 5)
	}
	
	public PaymentMethodPage clickDeletePaymentMethod()
	{
		WebUI.click(btn("Delete"))
		return this
	}
	
	public PaymentMethodPage clickUpdateBillingInformation()
	{
		WebUI.click(btn("Update"))
		return this
	}
	
	public PaymentMethodPage verifyAppearElementAfterClickUpdateBillingInfor()
	{
		WebUI.verifyElementPresent(btn("Save billing information"), 5)
		WebUI.verifyElementPresent(btn("Cancel"), 5)
	}
	
	public PaymentMethodPage clickCancelBillingInformation()
	{
		WebUI.click(btn("Cancel"))
		return this
	}
}
