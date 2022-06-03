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

import library.BarLibs;
import library.NCbookLibs;
import middleware.BarJDBC;

public class BarViewAll extends JFrame {
	JPanel panelMain;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object obj[];
	ArrayList<BarLibs> array;
	JScrollPane scrollPane;
	JLabel lblTitle;
	JButton btnSearch;
	private JTextField txtSearch;

	public BarViewAll() {
		setTitle("Bar History");
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

		lblTitle = new JLabel("View All Bar History");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Century Schoolbook", Font.BOLD, 25));
		lblTitle.setBounds(335, 11, 450, 44);
		panelMain.add(lblTitle);
		
 
		btnSearch =new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSearch.setBounds(887,29,95,20);
		panelMain.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
		txtSearch.setBounds(734, 29, 143, 20);
		panelMain.add(txtSearch);
		
		
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
		BarJDBC jdbc = new BarJDBC();
		array = jdbc.getBarHistory();
		dtm.setRowCount(0);
		for (BarLibs libs : array) {
			Object tmpRow[] = { libs.getBookingID(), libs.getRoomNo(), libs.getCustomerFname(), libs.getMenu(),
					libs.getRate(), libs.getQuantity(), libs.getRate()*libs.getQuantity()};
			dtm.addRow(tmpRow);
		}
		

	}

//	public static void main(String[] args) {
//		new BarViewAll();
//	}
}
