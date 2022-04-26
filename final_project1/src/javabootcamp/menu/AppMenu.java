package javabootcamp.menu;

import javabootcamp.person.AccountOwner;
import javabootcamp.utils.Utils;

/**
 * Contains the presented menus in the application
 * @author ketty
 *
 */
public abstract class AppMenu {
	
	 public static boolean mainMenuActivated;
	 public static boolean accountOwnerMenuActivated;
	 
	 static {
		 setMenusStatus(true,false);
	 }
	 
	 private static void setMenusStatus(boolean mainMenuAtatus,boolean accountOwnerMenuStatus) {
		 mainMenuActivated = mainMenuAtatus;
		 accountOwnerMenuActivated = accountOwnerMenuStatus;
		 
	 }
	 
	 public static boolean getMainMenuActivated() {
		 return mainMenuActivated;
	 }
	 
	 public static boolean getAccountOwnerMenuActivatedd() {
		 return accountOwnerMenuActivated;
	 }

	
	/**
	 * Displays main options menu
	 */
	public static void displayMainMenu() {
		System.out.println("please choose:");
		System.out.println("1 - log in");
		System.out.println("2 - open new account");
		System.out.println("3 - Exit app");
	}
	
	/**
	 * Displays account owner options menu
	 */
	public static void displayAccountOwnerMenu() {
		System.out.println("please choose:");
		System.out.println("1 - check balance");
		System.out.println("2 - produce report");
		System.out.println("3 - deposit");
		System.out.println("4 - withdrawal");
		System.out.println("5 - transfer funds");
		System.out.println("6 - pay bill");
		System.out.println("7 - get loan");
		System.out.println("8 - log out");
	}
	
	/**
	 * Displays bank manager options menu
	 */
	public static void displayBankManagerMenu() {
		System.out.println("please choose:");
		System.out.println("1 - approve applicants");
		System.out.println("2 - produce log report");
		System.out.println("3 - log out");
	}
	
	public static void exitMainMenu() {
		setMenusStatus(false,false);
	}
	
	public static void exitAccountOwnerMenu() {
		setMenusStatus(true,false);
	}
	
	public static void activateUserMenu(AccountOwner user) {
		accountOwnerMenuActivated = true;
		while(accountOwnerMenuActivated) {
			user.displaySelcetionMenu();
			int selection = Utils.scanner.nextInt();
			user.activateUserSelection(selection);
		}
	}
	
}
