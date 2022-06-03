package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import connect.Database;
import library.InvoiceLibs;

public class InvoiceJDBC extends Database {
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;
	InvoiceLibs libs = new InvoiceLibs();

//done
	// Method to get room id from database
	public ArrayList showRoomNumber() {
		ArrayList invoice = new ArrayList();
		String sql = "SELECT r.roomNo from rooms as r inner join bookings as b\r\n"
				+ "on b.roomNo = r.roomNo WHERE r.roomstatus='Checked in';";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				invoice.add(rs.getInt("roomNo"));
			}

		} catch (Exception e) {

		}
		return invoice;
	}
	// Method to get room id from database end

//	done
	// Method to get customer data from database
	public ArrayList<InvoiceLibs> showCustomerDetails(int roomId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT b.bookingID,b.arrivalDate,u.fname\r\n" + "from rooms as r\r\n"
				+ "inner join bookings as b\r\n" + "on b.roomNo = r.roomNo\r\n" + "inner join users as u\r\n"
				+ "on b.regID = u.regID\r\n" + "WHERE b.roomNo=?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, roomId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				InvoiceLibs libs = new InvoiceLibs();
				libs.setBookingId(rs.getInt("bookingID"));
				libs.setCustName(rs.getString("fname"));
				libs.setCheckInDate(rs.getString("arrivalDate"));
				invoice.add(libs);
			}

		} catch (Exception e) {

		}
		return invoice;
	}
	// Method to get customer data from database end

//done
	// Method to get room details for the purpose of billing
	public ArrayList<InvoiceLibs> getRoomDetailBill(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT b.prefferedRoom, b.arrivalDate, r.roomPrice \r\n" + "from bookings as b \r\n"
				+ "inner join rooms as r\r\n" + "ON b.roomNo = r.roomNo\r\n" + "where b.bookingID=?;";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setRoomType(rs.getString("prefferedRoom"));
				libs.setRoomLease(rs.getString("arrivalDate"));
				libs.setRoomprice(rs.getDouble("roomPrice"));

				String DateIn = rs.getString("arrivalDate");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String DateOut = formatter.format(date);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = format.parse(DateIn);
				Date date2 = format.parse(DateOut);

				long difference = Math.abs(date1.getTime() - date2.getTime());
				long differenceDates = difference / (24 * 60 * 60 * 1000);

				libs.setDays(differenceDates);

				libs.setRoomprice(rs.getDouble("RoomPrice"));
				invoice.add(libs);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get room details for the purpose of billing end

// DONE
	// Method to get Service details for the purpose of billing
	public ArrayList<InvoiceLibs> getServiceDetails(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT s.serviceName,c.Date,s.serviceType,s.rate\r\n" + "from customerService as c\r\n"
				+ "inner join services as s\r\n" + "on s.serviceId=c.serviceId\r\n" + "inner join bookings as b\r\n"
				+ "on b.bookingID = c.bookingID\r\n" + "WHERE b.bookingID = ?\r\n" + ";";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setParticulars(rs.getString("serviceName"));
				libs.setPurchaseDate(rs.getString("Date"));
				libs.setDescription(rs.getString("serviceType"));
				libs.setRate(rs.getDouble("rate"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get Service details for the purpose of billing end

//done
	// Method to get Restaurant details for the purpose of billing
	public ArrayList<InvoiceLibs> getRestaurantDetails(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT m.item,r.date,m.type,m.rate,r.quantity\r\n" + "from restaurantbar as r\r\n"
				+ "inner join menu as m\r\n" + "on r.menuID = m.id\r\n" + "inner join bookings as b\r\n"
				+ "on b.bookingID = r.bookingID\r\n" + "WHERE r.BookingID = ? AND m.type = 'Restaurant'\r\n" + ";";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setParticulars(rs.getString("item"));
				libs.setPurchaseDate(rs.getString("date"));
				libs.setDescription(rs.getString("type"));
				libs.setRate(rs.getDouble("rate"));
				libs.setQuantity(rs.getInt("quantity"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get Restaurant details for the purpose of billing end

//done
	// Method to get Bar details for the purpose of billing
	public ArrayList<InvoiceLibs> getBarDetails(int bId) {
		ArrayList<InvoiceLibs> invoice = new ArrayList<InvoiceLibs>();
		String sql = "SELECT m.item,r.date,m.type,m.rate,r.quantity\r\n" + "from restaurantbar as r\r\n"
				+ "inner join menu as m\r\n" + "on r.menuID = m.id\r\n" + "inner join bookings as b\r\n"
				+ "on b.bookingID = r.bookingID\r\n" + "WHERE r.BookingID = ? AND m.type = 'Bar'\r\n" + ";";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, bId);
			rs = pstat.executeQuery();

			while (rs.next()) {
				libs.setParticulars(rs.getString("item"));
				libs.setPurchaseDate(rs.getString("date"));
				libs.setDescription(rs.getString("type"));
				libs.setRate(rs.getDouble("rate"));
				libs.setQuantity(rs.getInt("quantity"));
				invoice.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return invoice;
	}
	// Method to get Bar details for the purpose of billing end

// done
	// Method to get Bar details for the purpose of billing
	public boolean insertIntoInvoice(InvoiceLibs libs) {
		boolean result = false;
		String sql = "Insert Into Invoice(Date,Status,subTotal,discount,serviceCharge,vatCharge,total)\r\n"
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, libs.getBillDate());
			pstat.setString(2, libs.getStatus());
			pstat.setDouble(3, libs.getSubTotal());
			pstat.setDouble(4, libs.getDiscount());
			pstat.setDouble(5, libs.getServiceCharge());
			pstat.setDouble(6, libs.getVatCharge());
			pstat.setDouble(7, libs.getTotal());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return result;
	}
	// Method to get Bar details for the purpose of billing end

}
