package spms.vo;

public class Review {
	protected int index;
	protected double rating;
	protected String text;
	protected String date;
	protected int indexMovie;
	protected int indexCustomer;
	protected String name;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIndexMovie() {
		return indexMovie;
	}
	public void setIndexMovie(int indexMovie) {
		this.indexMovie = indexMovie;
	}
	public int getIndexCustomer() {
		return indexCustomer;
	}
	public void setIndexCustomer(int indexCustomer) {
		this.indexCustomer = indexCustomer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
