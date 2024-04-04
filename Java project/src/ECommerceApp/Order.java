package ECommerceApp;

import java.util.*;

public class Order {

	private ArrayList<ArrayList<Product>> orderLists;
	private ArrayList<Double> payedAmounts;

	public Order() {
		orderLists = new ArrayList<ArrayList<Product>>();
		payedAmounts = new ArrayList<Double>();
	}

	public ArrayList<ArrayList<Product>> getOrderLists() {

		return orderLists;
	}

	public ArrayList<Double> getPayedAmounts() {

		return payedAmounts;
	}

	public void updateOrderHistory(ArrayList<Product> newList, Double newAmount) {

		orderLists.add(newList);
		payedAmounts.add(newAmount);

	}

	public void showOrderHistory() {
		ArrayList<Product> list;
		if (orderLists.size() > 0) {
			for (int i = 0; i < orderLists.size(); i++) {
				System.out.println("\nOrder #" + (i + 1));
				System.out.println("_______________________________");
				list = orderLists.get(i);
				for (Product item : list) {
					System.out.println(item.toString());
				}

				System.out.println("\nTotal: $ " + payedAmounts.get(i));
				System.out.println("_______________________________");
			}
		} else {
			System.out.println(" No previous Purchases ");

		}
	}
}