package spms.vo;

public class ReservItem {
	protected int indexMovie;
	protected String movie;
	protected String date;
	protected String cinema;
	protected String theater;
	protected String customer;
	protected String seat;
	protected String poster;
	protected String age;
	
	public int getIndexMovie() {
		return indexMovie;
	}
	public ReservItem setIndexMovie(int indexMovie) {
		this.indexMovie = indexMovie;
		return this;
	}
	public String getMovie() {
		return movie;
	}
	public ReservItem setMovie(String movieTitle) {
		this.movie = movieTitle;
		return this;
	}
	public String getDate() {
		return date;
	}
	public ReservItem setDate(String date) {
		this.date = date;
		return this;
	}
	public String getCinema() {
		return cinema;
	}
	public ReservItem setCinema(String cinema) {
		this.cinema = cinema;
		return this;
	}
	public String getTheater() {
		return theater;
	}
	public ReservItem setTheater(String theater) {
		this.theater = theater;
		return this;
	}
	public String getCustomer() {
		return customer;
	}
	public ReservItem setCustomer(String customer) {
		this.customer = customer;
		return this;
	}
	public String getSeat() {
		return seat;
	}
	public ReservItem setSeat(String seat) {
		this.seat = seat;
		return this;
	}
	public String getPoster() {
		return poster;
	}
	public ReservItem setPoster(String poster) {
		this.poster = poster;
		return this;
	}
	public String getAge() {
		return age;
	}
	public ReservItem setAge(String age) {
		this.age = age;
		return this;
	}
}
