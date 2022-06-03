//package name
package library;

//main class
public class UserLibs {
	int loginID;
	String usname;
	String uspwd;
	String usrole;

	// constructor
	public UserLibs() {
		this.loginID = 0;
		this.usname = "";
		this.uspwd = "";
		this.usrole = "";
	}

	// parameterized constructor // POLYMORPHISM multiple method with same name
	public UserLibs(int loginID, String usname, String uspwd, String usrole) {
		this.loginID = loginID;
		this.usname = usname;
		this.uspwd = uspwd;
		this.usrole = usrole;
	}

	// setter and getter
	public int getLoginID() {
		return loginID;
	}

	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	public String getUsname() {
		return usname;
	}

	public void setUsname(String usname) {
		this.usname = usname;
	}

	public String getUspwd() {
		return uspwd;
	}

	public void setUspwd(String uspwd) {
		this.uspwd = uspwd;
	}

	public String getUsrole() {
		return usrole;
	}

	public void setUsrole(String usrole) {
		this.usrole = usrole;
	}

	@Override
	public String toString() {
		return "User [loginID=" + loginID + ", usname=" + usname + ", uspwd=" + uspwd + ", usrole=" + usrole + "]";
	}

}
