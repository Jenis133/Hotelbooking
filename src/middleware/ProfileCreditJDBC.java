package middleware;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connect.Database;
import library.ProfileCreditLibs;

public class ProfileCreditJDBC extends Database {

	public boolean profileCreditInsert(ProfileCreditLibs credit) {
		boolean res = false;
		String sql = "update users set creditCardInfo= ? where regID = ?";
		Connection conn;
		PreparedStatement pstat;

		try {
			conn = connect(); // 1. loading driver and 2. connection
			pstat = conn.prepareStatement(sql); // 3. jdbc statement
			pstat.setString(1, credit.getCredit());// 4. set values
			pstat.setInt(2, credit.getCustomerID());
			pstat.executeUpdate(); // 5. execute sql statement
			pstat.close();
			conn.close();
			res = true;

		} catch (Exception ex) {
//			res = false;
			System.out.println("Error : " + ex.getMessage());
		}

		return res;
	}

}
