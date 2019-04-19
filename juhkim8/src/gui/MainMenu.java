/*
package gui;

import java.awt.event.*;
import javax.swing.*;

public class MainMenu {
	
	JFrame mainFrame;
	JMenuBar menuBar;
	JMenu mainMenu;
	
	//menu objects
	JMenu fileMenu;
	JMenu customerMenu;
	JMenu accountMenu;
	JMenu transactionMenu;
	
	//file sub-menu objects
	JMenuItem openFil3;
	JMenuItem manageFile;
	
	//customer sub-menu objects
	JMenu customerList;
	JMenu createCustomer;
	JMenu customerInfo;
	JMenu customerName;
	JMenu deleteCustomer;
	
	//account sub-menu objects
	JMenu newAccount;
	JMenu savingsAccount;
	JMenu creditAccount;
	JMenu accountInfo;
	JMenu closeAccount;
	
	//transaction sub-menu objects
	JMenu deposit;
	JMenu withdraw;
	JMenu transactioinInfo;
	
	public MainMenu()
	{
		mainFrame = new MainFrame();
		menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createCustomerMenu()));
		menuBar.add(createAccountMenu());
		menuBar.add(createTransactionMenu());
		mainFrame.setJMenuBar(menuBar);
	}

	class ExitItemListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}
	
	//crates the File menu
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		ActionListener listener = new ExitItemListener();
		exitItem.addActionListener(listener);
		menu.add(exitItem);
		return menu;
	}
}
*/
