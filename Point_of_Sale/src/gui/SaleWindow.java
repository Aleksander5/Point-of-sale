package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import data.*;

public final class SaleWindow {

	private JFrame frmSaleWindow;
	private JTextField txtCodeField;
	private JLabel lblError;
	private JLabel lblSum;
	private JList<String> listProductList;
	private ProductDatabase actualProductDB;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private ArrayList<Product> receiptList = new ArrayList<Product>();
	
	private float sum = 0.00f;
	
	public SaleWindow() {
		actualProductDB = new ProductDatabase();
		actualProductDB.addToProductList(new Product("T-Shirt", "100500", 9.99f));
		actualProductDB.addToProductList(new Product("Trousers", "123987", 29.99f));
		actualProductDB.addToProductList(new Product("Socks", "290595", 5.99f));
		actualProductDB.addToProductList(new Product("Sweater", "500500", 39.99f));
		actualProductDB.addToProductList(new Product("Belt", "741852", 14.99f));
		
		initialize();
	}

	
	private void initialize() {
		frmSaleWindow = new JFrame();
		frmSaleWindow.setTitle("Sale Window");
		frmSaleWindow.setResizable(false);
		frmSaleWindow.setBounds(100, 100, 400, 580);
		frmSaleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSaleWindow.getContentPane().setLayout(null);
		
		
		JLabel lblEnterCode = new JLabel("Enter code:");
		lblEnterCode.setFont(new Font("Arial", Font.BOLD, 14));
		lblEnterCode.setBounds(40, 20, 85, 15);
		frmSaleWindow.getContentPane().add(lblEnterCode);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Arial", Font.BOLD, 14));
		lblError.setBounds(100, 74, 200, 15);
		frmSaleWindow.getContentPane().add(lblError);

		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotal.setBounds(10, 520, 48, 15);
		frmSaleWindow.getContentPane().add(lblTotal);
		
		lblSum = new JLabel(String.format("%.2f", sum));
		lblSum.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSum.setBounds(65, 520, 90, 15);
		frmSaleWindow.getContentPane().add(lblSum);
		
		txtCodeField = new JTextField();
		txtCodeField.setBounds(135, 18, 225, 20);
		frmSaleWindow.getContentPane().add(txtCodeField);
		txtCodeField.setColumns(10);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(40, 45, 150, 22);
		btnAddProduct.addActionListener(new AddProductListener());
		frmSaleWindow.getContentPane().add(btnAddProduct);

		JButton btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.setBounds(210, 45, 150, 22);
		btnRemoveProduct.addActionListener(new RemoveProductListener());
		frmSaleWindow.getContentPane().add(btnRemoveProduct);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(285, 510, 100, 30);
		btnExit.addActionListener(new ExitListener());
		frmSaleWindow.getContentPane().add(btnExit);
		
		listProductList = new JList<String>();
		listProductList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollBar = new JScrollPane(listProductList);
		scrollBar.setLocation(10, 100);
		scrollBar.setSize(375, 400);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setViewportView(listProductList);
		frmSaleWindow.getContentPane().add(scrollBar);
		
		frmSaleWindow.setVisible(true);
	}
	
	private class AddProductListener implements ActionListener{
		public void actionPerformed( ActionEvent e ) {

				if( txtCodeField.getText().equals("") ) {
						lblError.setText("Error: Invaild bar-code");
				}
				else {
						if( actualProductDB.productChecking(txtCodeField.getText()) == false ){
								lblError.setText("Error: Product not found");
						}
						else {
								lblError.setText("");
								
								Product scannedProduct = actualProductDB.getProduct(txtCodeField.getText());
								
								receiptList.add(scannedProduct);

								model.addElement(scannedProduct.productToString());
								listProductList.setModel(model);

								sum += scannedProduct.getProductPrice();
								lblSum.setText(String.format("%.2f", sum));
								
								txtCodeField.setText("");
						}
						
				}
		}
	}
	
	private class ExitListener implements ActionListener{
		public void actionPerformed( ActionEvent e ) {
			
			ReceiptWindow receipt = new ReceiptWindow(getReceiptList(), sum);
			
			receiptList.clear();
			model.clear();
			sum = 0.00f;
			
			listProductList.setModel(model);
			lblSum.setText(String.format("%.2f", sum));
		}
	}
	
	private class RemoveProductListener implements ActionListener{
		public void actionPerformed( ActionEvent e ) {
				
				sum -= receiptList.get(listProductList.getSelectedIndex()).getProductPrice();
				receiptList.remove(listProductList.getSelectedIndex());
			
				model.remove(listProductList.getSelectedIndex());
				listProductList.setModel(model);
				
				lblSum.setText(String.format("%.2f", sum));
		}
	}
	
	public final ArrayList<Product> getReceiptList(){
			return receiptList;
	}
}






















