package ECommerceApp;

import java.util.*;

public class ShoppingCart {

	private ArrayList<Product> items = new ArrayList<>();

	public ArrayList<Product> getItems() {
		return items;
	}

	public void addItem(Product product, int quantity) {

		// Check if the product is already in the cart
		for (Product cartItem : items) {
			if (cartItem.getName().equals(product.getName())) {
				// If the product is found increment the quantity
				cartItem.incrementQuantity(quantity);
				System.out.println(product.getName() + " quantity incremented in the cart.");
				return;
			}
		}

		// If the product is not in the cart add a new product to the cart
		switch (product.getCategory()) {
		case "Electronics":
			items.add(new Electronics(product.getName(), product.getPrice(), quantity));
			break;
		case "Clothing":
			items.add(new Clothing(product.getName(), product.getPrice(), quantity));
			break;
		case "Furniture":
			items.add(new Furniture(product.getName(), product.getPrice(), quantity));
			break;

		}
	}

	public double calculateTotal(boolean Code) {
		double total = 0;
		for (Product item : items) {
			total += (item.getPrice() * item.getQuantity());
		}
		if (Code) {
			total *= 0.90;
		}
		return total;
	}
}