package library;

public class ProfileCreditLibs {
	String credit;
	int customerID;

	public ProfileCreditLibs() {
		this.credit = "";
		this.customerID = 0;
	}

	public ProfileCreditLibs(String credit, int customerID) {
		this.credit = credit;
		this.customerID = customerID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "ProfileCredit [credit=" + credit + ", customerID=" + customerID + "]";
	}

}
