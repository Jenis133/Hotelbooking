package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import library.RestaurantLibs;
import middleware.RestaurantJDBC;

public class Restaurant extends JFrame implements ItemListener, ActionListener, KeyListener {

	// declaration , initialization and memory allocation
	RestaurantLibs libs = new RestaurantLibs();
	RestaurantJDBC jdbc = new RestaurantJDBC();

	JPanel mainPanel, panelMiddle, panelLow;
	JTextField txtFname, txtRate, txtQty, txtAmt, txtArrivalDate, txtLname, txtDate, textField;
	JLabel lblTitle, lblEmail, lblContact, lblRoomNo, lblName, lblRate, lblQty, lblAmt, lblItemName, lblArrivalDate,
			date;
	JComboBox cmbRoomNo, cmbItemName;
	JButton btnAdd, btnViewAll;

	// declaration to show table
	DefaultTableModel dtm = new DefaultTableModel();
	Object obj[];
	JTable table;
	JScrollPane scrollPane;

	// declaration for arraylist
	ArrayList array = new ArrayList();

//	ArrayList<NCbookLibs> arrayCheckin;

	public Restaurant() {
		setTitle("Restaurant Service");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(190, 65, 1183, 674);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(143, 188, 143));
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		lblTitle = new JLabel("Best Restaurant : GOOD FOOD GOOD MOOD!!");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 25));
		lblTitle.setBounds(288, 1, 585, 40);
		mainPanel.add(lblTitle);

		panelLow = new JPanel();
		panelLow.setBackground(new Color(0, 0, 0));
		panelLow.setForeground(new Color(0, 0, 0));
		panelLow.setBounds(0, 587, 1167, 48);
		mainPanel.add(panelLow);
		panelLow.setLayout(null);

		lblEmail = new JLabel("restaurant123@gmail.com");
		lblEmail.setBounds(417, 0, 182, 18);
		panelLow.add(lblEmail);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setForeground(new Color(255, 255, 255));

		lblContact = new JLabel("01-5538938");
		lblContact.setFont(new Font("Arial", Font.BOLD, 13));
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setForeground(new Color(255, 255, 255));
		lblContact.setBounds(448, 15, 105, 24);
		panelLow.add(lblContact);

		lblRoomNo = new JLabel("Room No :");
		lblRoomNo.setFont(new Font("Arial", Font.BOLD, 13));
		lblRoomNo.setBounds(10, 75, 74, 21);
		mainPanel.add(lblRoomNo);

		cmbRoomNo = new JComboBox();
		showRoomNo();
		cmbRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbRoomNo.setBounds(93, 75, 62, 21);
		cmbRoomNo.addItemListener(this);
		mainPanel.add(cmbRoomNo);

		lblName = new JLabel("Guest Name:");
		lblName.setFont(new Font("Arial", Font.BOLD, 13));
		lblName.setBounds(361, 75, 105, 21);
		mainPanel.add(lblName);

		txtFname = new JTextField();
		txtFname.setEditable(false);
		txtFname.setBounds(458, 72, 132, 21);
		mainPanel.add(txtFname);

		txtLname = new JTextField();
		txtLname.setEditable(false);
		txtLname.setColumns(10);
		txtLname.setBounds(599, 72, 132, 21);
		mainPanel.add(txtLname);

		panelMiddle = new JPanel();
		panelMiddle.setBackground(new Color(46, 139, 87));
		panelMiddle.setBounds(10, 106, 1147, 470);
		mainPanel.add(panelMiddle);
		panelMiddle.setLayout(null);

		btnAdd = new JButton("Add ");
		btnAdd.setBackground(new Color(95, 158, 160));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(this);
//		
		btnAdd.setBounds(1040, 14, 95, 20);
		panelMiddle.add(btnAdd);

		lblRate = new JLabel("Rate:");
		lblRate.setForeground(Color.WHITE);
		lblRate.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRate.setBounds(10, 42, 100, 23);
		panelMiddle.add(lblRate);

		txtRate = new JTextField();
		txtRate.setFont(new Font("Arial", Font.PLAIN, 14));
		txtRate.setBounds(126, 42, 181, 20);
		txtRate.setFocusable(false);
		panelMiddle.add(txtRate);

		lblQty = new JLabel("Quantity:");
		lblQty.setForeground(Color.WHITE);
		lblQty.setFont(new Font("Arial", Font.PLAIN, 14));
		lblQty.setBounds(734, 14, 100, 23);
		panelMiddle.add(lblQty);

		txtQty = new JTextField();
		txtQty.addKeyListener(this);
		txtQty.setFont(new Font("Arial", Font.PLAIN, 14));
		txtQty.setColumns(10);
		txtQty.setBounds(850, 14, 181, 20);

		panelMiddle.add(txtQty);

		lblAmt = new JLabel("Total Amount:");
		lblAmt.setForeground(Color.WHITE);
		lblAmt.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAmt.setBounds(734, 40, 146, 23);
		panelMiddle.add(lblAmt);

		txtAmt = new JTextField();
		txtAmt.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAmt.setColumns(10);
		txtAmt.setBounds(850, 41, 181, 20);
		txtAmt.setFocusable(false);
		panelMiddle.add(txtAmt);

		lblItemName = new JLabel("Item Name:");

		lblItemName.setForeground(Color.WHITE);
		lblItemName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblItemName.setBounds(10, 14, 121, 23);
		panelMiddle.add(lblItemName);

		cmbItemName = new JComboBox();
		showItemName();
		cmbItemName.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbItemName.setBounds(126, 14, 181, 20);
		cmbItemName.addItemListener(this);
		panelMiddle.add(cmbItemName);

		btnViewAll = new JButton("View All");
		btnViewAll.setForeground(new Color(255, 255, 255));
		btnViewAll.setBackground(new Color(154, 205, 50));
		btnViewAll.setBounds(1040, 41, 95, 20);
		btnViewAll.addActionListener(this);
		panelMiddle.add(btnViewAll);

		lblArrivalDate = new JLabel("Arrival Date:");
		lblArrivalDate.setFont(new Font("Arial", Font.BOLD, 12));
		lblArrivalDate.setBounds(971, 75, 77, 21);
		mainPanel.add(lblArrivalDate);

		txtArrivalDate = new JTextField();
		txtArrivalDate.setEditable(false);
		txtArrivalDate.setBounds(1051, 75, 105, 20);
		mainPanel.add(txtArrivalDate);
		txtArrivalDate.setColumns(10);

		date = new JLabel("Date:");
		date.setFont(new Font("Arial", Font.BOLD, 12));
		date.setBounds(1004, 11, 62, 21);
		mainPanel.add(date);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(1045, 11, 112, 20);
		txtDate.setText(LocalDate.now().toString());
		mainPanel.add(txtDate);

		// scrollPane set to scroll the table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 1127, 359);
		panelMiddle.add(scrollPane);
		table = new JTable(dtm);
		showTable();
		scrollPane.setViewportView(table);

		setVisible(true);

	}

	// creating function to display columns in table
	public void showTable() {

		obj = new Object[8];
		obj[0] = "Order Date";
		obj[1] = "Booking ID";
		obj[2] = "Room No";
		obj[3] = "Name";
		obj[4] = "Restaurant Item";
		obj[5] = "Price";
		obj[6] = "Quantity";
		obj[7] = "Total";
		dtm.setColumnIdentifiers(obj);
		displayTable();
	}
	// creating columns in table end

	// adding rows in table start
	public void displayTable() {

	}

	// to get room number of all the checked in rooms start
	public void showRoomNo() {
		cmbRoomNo.addItem("Select");
		array = jdbc.getOccupiedRoom();
		for (int i = 0; i < array.size(); i++) {
			cmbRoomNo.addItem(array.get(i));
		}
	}
	// to get room number of all the checked in rooms end

	// to get the customers details by passing room number start
	public void showCustomerInfo() {
		array = jdbc.getCustomerDetails(libs);
		for (int i = 0; i < array.size(); i++) {
			txtFname.setText(libs.getCustomerFname());
			txtLname.setText(libs.getCustomerLname());
			txtArrivalDate.setText(libs.getArrivalDate());
		}

	}
	// to get the customers details by passing room number end

	// to get item start
	public void showItemName() {
		cmbItemName.addItem("Select");
		array = jdbc.getRestItemName();
		for (int i = 0; i < array.size(); i++) {
			cmbItemName.addItem(array.get(i));
		}

	}
	// to get item end

	// menuID Start
	public void getMenuID() {
		int id = jdbc.getMenuID(libs);
		libs.setmenuID(id);
		System.out.println(libs.getmenuID());
	}

	// menuID End

	// to get restaurant item price after selecting restaurat item start
	public void showItemPrice() {
		double rate = jdbc.getRestaurantPrice(libs);
		txtRate.setText(Double.toString(rate));
	}
	// to get restaurant item price after selecting restaurat item end

//	public static void main(String[] args) {
//		new Restaurant();
//	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbRoomNo) {
			libs.setRoomNo(Integer.parseInt(cmbRoomNo.getSelectedItem().toString()));
			showCustomerInfo();

		} else if (e.getSource() == cmbItemName) {
			if (cmbItemName.getSelectedIndex() > 0) {
				libs.setMenu(cmbItemName.getSelectedItem().toString());
				showItemPrice();
				getMenuID();
			} else {
				txtRate.setText(null);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			libs.setDate(txtDate.getText());
			libs.setQuantity(Integer.parseInt(txtQty.getText()));
			libs.setTotal(Double.parseDouble(txtAmt.getText()));
			boolean result = jdbc.insertRestaurant(libs);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Item Added Successfully!! ");
				Object obj[] = { txtDate.getText(), libs.getBookingID(), cmbRoomNo.getSelectedItem().toString(),
						txtFname.getText(), cmbItemName.getSelectedItem().toString(), txtRate.getText(),
						txtQty.getText(), txtAmt.getText() };

				dtm.addRow(obj);
			} else {
				JOptionPane.showMessageDialog(null, "Failed to Add Item!! ");
			}
		}
		if (e.getSource() == btnViewAll) {
			new RestaurantViewAll();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (cmbItemName.getSelectedIndex() > 0) {
			libs.setQuantity(Integer.parseInt(txtQty.getText()));
			libs.setRate(Double.parseDouble(txtRate.getText()));
			jdbc.calculateTotal(libs);
			txtAmt.setText(Double.toString(libs.getTotal()));

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (cmbItemName.getSelectedIndex() > 0) {
			libs.setQuantity(Integer.parseInt(txtQty.getText()));
			libs.setRate(Double.parseDouble(txtRate.getText()));
			jdbc.calculateTotal(libs);
			txtAmt.setText(Double.toString(libs.getTotal()));
		}

	}

}
