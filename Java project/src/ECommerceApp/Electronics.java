package ECommerceApp;

public class Electronics extends Product {

	public Electronics(String name, double price, int quantity) {
		super(name, price, quantity);
		category = "Electronics";
	}

	public String getCategory() {
		return category;

	}
}