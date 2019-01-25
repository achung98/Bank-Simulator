import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

class Action {
	public static void checkAction(int sel, long accNum, BankAcc bankAcc) {
		Scanner in = new Scanner(System.in);
		double ammount;
		long acc;
		boolean cont;
		int attempts = 0;

		switch(sel) {
		case 1:
			//Get balance
			System.out.println("Your balance is: " + bankAcc.getBalance(accNum) + "\n");
			break;
		case 2:
			cont = true;
			do {
				try {
					System.out.println("Enter the account you wish to transfer money: ");
					acc = in.nextLong();
					cont = true;
					//Check if destination account is valid
					if(acc != accNum) {
						//Check if destination account is valid
						if(bankAcc.getAccNum(acc)) {
							do {
								try {
									System.out.println("Enter the ammount you wish to transfer: (3 attempts)");
									ammount = in.nextDouble();
									//Check if the user has enough money
									if(bankAcc.getBalance(accNum) < ammount) {
										System.out.println("Invalid transaction. You have: " + bankAcc.getBalance(accNum) + "\n");
										attempts++;
									} else {
										bankAcc.addBalance(acc, ammount);
										bankAcc.withdrawBalance(accNum, ammount);
										System.out.println("Payment made!\n");
										cont = false;
									}
									if(attempts == 3)
										return;
								} catch(InputMismatchException e) {
									System.out.println("Please, enter an valid ammount\n");
									in.next();
								}
							}while(cont);
						} else {
							System.out.println("Account does not exist");
						}
					} else {
						System.out.println("That is your account!");
					}
				} catch(InputMismatchException e) {
					System.out.println("Please, enter a valid account number\n");
					in.next();
					}
			}while(cont);
			//Rewrite the Accounts.bin (update database)
			toDataBase(bankAcc);
			break;
		case 3:
			cont = true;
			do {
				try {
					System.out.println("Enter the ammount you wish to deposit:");
					ammount = in.nextDouble();
					cont = false;
					//Update account balance
					bankAcc.addBalance(accNum, ammount);
					System.out.println("Your new balance is: " + bankAcc.getBalance(accNum) + "\n");
				} catch(InputMismatchException e) {
					System.out.println("Please, enter a valid ammount\n");
					in.next();
				}
			}while(cont);
			//Rewrite the Accounts.bin (update database)
			toDataBase(bankAcc);
			break;
		case 4:
			cont = true;
			do {
				try {
					System.out.println("Enter the ammount you wish to withdraw:");
					ammount = in.nextDouble();
					cont = false;
					//Update account balance
					bankAcc.withdrawBalance(accNum, ammount);
					System.out.println("Your new balance is: " + bankAcc.getBalance(accNum) + "\n");
				} catch(InputMismatchException e) {
					System.out.println("Please, enter a valid ammount\n");
					in.next();
				}
			}while(cont);
			//Rewrite the Accounts.bin (update database)
			toDataBase(bankAcc);
			break;
		}
	}

	//Rewrite the Accounts.bin (update database)
	private static void toDataBase(BankAcc bankAcc) {
		try (ObjectOutputStream ow = new ObjectOutputStream(new FileOutputStream("Accounts.bin"))) {
			ow.writeObject(bankAcc);
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("An error has occured");
		}
	}
}
