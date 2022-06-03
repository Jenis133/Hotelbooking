package library;

public class ProfileLibs {
	int customerID;
	String fname;
	String lname;
	String email;
	String address;
	String credit;

	public ProfileLibs() {
		this.customerID = 0;
		this.fname = "";
		this.lname = "";
		this.email = "";
		this.address = "";
		this.credit = "";
	}

	public ProfileLibs(int customerID, String fname, String lname, String email, String address, String credit) {
		this.customerID = customerID;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.address = address;
		this.credit = credit;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "ProfileLibs [customerID=" + customerID + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", address=" + address + ", credit=" + credit + "]";
	}

//	public ProfileLibs() {
//		this.customerID = 0;
//		this.fname = "";
//		this.lname = "";
//		this.email = "";
//		this.address = "";
//	}
//
//	public ProfileLibs(int customerID, String fname, String lname, String email, String address) {
//		this.customerID = customerID;
//		this.fname = fname;
//		this.lname = lname;
//		this.email = email;
//		this.address = address;
//	}
//
//	public int getCustomerID() {
//		return customerID;
//	}
//
//	public void setCustomerID(int customerID) {
//		this.customerID = customerID;
//	}
//
//	public String getFname() {
//		return fname;
//	}
//
//	public void setFname(String fname) {
//		this.fname = fname;
//	}
//
//	public String getLname() {
//		return lname;
//	}
//
//	public void setLname(String lname) {
//		this.lname = lname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	@Override
//	public String toString() {
//		return "ProfileLibs [customerID=" + customerID + ", fname=" + fname + ", lname=" + lname + ", email=" + email
//				+ ", address=" + address + "]";
//	}

}
