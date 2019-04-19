/**
 * class BankLogic: allows to manage bank's customers and their bank accounts
 * @author Juhee Kim, juhkim-8
 *
 */

package logic;
import java.util.ArrayList;
public class BankLogic 
{
	ArrayList<Customer>	customerList = new ArrayList<Customer>(1000);
	
	//get all customers
	public ArrayList<String> getAllCustomers()
	{
		ArrayList<String> allCustomers = new ArrayList<String>(); 
		for(int i = 0; i < customerList.size(); i++)
		{
			String str = customerList.get(i).getName() + " " +
					     customerList.get(i).getSurname() + " " +
						 customerList.get(i).getPersonalNumber();
			allCustomers.add(i, str);
		}
		return allCustomers;
	}
	
	//get all customer's personal numbers
	public ArrayList<String> getAllPersonalNumbers()
	{
		ArrayList<String> allNumbers = new ArrayList<String>(); 
		for(int i = 0; i < customerList.size(); i++)
		{
			String str = customerList.get(i).getPersonalNumber();
			allNumbers.add(i, str);
		}
		return allNumbers;
	}
	
	//all account numbers that belong to a specific customer
	public ArrayList<String> getAllAccountNumbers(String pNo)
	{
		ArrayList<String> allAccountNumbers = new ArrayList<String>();
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{			
				customer.getAccountList();
				for(int a = 0; a < customer.getAccountList().size(); a++)
				{
					int no = customer.getAccountList().get(a).getAccountId();
					allAccountNumbers.add(Integer.toString(no));
				}
			}
		}
		return allAccountNumbers;
	}
	
	
	/**
	 * Create a new customer
	 * @param name - The new customer's first name
	 * @param surname - The new customer's surname
	 * @param pNo - The new customer's personal number
	 */
	public boolean createCustomer(String name, String surname, String pNo)
	{
		boolean makeNew = false;
		int a = 0;
		for(int i = 0; i < customerList.size(); i++)
		{
			if (customerList.get(i).getPersonalNumber() == pNo)
			{	
				a++;
			}
		}
		if(a == 0)
		{
			Customer newCustomer = new Customer(name, surname, pNo);
			customerList.add(newCustomer);
			makeNew = true;
		}
			
		return makeNew;
	}	


	/**
	 * Get information about a customer
	 * @param pNo - Customer's personal number
	 */
	public ArrayList<String>getCustomer(String pNo)
	{	
		for(int i = 0; i < customerList.size(); i++)
		{		
			Customer customer = customerList.get(i);
			if(customer.getPersonalNumber() == pNo)
			{
				ArrayList<String> customerInfo = new ArrayList<String>();
				
				customerInfo.add(customer.getNamePNo());
				for(int b = 0; b < customer.getAccountList().size(); b++)
					{
						//Get account number, balance, account type, and interest rate
						Account acc = customer.getAccountList().get(b);
						String accountInfo = acc.accountTypeInterest();
						customerInfo.add(accountInfo);
					}
				
				return customerInfo;
			}
		}		
		return null;	
	}

	/**
	 * Change a customer's name
	 * @param name - The customer's first name
	 * @param surname - The customer's surname
	 * @param pNo - The customer's personal number
	 */
	public boolean changeCustomerName(String name, String surname, String pNo)
	{
		boolean change = false;
		String newName = name;
		String newSurname = surname;
		
		for(int i = 0; i < customerList.size(); i++)
		{
			String personalNumber = customerList.get(i).getPersonalNumber();
			if (personalNumber.equals(pNo))
			{	
				customerList.get(i).changeName(newName, newSurname);
				change = true;
			}	
		}	
		return change;
		
	}

	/**
	 * Delete a customer from the system
	 * @param pNo - The customer's personal number
	 */
	public ArrayList<String> deleteCustomer(String pNo)
	{
		ArrayList<String> deleteCustomer = new ArrayList<String>(50);
		ArrayList<Account> deleteCustomerAccounts = new ArrayList<Account>(50);
		int count = 0;
		
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				String namePNo = customer.getNamePNo();
				deleteCustomer.add(0,namePNo);
				deleteCustomerAccounts = customer.getAccountList();
				for(int a = 0; a < deleteCustomerAccounts.size(); a++)
				{
					//Get account number, balance, account type, and interest rate
					String info = deleteCustomerAccounts.get(a).getDeleteInfo();
					deleteCustomer.add(a + 1,info);
					count ++;
				}
			customerList.remove(i);	
			return deleteCustomer;		
			}
		}
		if(count == 0)
			deleteCustomer = null;
		return deleteCustomer;
	}
	
	/**
	 * Create a customer a new savings account
	 * @param pNo - The customer's personal number
	 */	
	public int createSavingsAccount(String pNo)
	{	
		int nyKonto = -1;
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				SavingsAccount sa = new SavingsAccount();
				customer.addNewAccount(sa);
				nyKonto = sa.getAccountId();	
				System.out.println(nyKonto);
			}
		}
		return nyKonto;	
	}	

	/**
	 * Create a customer a new credit account
	 * @param pNo - The customer's personal number
	 */	
	public int createCreditAccount(String pNo)
	{	
		int nyKonto = -1;
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				CreditAccount ca = new CreditAccount();
				customer.addNewAccount(ca);
				nyKonto = ca.getAccountId();	
				System.out.println(nyKonto);
			}
		}
		return nyKonto;	
	}	
	
	/**
	 * Get information about a customer's bank account
	 * @param pNo - The customer's personal number
	 * @param accountId - The customer's bank account number
	 */
	public String getAccount(String pNo, int accountId)
	{
		String str = null;
		ArrayList<Account> customerAccountList;
		for(int i = 0; i < customerList.size(); i++)
		{	
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				customerAccountList = customer.getAccountList();
				for(int a = 0; a < customer.getAccountList().size(); a++)
				{
					if(accountId == customerAccountList.get(a).getAccountId())
					{
						//Get account number, balance, account type, and interest rate
						str = customerAccountList.get(a).accountTypeInterest();
					}
				}	
			}
		}
		return str;
	}
	
	/**
	 * Close a bank account
	 * @param pNo - The customer's personal number
	 * @param accountId - The customer's bank account number
	 */
	public String closeAccount(String pNo, int accountId)
	{
		String str = null;
		ArrayList<Account> customerAccountList  = new ArrayList<Account>(50);
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				customerAccountList = customer.getAccountList();
				for(int a = 0; a < customer.getAccountList().size(); a++)
				{
					if(accountId == customerAccountList.get(a).getAccountId())
					{
						//Get account number, balance, interest rate, and interest
						str = customerAccountList.get(a).getDeleteInfo();
						customerAccountList.remove(a);
					}
				}
			}
		}
		return str;
	}

	
	/**
	 * Deposit money in a bank account
	 * @param pNo - The customer's personal number
	 * @param accountId - The customer's bank account number
	 * @param amount - The amount of money to be deposited
	 */
	public boolean deposit(String pNo, int accountId, double amount)
	{
		boolean success = false;
		ArrayList<Account> customerAccountList = new ArrayList<Account>(50);
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				customerAccountList = customer.getAccountList();
				for(int a = 0; a < customer.getAccountList().size(); a++)
				{
					Account account = customerAccountList.get(a);
					if(accountId == customerAccountList.get(a).getAccountId())
					{
						success = account.deposit(amount);
					}
				}
			}
		}
		return success;
	}

	/**
	 * Withdraw money from a bank account
	 * @param pNo - The customer's personal number
	 * @param accountId - The customer's bank account number
	 * @param amount - the amount of money to be withdrawn
	 */
	public boolean withdraw(String pNo, int accountId, double amount)
	{
		boolean success = false;
		ArrayList<Account> customerAccountList = new ArrayList<Account>(50);
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				customerAccountList = customer.getAccountList();
				for(int a = 0; a < customerAccountList.size(); a++)
				{
					Account account = customerAccountList.get(a);
					if(accountId == account.getAccountId())
					{
						success = account.withdraw(amount);
					}
				}
			}
		}
		return success;
	}
	

	/**
	 * Get a list of past transactions
	 * @param pNo - The customer's personal number
	 * @param accountId - The customer's bank account number
	 */
	public ArrayList<String> getTransactions(String pNo, int accountId)
	{
 		ArrayList<Account> customerAccountList  = new ArrayList<Account>(50);
		ArrayList<String> transaction = null;
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				customerAccountList = customer.getAccountList();
				for(int a = 0; a < customer.getAccountList().size(); a++)
				{
					Account account = customerAccountList.get(a);
					if(accountId == account.getAccountId())
					{
						transaction = account.getTransaction().getAllTransaction();
					}

				}
			}
		}
		return transaction;
	}
	
	public String lastTransaction(String pNo, int accountId)
	{
 		ArrayList<Account> customerAccountList  = new ArrayList<Account>(50);
		ArrayList<String> transaction = null;
		String lastTransaction = null;
		for(int i = 0; i < customerList.size(); i++)
		{
			Customer customer = customerList.get(i);
			if (customer.getPersonalNumber() == pNo)
			{
				customerAccountList = customer.getAccountList();
				for(int a = 0; a < customer.getAccountList().size(); a++)
				{
					Account account = customerAccountList.get(a);
					if(accountId == account.getAccountId())
					{
						transaction = account.getTransaction().getAllTransaction();
						int size = transaction.size();
						lastTransaction = transaction.get(size - 1);
					}

				}
			}
		}
		return lastTransaction;
	}
}


 