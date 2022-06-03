package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.Database;
import library.RestaurantLibs;

public class RestaurantJDBC extends Database {
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
	public ArrayList<RestaurantLibs> getCustomerDetails(RestaurantLibs restaurant) {
		ArrayList<RestaurantLibs> array = new ArrayList<RestaurantLibs>();
		String sql = "select b.bookingID,u.regID,u.fname, u.lname, b.arrivalDate \r\n" + "from bookings as b \r\n"
				+ "inner join users as u \r\n" + "on b.regID = u.regID \r\n" + "where b.roomNo=?";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, restaurant.getRoomNo());
			rs = pstat.executeQuery();

			while (rs.next()) {
				restaurant.setBookingID(rs.getInt("bookingID"));// ENCAPSULATION
				restaurant.setCustomerFname(rs.getString("fname"));
				restaurant.setCustomerLname(rs.getString("lname"));
				restaurant.setArrivalDate(rs.getString("ArrivalDate"));
				array.add(restaurant);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}
		return array;
	}

	// shows the restaurant item name start
	public ArrayList getRestItemName() {
		ArrayList array = new ArrayList();
		String sql = "select item from menu where type = 'Restaurant'";

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
	// shows the restaurant item name end

	// get menuID start
	public int getMenuID(RestaurantLibs restaurant) {
		int menuID = 0;
		String sql = "SELECT id FROM menu WHERE item = ?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, restaurant.getMenu());
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

	// get price of restaurant item start
	public double getRestaurantPrice(RestaurantLibs restaurant) {
		double rate = 0.0;
		String sql = "SELECT rate FROM menu WHERE item = ? AND type = 'Restaurant' ";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, restaurant.getMenu());
			rs = pstat.executeQuery();
			while (rs.next()) {
				rate = rs.getDouble("rate");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());

		}
		return rate;
	}
	// get price of restaurant item end

	// to insert restaurant start
	public boolean insertRestaurant(RestaurantLibs restaurant) {
		boolean result = false;
		String sql = "INSERT INTO restaurantBar(bookingID, date, menuID, quantity) VALUES ( ?, ?, ?, ? ) ";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, restaurant.getBookingID());
			pstat.setString(2, restaurant.getDate());
			pstat.setInt(3, restaurant.getmenuID());
			pstat.setInt(4, restaurant.getQuantity());
			pstat.executeUpdate(); // INSERT UPDATE AND DELETE
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return result;
	}
	// to insert restaurant end

	public void calculateTotal(RestaurantLibs libs) {
		libs.setTotal(libs.getQuantity() * libs.getRate());
	}

	// customer restaurant history
	public ArrayList<RestaurantLibs> getRestaurantHistory() {
		ArrayList<RestaurantLibs> restaurantHistory = new ArrayList<RestaurantLibs>();
		String sql = "SELECT r.bookingID, r.date, u.fname, b.roomNo, m.item, m.rate,r.quantity\r\n"
				+ "FROM restaurantBar as r\r\n" + "inner join bookings as b\r\n" + "on r.bookingID = b.bookingID\r\n"
				+ "inner join users as u\r\n" + "on b.regID = u.regID\r\n" + "inner join menu as m\r\n"
				+ "on r.menuID =  m.id \r\n" + "WHERE m.type = 'Restaurant';";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				RestaurantLibs libs = new RestaurantLibs();
				libs.setBookingID(rs.getInt("bookingID"));
				libs.setDate(rs.getString("date"));
				libs.setCustomerFname(rs.getString("fname"));
				libs.setRoomNo(rs.getInt("roomNo"));
				libs.setMenu(rs.getString("item"));
				libs.setRate(rs.getDouble("rate"));
				libs.setQuantity(rs.getInt("quantity"));
				restaurantHistory.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errorrr : " + e.getMessage());
		}
		return restaurantHistory;
	}

}
