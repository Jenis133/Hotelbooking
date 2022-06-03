//package name
package library;

//main class
public class NCbookLibs {
	
	//declaration
	int bookingID, customerID, roomNo, tmpRoomNo;
	String fname, lname, email, address, arrivalDate, depatureDate, roomType, bookingStatus, creditCard, role;

	//contructor
	public NCbookLibs() {
		this.bookingID = 0;
		this.customerID = 0;
		this.roomNo = 0;
		this.fname = "";
		this.lname = "";
		this.email = "";
		this.address = "";
		this.arrivalDate = "";
		this.depatureDate = "";
		this.roomType = "";
		this.bookingStatus = "";
		this.creditCard = "";
		this.role = "";
		this.tmpRoomNo = 0;
	}

	//parameterized constructor
	public NCbookLibs(int bookingID, int customerID, int roomNo, String fname, String lname, String email,
			String address, String arrivalDate, String depatureDate, String roomType, String bookingStatus,
			String creditCard, String role) {
		this.bookingID = bookingID;
		this.customerID = customerID;
		this.roomNo = roomNo;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.address = address;
		this.arrivalDate = arrivalDate;
		this.depatureDate = depatureDate;
		this.roomType = roomType;
		this.bookingStatus = bookingStatus;
		this.creditCard = creditCard;
		this.role = role;
	}

	//setter gette
	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
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

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepatureDate() {
		return depatureDate;
	}

	public void setDepatureDate(String depatureDate) {
		this.depatureDate = depatureDate;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getTmpRoomNo() {
		return tmpRoomNo;
	}

	public void setTmpRoomNo(int tmpRoomNo) {
		this.tmpRoomNo = tmpRoomNo;
	}

	@Override
	public String toString() {
		return "NCbook [bookingID=" + bookingID + ", customerID=" + customerID + ", roomNo=" + roomNo + ", fname="
				+ fname + ", lname=" + lname + ", email=" + email + ", address=" + address + ", arrivalDate="
				+ arrivalDate + ", depatureDate=" + depatureDate + ", roomType=" + roomType + ", bookingStatus="
				+ bookingStatus + ", creditCard=" + creditCard + ", role=" + role + "]";
	}

}
