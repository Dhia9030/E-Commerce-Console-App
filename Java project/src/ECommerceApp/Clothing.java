package ECommerceApp;

public class Clothing extends Product {

	public Clothing(String name, double price, int quantity) {
		super(name, price, quantity);
		category = "Clothing";
	}

	public String getCategory() {
		return category;

	}
}
