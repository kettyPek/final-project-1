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
/**
 * This class manage bank application
 * @author ketty
 *
 */
public class AppManager {
	
	protected AccountOwner currentUser = null;
	protected static AccountOwner [] users = {};
	protected BankManager bankManager;
	
	public AppManager() {
		this.bankManager = defineBankManager();
	}
	
	/**
	 * Creates manual bank manager and add it to users array
	 * @return
	 */
	private BankManager defineBankManager() {
		BankManager bankManager = new BankManager();
		bankManager.setCredentials("admin0", "admin0");
		addUserToUsersArray(bankManager);
		return bankManager;
	}
	
	/**
	 * Starts application
	 */
	public void startApp() {
		while(AppMenu.getMainMenuActivated()){
			AppMenu.displayMainMenu();
			activateMainMenuSelection();	
		}
	}
	
	/**
	 * Activates options from the main menu by user input
	 */
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
		default:
			System.out.println("invalid input");
			break;	
		}
	}
	
	/**
	 * Directs to account owner menu if credentials correct
	 */
	private void logIn() {
		final int MAX_TRIES = 3;
		int tries = 0;
		while(tries<=MAX_TRIES) {
			String userName = Utils.scanStringOf("username");
			String password = Utils.scanStringOf("password");
			if(userHasAccount(userName,password)) {
				AppMenu.activateUserMenu(currentUser);
				return;
			}
			else {
				System.out.println("Inccorenct credentials, try again");
				tries++;
			}
		}
		System.out.println("You are blocked for 30 minutes, release time: " + getReleaseTimeAfterBlock()); 	
	}
	
	/**
	 * Activate open account option from main menu 
	 */
	private void openAccount() {
		System.out.println("Enter phone number:");
		long phoneNumber = Utils.scanner.nextLong();
		if(checkIfAccountExists(phoneNumber))
			System.out.println("You alredy have bank account");
		else {
			createAccountOwner(phoneNumber);
			createCredentials();
			addUserToUsersArray(currentUser);
			System.out.println(users.length);
			System.out.println("Request submitted successfully, manager aprroval required");
			bankManager.addUsersToApprove(currentUser);
		}
	}
		
	/**
	 * Exits application
	 */
	private void exitApp() {
		AppMenu.exitMainMenu();
		Utils.scanner.close();
	}
	
	/**
	 * Checks by given phone number if an account exist with same phone number
	 * @param phoneNumber - phone number of user
	 * @return true if account exist in users array, else returns false
	 */
	private boolean checkIfAccountExists(long phoneNumber) {
		for(AccountOwner user: users)
			if(user.getPhoneNumber()==phoneNumber)
				return true;
		return false;
	}
	
	/**
	 * Creates account owner object for applicant by given phone number
	 * @param phoneNumber - phone number of the applicant
	 */
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
		currentUser = new AccountOwner(firstName, lastName, phoneNumber, birthDate, monthlyIncome,bankManager);
	}
	
	/**
	 * Creates credentials for application user
	 */
	private void createCredentials() {
		String username;
		String password;
		do {
			System.out.println("Enter username:");
			username = Utils.scanner.next();
			if(usernameCanBeSet(username) )  {
				do {
					System.out.println("Enter password:");
					password = Utils.scanner.next();
					if(LogInCredentials.passwordIsValid(password))
						currentUser.setCredentials(username, password);
					else
						System.out.println("password isnt valid, try again");
				}while(!LogInCredentials.passwordIsValid(password));
			}
			else
				System.out.println("username incorrect, try again");	
		}while(!usernameCanBeSet(username));
	}
	
	/**
	 * Checks if username is unique
	 * @param username - userrname to check
	 * @return true if unique, else false
	 */
	private boolean userNameIsUnique(String username) {
		for(AccountOwner user: users) 
			if(user.getCredentials().getUsername().equals(username))
				return false;
		return true;	
	}
	
	/**
	 * checks is given username can be set for current user
	 * @param username - given username
	 * @return true if username can be set, else return false
	 */
	private boolean usernameCanBeSet(String username) {
		return userNameIsUnique(username) && LogInCredentials.usernameIsValid(username);
	}
	
	/**
	 * Adds given user to users array
	 * @param user to add
	 */
	private void addUserToUsersArray(AccountOwner user) {
		AccountOwner [] updatedUsers = new AccountOwner [users.length+1];
		for(int i=0; i<users.length; i++)
			updatedUsers[i] = users[i];
		updatedUsers[users.length] = user;
		users = updatedUsers;	
	}
	
	/**
	 * Assign cuurentUser to be the account owner by given credentials and return true
	 * @param userName
	 * @param password
	 */
	private AccountOwner receiveUserByCredentials(String userName, String password) {
		for(AccountOwner user : users) 
			if(userName.matches(user.getCredentials().getUsername())) 
				if(password.matches(user.getCredentials().getPassword())) 
					return user;
		return null;
	}
	
	/**
	 * Checks if the user has account already
	 * @param userName
	 * @param password
	 * @return - true if user has account, else return false
	 */
	private boolean userHasAccount(String userName, String password) {
		AccountOwner user = receiveUserByCredentials(userName,password);
		if(user != null) {
			if(user.userHasAccount()) {
				currentUser = user;
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Receive release time after blocking user
	 * @return release time
	 */
	private LocalTime getReleaseTimeAfterBlock() {
		LocalTime current = LocalTime.now();
		LocalTime release = current.plusMinutes(30);
		return release;
	}
	
	/**
	 * Returns account owner by its phone number
	 * Reruns null if phone number not exists 
	 * @param phoneNumer - account owner's phone number
	 * @return
	 */
	public static AccountOwner getAccountOwnerByPhoneNumber(long phoneNumer) {
		for(AccountOwner owner: users) 
			if(owner.getPhoneNumber()==phoneNumer)
				return owner;
		return null;
	}
		
}
