/**
 * abstract class Account: Super class of SavingsAccount and CreditAccount 
 * @author Juhee Kim, juhkim-8
 *
 */

package logic;

public abstract class Account 
{
	private int accountId = 0;
	private double amount = 0.0;
	private double interestRate;
	private String accountType;
	private double limit;
	private static int lastAssignedNumber = 1000;
	private Transaction transaction = new Transaction();
	
	public int getAccountId() 
	{
		return accountId;
	}

	public int getLastAssignedNumber()
	{
		return lastAssignedNumber;
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public double getInterestRate()
	{
		return interestRate;
	}
	
	public String getAccountType() 
	{
		return accountType;
	}

	public double getLimit() 
	{
		return limit;
	}

	public Transaction getTransaction()
	{
		return transaction;
	}
		
	public void setAccountId(int id)
	{
		accountId = id;
	}
	
	public void setAmount(double amount)
	{
		this.amount = amount; 
	}
	
	public void setInterestRate(double r)
	{
		interestRate = r;
	}
	
	public void setLimit(double limit) 
	{
		this.limit = limit;
	}

	public void setAccountType(String type)
	{
		accountType = type;
	}

	public static void setLastAssignedNumber(int lastAssignedNumber) 
	{
		Account.lastAssignedNumber = lastAssignedNumber;
	}

	public boolean deposit(double insättning)
	{
		amount += insättning;
		transaction.depositTransaction(insättning, amount);
		return true;
	}
	
	public abstract boolean withdraw(double uttag);

	public abstract String accountTypeInterest();
	
	public abstract String accountInterestRate();
	
	public abstract String getDeleteInfo();


 
}
