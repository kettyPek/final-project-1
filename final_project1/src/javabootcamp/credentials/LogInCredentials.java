package javabootcamp.credentials;

/**
 * Describes log in credentials 
 * @author ketty
 *
 */
public class LogInCredentials {
	
	protected String username;
	protected String password;

	
	public LogInCredentials(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * Checks if the username is valid
	 * Valid username contains only letters and digits 
	 * @param username 
	 * @return true if username is valid, else returns false
	 */
	public static boolean usernameIsValid(String username) {
		char ch ;
		for(int i=0; i<username.length(); i++) { 
			ch = username.charAt(i);
			if(ch<'0'||ch>'9' )
				if( ch<'a'|| ch>'z')
					if(ch<'A'|| ch>'B')
						return false;
		}
		return true;
	}
	
	/**
	 * Checks if the password is valid
	 * Valid password contains only letters and digits and its length is the range [4,8] 
	 * @param password
	 * @return true if password is valid, else returns false;
	 */
	public static boolean passwordIsValid(String password) {
		if(password.length()<4 || password.length()>8)
			return false;
		char ch ;
		int countDigit = 0, countLetter = 0;
		for(int i=0; i<password.length(); i++) { 
			ch = password.charAt(i);
			if(ch>='0'&&ch<='9' )
				countDigit++;
			else if(ch>='a'&&ch<='z' || ch>='A'&&ch<='B')
					countLetter++;
		}
		if(countDigit==0 || countLetter==0)
			return false;
		return true;
	}
	
	
	
}
