/**
 * class SavingsAccount: allows to manage account's balance, account number, 
 * 						 deposits, withdrawals, and get an outlook of the account 
 * @author Juhee Kim, juhkim-8
 *
 */

package logic;

public class SavingsAccount extends Account
{
	private int count = 0;
	
	public SavingsAccount()
	{
		setLastAssignedNumber(getLastAssignedNumber() + 1);	
		setAccountId(getLastAssignedNumber());
		setAccountType("Sparkonto");
		setLimit(0.0);
		setInterestRate(1.0);
	}	

	@Override	
	public boolean withdraw(double uttag)
	{	
		if(getAmount() == getLimit())
			return false;
		
		//check is it is the first withdraw and if there is enough balance in the account
		else if(count == 0 && (getAmount() - uttag) >= getLimit())
		{
			setAmount(getAmount() - uttag);
			getTransaction().withdrawTransaction(uttag, getAmount());
			count ++;
			return true;
		}
		
		//2% extra cash is withdrawn 
		else if(count == 1 && (getAmount() - uttag - (uttag * 0.02)) >= getLimit())
		{
			setAmount(getAmount() - uttag - (uttag * 0.02));
			getTransaction().withdrawTransaction(uttag, getAmount());
			return true;
		}	
		else
			return false;
	}

	@Override
	public String accountTypeInterest()
	{
		String str = this.getAccountId() + " " + getAmount() + " " + getAccountType() + " " + getInterestRate();
		return str;
	}
	
	@Override
	public String accountInterestRate()
	{
		double interest = (getAmount() * getInterestRate()) / 100.0;
		String str = this.getAccountId() + " " + getAmount() + " " + getInterestRate() + interest;
		return str;
	}
	
	@Override
	public String getDeleteInfo()
	{
		double interest = (getAmount() * getInterestRate()) / 100.0;
		if(getAmount() == 0.0)
		{
			interest = 0.0;
		}
		String str = this.getAccountId() + " " + getAmount() + " " + getAccountType() + " " + getInterestRate() + " " + interest;
		return str;
	}
}
	

