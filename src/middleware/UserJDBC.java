//package name
package middleware;

//importing libraries
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.Database;
import library.UserLibs;

//main class 
public class UserJDBC extends Database { // use all features of class Database

	public UserLibs login(UserLibs userLibs) {
		String sql = "SELECT * FROM users where uname=? AND pwd = ?"; // get values from table users (jdbc connection)
		try {
			Connection conn = connect(); // connection with database
			PreparedStatement pstat = conn.prepareStatement(sql); // creating jdbc statement initialize
			pstat.setString(1, userLibs.getUsname());
			pstat.setString(2, userLibs.getUspwd());
			ResultSet rs = pstat.executeQuery(); // execute query

			while (rs.next()) {
				userLibs.setLoginID(rs.getInt("regID"));
				userLibs.setUsname(rs.getString("uname"));
				userLibs.setUsrole(rs.getString("role"));
			}

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}

		return userLibs;
	}

}