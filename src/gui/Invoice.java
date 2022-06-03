
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import library.InvoiceLibs;
import middleware.InvoiceJDBC;

public class Invoice extends JFrame implements ActionListener {

	JPanel contentPane, mainPanel, panelMiddle, panelBottom;
	JLabel lblTitle, lblVateInvoice, lblBookingId, lblName, lblArrivalDate, lblRoomNo, lblBillDate, lblSubTotal,
			lblDiscount, lblServiceCharge, lblVAT, lblTotal, lblStatus;
	JTextField txtBookingId, txtName, txtArrivalDate, txtBillDate, txtSubTotal, txtDiscountPercent, txtDiscount,
			txtServiceCharge, txtVAT, txtTotal;
	JComboBox cmbRoomNo, cmbStatus;
	JScrollPane scrollPane;
	JButton btnSave, btnPrint;

	ArrayList<InvoiceLibs> arrLib = new ArrayList<InvoiceLibs>();
	ArrayList arrLibs = new ArrayList();
	InvoiceJDBC jdbc = new InvoiceJDBC();

	// declaration for display Table
	DefaultTableModel dtm = new DefaultTableModel();
	JTable table = new JTable(dtm);
	Object[] obj;

	int rowCount;
	double sum = 0.0, discount = 0.0;

	public Invoice() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Billing Window");
		setBounds(190, 65, 1183, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanel.setBackground(new Color(220, 220, 220));
		mainPanel.setBounds(0, 0, 1173, 637);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		lblTitle = new JLabel("Hotel Luton");
		lblTitle.setFont(new Font("Calisto MT", Font.BOLD, 26));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(439, 11, 292, 33);
		mainPanel.add(lblTitle);

		lblVateInvoice = new JLabel("VAT INVOICE");
		lblVateInvoice.setFont(new Font("Calisto MT", Font.BOLD, 26));
		lblVateInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblVateInvoice.setBounds(439, 52, 304, 33);
		mainPanel.add(lblVateInvoice);

		panelMiddle = new JPanel();
		panelMiddle.setBackground(new Color(192, 192, 192));
		panelMiddle.setBounds(10, 142, 1146, 76);
		mainPanel.add(panelMiddle);
		panelMiddle.setLayout(null);

		lblName = new JLabel("Customer Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(10, 21, 150, 35);
		panelMiddle.add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(159, 21, 113, 35);
		panelMiddle.add(txtName);

		lblArrivalDate = new JLabel("Arrival Date:");
		lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArrivalDate.setBounds(310, 21, 133, 35);
		panelMiddle.add(lblArrivalDate);

		txtArrivalDate = new JTextField();
		txtArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtArrivalDate.setEditable(false);
		txtArrivalDate.setColumns(10);
		txtArrivalDate.setBounds(453, 23, 121, 35);
		panelMiddle.add(txtArrivalDate);

		lblRoomNo = new JLabel("Room No:");
		lblRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRoomNo.setBounds(617, 21, 99, 35);
		panelMiddle.add(lblRoomNo);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		cmbRoomNo = new JComboBox();
		cmbRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbRoomNo.addActionListener(this);
		cmbRoomNo.removeAllItems();
		arrLibs = jdbc.showRoomNumber();
		cmbRoomNo.addItem("Select");
		for (int i = 0; i < arrLibs.size(); i++) {
			cmbRoomNo.addItem(arrLibs.get(i));
		}
		cmbRoomNo.setBounds(713, 21, 90, 35);
		panelMiddle.add(cmbRoomNo);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvoiceLibs libs = new InvoiceLibs();
				libs.setBillDate(txtBillDate.getText());
				libs.setStatus(cmbStatus.getSelectedItem().toString());
				libs.setSubTotal(Double.parseDouble(txtSubTotal.getText()));
				libs.setDiscount(Double.parseDouble(txtDiscount.getText()));
				libs.setServiceCharge(Double.parseDouble(txtServiceCharge.getText()));
				libs.setVatCharge(Double.parseDouble(txtVAT.getText()));
				libs.setTotal(Double.parseDouble(txtTotal.getText()));
				boolean result = jdbc.insertIntoInvoice(libs);
				if (result == true) {
					JOptionPane.showMessageDialog(null, "Bill saved successfully !!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to save bill !!");
				}
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSave.setBounds(1041, 22, 85, 35);
		panelMiddle.add(btnSave);

		lblStatus = new JLabel("Status :");
		lblStatus.setBounds(832, 21, 79, 35);
		panelMiddle.add(lblStatus);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbStatus = new JComboBox();
		cmbStatus.setBounds(910, 21, 121, 35);
		panelMiddle.add(cmbStatus);
		cmbStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		String type[] = { "Pending", "Paid" };
		for (int i = 0; i < type.length; i++) {
			cmbStatus.addItem(type[i]);
		}

		panelBottom = new JPanel();
		panelBottom.setBackground(new Color(192, 192, 192));
		panelBottom.setBounds(10, 405, 1146, 218);
		mainPanel.add(panelBottom);
		panelBottom.setLayout(null);

		lblSubTotal = new JLabel("Sub Total :");
		lblSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubTotal.setBounds(811, 11, 108, 31);
		panelBottom.add(lblSubTotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSubTotal.setBounds(1011, 13, 125, 31);
		panelBottom.add(txtSubTotal);
		txtSubTotal.setColumns(10);

		lblDiscount = new JLabel("Discount :");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiscount.setBounds(811, 50, 87, 31);
		panelBottom.add(lblDiscount);

		txtDiscountPercent = new JTextField();
		txtDiscountPercent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDiscountPercent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					calculateDiscount();
					calculateServiceCharge();
					calculateVat();
				}
			}
		});
		txtDiscountPercent.setColumns(10);
		txtDiscountPercent.setBounds(946, 52, 55, 31);
		panelBottom.add(txtDiscountPercent);

		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(1011, 52, 125, 31);
		panelBottom.add(txtDiscount);

		lblServiceCharge = new JLabel("Service Charge 10%  :");
		lblServiceCharge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblServiceCharge.setBounds(811, 94, 190, 31);
		panelBottom.add(lblServiceCharge);

		txtServiceCharge = new JTextField();
		txtServiceCharge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtServiceCharge.setBounds(1011, 96, 125, 31);
		panelBottom.add(txtServiceCharge);
		txtServiceCharge.setColumns(10);

		lblVAT = new JLabel("VAT 13% :");
		lblVAT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVAT.setBounds(811, 141, 190, 31);
		panelBottom.add(lblVAT);

		txtVAT = new JTextField();
		txtVAT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtVAT.setColumns(10);
		txtVAT.setBounds(1011, 143, 125, 31);
		panelBottom.add(txtVAT);

		lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(811, 181, 190, 31);
		panelBottom.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTotal.setColumns(10);
		txtTotal.setBounds(1011, 183, 125, 31);
		panelBottom.add(txtTotal);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 221, 1146, 179);
		mainPanel.add(scrollPane);
		displayTable();
		scrollPane.setViewportView(table);

		txtBookingId = new JTextField();
		txtBookingId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBookingId.setBounds(170, 96, 74, 35);
		mainPanel.add(txtBookingId);
		txtBookingId.setEditable(false);
		txtBookingId.setColumns(10);

		lblBookingId = new JLabel("Booking Id :");
		lblBookingId.setBounds(10, 96, 150, 35);
		mainPanel.add(lblBookingId);
		lblBookingId.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblBillDate = new JLabel("Bill Date :");
		lblBillDate.setBounds(847, 94, 150, 35);
		mainPanel.add(lblBillDate);
		lblBillDate.setFont(new Font("Times New Roman", Font.BOLD, 20));

		txtBillDate = new JTextField();
		txtBillDate.setBounds(1007, 96, 150, 35);
		mainPanel.add(txtBillDate);
		txtBillDate.setEditable(false);
		txtBillDate.setText(formatter.format(date));
	
		btnPrint = new JButton("Print");
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setBounds(1067, 11, 89, 23);
		mainPanel.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setJobName("Print Data");

				job.setPrintable(new Printable() {
					public int print(Graphics pg, PageFormat pf, int pageNum) {
						pf.setOrientation(PageFormat.LANDSCAPE);
						if (pageNum > 0) {
							return Printable.NO_SUCH_PAGE;
						}

						Graphics2D g2 = (Graphics2D) pg;
						g2.translate(pf.getImageableX(), pf.getImageableY());
						g2.scale(0.47, 0.47);

						mainPanel.print(g2);

						return Printable.PAGE_EXISTS;

					}
				});
				boolean ok = job.printDialog();
				if (ok) {
					try {

						job.print();
					} catch (PrinterException ex) {
						ex.printStackTrace();
					}
				}

			}
		});
		;

		setVisible(true);
	}

	// Method display Table set
	public void displayTable() {
		obj = new Object[6];
		obj[0] = "Particulars";
		obj[1] = "Ordered Date";
		obj[2] = "Description";
		obj[3] = "Rate";
		obj[4] = "Quantity";
		obj[5] = "Total";

		dtm.setColumnIdentifiers(obj);
	}
	// Method display Table set

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cmbRoomNo) {
			dtm.setRowCount(0);
			if (cmbRoomNo.getSelectedItem().equals("Select")) {
				txtName.setText(null);
				txtArrivalDate.setText(null);
			} else {
				int roomNo = Integer.parseInt(cmbRoomNo.getSelectedItem().toString());
				arrLib = jdbc.showCustomerDetails(roomNo);
				for (InvoiceLibs libs : arrLib) {
					txtBookingId.setText(Integer.toString(libs.getBookingId()));
					txtName.setText(libs.getCustName());
					txtArrivalDate.setText(libs.getCheckInDate());
				}

				arrLib = jdbc.getRoomDetailBill(Integer.parseInt(txtBookingId.getText()));
				for (InvoiceLibs invoice : arrLib) {
					Object obj[] = { invoice.getRoomType(), invoice.getRoomLease(),
							"Total stay days : " + invoice.getDays() + " days", invoice.getRoomprice(), "",
							invoice.getRoomprice() * invoice.getDays() };
					dtm.addRow(obj);
				}

				arrLib = jdbc.getServiceDetails(Integer.parseInt(txtBookingId.getText()));
				for (InvoiceLibs invoice : arrLib) {
					Object obj[] = { invoice.getParticulars(), invoice.getPurchaseDate(), invoice.getDescription(),
							invoice.getRate(), 1, invoice.getRate() * 1 };
					dtm.addRow(obj);
				}

				arrLib = jdbc.getRestaurantDetails(Integer.parseInt(txtBookingId.getText()));
				for (InvoiceLibs invoice : arrLib) {
					Object obj[] = { invoice.getParticulars(), invoice.getPurchaseDate(), invoice.getDescription(),
							invoice.getRate(), invoice.getQuantity(), invoice.getRate() * invoice.getQuantity() };
					dtm.addRow(obj);
				}

				arrLib = jdbc.getBarDetails(Integer.parseInt(txtBookingId.getText()));
				for (InvoiceLibs invoice : arrLib) {
					Object obj[] = { invoice.getParticulars(), invoice.getPurchaseDate(), invoice.getDescription(),
							invoice.getRate(), invoice.getQuantity(), invoice.getRate() * invoice.getQuantity() };
					dtm.addRow(obj);
				}
				calculateTotal();
			}
		}

	}

	public void calculateTotal() {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			sum += Double.parseDouble(dtm.getValueAt(i, 5).toString());
		}
		txtSubTotal.setText(Double.toString(sum));
		sum = 0;
	}

	public void calculateDiscount() {
		int temp = Integer.parseInt(txtDiscountPercent.getText());
		double tem = Double.parseDouble(txtSubTotal.getText());
		discount = tem * temp / 100;
		txtDiscount.setText(Double.toString(discount));
	}

	public void calculateServiceCharge() {
		double tmpsubTotal = Double.parseDouble(txtSubTotal.getText());
		double tmpDiscount = Double.parseDouble(txtDiscount.getText());
		double amount = tmpsubTotal - tmpDiscount;
		double serviceAmount = amount * 10 / 100;
		txtServiceCharge.setText(Double.toString(serviceAmount));
	}

	public void calculateVat() {
		double tmpsubTotal = Double.parseDouble(txtSubTotal.getText());
		double tmpDiscount = Double.parseDouble(txtDiscount.getText());
		double tmpServiceAmt = Double.parseDouble(txtServiceCharge.getText());
		double vatAmount = tmpsubTotal - tmpDiscount + tmpServiceAmt;
		double vatAmt = vatAmount * 13 / 100;
		double total = tmpsubTotal - tmpDiscount + tmpServiceAmt + vatAmt;
		txtVAT.setText(Double.toString(vatAmt));
		txtTotal.setText(Double.toString(total));
	}

//	public static void main(String[] args) {
//		new Invoice();
//	}
}
