//package name
package gui;

//import libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library.ProfileCreditLibs;
import library.ProfileLibs;
import middleware.ProfileJDBC;

//main class
public class CustomerDashboard extends JFrame implements ActionListener {

	// global variable //value store and can be used anywhere
	int customerID;
	ProfileCreditLibs pcLibs = new ProfileCreditLibs();

	// declaration
	JPanel panelMain, panelTitle, panelLeft;
	JLabel lblIntro, lblTitle, lblCustomer, img;
	JButton btnProfile, btnCBooking, btnLogout, btnExit;

	// sub class
	public CustomerDashboard(int customerID) {

		this.customerID = customerID;
		setTitle("Customer Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH); // window fully extended
		setLayout(null); // set layout null

		panelMain = new JPanel();
		panelMain.setBackground(SystemColor.activeCaption);
		panelMain.setBounds(0, 0, 1500, 900);
		panelMain.setLayout(null);
		add(panelMain);

		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 0, 0));
		panelTitle.setBounds(198, 0, 1172, 42);
		panelMain.add(panelTitle);
		panelTitle.setLayout(null);

		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(0, 0, 0));
		panelLeft.setBounds(0, 0, 199, 749);
		panelMain.add(panelLeft);
		panelLeft.setLayout(null);

		lblTitle = new JLabel("Luton Hotel");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(520, 11, 124, 20);
		panelTitle.add(lblTitle);

		lblCustomer = new JLabel("- Customer");
		lblCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomer.setForeground(new Color(255, 255, 255));
		lblCustomer.setBounds(60, 200, 94, 14);
		panelLeft.add(lblCustomer);

		btnProfile = new JButton("Profile");
		btnProfile.setForeground(new Color(255, 255, 255));
		btnProfile.setBackground(new Color(105, 105, 105));
		btnProfile.setBounds(22, 300, 147, 35);
		btnProfile.setFocusable(false);
		btnProfile.addActionListener(this);
		panelLeft.add(btnProfile);

		btnCBooking = new JButton("Booking");
		btnCBooking.setForeground(new Color(255, 255, 255));
		btnCBooking.setBackground(new Color(105, 105, 105));
		btnCBooking.setBounds(22, 400, 147, 35);
		btnCBooking.setFocusable(false);
		btnCBooking.addActionListener(this);
		panelLeft.add(btnCBooking);

		btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(105, 105, 105));
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setBounds(22, 500, 147, 35);
		btnLogout.setFocusable(false);
		btnLogout.addActionListener(this);
		panelLeft.add(btnLogout);

		btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(105, 105, 105));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBounds(22, 600, 147, 35);
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);
		panelLeft.add(btnExit);

		img = new JLabel();
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(70, 100, 50, 50);
		ImageIcon image = new ImageIcon(getClass().getResource("/customerProfile.png"));
		Image imagel = (image).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		image = new ImageIcon(imagel);
		img.setIcon(image);
		panelLeft.add(img);

		setVisible(true);

	}

	// JButton action Performed method
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnCBooking) {
			ProfileLibs profile = new ProfileLibs();
			ProfileJDBC jdbc = new ProfileJDBC();
			String creditCard = jdbc.customer(customerID);
			if (creditCard.equals("")) {
				JOptionPane.showMessageDialog(null, "Go to Profile and fill in your credit card details for guaranteed booking!\n If not provided then your booking request will be unguaranteed and cancelled any time");
				new CustomerBooking(customerID);
			} else {
				new CustomerBooking(customerID);
			}

		} else if (ae.getSource() == btnLogout) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Log Out", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				this.dispose();
				new HomePage();
			}

		} else if (ae.getSource() == btnProfile) {
			new Profile(customerID);

		} else if (ae.getSource() == btnExit) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);

			}

		}
	}

	// End of JButton action Performed method

	// main function
//	public static void main(String[] args) {
//		new CustomerDashboard(1);
//
//	}
}
