package spms.vo;

public class Reserv {
	protected int index;
	protected int seatX;
	protected int seatY;
	protected int payIndex;
	protected int payCategory;
	protected int showingIndex;
	
	public int getIndex() {
		return index;
	}
	public Reserv setIndex(int index) {
		this.index = index;
		return this;
	}
	public int getSeatX() {
		return seatX;
	}
	public Reserv setSeatX(int seatX) {
		this.seatX = seatX;
		return this;
	}
	public int getSeatY() {
		return seatY;
	}
	public Reserv setSeatY(int seatY) {
		this.seatY = seatY;
		return this;
	}
	public int getPayIndex() {
		return payIndex;
	}
	public Reserv setPayIndex(int payIndex) {
		this.payIndex = payIndex;
		return this;
	}
	public int getPayCategory() {
		return payCategory;
	}
	public Reserv setPayCategory(int payCategory) {
		this.payCategory = payCategory;
		return this;
	}
	public int getShowingIndex() {
		return showingIndex;
	}
	public void setShowingIndex(int showingIndex) {
		this.showingIndex = showingIndex;
	}
}
