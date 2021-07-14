package spms.vo;

import java.util.Date;

public class Pay {
	protected int index;
	protected Date date;
	protected int amount;
	protected String uid;
	protected int customerIndex;
	protected String cardName;
	protected String cardNumber;
	protected String push;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCustomerIndex() {
		return customerIndex;
	}
	public void setCustomerIndex(int customerIndex) {
		this.customerIndex = customerIndex;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPush() {
		return push;
	}
	public void setPush(String push) {
		this.push = push;
	}
}
