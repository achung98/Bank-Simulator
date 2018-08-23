import java.io.Serializable;

//Implements Serializable interface as these will be written to a object file
public class BankList implements Serializable{
	private class BankAcc implements Serializable{
		
		//Content of my linked list
		private long accNum;
		private double balance;
		private String name;
		
		//Link to the next node
		private BankAcc next;
		
		//Default constructor
		public BankAcc() {
			accNum = 0000000000L;
			balance = 0.0;
			name = null;
			next = null;
		}
		
		//Parametrized constructor
		public BankAcc(long accNum, double balance, String name, BankAcc next) {
			this.accNum = accNum;
			this.balance = balance;
			this.name = name;
			this.next = next;
		}
		
		// Getters and setters
		public String getName() {
			return name;
		}
		
		public long getAccNum() {
			return accNum;
		}
		
		public double getBalance() {
			return balance;
		}
		
		public void setBalance(double balance) {
			this.balance = balance;
		}
		
		public String toString() {
			return accNum + " " + balance + " " + name;
		}
		
	}
	
	//First Node on my list
	private BankAcc head;
	
	//Default constructor
	public BankList() {
		head = null;
	}
	
	//Add new account to the end of the linked list
	public void addNewAcc(long accNum, double balance, String name) {
		//If the list is empty, just add it as the first item
		if(head == null) 
			head = new BankAcc(accNum, balance, name, null);
		else {
			//Create new pointer to the head
			BankAcc first = head;
			//Check if there is a next node
			while(first.next != null) {
				//Assign first to the next node
				first = first.next;
			}
			//Create new node at the end of the linked list
			first.next = new BankAcc(accNum, balance, name, null);
		}	
	}
	
	//Check if the account number exists in a node
	public boolean getAccNum(long accNum) {
		if(head == null)
			return false;
		BankAcc first = head;
		//While it is not the end of the list, check for the account number on the node
		while(first != null) {
			if(first.getAccNum() == accNum)
				return true;
			else
				//Go to the next node
				first = first.next;
		}
		return false;
	}
	
	//Check the owner of the account number
	public String getAccOwner(long accNum) {
		if(head == null)
			return "Account not found";
		BankAcc first = head;
		//While it is not the end of the list, check for the name in the node of a certain account number
		while(first != null) {
			if(first.getAccNum() == accNum)
				return first.name;
			else
				//Go to the next node
				first = first.next;
		}
		return "Name not found";
	}
	
	//Get balance of an account
	public double getBal(long accNum) {
		if(head == null)
			return 0.0;
		BankAcc first = head;
		//While it is not the end of the list, check for the balance of a certain account number
		while(first != null) {
			if(first.getAccNum() == accNum)
				break;
			else
				//Go to the next node
				first = first.next;
		}
		return first.getBalance();
	}
	
	//Add balance to the an account number
	public void addBal(long accNum, double ammount) {
		BankAcc first = head;
		//While it is not the end of the list, find the account number
		while(first != null) {
			if(first.getAccNum() == accNum) {
				//Once the account is found, add the ammount desired to the balance of the account
				first.setBalance(first.getBalance() + ammount);
				break;
			} else
				//Go to the next node
				first = first.next;
		}
	}
	
	//Susbtract money from the balance of an account number
	public void withBal(long accNum, double ammount) {
		BankAcc first = head;
		//While it is not the end of the list, find the account number
		while(first != null) {
			if(first.getAccNum() == accNum) {
				//Once the account is found, substract the desired ammount
				first.setBalance(first.getBalance() - ammount);
				break;
			} else
				//Go to the next node
				first = first.next;
		}
	}
	
	//Add another list to the current linked list
	public void addAnotherList(BankList newBankList) {
		//If the head of the other list is null, do not make changes
		if(newBankList.head == null)
			return;
		BankAcc first = head;
		//If the next node is not null
		while(first.next != null) {
			first = first.next;
		}
		//This should be the last node. Set it to the head of the other linked list
		first.next = newBankList.head;
	}
	
	//Delete the linked list
	public void deleteAll() {
		head = null;
	}
}
