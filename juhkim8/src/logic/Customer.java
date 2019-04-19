/**
h* class Customer: allows to manage a customer's bank accounts/name
 * 				   and to retrieve information about the customer
 * @author Juhee Kim, juhkim-8
 *
 */

package logic;
import java.util.ArrayList;
public class Customer 
{
	private String customerName;
	private String customerSurname;
	private String personalNumber;
	private ArrayList<Account> accountList;
	
	public Customer(String pNo)
	{
		personalNumber = pNo;
		accountList = new ArrayList<Account>(50);
	}
	
	public Customer(String name, String surName, String pNo)
	{
		customerName = name;
		customerSurname = surName;
		personalNumber = pNo;
		accountList = null;
		accountList = new ArrayList<Account>(50);
	}

	public String getName()
	{
		return customerName;
	}
	
	public String getSurname()
	{
		return customerSurname;
	}
	
	public String getPersonalNumber()
	{
		return personalNumber;
	}
	
	public String getNamePNo()
	{
		String str = customerName + " " + customerSurname + " " + personalNumber;
		return str;
	}

	public ArrayList<Account> getAccountList()
	{
		return accountList;
	}
	
	public void changeName(String fn, String sn)
	{
		customerName = fn;
		customerSurname = sn;
	}
		
	public void addNewAccount(Account newAccount) 
	{
		accountList.add(newAccount);
	}
			
	public String toString()
	{
		String str = "Customer name: " + customerName + 
				 "\nCustomer surname: " + customerSurname + 
				 "\nPersonal number: " + personalNumber + 
				 "\nAccounts: " + accountList;
		return str;
	}
}