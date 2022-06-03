//package name
package gui;

//importing libraries
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

import library.ServiceLibs;
import middleware.ServiceJDBC;

//main class
public class Service extends JFrame implements ItemListener, ActionListener, KeyListener {
	// declaration , initialization and memory allocation
	ServiceLibs libs = new ServiceLibs();
	ServiceJDBC jdbc = new ServiceJDBC();

	// declaration
	JPanel mainPanel, panelMiddle, panelLow;
	JTextField txtFname, txtRate, txtLname, txtDate, txtCheckin;
	JLabel lblTitle, lblEmail, lblContact, lblRoomNo, lblName, lblServiceType, lblRate, lblSeviceName, lblDate,
			lblCheckin;
	JComboBox cmbRoomNo, cmbServiceType, cmbServiceName;
	JButton btnAdd, btnViewAll;

	// declaration to show table
	DefaultTableModel dtm = new DefaultTableModel();
	Object obj[];
	JTable table;
	JScrollPane scrollPane;

	// declaration for arraylist
	ArrayList array = new ArrayList();

	// sub class
	public Service() {
		setTitle("Internal & External Services");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(190, 65, 1183, 674);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(143, 188, 143));
		mainPanel.setLayout(null);

		lblTitle = new JLabel("Best Service Available in Hotel Luton");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Constantia", Font.BOLD, 30));
		lblTitle.setBounds(323, 2, 562, 40);
		mainPanel.add(lblTitle);

		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Arial", Font.BOLD, 12));
		lblDate.setBounds(1007, 12, 62, 21);
		mainPanel.add(lblDate);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(1045, 12, 114, 20);
		txtDate.setText(LocalDate.now().toString());
		mainPanel.add(txtDate);
		txtDate.setColumns(10);

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
		lblName.setBounds(410, 75, 105, 21);
		mainPanel.add(lblName);

		txtFname = new JTextField();
		txtFname.setEditable(false);
		txtFname.setBounds(512, 72, 132, 21);
		mainPanel.add(txtFname);

		txtLname = new JTextField();
		txtLname.setEditable(false);
		txtLname.setColumns(10);
		txtLname.setBounds(654, 72, 132, 21);
		mainPanel.add(txtLname);

		lblCheckin = new JLabel("Checkin Date:");
		lblCheckin.setFont(new Font("Arial", Font.BOLD, 12));
		lblCheckin.setBounds(954, 75, 89, 21);
		mainPanel.add(lblCheckin);

		txtCheckin = new JTextField();
		txtCheckin.setEditable(false);
		txtCheckin.setBounds(1043, 75, 114, 20);
		mainPanel.add(txtCheckin);

		panelMiddle = new JPanel();
		panelMiddle.setBackground(new Color(46, 139, 87));
		panelMiddle.setBounds(10, 104, 1149, 476);
		mainPanel.add(panelMiddle);
		panelMiddle.setLayout(null);

		lblServiceType = new JLabel("Service Type:");
		lblServiceType.setForeground(Color.WHITE);
		lblServiceType.setFont(new Font("Arial", Font.PLAIN, 14));
		lblServiceType.setBounds(10, 17, 121, 23);
		panelMiddle.add(lblServiceType);

		cmbServiceType = new JComboBox();
		showServiceType();
		cmbServiceType.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbServiceType.setBounds(126, 17, 181, 20);
		panelMiddle.add(cmbServiceType);
		cmbServiceType.addItemListener(this);

		lblSeviceName = new JLabel("Sevice Name:");
		lblSeviceName.setForeground(Color.WHITE);
		lblSeviceName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSeviceName.setBounds(443, 17, 121, 23);
		panelMiddle.add(lblSeviceName);

		cmbServiceName = new JComboBox();
		cmbServiceName.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbServiceName.setBounds(559, 17, 181, 20);
		cmbServiceName.addItemListener(this);
		panelMiddle.add(cmbServiceName);

		lblRate = new JLabel("Price:");
		lblRate.setForeground(Color.WHITE);
		lblRate.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRate.setBounds(895, 17, 100, 23);
		panelMiddle.add(lblRate);

		txtRate = new JTextField();
		txtRate.setFont(new Font("Arial", Font.PLAIN, 14));
		txtRate.setBounds(958, 17, 181, 20);
		txtRate.setEditable(false);
		panelMiddle.add(txtRate);

		btnAdd = new JButton("Add To Cart");
		btnAdd.setBackground(new Color(95, 158, 160));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(471, 69, 100, 20);
		btnAdd.addActionListener(this);
		panelMiddle.add(btnAdd);

		btnViewAll = new JButton("View All");
		btnViewAll.setForeground(new Color(255, 255, 255));
		btnViewAll.setBackground(new Color(154, 205, 50));
		btnViewAll.setBounds(606, 69, 100, 20);
		btnViewAll.addActionListener(this);
		panelMiddle.add(btnViewAll);

		panelLow = new JPanel();
		panelLow.setBackground(new Color(0, 0, 0));
		panelLow.setForeground(new Color(0, 0, 0));
		panelLow.setBounds(0, 587, 1167, 48);
		mainPanel.add(panelLow);
		panelLow.setLayout(null);

		lblEmail = new JLabel("services123@gmail.com");
		lblEmail.setBounds(521, 0, 182, 18);
		panelLow.add(lblEmail);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setForeground(new Color(255, 255, 255));

		lblContact = new JLabel("01-5538938");
		lblContact.setFont(new Font("Arial", Font.BOLD, 13));
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setForeground(new Color(255, 255, 255));
		lblContact.setBounds(552, 15, 105, 24);
		panelLow.add(lblContact);

		// scrollPane set to scroll the table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 1129, 365);
		panelMiddle.add(scrollPane);
		table = new JTable(dtm);
		showTable();
		scrollPane.setViewportView(table);

		getContentPane().add(mainPanel);
		setVisible(true);

	}

	// creating function to display columns in table
	public void showTable() {

		obj = new Object[7];
		obj[0] = "Booking ID";
		obj[1] = "Room No";
		obj[2] = "Name";
		obj[3] = "Date";
		obj[4] = "Service Type";
		obj[5] = "Service Name";
		obj[6] = "Price";
		dtm.setColumnIdentifiers(obj);
	}
	// creating columns in table end

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
			txtCheckin.setText(libs.getArrivalDate());
		}

	}
	// to get the customers details by passing room number end

	// to get type of services start
	public void showServiceType() {
		cmbServiceType.addItem("Select");
		array = jdbc.getServiceType();
		for (int i = 0; i < array.size(); i++) {
			cmbServiceType.addItem(array.get(i));
		}

	}
	// to get type of services end

	// to get Service name after selecting service type start
	public void showServiceInfo() {
		cmbServiceName.removeAllItems();
		cmbServiceName.addItem("Select");
		array = jdbc.getServiceDetails(libs);
		for (int i = 0; i < array.size(); i++) {
			cmbServiceName.addItem(array.get(i));
		}

	}
	// to get Service name after selecting service type end

	// to get service price after selecting service name start
	public void showServicePrice() {
		double rate = jdbc.getServicePrice(libs);
		txtRate.setText(Double.toString(rate));
	}
	// to get service price after selecting service name end

	// to get serviceID start
	public void showServiceID() {
		int id = jdbc.getServiceID(libs);
		libs.setServiceID(id);
	}
	// to get serviceID end

//	public void showTotalAmount() {
//		double rate = Double.parseDouble((txtRate.getText()));
//		int qty = Integer.parseInt((txtQty.getText()));
//		double total = rate * qty;
//		txtAmt.setText(Double.toString(total));
//
//	}

	public static void main(String[] args) {
		new Service();

	}

	// to get customer data after selecting the room number start
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbRoomNo) {
			String check = cmbRoomNo.getSelectedItem().toString();
			if (check.equals("Select")) {
				JOptionPane.showMessageDialog(null, "Select room no");
			} else {
				libs.setRoomNo(Integer.parseInt(cmbRoomNo.getSelectedItem().toString()));
				showCustomerInfo();
			}

		} else if (e.getSource() == cmbServiceType) {
			libs.setServiceType(cmbServiceType.getSelectedItem().toString());
			showServiceInfo();
		} else if (e.getSource() == cmbServiceName) {
			if (cmbServiceName.getSelectedIndex() > 0) {
				libs.setServiceName(cmbServiceName.getSelectedItem().toString());
				showServicePrice();
				showServiceID();

			} else {
				txtRate.setText(null);
			}

		}
	}
	// to get customer data after selecting the room number end

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			libs.setDate(txtDate.getText());
			boolean result = jdbc.insertService(libs);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Item Added Successfully!! ");

				Object obj[] = { libs.getBookingID(), cmbRoomNo.getSelectedItem().toString(), txtFname.getText(),
						txtDate.getText(), cmbServiceType.getSelectedItem().toString(),
						cmbServiceName.getSelectedItem().toString(), txtRate.getText() };

				dtm.addRow(obj);
				cmbServiceType.setSelectedIndex(0);
				cmbServiceName.setSelectedItem(0);
				txtRate.setText(null);

			} else {
				JOptionPane.showMessageDialog(null, "Failed to Add Item!! ");
			}

		} else if (e.getSource() == btnViewAll) {
			new ServiceViewAll();

		}
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
