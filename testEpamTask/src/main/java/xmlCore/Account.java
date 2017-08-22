package xmlCore;

public class Account {
	public String email;
	public String password;

	public void print() {
		System.out.println("Account: ");
		System.out.println(" email -> " + email);
		System.out.println(" password -> " + password);
	
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", password=" + password + "]";
	}
}
