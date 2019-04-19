package gui;

import javax.swing.JFrame;

import logic.BankLogic;


public class Test {
	
	public static void main(String arg[])
	{	
		BankLogic bank = new BankLogic();
		bank.createCustomer("Ejnar", "Kim", "123");
		bank.createCustomer("Juhee", "Sörensen", "123456");
		bank.createCreditAccount("123");
		bank.createCreditAccount("123");
		bank.createSavingsAccount("123");
		bank.createSavingsAccount("123");
		JFrame frame = new MainFrame(bank);
	
	}
}
