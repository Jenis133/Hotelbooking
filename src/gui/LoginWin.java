//package name
package gui;

//importing libraries
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.UserLibs;
import middleware.UserJDBC;
import java.awt.Font;
import java.awt.Image;

//main class
public class LoginWin extends JFrame implements ActionListener {

	// global variable
	int customerID;

	// declaration
	JPanel panelMain;
	JLabel lblTitle, lblUsername, lblPw, lblRegTitle, img;
	JTextField txtUsername;
	JPasswordField txtPw;
	JButton btnLogin, btnClose, btnRegister, btnReg;
	JComboBox cmbCustType, cmbStaffType;

	// sub class
	public LoginWin() {
		// creating frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Login");
		setBounds(500, 150, 350, 465);
		setResizable(false);
		getContentPane().setLayout(null);

		// adding main panel start
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 334, 426);
		panelMain.setBackground(new Color(119, 136, 153));
		panelMain.setLayout(null);
		// adding main panel end

		// label for title of login
		lblTitle = new JLabel("LOGIN TO HOTEL LUTON");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTitle.setBounds(67, 12, 217, 37);

		// label for username
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(71, 120, 141, 30);

		// textfield for username
		txtUsername = new JTextField();
		txtUsername.setBounds(71, 149, 199, 20);
		txtUsername.setColumns(10);

		// label for password
		lblPw = new JLabel("Password");
		lblPw.setBounds(71, 187, 164, 14);

		// textfield for password
		txtPw = new JPasswordField();
		txtPw.setBounds(71, 205, 199, 20);
		panelMain.add(txtPw);

		// login button
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(71, 236, 199, 23);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(this);

		// label for title of registration
		lblRegTitle = new JLabel("Haven't registered yet? ");
		lblRegTitle.setBounds(71, 292, 156, 14);
		panelMain.add(lblRegTitle);

		// register button
		btnReg = new JButton("REGISTER HERE");
		btnReg.setBounds(71, 308, 202, 23);
		btnReg.setForeground(new Color(255, 255, 255));
		btnReg.setBackground(new Color(0, 0, 0));
		btnReg.addActionListener(this);

		img = new JLabel();
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(139, 63, 50, 50);
		ImageIcon image = new ImageIcon(getClass().getResource("/pro.png"));
		Image imagel = (image).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		image = new ImageIcon(imagel);
		img.setIcon(image);
		panelMain.add(img);

		// adding in frame
		getContentPane().add(panelMain);
		panelMain.add(lblTitle);
		panelMain.add(lblUsername);
		panelMain.add(txtUsername);
		panelMain.add(lblPw);
		panelMain.add(btnLogin);
		panelMain.add(btnReg);

		setVisible(true);
	}

	// JButton action Performed method
	@Override
	public void actionPerformed(ActionEvent ae) {

		// close Login window
		if (ae.getSource() == btnClose) {
			System.exit(0);

			// login button event
		} else if (ae.getSource() == btnLogin) {
			UserLibs userLibs = new UserLibs(); // initialization and memory allocation
			userLibs.setUsname(txtUsername.getText()); // get username from textfield and send to middleware
			userLibs.setUspwd(txtPw.getText()); // get password from textfield and send to middleware
			UserJDBC jdbcuser = new UserJDBC(); // initialization and memory allocation
			jdbcuser.login(userLibs); // calling login method from JDBCUsers and pass value of user to login method

			// if fields are empty it display message "Please fill all the fields"
			if (txtUsername.getText().equals("") && txtPw.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");

				// this condition executes if the loginID is greater than 0
			} else if (userLibs.getLoginID() > 0) {
				customerID = userLibs.getLoginID();

				if (userLibs.getUsrole().equals("Non Corporate")) {
					JOptionPane.showMessageDialog(null, "Login successful as a Customer");
					new CustomerDashboard(customerID);
					
					this.dispose();

				} else if (userLibs.getUsrole().equals("Corporate")) {
					JOptionPane.showMessageDialog(null, "Login successful as a Corporate Customer");
					new CorporateDashboard(customerID);
					this.dispose();
				} else if (userLibs.getUsrole().equals("Staff")) {
					JOptionPane.showMessageDialog(null, "Login successful as a Staff");
					new ReceptionistDashboard();
					this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "invalid username and password");
			}

			// opens Registration window
		} else if (ae.getSource() == btnReg) {
			new Register();

		}

	}
	// end of JButton action Performed method

//	// Main function
//	public static void main(String[] args) {
//		new LoginWin();
//
//	}

}
