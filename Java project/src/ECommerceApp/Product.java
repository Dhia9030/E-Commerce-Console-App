package ECommerceApp;

import java.util.*;

public abstract class Product {
	private String name;
	private double price;
	private int quantity;
	private int remainingQuantity;
	protected String category;

	ArrayList<Rating> ratings = new ArrayList<>();

	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		remainingQuantity = quantity;

	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		quantity = q;
	}

	public abstract String getCategory();

	public void incrementQuantity(int q) {
		quantity += q;
	}

	public int getRemainingQuantity() {

		return remainingQuantity;

	}

	public void setRemainingQuantity(int q) {

		remainingQuantity = q;

	}

	public void updateRemainingQuantity(int q) {
		remainingQuantity -= q;

	}

	public double getSubtotal() {

		return (quantity * price);

	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	public double calculateAverageRating() {
		if (ratings.isEmpty()) {
			return 0.0;
		}

		double sum = 0.0;
		for (Rating rating : ratings) {
			sum += rating.getRating();
		}
		return sum / ratings.size();

	}

	public void addRating(String username, double rating) {
		Rating r;
		if (rating >= 0.0 && rating <= 5.0) {

			for (Rating existingRating : ratings) {
				if (existingRating.getUsername() == username) {
					ratings.remove(existingRating);
					System.out.println("NEW RATING OF " + username + " " + rating);
				}
				ratings.add(new Rating(username, rating));
				return;

			}

			// If the user hasn't rated yet, add a new rating
			Rating newRating = new Rating(username, rating);
			ratings.add(newRating);
		} else {
			System.out.println("Invalid rating. Please provide a rating between 0 and 5");
		}
	}

	public String starsRating() {
		String r = "";
		for (int i = 0; i < Math.floor(this.calculateAverageRating()); i++) {
			r += "★";
		}
		double difference = this.calculateAverageRating() - Math.floor(this.calculateAverageRating());
		if (difference >= 0.3 && difference < 0.8) {
			r += "☆";
		} else {
			if (difference > 0.7) {
				r += "★";
			}
		}
		return r;
	}

	public String toString() {

		return (this.getName() + " - $" + this.getPrice() + " ,Category: " + this.getCategory() + ", Quantity: "
				+ this.getQuantity());
	}

}