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

import library.NCbookLibs;
import library.RestaurantLibs;
import library.ServiceLibs;
import middleware.RestaurantJDBC;

public class RestaurantViewAll extends JFrame {
	JPanel panelMain;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object obj[];
	ArrayList<NCbookLibs> arrayCheckin;
	JScrollPane scrollPane;
	JLabel lblTitle;

	ArrayList<RestaurantLibs> array;

	public RestaurantViewAll() {
		setTitle("Restaurant History");
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

		lblTitle = new JLabel("View All Restaurant History");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Century Schoolbook", Font.BOLD, 25));
		lblTitle.setBounds(280, 11, 450, 44);
		panelMain.add(lblTitle);

		showTable();
		setVisible(true);

	}

	public void showTable() {
		obj = new Object[7];

		obj[0] = "Booking ID";
		obj[1] = "Room Number";
		obj[2] = "Customer's Name";
		obj[3] = "Item Name";
		obj[4] = "Rate";
		obj[5] = "Quantity";
		obj[6] = "Total Amount";
		dtm.setColumnIdentifiers(obj);
		refreshTable();
	}

	public void refreshTable() {
		RestaurantJDBC jdbc = new RestaurantJDBC();
		array = jdbc.getRestaurantHistory();
		dtm.setRowCount(0);
		for (RestaurantLibs libs : array) {
			Object tmpRow[] = { libs.getBookingID(), libs.getRoomNo(), libs.getCustomerFname(), libs.getMenu(),
					libs.getRate(), libs.getQuantity(), libs.getRate() * libs.getQuantity() };
			dtm.addRow(tmpRow);
		}
	}

//	public static void main(String[] args) {
//		new RestaurantViewAll();
//	}
}
