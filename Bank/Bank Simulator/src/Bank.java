import java.util.Scanner;

public class Bank {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sel;
	
		do {
			System.out.println("Select an option:\n"
					+ "1. Create an account\n"
					+ "2. Login\n"
					+ "3. Exit");
			sel = sc.nextLine();
			if(sel.equals("1")) {
				CreateAccount newAcc = new CreateAccount();
				newAcc.createAccount();
			} else if(sel.equals("2")) {
				Login exAcc = new Login();
				exAcc.greetings();
			} else if(!sel.equals("3"))
				System.out.println("Enter a valid option\n");
		}while(!sel.equals("3"));
	}
}
