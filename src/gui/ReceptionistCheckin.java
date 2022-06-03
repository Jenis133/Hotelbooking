//dd

//package name
package gui;

//import libraries
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import library.BookingLibs;
import library.NCbookLibs;
import middleware.CheckinJDBC;
import middleware.NCbookJDBC;

//main class
public class ReceptionistCheckin extends JFrame implements ItemListener, ActionListener {
	// global variable
	NCbookLibs booklibs = new NCbookLibs();

	// declaration
	JPanel panelMain, panelHead, panelTop, panelTable, panelLow;
	JTextField txtDate, txtCardInfo, txtFname, txtLname, txtID;
	JLabel lblTitle2, lblTitle, lblDate, lblCardInfo, lblName, lblRoomNo, lblBookID;
	JButton btnView, btnCheckin, btnClose;
	JComboBox cmbRoomNo;

	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	ArrayList<NCbookLibs> arrayCheckin;
	JScrollPane scrollPane;

	ArrayList<NCbookLibs> arrayBook, arrayRoomNo;
	Object obj[];

	// sub class
	public ReceptionistCheckin() {
		// frame and main panel
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(190, 65, 1183, 674);
		setTitle("Check in Customer");
		panelMain = new JPanel();
		panelMain.setBackground(new Color(204, 204, 102));
		panelMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMain.setLayout(null);

		// creating title start
		lblTitle = new JLabel("CHECKIN CUSTOMERS ");
		lblTitle.setBounds(445, 0, 274, 40);
		panelMain.add(lblTitle);
		lblTitle.setFont(new Font("Bell MT", Font.ITALIC, 24));

		lblTitle2 = new JLabel("TODAYYY!!");
		lblTitle2.setBounds(530, 38, 120, 27);
		panelMain.add(lblTitle2);
		lblTitle2.setFont(new Font("Bell MT", Font.ITALIC, 18));
		// creating title end

		// creating panel top start
		panelTop = new JPanel();
		panelTop.setBackground(new Color(143, 188, 143));
		panelTop.setBounds(0, 77, 1167, 46);
		panelMain.add(panelTop);
		panelTop.setLayout(null);
		// creating panel top end

		// creating label for date start
		lblDate = new JLabel("Date :");
		lblDate.setBounds(954, 11, 50, 28);
		panelMain.add(lblDate);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 15));
		// creating label for date end

		// creating date textfield and sending current date start
		String pattern = "yyyy-MM-dd";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		txtDate = new JTextField();
		txtDate.setBounds(1015, 14, 124, 23);
		panelMain.add(txtDate);
		txtDate.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDate.setText(dateInString);
		txtDate.setEditable(false);
		// creating date textfield and sending current date end

		// creating label for booking id start
		lblBookID = new JLabel("ID:");
		lblBookID.setForeground(Color.WHITE);
		lblBookID.setFont(new Font("Arial", Font.BOLD, 14));
		lblBookID.setBounds(23, 10, 72, 23);
		panelTop.add(lblBookID);
		// creating label for booking id end

		txtID = new JTextField();
		txtID.setFont(new Font("Arial", Font.PLAIN, 16));
		txtID.setEditable(false);
		txtID.setBounds(55, 10, 48, 23);
		panelTop.add(txtID);

		// creating label for name start
		lblName = new JLabel("Name : ");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Arial", Font.BOLD, 14));
		lblName.setBounds(387, 10, 72, 23);
		panelTop.add(lblName);
		// creating label for name end

		// creating textfield for name start
		txtFname = new JTextField();
		txtFname.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFname.setBounds(450, 10, 121, 23);
		txtFname.setEditable(false);
		panelTop.add(txtFname);

		txtLname = new JTextField();
		txtLname.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLname.setBounds(580, 10, 121, 23);
		txtLname.setEditable(false);
		panelTop.add(txtLname);
		// creating textfield for name end

		// creating button checkin start
		btnCheckin = new JButton("Check In");
		btnCheckin.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCheckin.setBounds(730, 10, 89, 23);
		btnCheckin.addActionListener(this);
		panelTop.add(btnCheckin);
		// creating button checkin end

		// creating label for room no start
		lblRoomNo = new JLabel("Room No :");
		lblRoomNo.setForeground(Color.WHITE);
		lblRoomNo.setFont(new Font("Arial", Font.BOLD, 14));
		lblRoomNo.setBounds(989, 10, 100, 23);
		panelTop.add(lblRoomNo);
		// creating label for room no end

		// creating combobox for room no start
		cmbRoomNo = new JComboBox();
		cmbRoomNo.setFont(new Font("Arial", Font.PLAIN, 16));
		cmbRoomNo.setEditable(false);
		cmbRoomNo.setBounds(1075, 10, 65, 23);
		panelTop.add(cmbRoomNo);
		// creating combobox for room no end

		// creating table set start
		// scrollPane set to scroll the table
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 122, 1167, 459);
		panelMain.add(scrollPane);
		showTable();
		// Mouse click event to fetch the data of selected row from the table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ae) {
				cmbRoomNo.removeAllItems(); // removes all item from combo box
				int i = table.getSelectedRow();
				// roomtype
				TableModel model = table.getModel();
				int bookingID = Integer.parseInt(model.getValueAt(i, 0).toString());
				booklibs.setBookingID(bookingID);

				txtID.setText(model.getValueAt(i, 0).toString());
				txtFname.setText(model.getValueAt(i, 1).toString());
				txtLname.setText(model.getValueAt(i, 2).toString());
				booklibs.setTmpRoomNo(Integer.parseInt(model.getValueAt(i, 9).toString()));
				cmbRoomNo.addItem(model.getValueAt(i, 9).toString());

				String roomType = model.getValueAt(i, 7).toString();
				arrayRoomNo = new ArrayList();
				arrayRoomNo = new NCbookJDBC().getRoomNo(roomType);

				for (int j = 0; j < arrayRoomNo.size(); j++) {
					cmbRoomNo.addItem(arrayRoomNo.get(j));
				}

			}

		});
		// Mouse click event to fetch the data of selected row from the table end
		// table set end
		getContentPane().add(panelMain);

		// creating panel low start
		panelLow = new JPanel();
		panelLow.setBackground(new Color(211, 211, 211));
		panelLow.setBounds(0, 579, 1166, 56);
		panelMain.add(panelLow);
		panelLow.setLayout(null);
		// creating panel low end

		// creating buttons start
		btnClose = new JButton("Close");
		btnClose.setBounds(1065, 10, 89, 36);
		panelLow.add(btnClose);
		btnClose.setForeground(new Color(255, 255, 255));
		btnClose.setBackground(new Color(255, 0, 0));
		btnClose.setFont(new Font("Arial", Font.BOLD, 15));
		btnClose.addActionListener(this);

		btnView = new JButton("View All");
		btnView.setBounds(961, 8, 89, 39);
		panelLow.add(btnView);
		btnView.setBackground(new Color(0, 100, 0));
		btnView.setForeground(new Color(255, 255, 255));
		btnView.setFont(new Font("Arial", Font.BOLD, 15));
		btnView.addActionListener(this);
		// creating buttons end
		setVisible(true); // frame visibility true
	}

	// creating function to display columns in table start
	public void showTable() {
		obj = new Object[10];

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
		obj[9] = "Room No";

		dtm.setColumnIdentifiers(obj);
		refreshTable();
	}
	// creating function to display columns in table end

	// adding rows in table start
	public void refreshTable() {
		arrayCheckin = new NCbookJDBC().getBooked();
		dtm.setRowCount(0);
		for (int i = 0; i < arrayCheckin.size(); i++) {
			NCbookLibs checkin = arrayCheckin.get(i);

			Object tmpRow[] = { checkin.getBookingID(), checkin.getFname(), checkin.getLname(), checkin.getEmail(),
					checkin.getAddress(), checkin.getArrivalDate(), checkin.getDepatureDate(), checkin.getRoomType(),
					checkin.getBookingStatus(), checkin.getRoomNo() };

			dtm.addRow(tmpRow);
		}
	}
	// adding rows in table end

	// action performed event start
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnCheckin) {
			booklibs.setRoomNo(Integer.parseInt(cmbRoomNo.getSelectedItem().toString()));
			NCbookJDBC jdbc = new NCbookJDBC();
			boolean result = jdbc.checkin(booklibs);
			if (result == true) {
				JOptionPane.showMessageDialog(null, "Checked in successful");
				txtID.setText(null);
				txtLname.setText(null);
				cmbRoomNo.removeAllItems();
				refreshTable();
			} else {
				JOptionPane.showMessageDialog(null, "Checked in unsuccessful");
			}
		} else if (ae.getSource() == btnView) {
			new ReceptionistViewCheckin();

		} else if (ae.getSource() == btnClose) {
			this.dispose();
		}

	}
	// action performed event start

//	public static void main(String[] args) {
//
//		new ReceptionistCheckin();
//	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}
}
