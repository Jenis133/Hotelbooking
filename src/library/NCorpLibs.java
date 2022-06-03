package library;

public class NCorpLibs {
	int nonCorpID;
	String fname;
	String lname;
	String address;
	String phoneNo;
	String email;
	String gender;
	String creditCardInfo;
	String uname;
	String pwd;
	String role;

	public NCorpLibs() {
		this.nonCorpID = 0;
		this.fname = "";
		this.lname = "";
		this.address = "";
		this.phoneNo = "";
		this.email = "";
		this.gender = "";
		this.creditCardInfo = "";
		this.uname = "";
		this.pwd = "";
		this.role = "Non Corporate";
	}

	public NCorpLibs(int nonCorpID, String fname, String lname, String address, String phoneNo, String email,
			String gender, String creditCardInfo, String uname, String pwd, String role) {
		this.nonCorpID = nonCorpID;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.gender = gender;
		this.creditCardInfo = creditCardInfo;
		this.uname = uname;
		this.pwd = pwd;

	}

	public int getNonCorpID() {
		return nonCorpID;
	}

	public void setNonCorpID(int nonCorpID) {
		this.nonCorpID = nonCorpID;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(String creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
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

	@Override
	public String toString() {
		return "NonCorps [nonCorpID=" + nonCorpID + ", fname=" + fname + ", lname=" + lname + ", address=" + address
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", gender=" + gender + ", creditCardInfo="
				+ creditCardInfo + ", uname=" + uname + ", pwd=" + pwd + ", role=" + role + "]";
	}

	

}