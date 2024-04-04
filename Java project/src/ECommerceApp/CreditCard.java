package ECommerceApp;

public class CreditCard implements Payment {
	private String cardNumber;
	private String expirationDate;
	private String cvv;

	public CreditCard(String cardNumber, String expirationDate, String cvv) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;

	}

	public void pay(double amount) {
		System.out.println("Processing credit card payment...");
		System.out.println("Amount: $" + amount);
		System.out.println("Card Number: " + cardNumber);
		System.out.println("Expiration Date: " + expirationDate);
		System.out.println("CVV: " + cvv);
		System.out.println("Payment successful!");
	}

}
