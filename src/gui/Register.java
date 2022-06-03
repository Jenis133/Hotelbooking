//package name
package gui;

//importing libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import library.CorpLibs;
import library.NCorpLibs;
import middleware.RegisterJDBC;
import validation.Regex;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

//Main Class
public class Register extends JFrame implements ActionListener {

	// Declaration
	JPanel mainPanel, panel1, pane2;
	JTabbedPane tPane;
	JLabel img,lblTitle1, lblTitle2, lblNcName, lblAddress, lblPhone, lblEmail, lblCredit, lblUsername, lblPassword,
			lblGender, lName, lAddress, lPhone, lEmail, lCompany, lUsername, lPassword, lblRetype1, lblRetype2, lCredit;
	JTextField txtFName, txtLName, txtPw, txtAddress, txtPhone, txtEmail, txtCredit, txtUsername, tFName, tLName,
			tAddress, tPhone, tEmail, tCompany, tUsername, tCredit;
	JPasswordField pwdField1, pwdField2, pwdRetype1, pwdRetype2;
	JComboBox cmbGender;
	JButton btnCancel, btnSubmit1, btnSubmit2;

	// Sub Class
	public Register() {
		// creating frame and main panel
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Registration");
		setBounds(300, 120, 770, 550);
		setResizable(false);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(95, 158, 160));
		mainPanel.setLayout(null);

		lblTitle1 = new JLabel("HOTEL LUTON");
		lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1.setForeground(new Color(255, 255, 255));
		lblTitle1.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle1.setBounds(39, 126, 240, 57);
		mainPanel.add(lblTitle1);

		lblTitle2 = new JLabel("Register now!!");
		lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle2.setForeground(new Color(240, 255, 240));
		lblTitle2.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle2.setBounds(49, 182, 209, 33);
		mainPanel.add(lblTitle2);

		// start of creating tab for non corporate customer
		tPane = new JTabbedPane(JTabbedPane.TOP);
		tPane.setBounds(308, 44, 446, 439);
		mainPanel.add(tPane);

		panel1 = new JPanel();
		tPane.addTab("Customer", null, panel1, null);
		panel1.setBackground(SystemColor.inactiveCaption);
		tPane.setBackground(new Color(192, 192, 192));
		panel1.setLayout(null);

		lblNcName = new JLabel("Name : ");
		lblNcName.setBounds(10, 28, 46, 14);
		panel1.add(lblNcName);

		txtFName = new JTextField();
		txtFName.setBounds(130, 25, 120, 20);
		panel1.add(txtFName);
		txtFName.setColumns(10);

		txtLName = new JTextField();
		txtLName.setBounds(260, 25, 150, 20);
		panel1.add(txtLName);
		txtLName.setColumns(10);

		lblAddress = new JLabel("Address : ");
		lblAddress.setBounds(10, 64, 62, 14);
		panel1.add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setBounds(130, 63, 278, 20);
		panel1.add(txtAddress);

		lblPhone = new JLabel("Phone No :");
		lblPhone.setBounds(10, 102, 81, 14);
		panel1.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(130, 100, 278, 20);
		panel1.add(txtPhone);
		txtPhone.setColumns(10);

		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(10, 140, 81, 14);
		panel1.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(130, 140, 280, 20);
		panel1.add(txtEmail);
		txtEmail.setColumns(10);

		lblGender = new JLabel("Gender : ");
		lblGender.setBounds(10, 181, 81, 14);
		panel1.add(lblGender);

		cmbGender = new JComboBox();
		cmbGender.setModel(new DefaultComboBoxModel(new String[] { "Select", "Male", "Female", "Others" }));
		cmbGender.setBounds(130, 178, 67, 22);
		panel1.add(cmbGender);

		lblUsername = new JLabel("Username :");
		lblUsername.setBounds(10, 220, 102, 14);
		panel1.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(130, 220, 280, 20);
		panel1.add(txtUsername);
		txtUsername.setColumns(10);

		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(10, 260, 102, 14);
		panel1.add(lblPassword);

		pwdField1 = new JPasswordField();
		pwdField1.setBounds(130, 260, 278, 20);
		panel1.add(pwdField1);

		lblRetype1 = new JLabel("Retype Password :");
		lblRetype1.setBounds(10, 300, 200, 14);
		panel1.add(lblRetype1);

		pwdRetype1 = new JPasswordField();
		pwdRetype1.setBounds(130, 300, 278, 20);
		panel1.add(pwdRetype1);

		btnSubmit1 = new JButton("Submit");
		btnSubmit1.setBounds(320, 360, 89, 23);
		btnSubmit1.addActionListener(this);
		panel1.add(btnSubmit1);
		// end of creating tab for noncorporate customer

		// start of creating tab for corporate customer
		JPanel panel2 = new JPanel();
		tPane.addTab("Corporate", null, panel2, null);
		panel2.setBackground(new Color(230, 230, 250));
		tPane.setBackground(new Color(230, 230, 250));
		panel2.setLayout(null);

		lName = new JLabel("Name :");
		lName.setBounds(10, 23, 82, 14);
		panel2.add(lName);

		tFName = new JTextField();
		tFName.setBounds(129, 20, 130, 20);
		panel2.add(tFName);
		tFName.setColumns(10);

		tLName = new JTextField();
		tLName.setBounds(270, 20, 144, 20);
		panel2.add(tLName);
		tLName.setColumns(10);

		lCompany = new JLabel("Company Name :");
		lCompany.setBounds(10, 72, 150, 14);
		panel2.add(lCompany);

		tCompany = new JTextField();
		tCompany.setBounds(129, 69, 285, 20);
		panel2.add(tCompany);
		tCompany.setColumns(10);

		lPhone = new JLabel("Phone No:");
		lPhone.setBounds(10, 115, 59, 14);
		panel2.add(lPhone);

		tPhone = new JTextField();
		tPhone.setBounds(129, 112, 285, 20);
		panel2.add(tPhone);

		lEmail = new JLabel("Email : ");
		lEmail.setBounds(10, 157, 46, 14);
		panel2.add(lEmail);

		tEmail = new JTextField();
		tEmail.setBounds(129, 154, 285, 20);
		panel2.add(tEmail);

		lAddress = new JLabel("Address :");
		lAddress.setBounds(10, 190, 59, 14);
		panel2.add(lAddress);

		tAddress = new JTextField();
		tAddress.setBounds(129, 190, 285, 20);
		panel2.add(tAddress);

		lUsername = new JLabel("Username :");
		lUsername.setBounds(10, 230, 82, 14);
		panel2.add(lUsername);

		tUsername = new JTextField();
		tUsername.setBounds(129, 230, 285, 20);
		panel2.add(tUsername);
		tUsername.setColumns(10);

		lPassword = new JLabel("Password :");
		lPassword.setBounds(10, 270, 82, 14);
		panel2.add(lPassword);

		pwdField2 = new JPasswordField();
		pwdField2.setBounds(129, 270, 288, 20);
		panel2.add(pwdField2);

		lblRetype2 = new JLabel("Retype Password: ");
		lblRetype2.setBounds(10, 315, 150, 14);
		panel2.add(lblRetype2);

		pwdRetype2 = new JPasswordField();
		pwdRetype2.setBounds(129, 315, 288, 20);
		panel2.add(pwdRetype2);

		btnSubmit2 = new JButton("Submit");
		btnSubmit2.setBounds(325, 360, 89, 23);
		btnSubmit2.addActionListener(this);
		panel2.add(btnSubmit2);
		// end of creating tab for corporate customer
		
		img = new JLabel();
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(82, 243, 186, 150);
		ImageIcon image = new ImageIcon(getClass().getResource("/verify.png"));
		Image imagel = (image).getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		image = new ImageIcon(imagel);
		img.setIcon(image);
		mainPanel.add(img);

		add(mainPanel);
		setVisible(true);
	}

	// Performs while clicking buttons
	@Override
	public void actionPerformed(ActionEvent ae) {

		// btnSubmit1 is Registration of Corporate Customer
		if (ae.getSource() == btnSubmit1) {

			// Checking if the fields are empty or not
			if (txtFName.getText().equals("") || txtLName.getText().equals("") || txtAddress.getText().equals("")
					|| txtPhone.getText().equals("") || txtEmail.getText().equals("")
					|| cmbGender.getSelectedItem().toString().equals("") || txtUsername.getText().equals("")
					|| pwdField1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill all the empty fields !!");
			}

			// Checking validation
			else {
				Regex reg = new Regex();// intialization and memory allocation
				boolean r1 = reg.RegFName(txtFName.getText());
				boolean r2 = reg.RegLName(txtLName.getText());
				boolean r3 = reg.RegPhone(txtPhone.getText());
				boolean r4 = reg.RegEmail(txtEmail.getText());
				boolean r5 = reg.Gender(cmbGender.getSelectedItem().toString());
				boolean r6 = reg.RegUsername(txtUsername.getText());
				boolean r7 = reg.RegPassword(pwdField1.getText());
				String password = pwdField1.getText();
				String retypePwd = pwdRetype1.getText();

				if (r1 == true) {
					if (r2 == true) {
						if (r3 == true) {
							if (r4 == true) {
								if (r5 == true) {
									if (r6 == true) {
										if (r7 == true) {
											if (password.equals(retypePwd)) {
												NCorpLibs noncorps = new NCorpLibs();

												noncorps.setFname(txtFName.getText());
												noncorps.setLname(txtLName.getText());
												noncorps.setAddress(txtAddress.getText());
												noncorps.setPhoneNo(txtPhone.getText());
												noncorps.setEmail(txtEmail.getText());
												noncorps.setGender(cmbGender.getSelectedItem().toString());
												noncorps.setUname(txtUsername.getText());
												noncorps.setPwd(pwdField1.getText());

												RegisterJDBC registerJDBC = new RegisterJDBC();
												boolean result = registerJDBC.insertNC(noncorps);
												if (result == true) {
													JOptionPane.showMessageDialog(null, "REGISTER SUCCESSFULLY");
													txtFName.setText(null);
													txtLName.setText(null);
													txtAddress.setText(null);
													txtPhone.setText(null);
													txtEmail.setText(null);
													cmbGender.setSelectedIndex(0);
													txtUsername.setText(null);
													pwdField1.setText(null);
													pwdRetype1.setText(null);

												} else {
													JOptionPane.showMessageDialog(null, "ERROR REGISTER");
												}

											} else {

												JOptionPane.showMessageDialog(null,
														" Password and Retype Password doesnot match!!");

											}
										} else {
											JOptionPane.showMessageDialog(null,
													"Invalid password!! There must be atleast 8 values and first letter must be capital");

										}

									} else {
										JOptionPane.showMessageDialog(null,
												"Invalid username!! There must be atleast 8 values, small letters and numbers are only valid");

									}

								} else {
									JOptionPane.showMessageDialog(null, "Please select your gender");
								}

							} else {
								JOptionPane.showMessageDialog(null, "Invalid email address");

							}

						} else {
							JOptionPane.showMessageDialog(null, "Invalid phone no");
						}

					} else {
						JOptionPane.showMessageDialog(null, "First letter of LAST NAME must be capital");
					}
				} else {
					JOptionPane.showMessageDialog(null, "First letter of FIRST NAME must be capital");
				}

			}
		}

		// btnSubmit2 is Registration of Non Corporate Customer
		else if (ae.getSource() == btnSubmit2) {

			// Checking if the fields are empty or not
			if (tFName.getText().equals("") || tLName.getText().equals("") || tCompany.getText().equals("")
					|| tPhone.getText().equals("") || tEmail.getText().equals("") || tAddress.getText().equals("")
					|| tUsername.getText().equals("") || pwdField2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill all the fields");
			}

			else {
				// Checking Validation
				Regex reg = new Regex();
				boolean r1 = reg.RegFName(tFName.getText());
				boolean r2 = reg.RegLName(tLName.getText());
				boolean r3 = reg.RegPhone(tPhone.getText());
				boolean r4 = reg.RegEmail(tEmail.getText());
				boolean r5 = reg.RegUsername(tUsername.getText());
				boolean r6 = reg.RegPassword(pwdField2.getText());
				String password = pwdField2.getText();
				String retype = pwdRetype2.getText();

				if (r1 == true) {
					if (r2 == true) {
						if (r3 == true) {
							if (r4 == true) {
								if (r5 == true) {
									if (r6 == true) {
										if (password.equals(retype)) {

											CorpLibs corpLibs = new CorpLibs(); // initialization and memory
																				// allocation
											corpLibs.setFname(tFName.getText()); // input text from textfield and
																					// send
																					// to
																					// middleware
											corpLibs.setLname(tLName.getText());
											corpLibs.setCompanyName(tCompany.getText());
											corpLibs.setPhoneNo(tPhone.getText());
											corpLibs.setEmail(tEmail.getText());
											corpLibs.setAddress(tAddress.getText());
											corpLibs.setUname(tUsername.getText());
											corpLibs.setPwd(pwdField2.getText());

											RegisterJDBC registerJDBC = new RegisterJDBC();
											boolean result = registerJDBC.insertC(corpLibs);
											if (result == true) {
												JOptionPane.showMessageDialog(null, "SAVED RECORD SUCCESSFULLY");
												tFName.setText(null);
												tLName.setText(null);
												tCompany.setText(null);
												tPhone.setText(null);
												tEmail.setText(null);
												tAddress.setText(null);
												tUsername.setText(null);
												pwdField2.setText(null);
												pwdRetype2.setText(null);

											} else {
												JOptionPane.showMessageDialog(null, "ERROR TO INSERT RECORD");
											}
										} else {
											JOptionPane.showMessageDialog(null,
													"Password and retype password doesnot match!!");
										}
									} else {
										JOptionPane.showMessageDialog(null,
												"Invalid password!! There must be atleast 8 values and first letter must be capital");
									}

								} else {
									JOptionPane.showMessageDialog(null,
											"Invalid username!! There must be atleast 8 values, small letters and numbers are only valid");
								}

							} else {
								JOptionPane.showMessageDialog(null, "Invalid email format");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Invalid phone format");
						}

					} else {
						JOptionPane.showMessageDialog(null, "First letter of LAST NAME must be capital");
					}

				} else {
					JOptionPane.showMessageDialog(null, "First letter of FIRST NAME must be capital");
				}
			}
		}

	}

	// Main Method
	public static void main(String[] args) {
		new Register();

	}

}
