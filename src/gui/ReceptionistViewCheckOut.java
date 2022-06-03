package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import library.NCbookLibs;
import middleware.NCbookJDBC;
import javax.swing.JLabel;
import java.awt.Font;

public class ReceptionistViewCheckOut extends JFrame {
	JPanel panelMain;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object obj[];
	ArrayList<NCbookLibs> arrayCheckin;
	JScrollPane scrollPane;
	JLabel lblTitle;

	public ReceptionistViewCheckOut() {
		setTitle("View CheckOut Customers");
		setBounds(280, 95, 1000, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 900, 500);
		panelMain.setBackground(new Color(143, 188, 143));
		panelMain.setLayout(null);
		getContentPane().add(panelMain);

		// creating table
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 58, 984, 503);
		panelMain.add(scrollPane);

		lblTitle = new JLabel("ALL CUSTOMER'S CHECK OUT LIST");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Century Schoolbook", Font.BOLD, 25));
		lblTitle.setBounds(270, 11, 499, 44);
		panelMain.add(lblTitle);

		showTable();
		setVisible(true);

	}

	public void showTable() {
		obj = new Object[11];

		obj[0] = "Booking ID";
		obj[1] = "First Name";
		obj[2] = "Last Name";
		obj[3] = "Email";
		obj[4] = "Address";
//		obj[5] = "Credit Card";
		obj[5] = "Arrival Date";
		obj[6] = "Departure Date";
		obj[7] = "Room Type";
		obj[8] = "Status";
		obj[9] = "Type";
		obj[10] = "Room No";

		dtm.setColumnIdentifiers(obj);
		refreshTable();
	}

	public void refreshTable() {
		arrayCheckin = new NCbookJDBC().getCheckedOut();
		dtm.setRowCount(0);
		for (int i = 0; i < arrayCheckin.size(); i++) {
			NCbookLibs ncLibs = arrayCheckin.get(i);
			Object tmpRow[] = { ncLibs.getBookingID(), ncLibs.getFname(), ncLibs.getLname(), ncLibs.getEmail(),
					ncLibs.getAddress(), ncLibs.getArrivalDate(), ncLibs.getDepatureDate(), ncLibs.getRoomType(),
					ncLibs.getBookingStatus(), ncLibs.getRole(), ncLibs.getRoomNo() };
			dtm.addRow(tmpRow);
		}

	}

//	public static void main(String[] args) {
//		new ReceiptViewCheckin();
//	}

}
