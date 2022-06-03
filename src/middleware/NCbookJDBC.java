package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.Database;
import library.NCbookLibs;
import library.UpdateLibs;

public class NCbookJDBC extends Database {
	// global variable
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;
	ArrayList<NCbookLibs> ncBook = new ArrayList<NCbookLibs>();

	// to get pending list of customers in receiptionist window
	public ArrayList<NCbookLibs> getPending() {

		ArrayList<NCbookLibs> arrayBooking = new ArrayList<NCbookLibs>();
		String sql = "SELECT b.bookingID, u.regID, u.fname , u.lname , u.email, u.address, u.creditCardInfo, b.arrivalDate, b.depatureDate , b.prefferedRoom, b.bookingStatus, b.roomNo, u.role\r\n"
				+ "FROM bookings as b\r\n" + "INNER JOIN users u ON\r\n" + "u.regID = b.regID\r\n"
				+ "WHERE b.bookingStatus = 'Pending' ";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				NCbookLibs book = new NCbookLibs(rs.getInt("bookingID"), rs.getInt("regID"), rs.getInt("roomNo"),
						rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("address"),
						rs.getString("arrivalDate"), rs.getString("depatureDate"), rs.getString("prefferedRoom"),
						rs.getString("bookingStatus"), rs.getString("creditCardInfo"), rs.getString("role"));
				arrayBooking.add(book);
			}

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
		return arrayBooking;
	}

	// to get all check in data
	
//	// to get booked status of customer in receiptionist window
//	public ArrayList<NCbookLibs> getBooked() {
//
//	
//		ArrayList<NCbookLibs> arrayCheckin = new ArrayList<NCbookLibs>();
//		String sql = "SELECT b.bookingID, u.regID, u.fname , u.lname , u.email, u.address, u.creditCardInfo, b.arrivalDate, b.depatureDate , b.prefferedRoom, b.bookingStatus, b.roomNo, u.role\r\n"
//				+ "FROM bookings as b\r\n" + "INNER JOIN users u ON\r\n" + "u.regID = b.regID\r\n"
//				+ "WHERE b.bookingStatus = 'Booked'  ";
//		try {
//			conn = connect();
//			pstat = conn.prepareStatement(sql);
//			rs = pstat.executeQuery();
//			while (rs.next()) {
//				NCbookLibs book = new NCbookLibs(rs.getInt("bookingID"), rs.getInt("regID"), rs.getInt("roomNo"),
//						rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("address"),
//						rs.getString("arrivalDate"), rs.getString("depatureDate"), rs.getString("prefferedRoom"),
//						rs.getString("bookingStatus"), rs.getString("creditCardInfo"), rs.getString("role"));
//				arrayCheckin.add(book);
//			}
//
//		} catch (Exception ex) {
//			System.out.println("ERROR : " + ex.getMessage());
//		}
//		return arrayCheckin;
//	}

	// to get booking status as booked of customer in receptionist window CURRENT DATE start
	public ArrayList<NCbookLibs> getBooked() {

		String date = LocalDate.now().toString();
		ArrayList<NCbookLibs> arrayCheckin = new ArrayList<NCbookLibs>();
		String sql = "SELECT b.bookingID, u.regID, u.fname , u.lname , u.email, u.address, u.creditCardInfo,"
				+ " b.arrivalDate, b.depatureDate , " + "b.prefferedRoom, b.bookingStatus, b.roomNo, u.role\r\n"
				+ "FROM bookings as b\r\n" + "INNER JOIN users u ON\r\n" + "u.regID = b.regID\r\n"
				+ "WHERE b.bookingStatus = 'Booked' and b.arrivalDate =?  ";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, date);
			rs = pstat.executeQuery();
			while (rs.next()) {
				NCbookLibs book = new NCbookLibs(rs.getInt("bookingID"), rs.getInt("regID"), rs.getInt("roomNo"),
						rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("address"),
						rs.getString("arrivalDate"), rs.getString("depatureDate"), rs.getString("prefferedRoom"),
						rs.getString("bookingStatus"), rs.getString("creditCardInfo"), rs.getString("role"));
				arrayCheckin.add(book);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());
		}
		return arrayCheckin;
	}
	// to get booking status as booked of customer in receptionist window CURRENT DATE end

	// to get checked in data of customers in receiptionist window
	public ArrayList<NCbookLibs> getCheckedIn() {

		ArrayList<NCbookLibs> arrayCheckin = new ArrayList<NCbookLibs>();
		String sql = "SELECT b.bookingID, u.regID, u.fname , u.lname , u.email, u.address, b.arrivalDate, b.depatureDate , b.prefferedRoom, b.bookingStatus, b.roomNo, u.creditCardInfo, u.role\r\n"
				+ "FROM bookings as b\r\n" + "INNER JOIN users u ON\r\n" + "u.regID = b.regID\r\n"
				+ "WHERE b.bookingStatus = 'Checked in' ";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				NCbookLibs book = new NCbookLibs(rs.getInt("bookingID"), rs.getInt("regID"), rs.getInt("roomNo"),
						rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("address"),
						rs.getString("arrivalDate"), rs.getString("depatureDate"), rs.getString("prefferedRoom"),
						rs.getString("bookingStatus"), rs.getString("creditCardInfo"), rs.getString("role"));
				arrayCheckin.add(book);
			}

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
		return arrayCheckin;
	}

	// to get available roomNo of particular room type
	public ArrayList getRoomNo(String type) {

		ArrayList arrayRoomNo = new ArrayList();
		String sql = "SELECT roomNo FROM rooms WHERE roomType=? AND roomStatus='Available'";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, type);
			rs = pstat.executeQuery();
			while (rs.next()) {
				String roomN = rs.getString("roomNo");
				arrayRoomNo.add(roomN);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return arrayRoomNo;
	}

	// to update bookings -> roomNo and bookingStatus
	// to update rooms -> roomstatus
	public boolean update(UpdateLibs updateLibs) {
		String sql1 = "UPDATE bookings \r\n" + "SET roomNo= ?,\r\n" + "bookingStatus = 'Booked'\r\n" + "WHERE\r\n"
				+ "bookingID = ?;";
		String sql2 = "UPDATE rooms\r\n" + "SET roomStatus = 'Booked'\r\n" + "WHERE\r\n" + "roomNo= ?;";
		boolean res = false;
		try {

			// update customer's booking status
			conn = connect();
			pstat = conn.prepareStatement(sql1);
			pstat.setInt(1, updateLibs.getRoomNo());
			pstat.setInt(2, updateLibs.getBookingID());
			pstat.executeUpdate(); // update delete insert

			pstat.close();
			conn.close();

			// update room status
			conn = connect();
			pstat = conn.prepareStatement(sql2);
			pstat.setInt(1, updateLibs.getRoomNo());
			pstat.executeUpdate();
			pstat.close();
			conn.close();

			res = true;
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
		}
		return res;
	}

	// to update booking status to not available
	public boolean notAvailable(UpdateLibs updateLibs) {
		String sql1 = "UPDATE bookings \r\n" + "SET bookingStatus = 'Not Guaranteed, Room Not Available'\r\n" + "WHERE\r\n"

				+ "bookingID = ?;";
		boolean res = false;
		try {
			// update customer's booking status
			conn = connect();
			pstat = conn.prepareStatement(sql1);
			pstat.setInt(1, updateLibs.getBookingID());
			pstat.executeUpdate(); // update delete insert
			pstat.close();
			conn.close();
			res = true;
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
		}
		return res;
	}

	public boolean checkOut(NCbookLibs booklibs) {
		String sql1 = "update bookings SET bookingStatus = 'Complete', roomNo = 0 WHERE bookingID = ? ";
		String sql2 = "update rooms SET roomStatus = 'Available' where roomNo= ?";
		boolean res = false;
		try {
			// update customer's booking status
			conn = connect();
			pstat = conn.prepareStatement(sql1);
			pstat.setInt(1, booklibs.getBookingID());
			pstat.executeUpdate(); // update delete insert
			pstat.close();
			conn.close();

			// update room's status to available
			conn = connect();
			pstat = conn.prepareStatement(sql2);
			pstat.setInt(1, booklibs.getRoomNo());
			pstat.executeUpdate(); // update delete insert
			pstat.close();
			conn.close();

			res = true;
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
		}
		return res;
	}

	// to update booking status to not guaranteed
	public boolean notGuaranteed(UpdateLibs updateLibs) {
		String sql1 = "UPDATE bookings \r\n" + "SET bookingStatus = 'Not Guaranteed, provide creditcard'\r\n"
				+ "WHERE\r\n"

				+ "bookingID = ?;";
		boolean res = false;
		try {
			// update customer's booking status
			conn = connect();
			pstat = conn.prepareStatement(sql1);
			pstat.setInt(1, updateLibs.getBookingID());
			pstat.executeUpdate(); // update delete insert
			pstat.close();
			conn.close();
			res = true;
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
		}
		return res;
	}

	
	// to get checked in data of customers in receiptionist window
		public ArrayList<NCbookLibs> getCheckedOut() {

			ArrayList<NCbookLibs> arrayCheckin = new ArrayList<NCbookLibs>();
			String sql = "SELECT b.bookingID, u.regID, u.fname , u.lname , u.email, u.address, b.arrivalDate, b.depatureDate , b.prefferedRoom, b.bookingStatus, b.roomNo, u.creditCardInfo, u.role\r\n"
					+ "FROM bookings as b\r\n" + "INNER JOIN users u ON\r\n" + "u.regID = b.regID\r\n"
					+ "WHERE b.bookingStatus = 'Complete' ";

			try {
				conn = connect();
				pstat = conn.prepareStatement(sql);
				rs = pstat.executeQuery();
				while (rs.next()) {
					NCbookLibs book = new NCbookLibs(rs.getInt("bookingID"), rs.getInt("regID"), rs.getInt("roomNo"),
							rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("address"),
							rs.getString("arrivalDate"), rs.getString("depatureDate"), rs.getString("prefferedRoom"),
							rs.getString("bookingStatus"), rs.getString("creditCardInfo"), rs.getString("role"));
					arrayCheckin.add(book);
				}

			} catch (Exception ex) {
				System.out.println("ERROR : " + ex.getMessage());
			}
			return arrayCheckin;
		}
		
		public boolean checkin(NCbookLibs booklibs) {
			String sql1 = "update bookings SET bookingStatus = 'Checked in', roomNo = ? WHERE bookingID = ? ";
			String sql2 = "update rooms SET roomStatus = 'Available' where roomNo= ?";
			String sql3 = "update rooms SET roomStatus = 'Checked in' where roomNo=?";
			boolean res = false;
			try {
				// update customer's booking status
				conn = connect();
				pstat = conn.prepareStatement(sql1);
				pstat.setInt(1, booklibs.getRoomNo());
				pstat.setInt(2, booklibs.getBookingID());
				pstat.executeUpdate(); // update delete insert
				pstat.close();
				conn.close();

				// update room's status to available
				conn = connect();
				pstat = conn.prepareStatement(sql2);
				pstat.setInt(1, booklibs.getTmpRoomNo());
				pstat.executeUpdate(); // update delete insert
				pstat.close();
				conn.close();

				// update room's status to Checked in
				conn = connect();
				pstat = conn.prepareStatement(sql3);
				pstat.setInt(1, booklibs.getRoomNo());
				pstat.executeUpdate(); // update delete insert
				pstat.close();
				conn.close();

				res = true;
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
			}
			return res;
		}
	
}
