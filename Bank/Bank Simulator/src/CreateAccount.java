import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class CreateAccount {
	private long accNum;
	private Scanner sc;
	private PrintWriter pw;
	ObjectOutputStream accounts;
	ObjectInputStream prevBankList;
	private static BankAcc newBankAcc = new BankAcc();
	
	
	public long getAccNum() {
		return accNum;
	}
	
	//Get the bank account linked list
	public static BankAcc getBankAcc() {
		return newBankAcc;
	}
	
	//Generate a random 9 digits account
	private long generateAccNum() {
		long lenght = 100000000;
		long limit = 999999999;
		return (long)(Math.random()*limit+lenght);
	}
	
	public void createAccount() {
		sc = new Scanner(System.in);
		String pass;
		long accNum;
		System.out.println("\nPlease, enter your desire password");
		pass = sc.nextLine();
		
		do {
			accNum = generateAccNum();
		}while(checkIfAccExist(accNum));
		
		System.out.println("\nYour account number is: " + accNum + ". Be sure to write it down as you will need it to login\n");
		//Add credentials to a text file for and easier access
		newAccount(accNum, pass);
	
	}
	
	private boolean checkIfAccExist(long accNum) {
		
		try {
			long accInfo;
			sc = new Scanner(new FileInputStream("Data.txt"));
			//Check if the randomly generated account already exists
			while(sc.hasNextLong()) {
				accInfo = sc.nextLong();
				if(accInfo == accNum) {
					return true;
				}
			}
		} catch(FileNotFoundException e) {
			System.err.println("File does not exist");
		}
		return false;
	}
	
	
	private void newAccount(long accNum, String pass) {
		sc = new Scanner(System.in);
		String op;
		double balance = 0.0;
		String name;
		BankAcc bankAcc;
		
		System.out.println("Introduce your name: ");
		name = sc.nextLine();
		
		System.out.println("\nDo you wish yo deposit?: (Yes or No) ");
		op = sc.nextLine();
		
		if(op.equals("Yes") || op.equals("yes")) {
			System.out.println("\nEnter the ammount you wish to deposit: ");
			balance = sc.nextDouble();
		}
		
		//Add a new node to the bank accounts linked list
		newBankAcc.addAcc(accNum, balance, name);
		//assign it to a temporarily BankAcc variable
		bankAcc = newBankAcc;
		
		/*Try to open the different files. Data.txt contains the credentials of the new user.
		Accounts.bin contains the bank accounts linked list. Thsi will act as my data base*/
		try {
			pw = new PrintWriter(new FileOutputStream("Data.txt", true));
			//Write the new user credentials
			pw.println(accNum + " " + pass);
			//Check if there is already a previous bank account linked list in the file.
			if(new File("Accounts.bin").length() != 0) {
				//If the file is empty, open a stream to the Accounts.bin file
				prevBankList = new ObjectInputStream(new FileInputStream("Accounts.bin"));
				//Read the current BankAcc object stored in it
				bankAcc = (BankAcc) prevBankList.readObject();
				//Append the value of the new BankAcc created to the one present in the file
				bankAcc.addBankList(newBankAcc);
			}
			accounts = new ObjectOutputStream(new FileOutputStream("Accounts.bin"));
			//Write the new (updated) bank account linked list to the Accounts.bin file
			accounts.writeObject(bankAcc);
			accounts.close();
		} catch(FileNotFoundException e) {
			System.err.println("File does not exist");
		} catch (IOException e) {
			System.out.println("An error has occured");
		} catch(ClassNotFoundException e) {
			System.out.println("An error has occured");
		} finally {
			pw.close();
		}
	}
}
