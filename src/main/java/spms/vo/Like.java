package spms.vo;

public class Like {
	protected int index;
	protected int indexMovie;
	protected int indexCustomer;
	protected int count;
	
	public int getIndex() {
		return index;
	}
	public Like setIndex(int index) {
		this.index = index;
		return this;
	}
	public int getIndexMovie() {
		return indexMovie;
	}
	public Like setIndexMovie(int indexMovie) {
		this.indexMovie = indexMovie;
		return this;
	}
	public int getIndexCustomer() {
		return indexCustomer;
	}
	public Like setIndexCustomer(int indexCustomer) {
		this.indexCustomer = indexCustomer;
		return this;
	}
	public int getCount() {
		return count;
	}
	public Like setCount(int count) {
		this.count = count;
		return this;
	}
	
}
