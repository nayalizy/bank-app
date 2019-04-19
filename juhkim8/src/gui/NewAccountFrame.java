package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import logic.BankLogic;


public class NewAccountFrame {

	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private String string = "";
	private String pNumber;
	private String accountNumber;
	private JLabel labelSuccess;
	private JComboBox numberBox;
	private ArrayList<String> list;

	public NewAccountFrame()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		list = new ArrayList<String>(1000);
	}	
	
	public JPanel accountCreateNew(BankLogic bank)
	{
		JLabel label = new JLabel("Enter customer info: ");
		mainPanel.add(label, BorderLayout.NORTH);
		
		//Radio buttons for choosing between savings account and credit account
		JRadioButton SAButton = new JRadioButton("Savings Account");
		JRadioButton CAButton = new JRadioButton("Credit Account");
		
		//group buttons and add them to a panel
		ButtonGroup group = new ButtonGroup();
		group.add(SAButton);
		group.add(CAButton);
		JPanel panel1 = new JPanel();
		panel1.add(SAButton);
		panel1.add(CAButton);
		panel1.setBorder(BorderFactory.createTitledBorder("Choose account type: "));
	
		JButton button = new JButton ("Submit");
		
		//add elements to the top panel and top panel to the main panel
		topPanel.setLayout(new BorderLayout());
		topPanel.add(personalNumberBox(bank), BorderLayout.NORTH);
		topPanel.add(panel1, BorderLayout.CENTER);
		topPanel.add(button, BorderLayout.SOUTH); 
		mainPanel.add(topPanel, BorderLayout.CENTER);
		
		//elements in the bottom panel (label, label)
		labelSuccess = new JLabel("Create New Account : " + string);
		JLabel accountLabel = new JLabel("Account number: " + accountNumber);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labelSuccess, BorderLayout.NORTH);
		bottomPanel.add(accountLabel, BorderLayout.SOUTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
				
		//Action Listener - find customer using given personal number, retrieve information and print it
		class CustomerInfoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pNumber = (String) numberBox.getSelectedItem();
				
				if(SAButton.isSelected()) 
				{
					accountNumber = Integer.toString((bank.createSavingsAccount(pNumber)));
				}
				if(CAButton.isSelected()) 
				{
					accountNumber = Integer.toString((bank.createCreditAccount(pNumber)));
				}

				if(accountNumber.equals("-1"))
				{
					string = "Customer does not exist";
					accountNumber = null;
				}
				labelSuccess.setText("Create Account: " + string);		
				accountLabel.setText("Account number:  " + accountNumber);
			}
		}	
					
		ActionListener listener = new CustomerInfoListener();
		button.addActionListener(listener);
		
		return mainPanel;
	}

	public JComboBox personalNumberBox(BankLogic bank)
	{
		numberBox = new JComboBox();
		list = bank.getAllPersonalNumbers();
		for(int i = 0; i < list.size(); i++)
		{
			numberBox.addItem(list.get(i));
		}
		return numberBox;		
	}
		
}
