/**
 * class CreditAccount: allows to manage a credit account's balance, account number, 
 * 						 deposits, withdrawals, and get an outlook of the account 
 * @author Juhee Kim, juhkim-8
 *
 */


package logic;

public class CreditAccount extends Account
{
	private final double DEBT_INTEREST = 7.0;	

	public CreditAccount()
	{		
		setLastAssignedNumber(getLastAssignedNumber() + 1);	
		setAccountId(getLastAssignedNumber());
		setAccountType("Kreditkonto");
		setLimit(-5000.0);
		setInterestRate(0.5);
	}	
	
	public double getDebtInterest()
	{
		return DEBT_INTEREST;
	}

	@Override
	public boolean withdraw(double uttag) 
	{
		//checks if the withdraw exceed the credit account's limit or not
		if ((getAmount() - uttag) >= getLimit())
		{
			setAmount(getAmount() - uttag);
			//LocalDateTime now = LocalDateTime.now();
			//String str = getDTF().format(now) + " -" + uttag + " " + getAmount();
			getTransaction().withdrawTransaction(uttag, getAmount());
			return true;
		}
		return false;		
	}

	@Override
	public String accountTypeInterest()
	{
		//decides whether interest rate is to be applied or debt interest
		String str;
		if(getAmount() < 0)
		{
			str = getAccountId() + " " + getAmount() + " " + getAccountType() + " 7.0";
		}
		else
		{
			str = getAccountId() + " " + getAmount() + " " + getAccountType() + " 0.5";
		}
		return str;
	}
	
	@Override
	public String accountInterestRate()
	{
		double interest = (getAmount() * getInterestRate()) / 100.0;
		String str = getAccountId() + " " + getAmount() + " " + getInterestRate() + interest;
		return str;
	}
	
	@Override
	public String getDeleteInfo()
	{
		//decides whether interest rate is to be applied or debt interest
		if(getAmount() >= 0)
		{
			double interest = ((getAmount() * getInterestRate()) / 100.0);
			return getAccountId() + " " + getAmount() + " " + getAccountType() + " " + getInterestRate() + " " + interest;
		}
		else 
		{
			double interest = ((getAmount() * DEBT_INTEREST) / 100.0);
			return getAccountId() + " " + getAmount() + " " + getAccountType() + " " + DEBT_INTEREST + " " + interest;
		}		
	}
}
