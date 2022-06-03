package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ReceptionistDashboard extends JFrame implements ActionListener {
	JPanel panelMain, panelTitle, panelHead, panelLeft, panelTable;
	JTextField txtDate, txtRoomType, txtCardInfo;
	JLabel lblTitle, lblTitle3, lblTitle2, lblDate, lblRoomNo, lblRoomType, lblCardInfo, lblReceiptionist, img;
	JButton btnAdd, btnCheckin, btnCheckout, btnLogout, btnExit, btnService, btnRes, btnBook, btnBar, btnBill;
	JComboBox cmbRoomNo;

	public ReceptionistDashboard() {
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Staff Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelMain = new JPanel();
		panelMain.setBackground(new Color(240, 230, 140));
		panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panelMain);
		panelMain.setLayout(null);

		panelTitle = new JPanel();
		panelTitle.setBackground(new Color(0, 0, 0));
		panelTitle.setBounds(198, 0, 1172, 42);
		panelMain.add(panelTitle);
		panelTitle.setLayout(null);

		lblTitle = new JLabel("Luton Hotel");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(540, 11, 124, 20);
		panelTitle.add(lblTitle);

		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(0, 0, 0));
		panelLeft.setBounds(0, 0, 199, 749);
		panelMain.add(panelLeft);
		panelLeft.setLayout(null);

		btnAdd = new JButton("Add Customer");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(105, 105, 105));
		btnAdd.setBounds(22, 210, 147, 35);
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(this);
		panelLeft.add(btnAdd);

		btnBook = new JButton("Book");
		btnBook.setForeground(new Color(255, 255, 255));
		btnBook.setBackground(new Color(105, 105, 105));
		btnBook.setBounds(22, 260, 147, 35);
		btnBook.setFocusable(false);
		btnBook.addActionListener(this);
		panelLeft.add(btnBook);

		btnCheckin = new JButton("CheckIN Customer");
		btnCheckin.setBackground(new Color(105, 105, 105));
		btnCheckin.setForeground(new Color(255, 255, 255));
		btnCheckin.setBounds(22, 310, 147, 35);
		btnCheckin.setFocusable(false);
		btnCheckin.addActionListener(this);
		panelLeft.add(btnCheckin);

		btnCheckout = new JButton("CheckOUT Customer");
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCheckout.setBackground(new Color(105, 105, 105));
		btnCheckout.setForeground(new Color(255, 255, 255));
		btnCheckout.setBounds(22, 360, 147, 35);
		btnCheckout.setFocusable(false);
		btnCheckout.addActionListener(this);
		panelLeft.add(btnCheckout);

		btnService = new JButton("Services");
		btnService.setBackground(new Color(105, 105, 105));
		btnService.setForeground(new Color(255, 255, 255));
		btnService.setBounds(22, 410, 147, 35);
		btnService.setFocusable(false);
		btnService.addActionListener(this);
		panelLeft.add(btnService);

		btnRes = new JButton("Restaurant");
		btnRes.setBackground(new Color(105, 105, 105));
		btnRes.setForeground(new Color(255, 255, 255));
		btnRes.setBounds(22, 460, 147, 35);
		btnRes.setFocusable(false);
		btnRes.addActionListener(this);
		panelLeft.add(btnRes);

		btnBar = new JButton("Bar");
		btnBar.setBackground(new Color(105, 105, 105));
		btnBar.setForeground(new Color(255, 255, 255));
		btnBar.setBounds(22, 510, 147, 35);
		btnBar.setFocusable(false);
		btnBar.addActionListener(this);
		panelLeft.add(btnBar);

		btnBill = new JButton("Generate Invoice");
		btnBill.setBackground(new Color(105, 105, 105));
		btnBill.setForeground(new Color(255, 255, 255));
		btnBill.setBounds(22, 560, 147, 35);
		btnBill.setFocusable(false);
		btnBill.addActionListener(this);
		panelLeft.add(btnBill);

		btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(105, 105, 105));
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setBounds(22, 610, 147, 35);
		btnLogout.setFocusable(false);
		btnLogout.addActionListener(this);
		panelLeft.add(btnLogout);

		btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(105, 105, 105));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBounds(22, 660, 147, 35);
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);
		panelLeft.add(btnExit);

		lblReceiptionist = new JLabel("- Staff");
		lblReceiptionist.setHorizontalAlignment(SwingConstants.LEFT);
		lblReceiptionist.setForeground(new Color(255, 255, 255));
		lblReceiptionist.setBounds(75, 140, 94, 14);
		panelLeft.add(lblReceiptionist);

		img = new JLabel();
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(70, 55, 50, 50);
		ImageIcon image = new ImageIcon(getClass().getResource("/staff.png"));
		Image imagel = (image).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		image = new ImageIcon(imagel);
		img.setIcon(image);
		panelLeft.add(img);

		setVisible(true);
	}

	// JButton action Performed method
	@Override
	public void actionPerformed(ActionEvent ae) {

		// close Login window
		if (ae.getSource() == btnAdd) {
			new AddCustomers();

		} else if (ae.getSource() == btnBook) {
			new ReceptionistBookRoom();
		} else if (ae.getSource() == btnCheckin) {
			new ReceptionistCheckin();
		} else if (ae.getSource() == btnService) {
			new Service();
		} else if (ae.getSource() == btnRes) {
			new Restaurant();
		} else if (ae.getSource() == btnBar) {
			new Bar();
		} else if (ae.getSource() == btnBill) {
			new Invoice();
		}else if (ae.getSource() == btnCheckout) {
			new ReceptionistCheckOut();
		} else if (ae.getSource() == btnLogout) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Log Out", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				this.dispose();
				new HomePage();
			}

		} else if (ae.getSource() == btnExit) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);

			}

		}
	}

//	public static void main(String[] args) {
//		new ReceptionistDashboard();
//	}

}
