package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.BankLogic;

public class TransactionFrame {

	private JPanel mainPanel;
	private JPanel bottomPanel;
	private ArrayList<String> list;
	private ArrayList<String> accountList;
	private ArrayList<String> transactionList;
	private String string = "";
	private String pNumber = "";
	private String accNumber;
	private int accountNumberInt;
	private BankLogic bank; 
	private JLabel labelSuccess;
	private JComboBox personalNumberBox;
	private JComboBox accountNumberBox;
	private JComboBox anBox;
	private JComboBox pnBox;
	private double amount;
	private JTextField amountField;
	private static final int FIELD_WIDTH = 20;
	private JScrollPane scroll;
	
	public TransactionFrame()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		bottomPanel = new JPanel();
		list = new ArrayList<String>(1000);
		accountList = new ArrayList<String>(50);
	}
	
	
	public JPanel deposit(BankLogic bank)
	{
		//get customer's personal numberand account number
		JPanel panel = new JPanel();
		JPanel panelA = new JPanel();
		JPanel panelB = new JPanel();
		JLabel labelA = new JLabel("Choose customer's personal number");
		JLabel labelB = new JLabel("Choose customer's account number");
		pnBox = new JComboBox();
		pnBox = personalNumberBox(bank);
		panelA.setLayout(new BorderLayout());
		panelA.add(labelA, BorderLayout.NORTH);
		panelA.add(pnBox, BorderLayout.SOUTH);
		anBox = new JComboBox();

		panelB.setLayout(new BorderLayout());
		panelB.add(labelB, BorderLayout.NORTH);
		panelB.add(anBox, BorderLayout.SOUTH);		
		panel.setLayout(new BorderLayout());
		panel.add(panelA, BorderLayout.NORTH);
		panel.add(panelB, BorderLayout.SOUTH);
		
		//add to the main panel
		mainPanel.add(panel, BorderLayout.CENTER);
		
		//elements in the bottom panel 
		amountField = new JTextField();
		amountField = amountField("Amount to deposit");
		labelSuccess = new JLabel();
		labelSuccess.setText("Deposit: " + string);
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(amountField, BorderLayout.NORTH);
		bottomPanel.add(labelSuccess, BorderLayout.CENTER);
		bottomPanel.add(scroll, BorderLayout.SOUTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		class PersonalNumberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pNumber = (String) personalNumberBox.getSelectedItem();
				accountList = bank.getAllAccountNumbers(pNumber);
				anBox.removeAllItems();	
				
				for(int i = 0; i < accountList.size(); i++)
				{	
					String account = accountList.get(i);
					anBox.insertItemAt(account, i);
				}			
			}
		}	
		
		class AccountNumberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				accNumber = (String) anBox.getSelectedItem();
				if(accNumber != null)
				{
					accountNumberInt = Integer.parseInt(accNumber);
				}
			}
		}	
		
		class depositListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				amount = Double.parseDouble(amountField.getText());
				boolean success = bank.deposit(pNumber, accountNumberInt, amount);
				if (success == true)
				{
					labelSuccess.setText("Deposit: Success");
					String transaction = bank.lastTransaction(pNumber, accountNumberInt);
					textArea.append(transaction + "\n");
				}
				else
				{
					labelSuccess.setText("Deposit: Fail");
				}
			
			}
		}
		
		ActionListener listener = new PersonalNumberListener();
		pnBox.addActionListener(listener);		
		ActionListener listener1 = new AccountNumberListener();
		anBox.addActionListener(listener1);
		ActionListener listener2 = new depositListener();
		amountField.addActionListener(listener2);
		
		return mainPanel;
	}
	
	public JPanel withdraw(BankLogic bank)
	{
		//get customer's personal numberand account number
		JPanel panel = new JPanel();
		JPanel panelA = new JPanel();
		JPanel panelB = new JPanel();
		JLabel labelA = new JLabel("Choose customer's personal number");
		JLabel labelB = new JLabel("Choose customer's account number");
		pnBox = new JComboBox();
		pnBox = personalNumberBox(bank);
		panelA.setLayout(new BorderLayout());
		panelA.add(labelA, BorderLayout.NORTH);
		panelA.add(pnBox, BorderLayout.SOUTH);
		anBox = new JComboBox();

		panelB.setLayout(new BorderLayout());
		panelB.add(labelB, BorderLayout.NORTH);
		panelB.add(anBox, BorderLayout.SOUTH);		
		panel.setLayout(new BorderLayout());
		panel.add(panelA, BorderLayout.NORTH);
		panel.add(panelB, BorderLayout.SOUTH);
		
		//add to the main panel
		mainPanel.add(panel, BorderLayout.CENTER);
		
		//elements in the bottom panel 
		amountField = new JTextField();
		amountField = amountField("Amount to withdraw");
		labelSuccess = new JLabel();
		labelSuccess.setText("Withdraw: " + string);
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(amountField, BorderLayout.NORTH);
		bottomPanel.add(labelSuccess, BorderLayout.CENTER);
		bottomPanel.add(scroll, BorderLayout.SOUTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		class PersonalNumberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				
				pNumber = (String) personalNumberBox.getSelectedItem();
				accountList = bank.getAllAccountNumbers(pNumber);
				anBox.removeAllItems();				 
				for(int i = 0; i < accountList.size(); i++)
				{	
					String account = accountList.get(i);
					anBox.insertItemAt(account, i);
				}			
			}
		}	
		
		class AccountNumberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
					accNumber = (String) anBox.getSelectedItem();
					if (accNumber != null) 
					{
						accountNumberInt = Integer.parseInt(accNumber);
					}
			}
		}	
		
		class withdrawListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				amount = Double.parseDouble(amountField.getText());
				boolean success = bank.withdraw(pNumber, accountNumberInt, amount);
				if (success == true)
				{
					labelSuccess.setText("Withdraw: Success");
					String transaction = bank.lastTransaction(pNumber, accountNumberInt);
					textArea.append(transaction + "\n");
				}
				else
				{
					labelSuccess.setText("Withdraw: Fail");
				}
			
			}
		}
		
		ActionListener listener = new PersonalNumberListener();
		pnBox.addActionListener(listener);		
		ActionListener listener1 = new AccountNumberListener();
		anBox.addActionListener(listener1);
		ActionListener listener2 = new withdrawListener();
		amountField.addActionListener(listener2);
		
		return mainPanel;
	}
	
	public JPanel transactionHistory(BankLogic bank)
	{
		//get customer's personal numberand account number
		JPanel panel = new JPanel();
		JPanel panelA = new JPanel();
		JPanel panelB = new JPanel();
		JLabel labelA = new JLabel("Choose customer's personal number");
		JLabel labelB = new JLabel("Choose customer's account number");
		pnBox = new JComboBox();
		pnBox = personalNumberBox(bank);
		panelA.setLayout(new BorderLayout());
		panelA.add(labelA, BorderLayout.NORTH);
		panelA.add(pnBox, BorderLayout.SOUTH);
		anBox = new JComboBox();

		panelB.setLayout(new BorderLayout());
		panelB.add(labelB, BorderLayout.NORTH);
		panelB.add(anBox, BorderLayout.SOUTH);		
		panel.setLayout(new BorderLayout());
		panel.add(panelA, BorderLayout.NORTH);
		panel.add(panelB, BorderLayout.SOUTH);
		
		//add to the main panel
		mainPanel.add(panel, BorderLayout.CENTER);
		
		//elements in the bottom panel 
		labelSuccess = new JLabel();
		labelSuccess.setText("Past transaction");
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labelSuccess, BorderLayout.NORTH);
		bottomPanel.add(scroll, BorderLayout.SOUTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		class PersonalNumberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pNumber = (String) personalNumberBox.getSelectedItem();
				accountList = bank.getAllAccountNumbers(pNumber);
				anBox.removeAllItems();					
				
				for(int i = 0; i < accountList.size(); i++)
				{	
					String account = accountList.get(i);
					anBox.insertItemAt(account, i);
				}			
			}
		}	
		
		class AccountNumberListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				accNumber = (String) anBox.getSelectedItem();
				if (accNumber != null) 
				{
					accountNumberInt = Integer.parseInt(accNumber);	
					transactionList = bank.getTransactions(pNumber, accountNumberInt);
					textArea.setText(null);
					for(int i = 0; i < transactionList.size(); i++)
					{
						textArea.append(transactionList.get(i) + "\n");
					}
				}

			}
		}	
	
		
		ActionListener listener = new PersonalNumberListener();
		pnBox.addActionListener(listener);		
		ActionListener listener1 = new AccountNumberListener();
		anBox.addActionListener(listener1);
		
		return mainPanel;
	}
	
	public JTextField amountField(String str)
	{
		amountField = new JTextField(FIELD_WIDTH);
		amountField.setBorder(BorderFactory.createTitledBorder(str));
		return amountField;
	}
	
	public JComboBox personalNumberBox(BankLogic bank)
	{
		personalNumberBox = new JComboBox();
		list = bank.getAllPersonalNumbers();
		for(int i = 0; i < list.size(); i++)
		{
			personalNumberBox.addItem(list.get(i));
		}
		return personalNumberBox;		
	}
	
	public JComboBox accountNumberBox(BankLogic bank, String pNumber)
	{
		accountNumberBox = new JComboBox();
		accountList = bank.getAllAccountNumbers(pNumber);
		for(int i = 0; i < accountList.size(); i++)
		{
			accountNumberBox.addItem(accountList.get(i));
		}
		return accountNumberBox;
	}
	
}
