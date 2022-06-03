package library;

public class UpdateLibs {
	int bookingID, roomNo;

	public UpdateLibs() {
		this.bookingID = 0;
		this.roomNo = 0;
	}

	public UpdateLibs(int bookingID, int roomNo) {
		this.bookingID = bookingID;
		this.roomNo = roomNo;
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
		return "UpdateLibs [bookingID=" + bookingID + ", roomNo=" + roomNo + "]";
	}

}
