package ECommerceApp;

public class Rating {
	private String username;
	private double rating;

	public Rating(String username, double rating) {
		this.username = username;
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public double getRating() {
		return rating;
	}
}
