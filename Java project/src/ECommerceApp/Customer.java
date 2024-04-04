package ECommerceApp;

import java.util.*;

public class Customer extends User {

	boolean discountCode;

	private ShoppingCart cart = new ShoppingCart();

	private Order orderHistory = new Order();

	public Customer(String username, String password) {
		super(username, password, false);
		discountCode = false;

	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart c) {
		cart = c;
	}

	public boolean getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(boolean statue) {

		discountCode = statue;

	}

	public Order getHistory() {

		return orderHistory;
	}

	public void removeFromCart(String productName, List<Product> inventory) {

		Scanner sc = new Scanner(System.in);

		ArrayList<Product> cartItems = cart.getItems();
		System.out.print("Quantity to remove: ");
		int quantity = sc.nextInt();
		sc.nextLine();

		for (Product item : cartItems) {
			if (item.getName().equalsIgnoreCase(productName)) {
				if (quantity >= item.getQuantity()) {
					cartItems.remove(item);
					System.out.println(productName + " removed from the cart.");
				} else {
					item.setQuantity(item.getQuantity() - quantity);

				}
				for (Product product : inventory) {
					if (product.getName().equalsIgnoreCase(productName)) {
						product.setRemainingQuantity(quantity + product.getRemainingQuantity());

					}

				}
				return;
			}
		}

		System.out.println("This Product not found in the cart: " + productName);
	}

	public void viewCart() {
		ArrayList<Product> cartItems = cart.getItems();
		if (cartItems.isEmpty()) {
			System.out.println("Your cart is empty.");
		} else {
			System.out.println("\nShopping Cart:");
			for (Product item : cartItems) {

				System.out.println(item.getName() + " : $" + item.getPrice() + " (Category: " + item.getCategory()
						+ "Quantity: " + item.getQuantity() + ")");
			}
			System.out.println("Total: $" + cart.calculateTotal(discountCode));
		}
	}

	public void addToCart(ArrayList<Product> inventory) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the product number to add to cart: ");
		int productNumber = sc.nextInt();
		System.out.print("Quantity needed: ");
		int quantity = sc.nextInt();

		if (this.isValidProductNumber(productNumber, inventory)) {
			Product selectedProduct = inventory.get(productNumber - 1);
			if (selectedProduct.getRemainingQuantity() >= quantity) {
				cart.addItem(selectedProduct, quantity);
				selectedProduct.updateRemainingQuantity(quantity);
				System.out.println(selectedProduct.getName() + " added to cart.");
				System.out.println("\nRemaining quantity of " + selectedProduct.getName() + " : "
						+ selectedProduct.getRemainingQuantity());
			} else {
				System.out.println("Sorry, the stock of " + selectedProduct.getName() + " is insufficient .");
			}
		} else {
			System.out.println("Invalid product number.");
		}
	}

	public void rateProduct(ArrayList<Product> inventory) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the product number to rate: ");
		int productNumber = sc.nextInt();
		sc.nextLine();
		if (this.isValidProductNumber(productNumber, inventory)) {

			Product selectedProduct = inventory.get(productNumber - 1);
			System.out.print("Give the rating: ");
			double rating = sc.nextDouble();
			selectedProduct.addRating(this.getUsername(), rating);

		} else {
			System.out.println("Invalid product number.");
		}

	}

	private boolean isValidProductNumber(int productNumber, ArrayList<Product> inventory) {
		return productNumber >= 1 && productNumber <= inventory.size();
	}

}
