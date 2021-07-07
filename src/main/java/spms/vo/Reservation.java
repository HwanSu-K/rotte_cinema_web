package spms.vo;

public class Reservation {
	protected int index;
	protected String startTime;
	protected String endTime;
	protected String date;
	protected String week;
	protected String theaterTitle;
	protected int theaterSeatX;
	protected int theaterSeatY;
	protected String cinemaTitle;
	protected String movieTitle;
	protected String moviePoster;
	protected int movieRunningTime;
	protected int movieLimitAge;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTheaterTitle() {
		return theaterTitle;
	}
	public void setTheaterTitle(String theaterTitle) {
		this.theaterTitle = theaterTitle;
	}
	public int getTheaterSeatX() {
		return theaterSeatX;
	}
	public void setTheaterSeatX(int theaterSeatX) {
		this.theaterSeatX = theaterSeatX;
	}
	public int getTheaterSeatY() {
		return theaterSeatY;
	}
	public void setTheaterSeatY(int theaterSeatY) {
		this.theaterSeatY = theaterSeatY;
	}
	public String getCinemaTitle() {
		return cinemaTitle;
	}
	public void setCinemaTitle(String cinemaTitle) {
		this.cinemaTitle = cinemaTitle;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMoviePoster() {
		return moviePoster;
	}
	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}
	public int getMovieRunningTime() {
		return movieRunningTime;
	}
	public void setMovieRunningTime(int movieRunningTime) {
		this.movieRunningTime = movieRunningTime;
	}
	public int getMovieLimitAge() {
		return movieLimitAge;
	}
	public void setMovieLimitAge(int movieLimitAge) {
		this.movieLimitAge = movieLimitAge;
	}
}
