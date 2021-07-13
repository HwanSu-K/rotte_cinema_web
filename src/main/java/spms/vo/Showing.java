package spms.vo;

public class Showing {
	protected int index;
	protected int indexMovie;
	protected String startTime;
	protected String date;
	protected int indexTheater;
	
	public int getIndex() {
		return index;
	}
	public Showing setIndex(int index) {
		this.index = index;
		return this;
	}
	public int getIndexMovie() {
		return indexMovie;
	}
	public Showing setIndexMovie(int indexMovie) {
		this.indexMovie = indexMovie;
		return this;
	}
	public String getStartTime() {
		return startTime;
	}
	public Showing setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}
	public String getDate() {
		return date;
	}
	public Showing setDate(String date) {
		this.date = date;
		return this;
	}
	public int getIndexTheater() {
		return indexTheater;
	}
	public Showing setIndexTheater(int indexTheater) {
		this.indexTheater = indexTheater;
		return this;
	}
	
	
}
