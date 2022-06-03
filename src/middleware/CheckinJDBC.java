package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connect.Database;
import library.CheckinLibs;

public class CheckinJDBC extends Database {
	// global variable
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;

	public ArrayList<CheckinLibs> getCheckin() {

		ArrayList<CheckinLibs> arrayCheckin = new ArrayList<CheckinLibs>();
		String sql = "SELECT b.bookingID, u.regID, u.fname , u.lname , u.email, u.address, u.creditCardInfo, b.arrivalDate, b.depatureDate , b.prefferedRoom, b.bookingStatus, b.roomNo\r\n"
				+ "FROM bookings as b\r\n" + "INNER JOIN users u ON\r\n" + "u.regID = b.regID\r\n"
				+ "WHERE b.bookingStatus = 'Pending'";

		try {
			conn = connect();
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			while (rs.next()) {
				CheckinLibs checkin = new CheckinLibs(rs.getInt("bookingID"), rs.getInt("regID"), rs.getInt("roomNo"),
						rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("address"),
						rs.getString("arrivalDate"), rs.getString("depatureDate"), rs.getString("prefferedRoom"),
						rs.getString("bookingStatus"), rs.getString("creditCardInfo"));
				arrayCheckin.add(checkin);
			}

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
		return arrayCheckin;
	}

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

}
