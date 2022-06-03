package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.Database;
import library.BookingLibs;
import library.ServiceLibs;

public class ServiceJDBC extends Database {
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
	public ArrayList<ServiceLibs> getCustomerDetails(ServiceLibs service) {
		ArrayList<ServiceLibs> array = new ArrayList<ServiceLibs>();
		String sql = "select b.bookingID,u.regID,u.fname, u.lname, b.arrivalDate \r\n" + "from bookings as b \r\n"
				+ "inner join users as u \r\n" + "on b.regID = u.regID \r\n" + "where b.roomNo=?";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, service.getRoomNo());
			rs = pstat.executeQuery();

			while (rs.next()) {
				service.setBookingID(rs.getInt("bookingID"));
				service.setCustomerFname(rs.getString("fname"));
				service.setCustomerLname(rs.getString("lname"));
				service.setArrivalDate(rs.getString("ArrivalDate"));
				array.add(service);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}
		return array;
	}

	// shows the type of services
	public ArrayList getServiceType() {
		ArrayList array = new ArrayList();
		String sql = "SELECT serviceType from services group by serviceType";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();

			while (rs.next()) {
				String service = rs.getString("serviceType");
				array.add(service);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}

		return array;
	}

	// shows the sevice name , rate according to its service type
	public ArrayList getServiceDetails(ServiceLibs service) {
		ArrayList array = new ArrayList();
		String sql = "SELECT serviceName , rate FROM services WHERE serviceType = ?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, service.getServiceType());
			rs = pstat.executeQuery();

			while (rs.next()) {
				String name = rs.getString("serviceName");
				array.add(name);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
		}
		return array;
	}

	// price of services
	public double getServicePrice(ServiceLibs service) {
		double rate = 0.0;
		String sql = "SELECT rate FROM services WHERE serviceName = ?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, service.getServiceName());
			rs = pstat.executeQuery();
			while (rs.next()) {
				rate = rs.getDouble("rate");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());

		}
		return rate;
	}

	// ID of services
	public int getServiceID(ServiceLibs service) {
		int serviceID = 0;
		String sql = "SELECT serviceID FROM services WHERE serviceName = ?";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, service.getServiceName());
			rs = pstat.executeQuery();
			while (rs.next()) {
				serviceID = rs.getInt("serviceID");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());

		}
		return serviceID;
	}

	// to insert service
	public boolean insertService(ServiceLibs service) {
		boolean result = false;
		String sql = "INSERT INTO customerService(date, serviceID, bookingID) VALUES (?, ?, ?) ";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, service.getDate());
			pstat.setInt(2, service.getServiceID());
			pstat.setInt(3, service.getBookingID());
			pstat.executeUpdate(); // INSERT UPDATE AND DELETE
			pstat.close();
			conn.close();
			result = true;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return result;
	}

	// customer service history
	public ArrayList<ServiceLibs> getServiceHistory() {
		ArrayList<ServiceLibs> serviceHistory = new ArrayList<ServiceLibs>();
		String sql = "SELECT cs.bookingID, cs.date, u.fname, b.roomNo, s.serviceType, s.serviceName, s.rate\r\n"
				+ "FROM customerService as cs\r\n"
				+ "inner join bookings as b\r\n"
				+ "on cs.bookingID = b.bookingID\r\n"
				+ "inner join users as u\r\n"
				+ "on b.regID = u.regID\r\n"
				+ "inner join services as s\r\n"
				+ "on cs.serviceID = s.serviceID";
		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			
			while(rs.next()) {
				ServiceLibs libs = new ServiceLibs();
				libs.setBookingID(rs.getInt("bookingID"));
				libs.setDate(rs.getString("date"));
				libs.setCustomerFname(rs.getString("fname"));
				libs.setRoomNo(rs.getInt("roomNo"));
				libs.setServiceType(rs.getString("serviceType"));
				libs.setServiceName(rs.getString("serviceName"));
				libs.setRate(rs.getDouble("rate"));
				serviceHistory.add(libs);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
		}
		return serviceHistory;
	}

}
