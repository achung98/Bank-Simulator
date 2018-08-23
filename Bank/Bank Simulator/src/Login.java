import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Login {
	private Scanner sn;
	private PrintWriter pw;
	private long accOwner;
	private String pass;
	private int attempts = 0;
	private boolean auth = false;
	
	public void greetings() {
		sn = new Scanner(System.in);
		boolean control = true;
		
		do {
			try {
				outerloop:
				do {
					System.out.println("\nEnter your account number: ");
					accOwner = sn.nextLong();
					control = false;
					if(checkIfAccExist(accOwner)) {
						do {
							System.out.println("Enter your password: (3 attempts)");
							pass = sn.next();
							if(!checkPass(accOwner, pass)) {
								attempts++;
								System.out.println("You have entered the wrong password. Attempts remaining: " + (3-attempts) + "\n");
							}
							else {
								break outerloop;
							}
						}while(attempts != 3);
					} else {
						System.out.println("That account number does no exist");
						attempts++;
					}
				}while(attempts !=3);
				if(attempts == 3) {
					System.out.println("Authentication error\n");
					return;
				}
			} catch(InputMismatchException e) {
				sn.next();
				System.out.println("Account number must contains only numbers");
			}
		}while(control);
		welcome(accOwner);
	}
	
	private void welcome(long accNum) {
		int sel = 0;
		BankAcc bankAcc = null;
		ObjectInputStream accounts;
		try {
			//Get the bank accounts linked list (Access data base)
			accounts = new ObjectInputStream(new FileInputStream("Accounts.bin"));
			bankAcc = (BankAcc) accounts.readObject();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("An error has occured");
		} catch(ClassNotFoundException e) {
			System.out.println("An error has occured");
		}
		
		//Get the name associated to the account number
		System.out.println("\n\nWelcome: " + bankAcc.getAccName(accNum) + "\n");
		
		do {
			try {
				System.out.println("Menu:\n"
						+ "1. Check balance\n"
						+ "2. Transfer money\n"
						+ "3. Deposit money\n"
						+ "4. Withdraw money\n"
						+ "5. Exit\n");
				sel = sn.nextInt();
				if(sel == 1 || sel == 2 || sel == 3 || sel == 4 || sel == 5)
					//Based on the users selection, an action will be activated
					Action.checkAction(sel, accNum, bankAcc);
				else {
					System.out.println("Enter a valid option\n");
				}
			} catch (InputMismatchException e) {
				sn.next();
				System.out.println("Enter a number!\n\n");
			}
		}while(sel != 5);
	}
		
	private boolean checkIfAccExist(long accNum) {
		Scanner sc;
		String[] tempAccNum;
		try {
			//Read the credentials in the Data.txt file
			sc = new Scanner(new FileInputStream("Data.txt"));
			while(sc.hasNextLine()) {
				//Store the account number and password in an array
				tempAccNum = sc.nextLine().split(" ");
				//Check if the account number exists
				if(Long.toString(accNum).equals(tempAccNum[0])) {
					return true;
				}
			}
			
		} catch(FileNotFoundException e) {
			System.err.println("File does not exist");
		}
		return false;
	}
	
	private boolean checkPass(Long accNum, String pass) {
		Scanner sc;
		String[]tempPass;
		try {
			//Read the credentials in the Data.txt file
			sc = new Scanner(new FileInputStream("Data.txt"));
			while(sc.hasNextLine()) {
				//Store the account number and password in an array
				tempPass = sc.nextLine().split(" ");
				//Check if password coincide with the one associated to the account number
				if(Long.toString(accNum).equals(tempPass[0]) && pass.equals(tempPass[1]))
					return true;
			}
		} catch(FileNotFoundException e) {
			System.err.println("File does not exist");
		}
		return false;
	}
}
