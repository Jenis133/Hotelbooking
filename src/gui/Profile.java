//dd

//package name
package gui;

//importing libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import library.ProfileCreditLibs;
import library.ProfileLibs;
import middleware.ProfileJDBC;
import validation.Regex;
import middleware.ProfileCreditJDBC;

//main class
public class Profile extends JFrame implements ActionListener {
	// global variable
	int customerID;
	ProfileLibs profile = new ProfileLibs();

	// declaration
	JPanel mainPanel;
	JLabel lblTitle, lblFirstName, lblLastName, lblAddress, lblEmail, lblCredit;
	JTextField txtFirstName, txtLastName, txtAddress, txtEmail, txtCredit;
	JButton btnLogout, btnAdd;

	// sub class
	public Profile(int customerID) {
		this.customerID = customerID;

		// frame and main panel
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(190, 320, 312, 340);
		setTitle("Profile");
		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.inactiveCaption);
		mainPanel.setBounds(0, 0, 296, 260);
		mainPanel.setLayout(null);

		// creating label of Title
		lblTitle = new JLabel("Customer's Profile");
		lblTitle.setForeground(new Color(15, 93, 118));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(63, 11, 199, 40);
		mainPanel.add(lblTitle);
		// end of creating label of Title

		// creating label of First Name
		lblFirstName = new JLabel("First Name :");
		lblFirstName.setForeground(new Color(0, 0, 0));
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFirstName.setBounds(10, 62, 88, 14);
		mainPanel.add(lblFirstName);
		// end of creating label of First Name

		// creating textfield for Last Name
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFirstName.setBounds(108, 61, 165, 20);
		txtFirstName.setEditable(false);
		mainPanel.add(txtFirstName);
		// end of creating textfield for Last Name

		// creating label for Last Name
		lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblLastName.setBounds(10, 106, 88, 14);
		mainPanel.add(lblLastName);
		// end of creating label for Last Name

		// creating textfield for Last Name
		txtLastName = new JTextField();
		txtLastName.setBounds(108, 104, 165, 20);
		mainPanel.add(txtLastName);
		txtLastName.setEditable(false);
		// end of creating textfield for Last Name

		// creating label for Address
		lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 15));
		lblAddress.setBounds(10, 149, 88, 14);
		mainPanel.add(lblAddress);
		// end of creating label for Address

		// creating textfield for Address
		txtAddress = new JTextField();
		txtAddress.setBounds(108, 147, 165, 20);
		txtAddress.setEditable(false);
		mainPanel.add(txtAddress);
		// end of creating textfield for Address

		// creating label for Email
		lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setBounds(10, 192, 88, 14);
		mainPanel.add(lblEmail);
		// end of creating label for Email

		// creating textfield for Email
		txtEmail = new JTextField();
		txtEmail.setBounds(108, 190, 165, 20);
		txtEmail.setColumns(10);
		txtEmail.setEditable(false);
		mainPanel.add(txtEmail);
		// end of creating textfield for Email

		// creating label for Email
		lblCredit = new JLabel("Credit Card :");
		lblCredit.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCredit.setBounds(10, 226, 88, 14);
		mainPanel.add(lblCredit);
		// end of creating label for Email

		// creating textfield for Email
		txtCredit = new JTextField();
		txtCredit.setBounds(108, 226, 165, 20);
		txtCredit.setColumns(10);

		mainPanel.add(txtCredit);
		// end of creating textfield for Email

		// creating button
//		btnLogout = new JButton("Logout");
//		btnLogout.setBounds(100, 270, 100, 20);
//		btnLogout.addActionListener(this);
//		mainPanel.add(btnLogout);
		// end of creating button

		btnAdd = new JButton("Add");
		btnAdd.setBounds(108, 262, 80, 20);
		btnAdd.addActionListener(this);
		mainPanel.add(btnAdd);

		getContentPane().add(mainPanel);
		profileDisplay();
		setVisible(true);
	}

	// creating function to display user name,address and email
	public void profileDisplay() {
		profile.setCustomerID(customerID);
		ProfileJDBC jdbc = new ProfileJDBC();
		jdbc.profile(profile);
		txtFirstName.setText(profile.getFname());
		txtLastName.setText(profile.getLname());
		txtAddress.setText(profile.getAddress());
		txtEmail.setText(profile.getEmail());
		txtCredit.setText(profile.getCredit());

	}
	// end of creating function

	// JButton action Performed method
	@Override
	public void actionPerformed(ActionEvent ae) {
//		if (ae.getSource() == btnLogout) {
//			new HomePage();
//			this.dispose();
//
//		}
		if (ae.getSource() == btnAdd) {

			if (txtCredit.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill all the empty fields !!");
			} else {
				Regex reg = new Regex();// intialization and memory allocation
				boolean r1 = reg.RegCreditCard(txtCredit.getText());
				if (r1 == true) {
					ProfileCreditLibs credit = new ProfileCreditLibs();
					credit.setCustomerID(customerID);
					credit.setCredit(txtCredit.getText());
					ProfileCreditJDBC jdbc = new ProfileCreditJDBC();
					boolean result = jdbc.profileCreditInsert(credit);
					if (result == true) {
						JOptionPane.showMessageDialog(null, "Credit card info added successfully");
					} else {
						JOptionPane.showMessageDialog(null, "ERROR TO INSERT");
					}

				} else {
					JOptionPane.showMessageDialog(null, "invalid");

				}

			}

		}

	}
	// end of JButton action Performed method

//	public static void main(String[] args) {
//		new Profile();
//
//	}

}
