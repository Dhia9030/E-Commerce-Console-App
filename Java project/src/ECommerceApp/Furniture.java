package ECommerceApp;

public class Furniture extends Product {

	public Furniture(String name, double price, int quantity) {
		super(name, price, quantity);
		category = "Furniture";
	}

	public String getCategory() {
		return category;
	}

}
