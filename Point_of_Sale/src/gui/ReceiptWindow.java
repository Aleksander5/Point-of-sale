package gui;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import data.*;

public final class ReceiptWindow {

	private JFrame frmReceipt;
	private JLabel lblSum;
	private JLabel lblTotalPrice;
	private JButton btnClose;
	
	private int frameHeight = 135;
	private int yLabelPosition = 60;
	private int yButtonPosition = 80;
	private int yNewLabelPosition = 45;
	
	public ReceiptWindow(ArrayList<Product> receiptList, float sum) {
		lblSum = new JLabel(String.format("%.2f", sum));
		lblTotalPrice = new JLabel("TOTAL:");
		btnClose = new JButton("Close");
		frmReceipt = new JFrame();
		frmReceipt.setBounds(100, 100, 300, frameHeight);
		
		printReceiptList(receiptList);
			
		initialize();
	}

	
	private void initialize() {
		frmReceipt.setResizable(false);
		frmReceipt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReceipt.getContentPane().setLayout(null);
		
		JLabel lblReceipt = new JLabel("RECEIPT:");
		lblReceipt.setFont(new Font("Arial", Font.BOLD, 20));
		lblReceipt.setBounds(100, 10, 95, 30);
		frmReceipt.getContentPane().add(lblReceipt);
		
		lblTotalPrice.setFont(new Font("Arial", Font.BOLD, 14));
		lblTotalPrice.setBounds(10, yLabelPosition, 50, 15);
		frmReceipt.getContentPane().add(lblTotalPrice);
			
		lblSum.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSum.setBounds(205, yLabelPosition, 65, 15);
		frmReceipt.getContentPane().add(lblSum);
		
		btnClose.setBounds(100, yButtonPosition, 90, 23);
		btnClose.addActionListener(new CloseListener());
		frmReceipt.getContentPane().add(btnClose);
		
		frmReceipt.setVisible(true);
	}
	
	private class CloseListener implements ActionListener{
		public void actionPerformed( ActionEvent e ) {
			frmReceipt.dispose();
		}
	}
	
	private final void printReceiptList( ArrayList<Product> productList ) {
		
			for(Product p: productList) {
				JLabel lblProductName = new JLabel(p.getProductName());
				lblProductName.setBounds(10, yNewLabelPosition, 185, 15);
				frmReceipt.getContentPane().add(lblProductName);
				
				JLabel lblProductPrice = new JLabel(String.format("%.2f", p.getProductPrice()));
				lblProductPrice.setBounds(205, yNewLabelPosition, 80, 15);
				frmReceipt.getContentPane().add(lblProductPrice);
				
				yNewLabelPosition += 20;
				yLabelPosition += 20;
				yButtonPosition += 20;
				frameHeight += 20;
				
				lblTotalPrice.setBounds(145, yLabelPosition, 50, 15);
				lblSum.setBounds(205, yLabelPosition, 65, 15);
				btnClose.setBounds(10, yButtonPosition, 90, 23);
				frmReceipt.setBounds(100, 100, 300, frameHeight);
			}
	}
}
