package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.Database;
import library.BarLibs;

public class BarJDBC extends Database {
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;

	// all the checked in room numbers which are in use.
	public ArrayList getOccupiedRoom() {
		ArrayList arrayOccupiedRoom = new ArrayList();
		String sql = "select roomNo from rooms where roomStatus ='Checked in'";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				int roomNumber = rs.getInt("roomNo");
				arrayOccupiedRoom.add(roomNumber);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());

		}
		return arrayOccupiedRoom;
	}

	// get customer data like name and arrival date
	public ArrayList<BarLibs> getCustomerDetails(BarLibs bar) {
		ArrayList<BarLibs> array = new ArrayList<BarLibs>();
		String sql = "select b.bookingID,u.regID,u.fname, u.lname, b.arrivalDate \r\n" + "from bookings as b \r\n"
				+ "inner join users as u \r\n" + "on b.regID = u.regID \r\n" + "where b.roomNo=?";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bar.getRoomNo());
			rs = pstat.executeQuery();

			while (rs.next()) {
				bar.setBookingID(rs.getInt("bookingID"));
				bar.setCustomerFname(rs.getString("fname"));
				bar.setCustomerLname(rs.getString("lname"));
				bar.setArrivalDate(rs.getString("ArrivalDate"));
				array.add(bar);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}
		return array;
	}

	// shows the bar item name start
	public ArrayList getBarItem() {
		ArrayList array = new ArrayList();
		String sql = "select item from menu where type = 'Bar'";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				String type = rs.getString("item");
				array.add(type);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}

		return array;
	}
	// shows the bar item name end

	// get menuID start
	public int getMenuID(BarLibs bar) {
		int menuID = 0;
		String sql = "SELECT id FROM menu WHERE item = ?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bar.getMenu());
			rs = pstat.executeQuery();
			while (rs.next()) {
				menuID = rs.getInt("id");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());

		}
		return menuID;
	}
	// get menuID end

	// get price of bar item start
	public double getBarPrice(BarLibs bar) {
		double rate = 0.0;
		String sql = "SELECT rate FROM menu WHERE item = ? AND type = 'Bar' ";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, bar.getMenu());
			rs = pstat.executeQuery();
			while (rs.next()) {
				rate = rs.getDouble("rate");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());

		}
		return rate;
	}
	// get price of bar item end

	// to calculate total start
	public void calculateTotal(BarLibs libs) {
		libs.setTotal(libs.getQuantity() * libs.getRate());
	}
	// to calculate total end

	// to insert bar start
	public boolean insertBar(BarLibs bar) {
		boolean result = false;
		String sql = "INSERT INTO restaurantBar(bookingID, date, menuID, quantity) VALUES ( ?, ?, ?, ? ) ";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bar.getBookingID());
			pstat.setString(2, bar.getDate());
			pstat.setInt(3, bar.getMenuID());
			pstat.setInt(4, bar.getQuantity());
			pstat.executeUpdate(); // INSERT UPDATE AND DELETE
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return result;
	}
	// to insert bar end

	// customer bar history
	public ArrayList<BarLibs> getBarHistory() {
		ArrayList<BarLibs> barHistory = new ArrayList<BarLibs>();
		String sql = "SELECT r.bookingID, r.date, u.fname, b.roomNo, m.item, m.rate,r.quantity\r\n"
				+ "FROM restaurantBar as r\r\n" + "inner join bookings as b\r\n" + "on r.bookingID = b.bookingID\r\n"
				+ "inner join users as u\r\n" + "on b.regID = u.regID\r\n" + "inner join menu as m\r\n"
				+ "on r.menuID =  m.id \r\n" + "WHERE m.type = 'Bar'";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				BarLibs libs = new BarLibs();
				libs.setBookingID(rs.getInt("bookingID"));
				libs.setDate(rs.getString("date"));
				libs.setCustomerFname(rs.getString("fname"));
				libs.setRoomNo(rs.getInt("roomNo"));
				libs.setMenu(rs.getString("item"));
				libs.setRate(rs.getDouble("rate"));
				libs.setQuantity(rs.getInt("quantity"));
				barHistory.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errorrr : " + e.getMessage());
		}
		return barHistory;
	}

}
