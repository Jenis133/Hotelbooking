package library;

public class BookingLibs {
	String arrivalDate;
	String depatureDate;
	String prefferedRoom;
	String bookingStatus;
	int regID;
	int bookingID;
	int roomNo;

	public BookingLibs() {
		this.arrivalDate = "";
		this.depatureDate = "";
		this.prefferedRoom = "";
		this.bookingStatus = "Pending";
		this.regID = 0;
		this.bookingID = 0;
		this.roomNo = 0;
	}

	public BookingLibs(String arrivalDate, String depatureDate, String prefferedRoom, String bookingStatus, int regID,
			int bookingID, int roomNo) {
		this.arrivalDate = arrivalDate;
		this.depatureDate = depatureDate;
		this.prefferedRoom = prefferedRoom;
		this.bookingStatus = bookingStatus;
		this.regID = regID;
		this.bookingID = bookingID;
		this.roomNo = roomNo;
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

	public String getPrefferedRoom() {
		return prefferedRoom;
	}

	public void setPrefferedRoom(String prefferedRoom) {
		this.prefferedRoom = prefferedRoom;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getRegID() {
		return regID;
	}

	public void setRegID(int regID) {
		this.regID = regID;
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

	@Override
	public String toString() {
		return "BookingLibs [arrivalDate=" + arrivalDate + ", depatureDate=" + depatureDate + ", prefferedRoom="
				+ prefferedRoom + ", bookingStatus=" + bookingStatus + ", regID=" + regID + ", bookingID=" + bookingID
				+ ", roomNo=" + roomNo + "]";
	}

}