//package name
package middleware;

//import libraries
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.Database;
import library.BookingLibs;
import library.NCorpLibs;

//main class
public class BookingJDBC extends Database {

	public boolean credit(NCorpLibs ncorps) {
		boolean res = false;
		String sql = "INSERT INTO users(creditCardInfo) VALUES (?)";
		Connection conn;
		PreparedStatement pstat;
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ncorps.getCreditCardInfo());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			res = true;

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "COULD NOT BOOK A ROOM" + ex.getMessage());

		}
		return res;
	}

	// save booking
	public boolean save(BookingLibs book) {
		boolean res = false;
		String sql = "INSERT INTO bookings(arrivalDate ,depatureDate, prefferedRoom, bookingStatus, regID) VALUES (?,?,?,?,?)";
		Connection conn;
		PreparedStatement pstat;
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, book.getArrivalDate());
			pstat.setString(2, book.getDepatureDate());
			pstat.setString(3, book.getPrefferedRoom());
			pstat.setString(4, book.getBookingStatus());
			pstat.setInt(5, book.getRegID());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			res = true;

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "COULD NOT BOOK A ROOM");

		}
		return res;
	}

	public boolean update(BookingLibs book) {

		boolean res = false;
		String sql = "UPDATE bookings SET arrivalDate =? ,depatureDate = ?, prefferedRoom= ?,bookingStatus=?, roomNo = 0 WHERE bookingID =?";
		String sql2 = "UPDATE rooms SET roomStatus= 'Available' WHERE roomNo = ?  ";
		Connection conn;
		PreparedStatement pstat;
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, book.getArrivalDate());
			pstat.setString(2, book.getDepatureDate());
			pstat.setString(3, book.getPrefferedRoom());
			pstat.setString(4, "Pending");
			pstat.setInt(5, book.getBookingID());
			pstat.executeUpdate();
			pstat.close();
			conn.close();

			conn = connect();
			pstat = conn.prepareStatement(sql2);
			pstat.setInt(1, book.getRoomNo());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			res = true;

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());

		}
		return res;
	}

	public boolean delete(BookingLibs bookingLibs) {
		Connection conn;
		PreparedStatement pstat;
		String sql = "DELETE FROM bookings WHERE bookingID = ?";
		boolean result = false;
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bookingLibs.getBookingID());
			pstat.executeUpdate(); // 5. running sql statement
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());
		}
		return result;
	}

	public ArrayList<BookingLibs> getBookings(int customerID) {
		ArrayList<BookingLibs> bookings = new ArrayList<BookingLibs>();
		String sql = "SELECT * FROM bookings WHERE regID = ?";
		Connection conn;
		PreparedStatement pstat;
		ResultSet rs;
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, customerID);
			rs = pstat.executeQuery();
			while (rs.next()) {
				BookingLibs book = new BookingLibs(rs.getString("arrivalDate"), rs.getString("depatureDate"),
						rs.getString("prefferedRoom"), rs.getString("bookingStatus"), rs.getInt("regID"),
						rs.getInt("bookingID"), rs.getInt("roomNo"));
				bookings.add(book);
			}

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
		return bookings;
	}

}
