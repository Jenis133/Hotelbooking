package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import library.ServiceLibs;
import middleware.ServiceJDBC;

public class ServiceViewAll extends JFrame {
	JPanel panelMain;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object obj[];
	ArrayList<ServiceLibs> array;
	JScrollPane scrollPane;
	JLabel lblTitle;
	JButton btnSearch;
	JTextField txtSearch;

	public ServiceViewAll() {
		setTitle("Service History");
		setBounds(280, 95, 1000, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 900, 500);
		panelMain.setBackground(new Color(143, 188, 143));
		panelMain.setLayout(null);
		add(panelMain);

//		// creating table
//		scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(0, 58, 984, 503);
//		panelMain.add(scrollPane);

		lblTitle = new JLabel("View All Service History");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Century Schoolbook", Font.BOLD, 25));
		lblTitle.setBounds(303, 11, 362, 44);
		panelMain.add(lblTitle);
//
//		btnSearch = new JButton("Search");
//		btnSearch.setFont(new Font("Arial", Font.PLAIN, 13));
//		btnSearch.setBounds(887, 29, 95, 20);
//		panelMain.add(btnSearch);
//
//		txtSearch = new JTextField();
//		txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
//		txtSearch.setBounds(734, 29, 143, 20);
//		panelMain.add(txtSearch);

		// creating table
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 58, 984, 503);
		panelMain.add(scrollPane);

		showTable();
		setVisible(true);

	}

	public void showTable() {
		obj = new Object[6];

		obj[0] = "Booking ID";
		obj[1] = "Room Number";
		obj[2] = "Customer's Name";
		obj[3] = "Service Type";
		obj[4] = "Service Name";
		obj[5] = "Price";
		dtm.setColumnIdentifiers(obj);
		refreshTable();
	}

	public void refreshTable() {
		ServiceJDBC jdbc = new ServiceJDBC();
		array = jdbc.getServiceHistory();
		dtm.setRowCount(0);
		for (ServiceLibs libs : array) {
			Object tmpRow[] = { libs.getBookingID(), libs.getRoomNo(), libs.getCustomerFname(), libs.getServiceType(),
					libs.getServiceName(), libs.getRate() };
			dtm.addRow(tmpRow);
		}

	}

	public static void main(String[] args) {
		new ServiceViewAll();
	}
}
