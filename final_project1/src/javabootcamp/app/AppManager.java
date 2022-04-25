package javabootcamp.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import javabootcamp.credentials.LogInCredentials;
import javabootcamp.person.AccountOwner;

public class AppManager {
	
	Scanner scanner = new Scanner(System.in);
	
	protected AccountOwner currentUser = null;
	protected AccountOwner [] users = {};
	
	
	public void manageApp() {
		
		displayMenu();
		startAction();
		scanner.close();	
	}
	
	private void displayMenu() {
		System.out.println("please choose:");
		System.out.println("1 - login");
		System.out.println("2 - open new account");
	}
	
	private void startAction() {
		int action = scanner.nextInt();
		switch(action) {
		case 1:
			logIn();
			break;
		case 2:
			openAccount();
			break;
		}
	}
	
	private void logIn() {
		final int MAX_TRIES = 3;
		int tries = 0;
		while(tries<=MAX_TRIES) {
			System.out.println("Enter username:");
			String userName = scanner.nextLine();
			System.out.println("Enter password:");
			String password = scanner.nextLine();
			credentialsAreCorrect(userName,password);
			if(currentUser!=null)
				break;
			else {
				System.out.println("Inccorenct credentials, try again");
				tries++;
			}		
		}
		if(tries==3) 
			System.out.println("You are blocked for 30 minutes, release time: " + getReleaseTimeAfterBlock());
		else
			AccountOwnerMenu();
	}
	
	private void openAccount() {
		System.out.println("Enter phone number:");
		long phoneNumber = scanner.nextLong();
		if(checkIfAccountExists(phoneNumber))
			System.out.println("You alredy have bank account");
		else {
			createAccountOwner(phoneNumber);
			createCredentials();
			addCurrentUserToUsersArray();
			System.out.println("Congrats you opend new account");
		}
	}
	
	private boolean checkIfAccountExists(long phoneNumber) {
		for(AccountOwner user: users)
			if(user.getPhoneNumber()==phoneNumber)
				return true;
		return false;
	}
	
	private void createAccountOwner(long phoneNumber) {
		System.out.println("Enter first name:");
		String firstName = scanner.next();
		System.out.println("Enter last name:");
		String lastName = scanner.next();
		System.out.println("Enter birth date in format: year-month-day");
		String birthDateString = scanner.next();
		LocalDate birthDate = LocalDate.parse(birthDateString);
		System.out.println("Enter monthly income:");
		double monthlyIncome = scanner.nextDouble();
		currentUser = new AccountOwner(firstName, lastName, phoneNumber, birthDate, monthlyIncome);
	}
	
	private void createCredentials() {
		String username;
		String password;
		do {
			System.out.println("Enter username:");
			username = scanner.next();
			if(LogInCredentials.usernameIsValid(username)) {
				if(userNameIsUnique(username)) {
					do {
						System.out.println("Enter password:");
						password = scanner.next();
						if(LogInCredentials.passwordIsValid(password))
							currentUser.setCredentials(username, password);
						else
							System.out.println("password isnt valid, try again");
					}while(!LogInCredentials.passwordIsValid(password));
				}
			}
			else
				System.out.println("username incorrect, try again");	
		}while(!LogInCredentials.usernameIsValid(username));
	}
	
	private void addCurrentUserToUsersArray() {
		AccountOwner [] updatedUsers = new AccountOwner [users.length+1];
		for(int i=0; i<users.length; i++)
			updatedUsers[i] = users[i];
		updatedUsers[users.length] = currentUser;
		users = updatedUsers;	
	}
	
	private boolean userNameIsUnique(String username) {
		for(AccountOwner user: users) 
			if(user.getCredentials().getUsername().equals(username))
				return false;
		return true;	
	}
	
	//
	private void credentialsAreCorrect(String userName, String password) {
		for(AccountOwner owner : users) 
			if(userName.matches(owner.getCredentials().getUsername())) 
				if(password.matches(owner.getCredentials().getPassword())) 
					currentUser = owner;
	}
	
	private LocalTime getReleaseTimeAfterBlock() {
		LocalTime current = LocalTime.now();
		LocalTime release = current.plusMinutes(30);
		return release;
	}
	
	private void AccountOwnerMenu() {	
		//TODO finish method
	}
	
	
}
