package javabootcamp.menu;
/**
 * Contains the presented menus in the application
 * @author ketty
 *
 */
public class AppMenu {
	
	/**
	 * Displays main options menu
	 */
	public static void displayMainMenu() {
		System.out.println("please choose:");
		System.out.println("1 - login");
		System.out.println("2 - open new account");
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
	}
	
	public static void displayBankManagerMenu() {
		System.out.println("please choose:");
		System.out.println("1 - approve applicants");
	}
}
