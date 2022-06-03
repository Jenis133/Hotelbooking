//package middleware;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import connect.Database;
//import library.ServiceLibs;
//
//public class UserServiceJDBC extends Database {
//	public boolean add(ServiceLibs serviceLibs) {
//		boolean res = false;
//		String sql = "INSERT INTO services (serviceID, serviceType, serviceName, rate) VALUES(?,?,?,?)";
//		Connection con;
//		PreparedStatement pstat;
//
//		try {
//			con = connect(); // connect--> 1. loading driver (class.forName(DRIVER)) and 2.connection conn =
//			// DriverManager.getConnection(URL, USER, PASS);
//			pstat = con.prepareStatement(sql); // 3. create jdbc statement
//			pstat.setInt(1, serviceLibs.getServiceID()); // 4. set values
//			pstat.setString(2, serviceLibs.getServiceType());
//			pstat.setString(3, serviceLibs.getServiceName());
//			pstat.setDouble(4, serviceLibs.getRate());
//			pstat.executeUpdate(); // 5. execute sql statement
//			pstat.close();
//			con.close();
//			res = true;
//
//		} catch (Exception ex) {
//			res = false;
//			System.out.println("Error : " + ex.getMessage()); // display error message
//		}
//
//		return res;
//	}
//
//	// Search service
//	public ServiceLibs search(ServiceLibs serviceLibs) {
//
//		String sql = "SELECT * FROM services WHERE serviceID =?";
//		ServiceLibs serviceSearch = new ServiceLibs();
//		try {
//			Connection conn;
//			PreparedStatement pstat;
//
//			ResultSet rs;
//			conn = connect();
//			pstat = conn.prepareStatement(sql);
//			pstat.setInt(1, serviceLibs.getServiceID());
//
//			rs = pstat.executeQuery();
//			while (rs.next()) {
//				serviceSearch = new ServiceLibs(rs.getInt("serviceID"), rs.getString("serviceType"),
//						rs.getString("serviceName"), rs.getDouble("Rate"));
//				;
//			}
//			rs.close();
//			pstat.close();
//			conn.close();
//
//		} catch (Exception ex) {
//			System.out.println("Error : " + ex.getMessage());
//			return serviceSearch;
//
//		}
//
//		return serviceSearch;
//
//	}
//
//	// edit service
//	public boolean update(ServiceLibs serviceLibs) {
//		Connection conn;
//		PreparedStatement pstat; // jdbc Statement
//		String sql = "UPDATE services SET  serviceType = ? , serviceName = ? , rate = ? WHERE serviceID=?";
//		boolean res = false;
//		try {
//			// input process and output
//			conn = connect(); // 1. loading driver (class.forName(DRIVER)) and 2.connection conn =
//								// DriverManager.getConnection(URL, USER, PASS);
//			pstat = conn.prepareStatement(sql);// 3 create jdbc statment
//			// 4. set values
//			pstat.setString(1, serviceLibs.getServiceType());
//			pstat.setString(2, serviceLibs.getServiceName());
//			pstat.setDouble(3, serviceLibs.getRate());
//			pstat.setInt(4, serviceLibs.getServiceID());
//			pstat.executeUpdate();// 5 running sql statement
//			conn.close();
//			res = true;
//		} catch (Exception ex) {
//			res = false;
//			System.out.println("Error : " + ex.getMessage());
//		}
//		return res;
//	}
//
//	// delete
//	public boolean delete(int service) {
//		Connection conn;
//		PreparedStatement pstat;
//		String sql = "DELETE FROM services WHERE serviceID=?";
//		boolean result = false;
//
//		try {
//			conn = connect();// 1. loading driver (class.forName(DRIVER)) and 2.connection conn =
//			// DriverManager.getConnection(URL, USER, PASS);
//			pstat = conn.prepareStatement(sql); // 3 create jdbc statment
//			pstat.setInt(1, service);// 4. setting values
//			pstat.executeUpdate(); // 5. running sql statement
//			pstat.close();
//			conn.close();
//			result = true;
//		} catch (Exception ex) {
//			System.out.println("Error : " + ex.getMessage());
//		}
//		return result;
//	}
//
//}
