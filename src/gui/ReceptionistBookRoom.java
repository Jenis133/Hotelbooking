//package name
package gui;

//importing libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.TableModel;

import library.NCbookLibs;
import library.ProfileLibs;
import library.UpdateLibs;
import middleware.CheckinJDBC;
import middleware.NCbookJDBC;

//main class
public class ReceptionistBookRoom extends JFrame implements ActionListener, ItemListener {

	// global variable
	UpdateLibs updatelibs = new UpdateLibs();
	ProfileLibs pro = new ProfileLibs();

	// declaration
	JPanel mainPanel, panelMiddle;
	JTextField txtCredit, txtArrivalDate, txtDepatureDate, txtRoomType, txtBookingID;
	JButton btnBooked, btnNotAvailable, btnNotGuaranteed;
	JLabel lblDate, lblCreditCard, lblTitle, lblArrivalDate, lblDepartureDate, lblRoomType, lblRoomNo, lblBookingId,
			lblCustomerType;
	JComboBox cmbRoomNo;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object obj[];
	ArrayList<NCbookLibs> arrayBook, arrayRoomNo;
	JScrollPane scrollPane;
	JTextField txtCustomerType;

	// sub class
	public ReceptionistBookRoom() {

		// frame and main panel
		setTitle("Customer Bookings Confirmation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(190, 65, 1183, 674);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(240, 230, 140));
		mainPanel.setLayout(null);
		add(mainPanel);

		// creating label for Title start
		lblTitle = new JLabel("Book A Room");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Modern No. 20", Font.BOLD, 35));
		lblTitle.setBounds(453, 0, 228, 52);
		mainPanel.add(lblTitle);
		// creating label for Title end

		// creating label for bookingID start
		lblBookingId = new JLabel("Booking ID:");
		lblBookingId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBookingId.setBounds(55, 54, 81, 20);
		mainPanel.add(lblBookingId);
		// creating label for bookingID end

		// creating textfield for bookingID start
		txtBookingID = new JTextField();
		txtBookingID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBookingID.setEditable(false);
		txtBookingID.setBounds(159, 54, 56, 20);
		mainPanel.add(txtBookingID);
		// creating textfield for bookingID end

		// creating label for Credit Card Info start
		lblCreditCard = new JLabel("Credit Card Info :");
		lblCreditCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCreditCard.setForeground(new Color(0, 0, 0));
		lblCreditCard.setBounds(54, 113, 110, 14);
		mainPanel.add(lblCreditCard);
		// creating label for Credit Card Info end

		// creating textfield for Credit Card Info start
		txtCredit = new JTextField();
		txtCredit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCredit.setEditable(false);
		txtCredit.setBounds(160, 110, 143, 20);
		mainPanel.add(txtCredit);
		// creating textfield for Credit Card Info end

		// creating label for Arrival date
		lblArrivalDate = new JLabel("Arrival Date : ");
		lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArrivalDate.setForeground(new Color(0, 0, 0));
		lblArrivalDate.setBounds(55, 151, 81, 20);
		mainPanel.add(lblArrivalDate);
		// creating label for Arrival end

		// creating textfield for arrival date start
		txtArrivalDate = new JTextField();
		txtArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtArrivalDate.setEditable(false);
		txtArrivalDate.setBounds(161, 151, 140, 20);
		mainPanel.add(txtArrivalDate);
		// creating textfield for arrival date end

		// creating label for Departure Date Info start
		lblDepartureDate = new JLabel("Departure Date : ");
		lblDepartureDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepartureDate.setForeground(new Color(0, 0, 0));
		lblDepartureDate.setBounds(815, 150, 138, 14);
		mainPanel.add(lblDepartureDate);
		// creating label for Departure Date end

		// creating textfield for Departure date start
		txtDepatureDate = new JTextField();
		txtDepatureDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDepatureDate.setEditable(false);
		txtDepatureDate.setBounds(953, 147, 152, 20);
		mainPanel.add(txtDepatureDate);
		// creating textfield for Departure date end

		// creating label for Room Type start
		lblRoomType = new JLabel("Room Type : ");
		lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRoomType.setForeground(new Color(0, 0, 0));
		lblRoomType.setBounds(55, 196, 126, 14);
		mainPanel.add(lblRoomType);
		// creating label for Room Type end

		// creating textfield for Room Type start
		txtRoomType = new JTextField();
		txtRoomType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRoomType.setEditable(false);
		txtRoomType.setBounds(159, 193, 140, 20);
		mainPanel.add(txtRoomType);
		// creating textfield for Room Type end

		// creating label for Room Number start
		lblRoomNo = new JLabel("Available Room No:");
		lblRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRoomNo.setForeground(new Color(0, 0, 0));
		lblRoomNo.setBounds(815, 193, 126, 14);
		mainPanel.add(lblRoomNo);
		// creating label for Room Number end

		// creating combo box for room no start
		cmbRoomNo = new JComboBox();
		cmbRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbRoomNo.setBounds(953, 187, 152, 22);
		cmbRoomNo.addItemListener(this);
		mainPanel.add(cmbRoomNo);
		// creating combo box for room no end

		// creating buttons start
		btnBooked = new JButton("Booked");
		btnBooked.setForeground(new Color(255, 255, 255));
		btnBooked.setBackground(new Color(0, 128, 0));
		btnBooked.setBounds(364, 223, 110, 23);
		mainPanel.add(btnBooked);
		btnBooked.addActionListener(this);

		btnNotAvailable = new JButton("Not Available");
		btnNotAvailable.setForeground(new Color(255, 255, 255));
		btnNotAvailable.setBackground(new Color(0, 128, 128));
		btnNotAvailable.setBounds(506, 223, 126, 23);
		mainPanel.add(btnNotAvailable);
		btnNotAvailable.addActionListener(this);

		btnNotGuaranteed = new JButton("Not Guaranteed");
		btnNotGuaranteed.setForeground(new Color(255, 255, 255));
		btnNotGuaranteed.setBackground(new Color(255, 0, 0));
		btnNotGuaranteed.setBounds(658, 223, 122, 23);
		mainPanel.add(btnNotGuaranteed);
		btnNotGuaranteed.addActionListener(this);
		// creating buttons end

		// table set start

		// scrollPane set to scroll the table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 262, 1145, 362);
		mainPanel.add(scrollPane);
		showTable();
		scrollPane.setViewportView(table);

		lblCustomerType = new JLabel("Customer Type:");
		lblCustomerType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCustomerType.setForeground(new Color(0, 0, 0));
		lblCustomerType.setBounds(814, 113, 138, 14);
		mainPanel.add(lblCustomerType);

		txtCustomerType = new JTextField();
		txtCustomerType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCustomerType.setEditable(false);
		txtCustomerType.setBounds(953, 110, 152, 20);
		mainPanel.add(txtCustomerType);

		panelMiddle = new JPanel();
		panelMiddle.setBackground(new Color(204, 204, 102));
		panelMiddle.setBounds(10, 85, 1145, 171);
		mainPanel.add(panelMiddle);

		// Mouse click event to fetch the data of selected row from the table start
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ae) {
				cmbRoomNo.removeAllItems();
				int i = table.getSelectedRow();
				txtBookingID.setText(table.getValueAt(i, 0).toString());
				txtCredit.setText(table.getValueAt(i, 5).toString());
				txtCustomerType.setText(table.getValueAt(i, 6).toString());
				txtRoomType.setText(table.getValueAt(i, 9).toString());
				txtArrivalDate.setText(table.getValueAt(i, 7).toString());
				txtDepatureDate.setText(table.getValueAt(i, 8).toString());

				arrayRoomNo = new ArrayList();
				arrayRoomNo = new CheckinJDBC().getRoomNo(txtRoomType.getText());
				for (int j = 0; j < arrayRoomNo.size(); j++) {
					cmbRoomNo.addItem(arrayRoomNo.get(j));
				}
			}

		});
		// Mouse click event to fetch the data of selected row from the table end

		setVisible(true); // frame visibility
	}

	// creating function to display columns in table
	public void showTable() {
		obj = new Object[11];

		obj[0] = "Booking ID";
		obj[1] = "First Name";
		obj[2] = "Last Name";
		obj[3] = "Email";
		obj[4] = "Address";
		obj[5] = "Credit Card";
		obj[6] = "Customer Type";
		obj[7] = "Arrival Date";
		obj[8] = "Departure Date";
		obj[9] = "Room Type";
		obj[10] = "Status";

		dtm.setColumnIdentifiers(obj);
		refreshTable();
	}
	// creating columns in table end

	// adding rows in table start
	public void refreshTable() {
		arrayBook = new NCbookJDBC().getPending();
		dtm.setRowCount(0);
		for (int i = 0; i < arrayBook.size(); i++) {
			NCbookLibs ncLibs = arrayBook.get(i);
			Object tmpRow[] = { ncLibs.getBookingID(), ncLibs.getFname(), ncLibs.getLname(), ncLibs.getEmail(),
					ncLibs.getAddress(), ncLibs.getCreditCard(), ncLibs.getRole(), ncLibs.getArrivalDate(),
					ncLibs.getDepatureDate(), ncLibs.getRoomType(), ncLibs.getBookingStatus(), ncLibs.getRole() };
			dtm.addRow(tmpRow);
		}

	}
	// adding rows in table end

	// action perform event start
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnBooked) {
			int i = table.getSelectedRow();
			TableModel model = table.getModel();
			int bookingID = Integer.parseInt(model.getValueAt(i, 0).toString());
			updatelibs.setBookingID(bookingID);
			updatelibs.setRoomNo(Integer.parseInt(cmbRoomNo.getSelectedItem().toString()));

			NCbookJDBC bookJDBC = new NCbookJDBC();
			boolean result = bookJDBC.update(updatelibs);

			if (result == true) {
				JOptionPane.showMessageDialog(null, "BOOKED A ROOM successful");
				txtCredit.setText(null);
				txtArrivalDate.setText(null);
				txtDepatureDate.setText(null);
				txtRoomType.setText(null);
				cmbRoomNo.setSelectedItem(null);

				refreshTable();
			} else {
				JOptionPane.showMessageDialog(null, "BOOKED A ROOM unsuccessful");
			}
		} else if (ae.getSource() == btnNotAvailable) {
			int i = table.getSelectedRow();
			TableModel model = table.getModel();
			int bookingID = Integer.parseInt(model.getValueAt(i, 0).toString());
			updatelibs.setBookingID(bookingID);

			NCbookJDBC bookJDBC = new NCbookJDBC();
			boolean result = bookJDBC.notAvailable(updatelibs);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Room Not Available message SENT");
				txtCredit.setText(null);
				txtArrivalDate.setText(null);
				txtDepatureDate.setText(null);
				txtRoomType.setText(null);
				cmbRoomNo.setSelectedItem(null);
				refreshTable();
			} else {
				JOptionPane.showMessageDialog(null, "Room Not Available message NOT SENT");
			}

		} else if (ae.getSource() == btnNotGuaranteed) {
			int i = table.getSelectedRow();
			TableModel model = table.getModel();
			int bookingID = Integer.parseInt(model.getValueAt(i, 0).toString());
			updatelibs.setBookingID(bookingID);

			NCbookJDBC bookJDBC = new NCbookJDBC();
			boolean result = bookJDBC.notGuaranteed(updatelibs);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Unguaranteed message SENT!");
				txtCredit.setText(null);
				txtArrivalDate.setText(null);
				txtDepatureDate.setText(null);
				txtRoomType.setText(null);
				cmbRoomNo.setSelectedItem(null);

				refreshTable();
			} else {
				JOptionPane.showMessageDialog(null, "Unguaranteed message NOT SENT!");
			}
		}
	}
	// action perform event start

//	public static void main(String[] args) {
//		new ReceptionistBookRoom();
//	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}
}
