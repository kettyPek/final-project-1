package javabootcamp.utils;

import java.time.LocalDate;
import java.util.Scanner;

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
	
	public static int scanIntOf(String type) {
		System.out.println("Enter " + type);
		int userInput = scanner.nextInt();
		return userInput;
	}
	
	public static double scanDoubleOf(String type) {
		System.out.println("Enter " + type);
		double userInput = scanner.nextDouble();
		return userInput;
	}
	
	public static String scanStringOf(String type) {
		System.out.println("Enter " + type);
		String userInput = scanner.next();
		return userInput;
	}
	
	public static long scanLongOf(String type) {
		System.out.println("Enter " + type);
		long userInput = scanner.nextLong();
		return userInput;
	}
	
	

}
