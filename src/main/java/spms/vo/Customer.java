package spms.vo;

public class Customer {
	protected int index;
	protected String email;
	protected String password;
	protected String name;
	protected String birth;
	protected String phonenum;
	protected String zipcode;
	protected String address;
	protected String detailaddress;
	protected int access;
	protected String token;
	
	public int getIndex() {
		return index;
	}
	public Customer setIndex(int index) {
		this.index = index;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Customer setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Customer setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getName() {
		return name;
	}
	public Customer setName(String name) {
		this.name = name;
		return this;
	}
	public String getBirth() {
		return birth;
	}
	public Customer setBirth(String birth) {
		this.birth = birth;
		return this;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public Customer setPhonenum(String phonenum) {
		this.phonenum = phonenum;
		return this;
	}
	public String getZipcode() {
		return zipcode;
	}
	public Customer setZipcode(String zipcode) {
		this.zipcode = zipcode;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public Customer setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public Customer setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
		return this;
	}
	public int getAccess() {
		return access;
	}
	public Customer setAccess(int access) {
		this.access = access;
		return this;
	}
	public String getToken() {
		return token;
	}
	public Customer setToken(String token) {
		this.token = token;
		return this;
	}

}
