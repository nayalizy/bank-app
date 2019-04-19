package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logic.BankLogic;

public class AccountFrame {
		 
	private JPanel mainPanel;
	private JPanel bottomPanel;
	private ArrayList<String> list;
	private ArrayList<String> accountList;
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
	private JScrollPane scroll;

	public AccountFrame()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		bottomPanel = new JPanel();
		list = new ArrayList<String>(1000);
		accountList = new ArrayList<String>(50);
	}
	
	//3-2 select customer's personal number and account id to get account info
	public JPanel accountInfo(BankLogic bank)
	{
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
		//anBox = accountNumberBox(bank, pNumber);
		panelB.setLayout(new BorderLayout());
		panelB.add(labelB, BorderLayout.NORTH);
		panelB.add(anBox, BorderLayout.SOUTH);		
		panel.setLayout(new BorderLayout());
		panel.add(panelA, BorderLayout.NORTH);
		panel.add(panelB, BorderLayout.SOUTH);
		//and to the main panel
		mainPanel.add(panel, BorderLayout.CENTER);
		
		//elements in the bottom panel (label, textArea)
		labelSuccess = new JLabel("Account info: ");
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labelSuccess, BorderLayout.NORTH);
		bottomPanel.add(scroll, BorderLayout.CENTER);
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
					System.out.println(anBox.getSelectedItem());
					string = bank.getAccount(pNumber, accountNumberInt);
					textArea.setText(null);
					textArea.setText(string);
				}
			}
		}	
		
		ActionListener listener = new PersonalNumberListener();
		pnBox.addActionListener(listener);		
		ActionListener listener1 = new AccountNumberListener();
		anBox.addActionListener(listener1);

		
		return mainPanel;
	}
	

	//3-3 Close Account
	public JPanel closeAccount(BankLogic bank)
	{
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
		//anBox = accountNumberBox(bank, pNumber);
		panelB.setLayout(new BorderLayout());
		panelB.add(labelB, BorderLayout.NORTH);
		panelB.add(anBox, BorderLayout.SOUTH);		
		panel.setLayout(new BorderLayout());
		panel.add(panelA, BorderLayout.NORTH);
		panel.add(panelB, BorderLayout.SOUTH);
		//and to the main panel
		mainPanel.add(panel, BorderLayout.CENTER);
		
		//elements in the bottom panel (label, textArea)
		labelSuccess = new JLabel("Delete Account: ");
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labelSuccess, BorderLayout.NORTH);
		bottomPanel.add(scroll, BorderLayout.CENTER);
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
					System.out.println(anBox.getSelectedItem());
					string = bank.closeAccount(pNumber, accountNumberInt);
					
					if(string != null)
					{
						labelSuccess.setText("Delete Account: Success");
					}
					else
					{	
						labelSuccess.setText("Delete Account: Fail");
					}				
					textArea.setText(null);
					textArea.setText(string);
				}
			}
		}	
		
		ActionListener listener = new PersonalNumberListener();
		pnBox.addActionListener(listener);		
		ActionListener listener1 = new AccountNumberListener();
		anBox.addActionListener(listener1);

		
		return mainPanel;
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
