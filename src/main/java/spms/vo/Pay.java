package spms.vo;

public class Pay {
	protected int index;
	protected int amount;
	protected String uid;
	protected int customerIndex;
	protected String cardName;
	protected String cardNumber;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
}
