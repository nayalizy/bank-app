package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logic.BankLogic;

public class CustomerFrame {
	
	private JLabel label;
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel subPanel;
	private ArrayList<String> list;
	private String string = null;
	private String fName = null;
	private String lName = null;
	private String pNumber = null;
	private static final int FIELD_WIDTH = 20;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField personalNumberField;
	private JLabel labelSuccess;
	private JComboBox numberBox;
	private JScrollPane scroll;
	
	public CustomerFrame()
	{
		label = new JLabel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		subPanel = new JPanel();
		list = new ArrayList<String>(1000);
	}
	
	//2-1 Customer list function in Customer menu
	public JPanel customerList(BankLogic bank)
	{
		//elements in the top panel (label, button)
		label = customLabel("To retrieve customer list, press ok");
		JButton button = new JButton("OK");
		
		//add them to the top panel and top panel to the main panel
		topPanel.setLayout(new BorderLayout());
		topPanel.add(label, BorderLayout.NORTH);
		topPanel.add(button, BorderLayout.SOUTH);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		//elements in the bottom panel (label, textArea)
		JLabel label1 = new JLabel("List: ");
		JTextArea textArea = new JTextArea(15,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(label1, BorderLayout.NORTH);
		bottomPanel.add(scroll, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		
		//Action Listener - retrieve an Array List of customers' name and personal number (for customerList())
		class GetCustomerListListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//save customers in ArrayList
				textArea.setText(null);
				list = bank.getAllCustomers();				
				for(int i = 0; i < list.size(); i++)
				{
					textArea.append(i + ". " + list.get(i) + " \n");
				}
			}
		}					
		//Add Action Listener
		ActionListener listener = new GetCustomerListListener();
		button.addActionListener(listener);	
		
		return mainPanel;
	}
	
	//2-2 Create customer info function in customer menu
	public JPanel customerInfo(BankLogic bank)
	{
		//elements in the top panel (label, comboBox, button)
		label = customLabel("Get customer info:");
		JButton button = new JButton ("Submit");
		
		//add elements to the top panel and top panel to the main panel
		topPanel.setLayout(new BorderLayout());
		topPanel.add(label, BorderLayout.NORTH);
		topPanel.add(personalNumberBox(bank), BorderLayout.CENTER);
		topPanel.add(button, BorderLayout.SOUTH); 
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		//elements in the bottom panel (label, textArea)
		labelSuccess = new JLabel("Customer Found : " + string);
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);

		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labelSuccess, BorderLayout.NORTH);
		bottomPanel.add(scroll, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
				
		//Action Listener - find customer using given personal number, retrieve information and print it
		class CustomerInfoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pNumber = (String) numberBox.getSelectedItem();
				list = bank.getCustomer(pNumber);
				textArea.setText(null);
				
				if (list != null)
				{
					string = "Succees";
					for(int i = 0; i < list.size(); i++)
					{
						textArea.append(i + ". " + list.get(i) + " \n");
					}
				}	
				else if (list == null)
				{	
					string = "Fail";
				}				
				labelSuccess.setText("customer Found: " + string);		
			}
		}	
					
		ActionListener listener = new CustomerInfoListener();
		button.addActionListener(listener);
		
		return mainPanel;
	}
	
	//2-3 Create new customer function in Customer menu
	public JPanel customerCreate(BankLogic bank)
	{
		//elements in the top panel (label, panel, button)
		label = customLabel("Create new Customer");
		JButton button = new JButton("Submit");
		
		//add all to the top panel and add top panel to main panel
		topPanel.setLayout(new BorderLayout());
		topPanel.add(label, BorderLayout.NORTH);
		topPanel.add(inputPanel(), BorderLayout.CENTER);
		topPanel.add(button, BorderLayout.SOUTH);
		mainPanel.add(topPanel, BorderLayout.CENTER);
		
		//elements in the bottom panel (label)
		labelSuccess = new JLabel("Create result: ");
		
		//add element to the bottom panel and bottom panel to main panel
		bottomPanel.add(labelSuccess, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		//Action Listener - get new customer's name and personal number to create account (for createCustomer())
		class CreateCustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
			
				fName = firstNameField.getText();
				lName = lastNameField.getText();
				pNumber = personalNumberField.getText();
				
				if(fName == null || lName == null || pNumber == null)
				{
					string = "Fail";
					labelSuccess.setText("Create result: " + string);
				}	
				
				if((fName != null) && (lName != null) && (pNumber != null))
				{
					boolean success;
					success = bank.createCustomer(fName, lName, pNumber);
					if (success == true)
					{
						string = "Succees";
					}	
					else 
					{	
						string = "Fail";
					}					
					labelSuccess.setText("Create result: " + string);
				}
			}
		}	
				
		//Add Action Listener
		ActionListener listener = new CreateCustomerListener();
		button.addActionListener(listener);
	
		return mainPanel;
	}

	// 2-4 Change customer's name
	public JPanel customerNewName(BankLogic bank)
	{
		//elements in the top panel (label, panel, button)
		label = customLabel("Enter new info : " + "\nNote! Cannot change personal number");
		JButton button = new JButton("Submit");

		//add elements to the top panel and top panel to the main panel
		topPanel.setLayout(new BorderLayout());
		topPanel.add(label, BorderLayout.NORTH);
		topPanel.add(inputComboBoxPanel(bank), BorderLayout.CENTER);
		topPanel.add(button, BorderLayout.SOUTH);
		mainPanel.add(topPanel, BorderLayout.CENTER);
		
		//elements in the bottom panel (label)
		labelSuccess = new JLabel("Change name : ");
				
		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.add(labelSuccess);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		//Action Listener - get customer's new name, find the person using her personal number, and change her name
		class CreateCustomerListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pNumber = (String) numberBox.getSelectedItem();
				fName = firstNameField.getText();
				lName = lastNameField.getText();
				boolean success = bank.changeCustomerName(fName, lName, pNumber);
				
				if(success == true)
				{
					string = "Sucess. Customer name " + fName + " " + lName;
				}
				else if(success == false)
				{
					string = "Fail";
				}
				
				labelSuccess.setText("Change name : " + string );
			}
		}
		
		//Add Action Listener
		ActionListener listener = new CreateCustomerListener();
		button.addActionListener(listener);
			
		return mainPanel;
	}
	
	//2-5 Delete customer
	public JPanel customerDelete(BankLogic bank)
	{
		//elements in the top panel (label, textField, button)
		label = customLabel("Enter customer info: ");
		JButton button = new JButton ("Submit");
				
		//add elements to the top panel and top panel to the main panel
		topPanel.setLayout(new BorderLayout());
		topPanel.add(label, BorderLayout.NORTH);
		topPanel.add(personalNumberBox(bank), BorderLayout.CENTER);
		topPanel.add(button, BorderLayout.SOUTH); 
		mainPanel.add(topPanel, BorderLayout.CENTER);
		
		//elements in the bottom panel (label, textArea)
		labelSuccess = new JLabel("Delete success : " + string);
		JTextArea textArea = new JTextArea(7,25);
		scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		//add elements to the bottom panel and bottom panel to the main panel
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(labelSuccess, BorderLayout.NORTH);
		bottomPanel.add(scroll, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	
		//Action Listener - get customer's personal number and delete the person and retrieve information about her
		class CustomerDeleteListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				pNumber = (String) numberBox.getSelectedItem();
				list = bank.deleteCustomer(pNumber);

				if (list != null)
				{
					string = "Succees";
					for(int i = 0; i < list.size(); i++)
					{
						textArea.append(i + ". " + list.get(i) + " \n");
					}
				}	
				else if (list == null)
				{	
					string = "Fail";
				}
				
				labelSuccess.setText("Delete success: " + string);
			}
		}	
			
		//Add action listener		
		ActionListener listener = new CustomerDeleteListener();
		button.addActionListener(listener);
		
		return mainPanel;
	}
	
	public JTextField firstNameField()
	{
		firstNameField = new JTextField(FIELD_WIDTH);
		firstNameField.setBorder(BorderFactory.createTitledBorder("Customer's First Name:"));
		return firstNameField;
	}
	
	public JTextField lastNameField()
	{
		lastNameField = new JTextField(FIELD_WIDTH);
		lastNameField.setBorder(BorderFactory.createTitledBorder("Customer's Last Name:"));
		return lastNameField;
	}
	
	public JTextField personalNumberField()
	{
		personalNumberField = new JTextField(FIELD_WIDTH);
		personalNumberField.setBorder(BorderFactory.createTitledBorder("Customer's Personal Number"));
		return personalNumberField;
	}
	
	public JPanel inputPanel()
	{
		subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		subPanel.add(firstNameField(), BorderLayout.NORTH);
		subPanel.add(lastNameField(), BorderLayout.CENTER);
		subPanel.add(personalNumberField(), BorderLayout.SOUTH);
		return subPanel;
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
	
	public JPanel inputComboBoxPanel(BankLogic bank)
	{
		subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		subPanel.add(personalNumberBox(bank), BorderLayout.NORTH);
		subPanel.add(firstNameField(), BorderLayout.CENTER);
		subPanel.add(lastNameField(), BorderLayout.SOUTH);
		return subPanel;
	}
	
	public JLabel customLabel(String str)
	{
		JLabel label = new JLabel(str);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        label.setBorder(border);
        return label;
	}
}

