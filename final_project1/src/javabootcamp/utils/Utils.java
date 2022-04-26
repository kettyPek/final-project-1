package javabootcamp.utils;

import java.time.LocalDate;
import java.util.Scanner;
/**
 * Contains Scanner object and static methods
 * @author ketty
 *
 */
public abstract class Utils {
	
	public static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Receive date from a user
	 * @return date in LocalDate type
	 */
	public static LocalDate receiveDateFromUser() {
		System.out.println("Enter date int format year-month-day:");
		String dateString = scanner.next();
		LocalDate date = LocalDate.parse(dateString);
		return date;
	}
	
	/**
	 * Receive string from the user which represent object to scan, and scans integer
	 * @param type - object to scan
	 * @return scanned integer
	 */
	public static int scanIntOf(String type) {
		System.out.println("Enter " + type);
		int userInput = scanner.nextInt();
		return userInput;
	}
	
	/**
	 * Receive string from the user which represent object to scan, and scans double 
	 * @param type - object to scan
	 * @return scanned double
	 */
	public static double scanDoubleOf(String type) {
		System.out.println("Enter " + type);
		double userInput = scanner.nextDouble();
		return userInput;
	}
	
	/**
	 * Receive string from the user which represent object to scan, and scans String 
	 * @param type - object to scan
	 * @return scanned String
	 */
	public static String scanStringOf(String type) {
		System.out.println("Enter " + type);
		String userInput = scanner.next();
		return userInput;
	}
	
	/**
	 * Receive string from the user which represent object to scan, and scans Long 
	 * @param type - object to scan
	 * @return scanned long
	 */
	public static long scanLongOf(String type) {
		System.out.println("Enter " + type);
		long userInput = scanner.nextLong();
		return userInput;
	}
	
	

}
