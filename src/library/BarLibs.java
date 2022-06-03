package library;

public class BarLibs {
	int bookingID, roomNo, quantity, menuID;
	String date, customerFname, customerLname, arrivalDate, menu;
	Double rate, total;
	
	public BarLibs() {
		this.bookingID = 0;
		this.roomNo = 0;
		this.quantity = 0;
		this.menuID = 0;
		this.date = "";
		this.customerFname = "";
		this.customerLname = "";
		this.arrivalDate = "";
		this.menu = "";
		this.rate = 0.0;
		this.total = 0.0;
	}
	
	public BarLibs(int bookingID, int roomNo, int quantity, int menuID, String date, String customerFname,
			String customerLname, String arrivalDate, String menu, Double rate, Double total) {
		this.bookingID = bookingID;
		this.roomNo = roomNo;
		this.quantity = quantity;
		this.menuID = menuID;
		this.date = date;
		this.customerFname = customerFname;
		this.customerLname = customerLname;
		this.arrivalDate = arrivalDate;
		this.menu = menu;
		this.rate = rate;
		this.total = total;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerFname() {
		return customerFname;
	}

	public void setCustomerFname(String customerFname) {
		this.customerFname = customerFname;
	}

	public String getCustomerLname() {
		return customerLname;
	}

	public void setCustomerLname(String customerLname) {
		this.customerLname = customerLname;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "BarLibs [bookingID=" + bookingID + ", roomNo=" + roomNo + ", quantity=" + quantity + ", menuID="
				+ menuID + ", date=" + date + ", customerFname=" + customerFname + ", customerLname=" + customerLname
				+ ", arrivalDate=" + arrivalDate + ", menu=" + menu + ", rate=" + rate + ", total=" + total + "]";
	}

	

	

}
