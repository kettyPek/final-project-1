package javabootcamp.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import javabootcamp.account.AccountType;
import javabootcamp.credentials.LogInCredentials;
import javabootcamp.menu.AppMenu;
import javabootcamp.person.AccountOwner;
import javabootcamp.person.BankManager;
import javabootcamp.utils.Utils;

public class AppManager {
	
	
	
	protected AccountOwner currentUser = null;
	protected AccountOwner [] users = {};
	
	public AppManager() {
		defineBankManager();
	}
	
	private void defineBankManager() {
		AccountOwner bankManager = new BankManager();
		bankManager.setCredentials("admin0", "admin0");
		System.out.println("admin");
		addUserToUsersArray(bankManager);
	}
	
	
	public void startApp() {
		AppMenu.displayMainMenu();
		activateMainMenuSelection();	
	}
	
	private void activateMainMenuSelection() {
		int selection = Utils.scanner.nextInt();
		switch(selection) {
		case 1:
			logIn();
			break;
		case 2:
			openAccount();
			break;
		case 3:
			exitApp();
			break;
		}
	}
	
	private void enterSelectionMenu() {
		currentUser.displaySelcetionMenu();
		int selection = Utils.scanner.nextInt();
		currentUser.activateUserSelection(selection);
	}
	
	private void logIn() {
		final int MAX_TRIES = 3;
		int tries = 0;
		while(tries<=MAX_TRIES) {
			System.out.println("Enter username:");
			String userName = Utils.scanner.next();
			System.out.println("Enter password:");
			String password = Utils.scanner.next();
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
			enterSelectionMenu();
	}
	
	private void openAccount() {
		System.out.println("Enter phone number:");
		long phoneNumber = Utils.scanner.nextLong();
		if(checkIfAccountExists(phoneNumber))
			System.out.println("You alredy have bank account");
		else {
			createAccountOwner(phoneNumber);
			createCredentials();
			addUserToUsersArray(currentUser);
			System.out.println("Request submitted successfully, manager aprroval required");
		}
	}
		
	private void exitApp() {
		Utils.scanner.close();
	}
	//
	private boolean checkIfAccountExists(long phoneNumber) {
		for(AccountOwner user: users)
			if(user.getPhoneNumber()==phoneNumber)
				return true;
		return false;
	}
	
	private void createAccountOwner(long phoneNumber) {
		System.out.println("Enter first name:");
		String firstName = Utils.scanner.next();
		System.out.println("Enter last name:");
		String lastName = Utils.scanner.next();
		System.out.println("Enter birth date in format: year-month-day");
		String birthDateString = Utils.scanner.next();
		LocalDate birthDate = LocalDate.parse(birthDateString);
		System.out.println("Enter monthly income:");
		double monthlyIncome = Utils.scanner.nextDouble();
		currentUser = new AccountOwner(firstName, lastName, phoneNumber, birthDate, monthlyIncome);
	}
	
	private void createCredentials() {
		String username;
		String password;
		do {
			System.out.println("Enter username:");
			username = Utils.scanner.next();
			if(LogInCredentials.usernameIsValid(username)) {
				if(userNameIsUnique(username)) {
					do {
						System.out.println("Enter password:");
						password = Utils.scanner.next();
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
	
	private void addUserToUsersArray(AccountOwner user) {
		AccountOwner [] updatedUsers = new AccountOwner [users.length+1];
		for(int i=0; i<users.length; i++)
			updatedUsers[i] = users[i];
		updatedUsers[users.length] = user;
		users = updatedUsers;	
	}
	
	private boolean userNameIsUnique(String username) {
		for(AccountOwner user: users) 
			if(user.getCredentials().getUsername().equals(username))
				return false;
		return true;	
	}
	
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
	
	
}
