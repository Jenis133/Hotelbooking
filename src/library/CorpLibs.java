package library;

public class CorpLibs {
	// Variable declaration
	int corpID;
	String fname;
	String lname;
	String companyName;
	String phoneNo;
	String email;
	String address;
	String creditCard;
	String uname;
	String pwd;
	String role;

	// Default Constructor
	public CorpLibs() {
		this.corpID = 0;
		this.fname = "";
		this.lname = "";
		this.companyName = "";
		this.phoneNo = "";
		this.email = "";
		this.address = "";
		this.creditCard = "";
		this.uname = "";
		this.pwd = "";
		this.role = "Corporate";
	}

	// Parameterized Constructor
	public CorpLibs(int corpID, String fname, String lname, String companyName, String phoneNo, String email,
			String address, String creditCard, String uname, String pwd, String role) {
		this.corpID = corpID;
		this.fname = fname;
		this.lname = lname;
		this.companyName = companyName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.creditCard = creditCard;
		this.uname = uname;
		this.pwd = pwd;
		this.role = role;
	}

	public int getCorpID() {
		return corpID;
	}

	public void setCorpID(int corpID) {
		this.corpID = corpID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//convert to string 
	@Override
	public String toString() {
		return "CorpLibs [corpID=" + corpID + ", fname=" + fname + ", lname=" + lname + ", companyName=" + companyName
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", address=" + address + ", creditCard=" + creditCard
				+ ", uname=" + uname + ", pwd=" + pwd + ", role=" + role + "]";
	}



}
