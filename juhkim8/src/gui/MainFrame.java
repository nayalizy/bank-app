package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import logic.BankLogic;

public class MainFrame extends JFrame {

	private JFrame frame;
	private JPanel mainPanel;
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private BankLogic bank;
	
	public MainFrame(BankLogic bank)
	{	
		this.bank = bank;
		JFrame frame = new JFrame ("menu");
	    frame.setJMenuBar(menuBar());
	    frame.setLayout(new BorderLayout());
	    mainPanel = new JPanel();
	    frame.add(mainPanel, BorderLayout.CENTER);
	    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    frame.setVisible(true);
     	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
	
	public void setMainPanel(JPanel panel)
	{
		mainPanel.removeAll();
		mainPanel.add(panel);
	    mainPanel.validate();
	    mainPanel.repaint();
	}
	
	public JMenuBar menuBar()
	{
		JMenuBar bar = new JMenuBar ();
	    bar.setVisible (true);
	    bar.add(createFileMenu());
	    bar.add(createCustomerMenu());
	    bar.add(createAccountMenu());
	    bar.add(createTransactionMenu());
	    return bar;
	}
	
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
	    JMenuItem newFile = new JMenuItem("New");
	    JMenuItem openFile = new JMenuItem("Open File");
	    JMenuItem save = new JMenuItem("Save");
	    JMenuItem saveAs = new JMenuItem("Save As...");
	    menu.add(newFile);
	    menu.add(openFile);    
	    menu.add(save);
	    menu.add(saveAs);
		return menu;
	}
	
	public JMenu createCustomerMenu()
	{
	    JMenu menu = new JMenu("Customer");
		menu.add(allCustomers());
	    menu.add(customerInfo());
	    menu.add(createNewcustomer());
	    menu.add(changeName());
	    menu.add(deleteCustomer());
	    return menu;
	}
	
	public JMenu createAccountMenu()
	{
		JMenu menu = new JMenu("Account");
		menu.add(newAccount());
		menu.add(accountInfo());
		menu.add(closeAccount());
		return menu;
	}
	
	public JMenu createTransactionMenu()
	{
		JMenu menu = new JMenu("Transaction");
		JMenuItem withdraw = new JMenuItem("Withdraw");
		JMenuItem history = new JMenuItem("History");
		menu.add(deposit());
		menu.add(withdraw());
		menu.add(transactionHistory());
		return menu;
	}
	
	//For getting a list of all customers under customer menu
	public JMenuItem allCustomers()
	{
		class CustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				CustomerFrame customer = new CustomerFrame();
				setMainPanel(customer.customerList(bank));
			}
		}
		JMenuItem item = new JMenuItem("All Customers");
		ActionListener listener = new CustomerListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For getting info of a specific customer under customer menu
	public JMenuItem customerInfo()
	{
		class CustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				CustomerFrame customer = new CustomerFrame();
				setMainPanel(customer.customerInfo(bank));
			}
		}		
		JMenuItem item = new JMenuItem("Customer Info");
		ActionListener listener = new CustomerListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For creating new customers under customer menu
	public JMenuItem createNewcustomer()
	{
		class CustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				CustomerFrame customer = new CustomerFrame();
				setMainPanel(customer.customerCreate(bank));
			}
		}
		JMenuItem item = new JMenuItem("Create New");
		ActionListener listener = new CustomerListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For changing a customer's name under customer menu
	public JMenuItem changeName()
	{
		class CustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				CustomerFrame customer = new CustomerFrame();
				setMainPanel(customer.customerNewName(bank));
			}
		}
		JMenuItem item = new JMenuItem("Change Name");
		ActionListener listener = new CustomerListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For deleting a customer under customer menu
	public JMenuItem deleteCustomer()
	{
		class CustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				CustomerFrame customer = new CustomerFrame();
				setMainPanel(customer.customerDelete(bank));
			}
		}
		JMenuItem item = new JMenuItem("Delete Customer");
		ActionListener listener = new CustomerListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For creating a new bank account
	public JMenuItem newAccount()
	{
		class AccountListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				NewAccountFrame account = new NewAccountFrame();
				setMainPanel(account.accountCreateNew(bank));
			}
		}
		JMenuItem item = new JMenuItem("Create Account");
		ActionListener listener = new AccountListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For getting more information about a specific account
	public JMenuItem accountInfo()
	{
		class AccountListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				AccountFrame account = new AccountFrame();
				setMainPanel(account.accountInfo(bank));
			}
		}
		JMenuItem item = new JMenuItem("Account Info");
		ActionListener listener = new AccountListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For closing a bank account
	public JMenuItem closeAccount()
	{
		class AccountListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				AccountFrame account = new AccountFrame();
				setMainPanel(account.closeAccount(bank));
			}
		}
		JMenuItem item = new JMenuItem("Close Account");
		ActionListener listener = new AccountListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For deposit
	public JMenuItem deposit()
	{
		class TransactionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				TransactionFrame transaction = new TransactionFrame();
				setMainPanel(transaction.deposit(bank));
			}
		}
		JMenuItem item = new JMenuItem("Deposit");
		ActionListener listener = new TransactionListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For withdraw
	public JMenuItem withdraw()
	{
		class TransactionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				TransactionFrame transaction = new TransactionFrame();
				setMainPanel(transaction.withdraw(bank));
			}
		}
		JMenuItem item = new JMenuItem("Withdraw");
		ActionListener listener = new TransactionListener();
		item.addActionListener(listener);
		return item;
	}
	
	//For transaction history
	public JMenuItem transactionHistory()
	{
		class TransactionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				TransactionFrame transaction = new TransactionFrame();
				setMainPanel(transaction.transactionHistory(bank));
			}
		}
		JMenuItem item = new JMenuItem("Transaction History");
		ActionListener listener = new TransactionListener();
		item.addActionListener(listener);
		return item;
	}
	
	
}


