package ECommerceApp;

import java.util.*;

public class Application {

	private static ArrayList<Product> inventory = new ArrayList<Product>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Integer> codes = new ArrayList<Integer>();
	private static User currentUser = null;
	private static ShoppingCart newCart = new ShoppingCart();

	public static void main(String[] args) {
		initializeInventory();
		initializeAdmins();
		initializeDiscountCode();
		users.add(new Customer("B", "1"));

		Scanner sc = new Scanner(System.in);

		while (true) {
			if (currentUser == null) {
				System.out.println("\n1. Sign Up\n2. Login\n3. Exit");
				System.out.print("Enter your choice: ");
				int mainChoice = sc.nextInt();

				switch (mainChoice) {
				case 1:
					signupMenu(sc);
					break;
				case 2:
					loginMenu();
					break;
				case 3:
					System.out.println("Exiting the application. Goodbye!");
					System.exit(0);
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} else {
				if (currentUser.isAdmin()) {
					adminMenu(sc);
				} else {

					customerMenu(sc);
				}
			}
		}
	}

	// Initializing Inventory

	private static void initializeInventory() {
		inventory.add(new Electronics("Laptop", 999.99, 10));
		inventory.add(new Electronics("Smartphone", 499.99, 15));
		inventory.add(new Electronics("Headphones", 79.99, 8));

		inventory.add(new Furniture("Desk", 199.99, 12));
		inventory.add(new Furniture("Chair", 49.99, 20));

		inventory.add(new Clothing("T-Shirt", 19.99, 25));
		inventory.add(new Clothing("Jeans", 39.99, 18));
	}

	// Initializing Admin

	private static void initializeAdmins() {
		users.add(new Admin("A", "1"));
	}

	// Initializing Discount codes

	private static void initializeDiscountCode() {
		codes.add(123456);
		codes.add(11111);
		codes.add(7642);
		codes.add(253532);
		codes.add(243345);

	}

	// Sign up

	private static void signupMenu(Scanner sc) {
		System.out.println("\nSign Up Menu:");
		System.out.print("Enter new username: ");
		sc.nextLine();
		String newUsername = sc.nextLine();
		System.out.print("Enter new password: ");
		String newPassword = sc.nextLine();

		// Check if the username is already taken
		boolean usernameTaken = false;
		for (User user : users) {
			if (user.getUsername().equals(newUsername)) {
				usernameTaken = true;
				break;
			}
		}

		if (!usernameTaken) {

			User newUser = new Customer(newUsername, newPassword);
			users.add(newUser);
			System.out.println("Sign up successful. Welcome, " + newUsername + "!");
			currentUser = newUser;
		} else {
			System.out.println("Username already taken. Please choose a different username.");
		}
	}

	// Login

	private static void loginMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nLogin Menu:");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				currentUser = user;
				System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
				return;
			}
		}

		System.out.println("Invalid username or password. Please try again.");
	}

	// CustomerMenu

	private static void customerMenu(Scanner sc) {

		Customer customer = (Customer) currentUser;

		System.out.println("\nCustomer Menu:");
		System.out.println("1. View Products");
		System.out.println("2. Add to Cart");
		System.out.println("3. Remove from Cart");
		System.out.println("4. View Cart");
		System.out.println("5. Filter by Keyword");
		System.out.println("6. Filter by Category");
		System.out.println("7. Enter Discount Code");
		System.out.println("8. Proceed to payment");
		System.out.println("9. Show Order History");
		System.out.println("10. Give rating");
		System.out.println("11. Logout");

		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			displayInventory();
			break;
		case 2:
			displayInventory();
			((Customer) currentUser).addToCart(inventory);
			break;

		case 3:
			System.out.print("Product name to get removed: ");
			String productName = sc.nextLine();
			customer.removeFromCart(productName, inventory);
		case 4:
			customer.viewCart();
			break;
		case 5:
			searchProducts(sc);
			break;
		case 6:
			filterByCategory(sc);
			break;
		case 7:
			getDiscount(sc);
			break;
		case 8:

			boolean isValid = paymentProcess();
			if (isValid) {
				trackAndUpdateInventory();
				customer.getCart().getItems().clear();

			}
			break;

		case 9:

			customer.getHistory().showOrderHistory();
			break;

		case 10:
			displayInventory();
			customer.rateProduct(inventory);
			break;

		case 11:
			initializeQuantity();
			customer.setCart(newCart);
			currentUser = null;
			System.out.println("Logout successful.");
			break;

		default:
			System.out.println("Invalid choice. Please try again.");
		}
	}

	// AdminMenu

	private static void adminMenu(Scanner sc) {
		Admin admin = (Admin) currentUser;

		System.out.println("\nAdmin Menu:");
		System.out.println("1. View Inventory");
		System.out.println("2. Add Product to Inventory");
		System.out.println("3. Update Product in Inventory");
		System.out.println("4. Delete Product from Inventory");
		System.out.println("5. Logout");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			displayInventory();
			break;
		case 2:
			admin.addProductToInventory(inventory);
			break;
		case 3:
			displayInventory();
			admin.updateProductInInventory(inventory);
			break;
		case 4:
			displayInventory();
			admin.deleteProductFromInventory(inventory);
			break;
		case 5:
			currentUser = null;
			System.out.println("Logout successful.");
			break;
		default:
			System.out.println("Invalid choice. Please try again.");
		}
	}

	// Display the Products

	private static void displayInventory() {

		System.out.println("\nInventory:");
		for (int i = 0; i < inventory.size(); i++) {
			Product product = inventory.get(i);
			System.out.println(
					(i + 1) + ". " + product.toString() + ", Remaining Quantity: " + product.getRemainingQuantity()
							+ ")" + "     Rating:" + product.calculateAverageRating() + " " + product.starsRating());

		}
	}

	// Dynamic Search

	private static void filterByCategory(Scanner sc) {

		System.out.println("\nFilter by Category:");
		System.out.println("1. Electronics\n2. Furniture\n3. Clothing");
		System.out.print("Enter category choice: ");
		int categoryChoice = sc.nextInt();

		String selectedCategory = null;

		switch (categoryChoice) {
		case 1:
			selectedCategory = "Electronics";
			break;
		case 2:
			selectedCategory = "Furniture";
			break;
		case 3:
			selectedCategory = "Clothing";
			break;
		default:
			System.out.println("Invalid category choice.");
			return;
		}

		System.out.println("\nProducts in the " + selectedCategory + " category:");
		for (Product product : inventory) {
			if (product.getCategory().equals(selectedCategory)) {
				System.out.println(
						product.getName() + " - $" + product.getPrice() + " (Quantity: " + product.getQuantity() + ")");
			}
		}

	}

	private static void searchProducts(Scanner sc) {
		System.out.print("Enter search keyword: ");
		String keyword = sc.next().toLowerCase();

		List<Product> searchResults = new ArrayList<Product>();

		for (Product product : inventory) {
			if (product.getName().toLowerCase().contains(keyword)) {
				searchResults.add(product);
			}
		}

		if (searchResults.isEmpty()) {
			System.out.println("No products found matching the search criteria.");
		} else {
			System.out.println("\nSearch Results:");
			for (Product product : searchResults) {
				System.out.println(
						product.getName() + " : $" + product.getPrice() + " (Category: " + product.getCategory() + ")");
			}
		}
	}

	// Tracking the inventory

	private static void trackAndUpdateInventory() {
		for (Product product : inventory) {
			product.setQuantity(product.getRemainingQuantity());
			if (product.getQuantity() == 0) {
				System.out.println("Warning: " + product.getName() + " is out of stock.");
			} else if (product.getQuantity() < 5) {
				System.out.println(
						"Alert: Low stock for " + product.getName() + ". Remaining quantity: " + product.getQuantity());
			}

		}
	}

	private static void initializeQuantity() {

		for (Product product : inventory) {
			product.setRemainingQuantity(product.getQuantity());
		}
	}

	// Discount Process

	private static void getDiscount(Scanner sc) {
		System.out.print(" Enter your discount code: ");
		int code = sc.nextInt();
		boolean p = false;
		for (int i : codes) {
			if (code == i) {
				p = !p;
				((Customer) currentUser).setDiscountCode(true);
				break;
			}
		}

		if (p) {
			System.out.println(
					"Congratulations!, Discount Code Applied Successfully!\nEnjoy a 10% discount On All The Products");
		} else {
			System.out.println("Wrong Code");
		}

	}

	// Payment Process

	private static boolean paymentProcess() {
		boolean isValid = false;
		Customer customer = (Customer) currentUser;
		if (customer.getCart().getItems().isEmpty()) {
			System.out.println("Cart is Empty, Payment Cancelled");
			return isValid;
		} else {
			System.out.println("\nCheckout Summary:");
			for (Product item : customer.getCart().getItems()) {
				System.out.println(
						item.getName() + " - Quantity: " + item.getQuantity() + " - Subtotal: $" + item.getSubtotal());

			}
			if (customer.getDiscountCode()) {
				System.out.println("Amount to pay after Discount : " + customer.getCart().calculateTotal(true));
			} else {
				System.out.println("Amount to pay : " + customer.getCart().calculateTotal(false));
			}
			double amount = customer.getCart().calculateTotal(customer.getDiscountCode());
			Payment paymentStrategy = choosePaymentMethod(amount);
			if (paymentStrategy != null) {
				ArrayList<Product> temp = new ArrayList<>(customer.getCart().getItems());
				customer.getHistory().updateOrderHistory(temp,
						customer.getCart().calculateTotal(customer.getDiscountCode()));
				isValid = true;
				paymentStrategy.pay(amount);

			}
			return isValid;

		}
	}

	private static Payment choosePaymentMethod(double amountToPay) {

		System.out.println("\nChoose a payment method:");
		System.out.println("1. Credit Card");
		System.out.println("2. PayPal");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		switch (choice) {

		case 1:

			System.out.println("\nEnter Credit Card details:");
			System.out.print("Card Number: ");
			String cardNumber = sc.next();
			System.out.print("Expiration Date: ");
			String expirationDate = sc.next();
			System.out.print("CVV: ");
			String cvv = sc.next();
			return new CreditCard(cardNumber, expirationDate, cvv);

		case 2:

			System.out.println("\nEnter PayPal details:");
			System.out.print("PayPal Email: ");
			String email = sc.next();
			return new PayPal(email);

		default:

			System.out.println("Invalid choice. Payment canceled.");
			return null;
		}
	}

}
