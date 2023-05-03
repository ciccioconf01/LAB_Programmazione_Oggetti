package diet;


public class Customer {
	String nome;
	String cognome;
	String Email="";
	String telefono="";
	
	public Customer(String name, String surname) {
		nome = name;
		cognome = surname;
	}
	
	public String getLastName() {
		return cognome;
	}
	
	public String getFirstName() {
		return nome;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public String getPhone() {
		return telefono;
	}
	
	public void SetEmail(String email) {
		Email = email;
	}
	
	public void setPhone(String phone) {
		telefono = phone;
	}
	
	@Override
	public String toString() {
		String s="";
		s = nome+" "+cognome;
		return s;
	}
	
	
}
