package javabootcamp.app;

import java.time.LocalTime;
import java.util.Scanner;

import javabootcamp.person.AccountOwner;

public class AppManager {
	
	Scanner scanner = new Scanner(System.in);
	
	protected AccountOwner currentUser;
	protected AccountOwner [] users;
	
	
	public void manageApp() {
		
		displayMenu();
		startAction();
		scanner.close();	
	}
	
	private void displayMenu() {
		System.out.println("please choose:");
		System.out.println("1 - login");		
	}
	
	private void startAction() {
		int action = scanner.nextInt();
		switch(action) {
		case 1:
			logIn();
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
		
	}
}
