import java.io.Serializable;

//This class will be used to add items to the linked list BankList without the necessity of creating a BankList in the other classes
//Implements Serializable interface as it will be written to a object file
public class BankAcc implements Serializable{
	//Create a BankList.
	BankList bankList = new BankList();
	
	//Call the addNewAcc method from the class BankList. Add a desired account to the linked list
	public void addAcc(long accNum, double balance, String name) {
		bankList.addNewAcc(accNum, balance, name);
	}
	
	//Call the getBal method from the class BankList. Add a desired account to the linked list
	public double getBalance(long accNum) {
		return bankList.getBal(accNum);
	}
	
	//Call the addBal method from the class BankList. Add a desired account to the linked list
	public void addBalance(long accNum, double ammount) {
		bankList.addBal(accNum, ammount);
	}
	
	//Call the withBal method from the class BankList. Add a desired account to the linked list
	public void withdrawBalance(long accNum, double ammount) {
		bankList.withBal(accNum, ammount);
	}
	
	//Call the getAccNum method from the class BankList. Add a desired account to the linked list
	public boolean getAccNum(long accNum) {
		return bankList.getAccNum(accNum);
	}
	
	//Call the getAccOwner method from the class BankList. Add a desired account to the linked list
	public String getAccName(long accNum) {
		return bankList.getAccOwner(accNum);
	}
	
	//Call the addAnotherList method from the class BankList. Add a desired account to the linked list
	public void addBankList(BankAcc prevBankList) {
		bankList.addAnotherList(prevBankList.bankList);
	}
	
	//Call the deleteAll method from the class BankList. Add a desired account to the linked list
	public void delete() {
		bankList.deleteAll();
	}
}
