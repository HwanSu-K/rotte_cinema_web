package spms.vo;

public class MovieDate {
	String date;
	String weekEng;
	String weekKor;
	String yearMonth;
	String day;
	
	
	public String getDate() {
		return date;
	}
	public MovieDate setDate(String date) {
		this.date = date;
		return this;
	}
	public String getWeekEng() {
		return weekEng;
	}
	public MovieDate setWeekEng(String weekEng) {
		this.weekEng = weekEng;
		return this;
	}
	public String getWeekKor() {
		return weekKor;
	}
	public MovieDate setWeekKor(String weekKor) {
		this.weekKor = weekKor;
		return this;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public MovieDate setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
		return this;
	}
	public String getDay() {
		return day;
	}
	public MovieDate setDay(String day) {
		this.day = day;
		return this;
	}
}
