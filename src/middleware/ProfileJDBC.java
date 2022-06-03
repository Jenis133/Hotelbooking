package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import connect.Database;
import library.ProfileCreditLibs;
import library.ProfileLibs;

public class ProfileJDBC extends Database {
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;

	public void profile(ProfileLibs profile) {

		try {
			conn = connect();
			String sql = "SELECT * FROM users WHERE regID = ? ";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, profile.getCustomerID());
			rs = pstat.executeQuery();
			while (rs.next()) {
				profile.setFname(rs.getString("fname"));
				profile.setLname(rs.getString("lname"));
				profile.setAddress(rs.getString("address"));
				profile.setEmail(rs.getString("email"));
				profile.setCredit(rs.getString("creditCardInfo"));

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());
		}
	}

	public String customer(int customerID) {
		ProfileLibs profile = new ProfileLibs();
		String creditCard = null;
		try {
			conn = connect();
			String sql = "SELECT creditCardInfo FROM users WHERE regID = ? ";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, customerID);
			rs = pstat.executeQuery();
			while (rs.next()) {
				creditCard = rs.getString("creditCardInfo");

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());
		}
		return creditCard;
	}

}
