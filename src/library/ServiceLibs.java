package library;

public class ServiceLibs {
	int bookingID, roomNo, serviceID;
	String date, customerFname, customerLname, arrivalDate, serviceType, serviceName;
	double rate;

	public ServiceLibs() {
		this.bookingID = 0;
		this.roomNo = 0;
		this.serviceID = 0;
		this.date = "";
		this.customerFname = "";
		this.customerLname = "";
		this.arrivalDate = "";
		this.serviceType = "";
		this.serviceName = "";
		this.rate = 0.0;

	}

	public ServiceLibs(int bookingID, int roomNo, int quantity, int serviceID, String date, String customerFname,
			String customerLname, String arrivalDate, String serviceType, String serviceName, double rate) {
		super();
		this.bookingID = bookingID;
		this.roomNo = roomNo;
		this.serviceID = serviceID;
		this.date = date;
		this.customerFname = customerFname;
		this.customerLname = customerLname;
		this.arrivalDate = arrivalDate;
		this.serviceType = serviceType;
		this.serviceName = serviceName;
		this.rate = rate;
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

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "ServiceLibs [bookingID=" + bookingID + ", roomNo=" + roomNo + ", serviceID=" + serviceID + ", date="
				+ date + ", customerFname=" + customerFname + ", customerLname=" + customerLname + ", arrivalDate="
				+ arrivalDate + ", serviceType=" + serviceType + ", serviceName=" + serviceName + ", rate=" + rate
				+ "]";
	}

}