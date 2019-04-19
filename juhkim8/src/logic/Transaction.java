package logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction {
	
	private ArrayList<String> transactions;
	private String info;
	private Calendar cal;
    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    

	public Transaction()
	{
		transactions = new ArrayList<String>(100);
	}

	public ArrayList<String> getAllTransaction() 
	{
		return transactions;
	}
	
	public void depositTransaction(double insättning, double amount) 
	{
		cal = Calendar.getInstance();
		String time = SDF.format(cal.getTime());
		info = time + " " + insättning + " " + amount;  
		transactions.add(info);
	}
	
	public void withdrawTransaction(double uttag, double amount) 
	{
		cal = Calendar.getInstance();
		String time = SDF.format(cal.getTime());
		info = time + " -" + uttag + " " + amount;  
		transactions.add(info);
	}

}

