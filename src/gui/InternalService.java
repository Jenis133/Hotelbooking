package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import global.IntServiceG;

public class InternalService extends JFrame implements ActionListener {

	JPanel mainPanel, panelLeft;
	JTextField txtRate, txtLastName, txtQuantity, txtTotal, txtFirstName;
	JLabel lblTitle, lblIntro, lblRoomNo, lblFirstName, lblLastName, lblServiceType, lblQuantity, lblRate, lblTotal;
	JComboBox cmbServiceType, cmbRoomNo;
	JButton btnSave, btnUpdate, btnDelete, btnPay, btnClose;
	JScrollPane scrollPane;
	JTable table;

	public InternalService() {
		setTitle("Internal Services");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1195, 675);
		setResizable(false);
		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.activeCaption);
		mainPanel.setLayout(null);

		// title of the window
		lblTitle = new JLabel("Internal Services for Customers");
		lblTitle.setBounds(360, 0, 600, 56);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		mainPanel.add(lblTitle);
		// end of title label

		// creating left panel
		panelLeft = new JPanel();
		panelLeft.setBounds(35, 81, 464, 497);
		panelLeft.setBackground(new Color(95, 158, 160));
		mainPanel.add(panelLeft);
		panelLeft.setLayout(null);
		// end of creating left panel

		// intro label
		lblIntro = new JLabel("Services Available for You");
		lblIntro.setBounds(104, 21, 299, 34);
		lblIntro.setForeground(new Color(230, 230, 250));
		lblIntro.setFont(new Font("Arial", Font.BOLD, 20));
		panelLeft.add(lblIntro);
		// end of intro label

		lblFirstName = new JLabel("First Name :");
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFirstName.setBounds(30, 161, 103, 24);
		panelLeft.add(lblFirstName);

		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFirstName.setBounds(169, 159, 234, 24);
		panelLeft.add(txtFirstName);
		txtFirstName.setColumns(10);

		lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLastName.setBounds(30, 197, 113, 24);
		panelLeft.add(lblLastName);

		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Arial", Font.PLAIN, 11));
		txtLastName.setBounds(169, 199, 234, 24);
		panelLeft.add(txtLastName);
		txtLastName.setColumns(10);

		lblRoomNo = new JLabel("Room No : ");
		lblRoomNo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRoomNo.setBounds(30, 116, 103, 34);
		panelLeft.add(lblRoomNo);

		cmbRoomNo = new JComboBox();
		cmbRoomNo.setFont(new Font("Arial", Font.PLAIN, 11));
		cmbRoomNo.setBounds(169, 123, 234, 24);
		panelLeft.add(cmbRoomNo);

		lblServiceType = new JLabel("Service Type:");
		lblServiceType.setFont(new Font("Arial", Font.PLAIN, 16));
		lblServiceType.setBounds(30, 238, 113, 24);
		panelLeft.add(lblServiceType);

		cmbServiceType = new JComboBox();
//		cmbServiceType.setFont(new Font("Arial", Font.PLAIN, 11));
		cmbServiceType.setBounds(169, 240, 234, 24);
		for (int i = 0; i < IntServiceG.INT_SERVICE.length; i++) {
			cmbServiceType.addItem(IntServiceG.INT_SERVICE[i]);
		}
		panelLeft.add(cmbServiceType);

		lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuantity.setBounds(30, 280, 129, 24);
		panelLeft.add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Arial", Font.PLAIN, 11));
		txtQuantity.setBounds(169, 281, 234, 24);
		panelLeft.add(txtQuantity);
		txtQuantity.setColumns(10);

		lblRate = new JLabel("Rate :");
		lblRate.setFont(new Font("Arial", Font.PLAIN, 16));
		lblRate.setBounds(30, 325, 113, 18);
		panelLeft.add(lblRate);

		txtRate = new JTextField();
		txtRate.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRate.setBounds(169, 319, 233, 26);
		panelLeft.add(txtRate);
		txtRate.setColumns(10);

		lblTotal = new JLabel("Total : ");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTotal.setBounds(30, 364, 113, 20);
		panelLeft.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 11));
		txtTotal.setBounds(169, 360, 234, 24);
		panelLeft.add(txtTotal);
		txtTotal.setColumns(10);

		btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSave.setBounds(30, 422, 89, 23);
		btnSave.addActionListener(this);
		panelLeft.add(btnSave);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 12));
		btnUpdate.setBounds(129, 422, 89, 23);
		btnUpdate.addActionListener(this);
		panelLeft.add(btnUpdate);

		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setBounds(228, 422, 89, 23);
		btnDelete.addActionListener(this);
		panelLeft.add(btnDelete);

		btnPay = new JButton("PAY");
		btnPay.setFont(new Font("Arial", Font.PLAIN, 12));
		btnPay.setBounds(327, 422, 89, 23);
		btnPay.addActionListener(this);
		panelLeft.add(btnPay);

		btnClose = new JButton("CLOSE");
		btnClose.setBounds(1063, 594, 89, 23);
		btnClose.setFont(new Font("Arial", Font.PLAIN, 12));
		btnClose.addActionListener(this);
		mainPanel.add(btnClose);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(526, 82, 628, 497);
		mainPanel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "First Name", "Last Name", "Room No", "Quantity", "Service Type", "Rate", "Total" }));
		scrollPane.setViewportView(table);

		add(mainPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnClose) {
			this.dispose();
		}
	}

	public static void main(String[] args) {
		new InternalService();
	}
}
