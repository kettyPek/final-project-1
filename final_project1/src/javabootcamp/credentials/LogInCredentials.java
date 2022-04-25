package javabootcamp.credentials;

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
