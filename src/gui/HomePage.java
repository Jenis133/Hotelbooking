//package name
package gui;

//importing libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.UserLibs;
import middleware.UserJDBC;

//Main class
public class HomePage extends JFrame implements ActionListener {
	// global variable
	int customerID;

	// declaration
	JLabel lbl, lbl2, lbl3, lbl7, lbl4, lbl5, imgHome, lblUsername, lblPw, lblRegTitle, img, lblTitle;
	JTextField txtUsername, txtPw;
	JButton btnLogin, btnReg;
	JPanel panelMain, panelLogin;

	// sub class
	public HomePage() {
		setTitle("Homepage");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(true);

		// creating main panel
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 1550, 900);
		panelMain.setBackground(new Color(173, 216, 230));
		add(panelMain);
		panelMain.setLayout(null);

		panelLogin = new JPanel();
		panelLogin.setBounds(830, 130, 334, 426);
		panelLogin.setBackground(new Color(95, 158, 160));
		panelLogin.setLayout(null);
		panelMain.add(panelLogin);

		// creating label
		lbl = new JLabel();
		lbl.setBounds(95, 120, 1000, 50);
		lbl.setText("Welcome to Luton Hotel");
		lbl.setForeground(Color.WHITE);
		lbl.setFont(new Font("Serif", Font.BOLD, 50));
		panelMain.add(lbl);

		lbl2 = new JLabel();
		lbl2.setBounds(95, 200, 1000, 50);
		lbl2.setText("Hotel for you and your loved ones, all year round");
		lbl2.setForeground(Color.WHITE);
		lbl2.setFont(new Font("Serif", Font.PLAIN, 20));
		panelMain.add(lbl2);

		lbl3 = new JLabel();
		lbl3.setBounds(95, 270, 1000, 50);
		lbl3.setText("Enjoy the unique experience with the best provided services and rooms with ");
		lbl3.setForeground(Color.WHITE);
		lbl3.setFont(new Font("Serif", Font.PLAIN, 20));
		panelMain.add(lbl3);

		lbl4 = new JLabel("amazing views for you and your whole family.");
		lbl4.setForeground(Color.WHITE);
		lbl4.setBounds(95, 300, 400, 50);
		lbl4.setFont(new Font("Serif", Font.PLAIN, 20));
		panelMain.add(lbl4);

		lbl5 = new JLabel("Come book a room now!!!");
		lbl5.setForeground(Color.WHITE);
		lbl5.setBounds(95, 360, 400, 50);
		lbl5.setFont(new Font("Serif", Font.PLAIN, 20));
		panelMain.add(lbl5);
		// end of creating label

//		// creating buttons
//		login = new JButton("Login");
//		login.setBounds(90, 400, 150, 60);
//		login.setFocusable(false);
//		login.addActionListener(this);
//		panelMain.add(login);
//
//		exit = new JButton("Exit");
//		exit.setBounds(90, 490, 150, 60);
//		exit.setFocusable(false);
//		exit.addActionListener(this);
//		panelMain.add(exit);

		// end of creating buttons

		imgHome = new JLabel();
		imgHome.setHorizontalAlignment(SwingConstants.CENTER);
		imgHome.setBounds(0, 0, 1550, 900);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/HomePage.jpg"));
		Image imageInstance = (imageIcon).getImage().getScaledInstance(1550, 900, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(imageInstance);
		imgHome.setIcon(imageIcon);
		panelMain.add(imgHome);

		// end of creating main panel

		// -----------------------

		// label for title of login
		lblTitle = new JLabel("LOGIN TO HOTEL LUTON");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblTitle.setBounds(67, 12, 217, 37);
		panelLogin.add(lblTitle);

		// label for username
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(71, 120, 141, 30);
		panelLogin.add(lblUsername);

		// textfield for username
		txtUsername = new JTextField();
		txtUsername.setBounds(71, 149, 199, 20);
		txtUsername.setColumns(10);
		panelLogin.add(txtUsername);

		// label for password
		lblPw = new JLabel("Password");
		lblPw.setBounds(71, 187, 164, 14);
		panelLogin.add(lblPw);

		// textfield for password
		txtPw = new JPasswordField();
		txtPw.setBounds(71, 205, 199, 20);
		panelLogin.add(txtPw);

		// login button
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(71, 236, 199, 23);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(this);
		panelLogin.add(btnLogin);

		// label for title of registration
		lblRegTitle = new JLabel("Haven't registered yet? ");
		lblRegTitle.setBounds(71, 292, 156, 14);
		panelLogin.add(lblRegTitle);

		// register button
		btnReg = new JButton("REGISTER HERE");
		btnReg.setBounds(71, 308, 202, 23);
		btnReg.setForeground(new Color(255, 255, 255));
		btnReg.setBackground(new Color(0, 0, 0));
		btnReg.addActionListener(this);
		panelLogin.add(btnReg);

		img = new JLabel();
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(139, 63, 50, 50);
		ImageIcon image = new ImageIcon(getClass().getResource("/pro.png"));
		Image imagel = (image).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		image = new ImageIcon(imagel);
		img.setIcon(image);
		panelLogin.add(img);

		// adding in the frame

		setVisible(true);

	}

	// JButton action Performed method
	@Override
	public void actionPerformed(ActionEvent ae) {

		// login button event
		if (ae.getSource() == btnLogin) {
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

	// main method
	public static void main(String[] args) {
		new HomePage();
	}

}