//dd

//package name
package gui;

//importing libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import global.RoomG;
import library.BookingLibs;
import library.NCorpLibs;
import middleware.BookingJDBC;

//main class
public class CustomerBooking extends JFrame implements ActionListener, ItemListener {
	// global variable
	int customerID;
	BookingLibs bookingLibs = new BookingLibs();
	NCorpLibs noncorps = new NCorpLibs();

	// declaration
	JPanel mainPanel, panelLeft, panelTable;
	JTextField txtRoomPrice, txtCredit, txtPrice, txtExtraService;
	JLabel lblTitle, lblIntro, lblArrivalDate, lblDepatureDate, lblPrefferedRoom, lblCredit, lblPrice, lblExtraService;
	JDateChooser dateArrival, dateDepature;
	JComboBox cmbPrefferedRoom;
	JButton btnSave, btnUpdate, btnDelete, btnPay, btnClear, btnClose;
	JCheckBox chkTele, chkMiniBar, chkTv, chkAC;

	DefaultTableModel dtm = new DefaultTableModel();
	Object obj[];
	ArrayList<BookingLibs> bookings;
	JTable table;
	JScrollPane scrollPane;

	// sub class
	public CustomerBooking(int customerID) {

		this.customerID = customerID;

		// designing part start

		// frame and main panel
		setTitle("Booking Form");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(172, 65, 1201, 674);
		setResizable(false);

		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.activeCaption);
		mainPanel.setLayout(null);

		// jlabel , jtextfields ,combobox and jdatechooser
		lblTitle = new JLabel("Booking Form");
		lblTitle.setBounds(499, 0, 399, 56);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		mainPanel.add(lblTitle);

		panelLeft = new JPanel();
		panelLeft.setBounds(35, 81, 464, 497);
		panelLeft.setBackground(new Color(95, 158, 160));
		mainPanel.add(panelLeft);
		panelLeft.setLayout(null);

		lblIntro = new JLabel("Fill your booking form here");
		lblIntro.setBounds(104, 21, 299, 34);
		lblIntro.setForeground(new Color(230, 230, 250));
		lblIntro.setFont(new Font("Arial", Font.BOLD, 20));
		panelLeft.add(lblIntro);

		lblArrivalDate = new JLabel("Arrival Date : ");
		lblArrivalDate.setFont(new Font("Arial", Font.PLAIN, 16));
		lblArrivalDate.setBounds(30, 105, 103, 34);
		panelLeft.add(lblArrivalDate);

		dateArrival = new JDateChooser();
		dateArrival.setBounds(169, 113, 234, 20);
		dateArrival.setDateFormatString("yyyy-MM-dd");
		panelLeft.add(dateArrival);

		lblDepatureDate = new JLabel("Depature Date :");
		lblDepatureDate.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDepatureDate.setBounds(30, 160, 113, 24);
		panelLeft.add(lblDepatureDate);

		dateDepature = new JDateChooser();
		dateDepature.setBounds(169, 160, 234, 20);
		dateDepature.setDateFormatString("yyyy-MM-dd");
		panelLeft.add(dateDepature);

		lblPrefferedRoom = new JLabel("Preffered Room :");
		lblPrefferedRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPrefferedRoom.setBounds(30, 210, 200, 24);
		panelLeft.add(lblPrefferedRoom);

		cmbPrefferedRoom = new JComboBox();
		for (int i = 0; i < RoomG.PREFFERED_ROOM.length; i++) {
			cmbPrefferedRoom.addItem(RoomG.PREFFERED_ROOM[i]);
		}
		cmbPrefferedRoom.addItemListener(this);
		cmbPrefferedRoom.setBounds(169, 210, 234, 24);
		panelLeft.add(cmbPrefferedRoom);

		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPrice.setBounds(30, 254, 200, 24);
		panelLeft.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrice.setBounds(169, 254, 234, 24);
		panelLeft.add(txtPrice);

		// end of jlabel , jtextfields ,combobox and jdatechooser

		// creating buttons
		btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSave.setBounds(30, 422, 89, 23);
		panelLeft.add(btnSave);
		btnSave.addActionListener(this);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUpdate.setBounds(129, 422, 89, 23);
		panelLeft.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setBounds(228, 422, 89, 23);
		panelLeft.add(btnDelete);
		btnDelete.addActionListener(this);

//		btnPay = new JButton("PAY");
//		btnPay.setFont(new Font("Arial", Font.PLAIN, 12));
//		btnPay.setBounds(327, 422, 89, 23);
//		panelLeft.add(btnPay);
//		btnPay.addActionListener(this);

		btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Arial", Font.PLAIN, 12));
		btnClear.setBounds(330, 422, 89, 23);
		panelLeft.add(btnClear);
		btnClear.addActionListener(this);

		btnClose = new JButton("CLOSE");
		btnClose.setBounds(1062, 586, 89, 23);
		btnClose.setFont(new Font("Arial", Font.PLAIN, 12));
		panelLeft.add(btnClose);
		btnClose.addActionListener(this);
		// creating buttons end

		// designing part end

		// table set start
		panelTable = new JPanel();
		panelTable.setBounds(534, 81, 617, 497);
		panelTable.setBackground(new Color(95, 158, 160));
		panelTable.setLayout(null);
		mainPanel.add(panelTable);

		// scrollPane set to scroll the table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 617, 497);
		panelTable.add(scrollPane);
		table = new JTable(dtm);
		showTable();
		scrollPane.setViewportView(table);

		// Mouse click event to fetch the data of selected row from the table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ae) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				int bookingId = Integer.parseInt(model.getValueAt(i, 0).toString());
				bookingLibs.setBookingID(bookingId);
				int roomNo = Integer.parseInt(model.getValueAt(i, 5).toString());
				bookingLibs.setRoomNo(roomNo);
				String bookingStatus = model.getValueAt(i, 4).toString();
				bookingLibs.setBookingStatus(bookingStatus);
				try {
					Date dateA = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(i, 1).toString());
					Date dateD = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(i, 2).toString());
					dateArrival.setDate(dateA);
					dateDepature.setDate(dateD);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR : " + ex.getMessage());
				}
				String roomType = model.getValueAt(i, 3).toString();

				if (roomType.equals("Single")) {
					cmbPrefferedRoom.setSelectedIndex(1);
				} else if (roomType.equals("Double")) {
					cmbPrefferedRoom.setSelectedIndex(2);
				} else if (roomType.equals("Twin")) {
					cmbPrefferedRoom.setSelectedIndex(3);
				}

			}
		});
		// Mouse click event to fetch the data of selected row from the table end
		// table set end

		getContentPane().add(mainPanel);// adding in frame
		setVisible(true); // frame visibility true
	}

	// creating function to display columns in table
	public void showTable() {

		obj = new Object[6];
		obj[0] = "Booking ID";
		obj[1] = "Arrival Date";
		obj[2] = "Departure Date";
		obj[3] = "Room Type";
		obj[4] = "Booking Status";
		obj[5] = "Room Number";
		dtm.setColumnIdentifiers(obj);
		displayTable();
	}
	// creating columns in table end

	// adding rows in table start
	public void displayTable() {
		bookings = new BookingJDBC().getBookings(this.customerID);
		dtm.setRowCount(0);
		for (int i = 0; i < bookings.size(); i++) {
			BookingLibs booking = bookings.get(i);
			Object tmpRow[] = { booking.getBookingID(), booking.getArrivalDate(), booking.getDepatureDate(),
					booking.getPrefferedRoom(), booking.getBookingStatus(), booking.getRoomNo() };
			dtm.addRow(tmpRow);
		}
	}
	// adding rows in table end

	// button click events start
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnSave) {

			bookingLibs = new BookingLibs();
			String dateA = ((JTextField) dateArrival.getDateEditor().getUiComponent()).getText();
			String dateD = ((JTextField) dateDepature.getDateEditor().getUiComponent()).getText();

			bookingLibs.setArrivalDate(dateA);
			bookingLibs.setDepatureDate(dateD);
			bookingLibs.setPrefferedRoom(cmbPrefferedRoom.getSelectedItem().toString());
			bookingLibs.setRegID(customerID);

			BookingJDBC jdbc = new BookingJDBC();
			boolean result = jdbc.save(bookingLibs);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Booking request sent successfully.");
				dateArrival.setDate(null);
				dateDepature.setDate(null);
				cmbPrefferedRoom.setSelectedIndex(0);

				displayTable();
			}

		} else if (ae.getSource() == btnUpdate) {
			if (bookingLibs.getBookingStatus().toString().equals("Checked in")) {
				JOptionPane.showMessageDialog(null, "Update denied!! You have already checked in the room.");

			} else {
				String dateA = ((JTextField) dateArrival.getDateEditor().getUiComponent()).getText();
				String dateD = ((JTextField) dateDepature.getDateEditor().getUiComponent()).getText();
				bookingLibs.setArrivalDate(dateA);
				bookingLibs.setDepatureDate(dateD);
				bookingLibs.setPrefferedRoom(cmbPrefferedRoom.getSelectedItem().toString());

				BookingJDBC jdbc = new BookingJDBC();
				boolean result = jdbc.update(bookingLibs);
				if (result == true) {
					JOptionPane.showMessageDialog(null, "Update Successful");
					dateArrival.setDate(null);
					dateDepature.setDate(null);
					cmbPrefferedRoom.setSelectedIndex(0);
					displayTable();
				} else {
					JOptionPane.showMessageDialog(null, "Update Unsuccessful");
				}

			}

		} else if (ae.getSource() == btnDelete) {
			if (bookingLibs.getBookingStatus().toString().equals("Checked in")) {
				JOptionPane.showMessageDialog(null, "Cannot delete this data!! You have already checked in the room.");

			} else {

				BookingJDBC jdbc = new BookingJDBC();
				boolean result = jdbc.delete(bookingLibs);
				if (result == true) {
					JOptionPane.showMessageDialog(null, "Delete successful");
					dateArrival.setDate(null);
					dateDepature.setDate(null);
					cmbPrefferedRoom.setSelectedIndex(0);
					displayTable();
				} else {
					JOptionPane.showMessageDialog(null, "Delete unsuccessful");
				}
			}

//		} else if (ae.getSource() == btnPay) {
//			JOptionPane.showMessageDialog(null, "Permission denined!!");

		} else if (ae.getSource() == btnClose) {
			this.dispose();
		} else if (ae.getSource() == btnClear) {
			dateArrival.setDate(null);
			dateDepature.setDate(null);
			cmbPrefferedRoom.setSelectedIndex(0);

		}
	}
	// button click events end

	// shows room price according to room type item listener method start
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbPrefferedRoom) {
			int i = cmbPrefferedRoom.getSelectedIndex();
			txtPrice.setText(RoomG.ROOM_PRICE[i].toString());
		}

	}
	// shows room price according to room type item listener method end

//	public static void main(String[] args) {
//		new CustomerBooking(1);
//
//	}

}
