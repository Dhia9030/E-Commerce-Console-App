package ECommerceApp;

import java.util.*;

public class Admin extends User {

	Scanner sc = new Scanner(System.in);

	public Admin(String username, String password) {
		super(username, password, true);
	}

	public void addProductToInventory(List<Product> inventory) {

		System.out.print("Enter product name: ");
		String name = sc.nextLine();
		System.out.print("Enter product price: ");
		double price = sc.nextDouble();
		System.out.print("Enter product quantity: ");
		int quantity = sc.nextInt();
		System.out.println("Select category: ");
		System.out.println("1. Electronics");
		System.out.println("2. Furniture");
		System.out.println("3. Clothing");
		int categoryChoice = sc.nextInt();

		switch (categoryChoice) {
		case 1:
			inventory.add(new Electronics(name, price, quantity));
			break;
		case 2:
			inventory.add(new Furniture(name, price, quantity));
			break;
		case 3:
			inventory.add(new Clothing(name, price, quantity));
			break;
		default:
			System.out.println("Invalid category choice.");
			break;
		}
		System.out.println(name + " added to the inventory.");
	}

	public void updateProductInInventory(List<Product> inventory) {

		System.out.print("Enter the product NUMBER to update: ");
		int productNumber = sc.nextInt();

		if (isValidProductNumber(productNumber, inventory)) {
			System.out.print("Enter new product name: ");
			String name = sc.next();
			System.out.print("Enter new product price: ");
			double price = sc.nextDouble();
			System.out.print("Enter new product quantity: ");
			int quantity = sc.nextInt();
			System.out.println("Select category: ");
			System.out.println("1. Electronics");
			System.out.println("2. Furniture");
			System.out.println("3. Clothing");
			int categoryChoice = sc.nextInt();

			switch (categoryChoice) {
			case 1:
				inventory.set(productNumber - 1, new Electronics(name, price, quantity));
				break;
			case 2:
				inventory.set(productNumber - 1, new Furniture(name, price, quantity));
				break;
			case 3:
				inventory.set(productNumber - 1, new Clothing(name, price, quantity));
				break;
			default:
				System.out.println("Invalid category choice.");
				break;
			}
			System.out.println("Product updated successfully.");
		} else {
			System.out.println("Invalid product number.");
		}

	}

	public void deleteProductFromInventory(List<Product> inventory) {

		System.out.print("Enter the product number to delete: ");
		int productNumber = sc.nextInt();

		if (this.isValidProductNumber(productNumber, inventory)) {
			Product deletedProduct = inventory.remove(productNumber - 1);
			System.out.println(deletedProduct.getName() + " deleted from the inventory.");
		} else {
			System.out.println("Invalid product number.");
		}
	}

	private boolean isValidProductNumber(int productNumber, List<Product> inventory) {
		return productNumber >= 1 && productNumber <= inventory.size();
	}

}
