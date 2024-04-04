package ECommerceApp;

public class PayPal implements Payment {
	private String email;

	public PayPal(String email) {
		this.email = email;
	}

	public void pay(double amount) {

		System.out.println("Processing PayPal payment");
		System.out.println("Amount: $" + amount);
		System.out.println("PayPal Email: " + email);
		System.out.println("Payment successful!");
	}
}
