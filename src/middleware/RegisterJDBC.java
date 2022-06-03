package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connect.Database;
import library.CorpLibs;
import library.NCorpLibs;

public class RegisterJDBC extends Database {

	// JDBC REGISTER

	// Non Corporate
	public boolean insertNC(NCorpLibs noncorps) {
		boolean res = false;
		String sql = "INSERT INTO users (fname, lname, address, phoneNo, email, gender, creditCardInfo, uname, pwd, role) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection conn;
		PreparedStatement pstat;

		try {
			conn = connect(); // 1. loading driver and 2. connection
			pstat = conn.prepareStatement(sql); // 3. jdbc statement
			pstat.setString(1, noncorps.getFname());// 4. set values
			pstat.setString(2, noncorps.getLname());
			pstat.setString(3, noncorps.getAddress());
			pstat.setString(4, noncorps.getPhoneNo());
			pstat.setString(5, noncorps.getEmail());
			pstat.setString(6, noncorps.getGender());
			pstat.setString(7, noncorps.getCreditCardInfo());
			pstat.setString(8, noncorps.getUname());
			pstat.setString(9, noncorps.getPwd());
			pstat.setString(10, noncorps.getRole());
			pstat.executeUpdate(); // 5. execute sql statement
			pstat.close();
			conn.close();
			res = true;

		} catch (Exception ex) {
			res = false;
			System.out.println("Error : " + ex.getMessage());
		}

		return res;
	}

	// Corporate
	public boolean insertC(CorpLibs corpLibs) {
		boolean res = false;
		String sql = "INSERT INTO users (fname, lname, companyName, phoneNo, email, address, creditCardInfo, uname, pwd, role) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection conn;
		PreparedStatement pstat;

		try {
			conn = connect(); // connect
			pstat = conn.prepareStatement(sql); // statement
			pstat.setString(1, corpLibs.getFname());// set values
			pstat.setString(2, corpLibs.getLname());
			pstat.setString(3, corpLibs.getCompanyName());
			pstat.setString(4, corpLibs.getPhoneNo());
			pstat.setString(5, corpLibs.getEmail());
			pstat.setString(6, corpLibs.getAddress());
			pstat.setString(7, corpLibs.getCreditCard());
			pstat.setString(8, corpLibs.getUname());
			pstat.setString(9, corpLibs.getPwd());
			pstat.setString(10, corpLibs.getRole());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
			res = true;

		} catch (Exception ex) {
			res = false;
			System.out.println("Error : " + ex.getMessage());
		}

		return res;
	}

}