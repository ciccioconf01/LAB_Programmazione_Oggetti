package social;

import java.util.*;


public class Social {

	List<utente> utenti = new ArrayList<>();
	List<gruppo> gruppi = new ArrayList<>();
	
	public void addPerson(String code, String name, String surname)
			throws PersonExistsException {
		
		utente persona = new utente(code,name,surname);
		boolean trovato = false;
		for (utente ut: utenti) {
			if (ut.GetCod().compareTo(code)==0) {
				trovato = true;
			}
		}
		
		if (trovato == true) {
			throw new PersonExistsException ();
		}
		
		else {
			utenti.add(persona);
		}
		

	}

	
	public String getPerson(String code) throws NoSuchCodeException {
		
		String stringa = "";
		boolean trovato = false;
		for (utente persona : utenti) {
			if (persona.GetCod().compareTo(code)==0) {
				trovato = true;
				stringa = persona.GetCod() + " " +persona.GetNome() + " " + persona.GetCognome();
			}
		}
		
		if (trovato == false) {
			throw new NoSuchCodeException ();
		}
		
		else {
			return stringa;
		}
	}

	/**
	 * Define a friendship relationship between to persons given their codes.
	 * 
	 * Friendship is bidirectional: if person A is friend of a person B, that means that person B is a friend of a person A.
	 * 
	 * @param codePerson1	first person code
	 * @param codePerson2	second person code
	 * @throws NoSuchCodeException in case either code does not exist
	 */
	public void addFriendship(String codePerson1, String codePerson2)
			throws NoSuchCodeException {
		
		boolean trovato1=false;
		boolean trovato2=false;
		utente persona1 = null;
		utente persona2 = null;
		
		for (utente persona : utenti) {
			if (persona.GetCod().compareTo(codePerson1)==0) {
				trovato1 = true;
				persona1 = persona;
			}
			
			if (persona.GetCod().compareTo(codePerson2)==0) {
				trovato2 = true;
				persona2 = persona;
			}
			
			
		}
		
		if (trovato1 == false || trovato2 == false) {
			throw new NoSuchCodeException();
		}
		else {
			persona1.addFriend(persona2);
			persona2.addFriend(persona1);
		}
		
		

	}

	
	public Collection<String> listOfFriends(String codePerson)
			throws NoSuchCodeException {
		Collection<String> collezione = new ArrayList<>();
		Collection<utente> collezione2 = new ArrayList<>();
		
		boolean trovato=false;
		for (utente persona : utenti) {
			if (persona.GetCod().compareTo(codePerson)==0) {
				trovato = true;
				collezione2 = persona.GetFriend();
			}
		}
		String stringa;
		if (trovato == false) {
			throw new NoSuchCodeException ();
		}
		
		else {
			for (utente elem : collezione2) {
				stringa = elem.GetCod();
				collezione.add(stringa);
			}
		}
		
			
		return collezione;
	}

	
	public Collection<String> friendsOfFriends(String codePerson)
			throws NoSuchCodeException {
		boolean trovato=false;
		Collection<utente> collezione2 =  new ArrayList<>();
		Collection<utente> collezioneparz =  new ArrayList<>();
		Collection<String> collezionetot =  new ArrayList<>();
		for (utente persona : utenti) {
			if (persona.GetCod().compareTo(codePerson)==0) {
				trovato = true;
				collezione2 = persona.GetFriend();
			}
		}
		
		if (trovato == false) {
			throw new NoSuchCodeException ();
		}
		
		else {
			for (utente elem : collezione2) {
				collezioneparz = elem.GetFriend();
				for (utente elem2 : collezioneparz) {
					if (elem2.GetCod().compareTo(codePerson)!=0) {
					collezionetot.add(elem2.GetCod());
					}
				}
			}
		}
		
		return collezionetot;
	}

	
	public Collection<String> friendsOfFriendsNoRepetition(String codePerson)
			throws NoSuchCodeException {
		boolean trovato=false;
		Collection<utente> collezione2 =  new ArrayList<>();
		Collection<utente> collezioneparz =  new ArrayList<>();
		Collection<String> collezionetot =  new ArrayList<>();
		Set <String> set = new HashSet<>();
		for (utente persona : utenti) {
			if (persona.GetCod().compareTo(codePerson)==0) {
				trovato = true;
				collezione2 = persona.GetFriend();
			}
		}
		
		if (trovato == false) {
			throw new NoSuchCodeException ();
		}
		
		else {
			for (utente elem : collezione2) {
				collezioneparz = elem.GetFriend();
				for (utente elem2 : collezioneparz) {
					if (elem2.GetCod().compareTo(codePerson)!=0) {
					set.add(elem2.GetCod());
					}
				}
			}
			
			for (String elem : set) {
				collezionetot.add(elem);
			}
		}
		
		return collezionetot;
	}



	public void addGroup(String groupName) {
		gruppo group = new gruppo(groupName);
		gruppi.add(group);
	}

	/**
	 * Retrieves the list of groups.
	 * 
	 * @return the collection of group names
	 */
	public Collection<String> listOfGroups() {
		Collection<String> lista = new ArrayList<>();
		for( gruppo elem : gruppi) {
			lista.add(elem.GetName());
		}
		
		return lista;
	}

	/**
	 * Add a person to a group
	 * 
	 * @param codePerson person code
	 * @param groupName  name of the group
	 * @throws NoSuchCodeException in case the code or group name do not exist
	 */
	public void addPersonToGroup(String codePerson, String groupName) throws NoSuchCodeException {
		boolean trovato1 = false;
		boolean trovato2 = false;
		utente p = null;
		gruppo g = null;
		for (utente persona : utenti) {
			if (persona.GetCod().compareTo(codePerson)==0) {
				trovato1 = true;
				p = persona;
			}
		}
		
		for (gruppo elem : gruppi) {
			if (elem.GetName().compareTo(groupName)==0) {
				trovato2=true;
				g = elem;
			}
		}
		
		if(trovato1 == false || trovato2 == false) {
			throw new NoSuchCodeException();
		}
		else {
			g.addUtente(p);
		}
	}

	
	public Collection<String> listOfPeopleInGroup(String groupName) {
		Collection<String> lista = new ArrayList <>();
		List<utente> listaUtenti = null;
		gruppo g;
		boolean trovato = false;
		
		for (gruppo elem : gruppi) {
			if (elem.GetName().compareTo(groupName)==0) {
				trovato=true;
				listaUtenti = elem.getLista();
			}
		}
		
		if (trovato == false) {
			return null;
		}
		
		else {
			for (utente ut : listaUtenti) {
				lista.add(ut.GetCod());
			}
			
			return lista;
			
		}
	}

	
	public String personWithLargestNumberOfFriends() {
		int max = 0;
		String stringa="";
		for (utente elem : utenti) {
			if (elem.GetNumberFriend() > max) {
				stringa = elem.GetCod();
				max = elem.GetNumberFriend();
			}
		}
		
		return stringa;
	}

	
	public String personWithMostFriendsOfFriends() {
		int acc = 0;
		int max = 0;
		String stringa = "";
		for (utente elem : utenti) {
			
			for (utente elem2 : elem.GetFriend()) {
				acc = acc + elem2.GetNumberFriend();
				
			}
			
			if (acc >= max) {
				stringa = elem.GetCod();
				max = acc;
			}
			
			acc = 0;
		}
		
		return stringa;
		
	}

	
	public String largestGroup() {
		int max = 0;
		String stringa = "";
		for (gruppo elem : gruppi) {
			if (elem.getNumberIscritti() > max) {
				stringa = elem.GetName();
				max = elem.getNumberIscritti();
			}
		}
		
		return stringa;
	}

	/**
	 * Find the code of the person that is member of
	 * the largest number of groups
	 * 
	 * @return the code of the person
	 */
	public String personInLargestNumberOfGroups() {
		int max = 0;
		int acc = 0;
		boolean trovato = false;
		String ricorda = "";
		for (utente u : utenti) {
			
			for (gruppo g : gruppi) {
				
				for (utente u2 : g.getLista()) {
					if (u.GetCod().compareTo(u2.GetCod())==0) {
						trovato = true;
					}
				}
				
				if (trovato == true) {
					acc++;
				}
				trovato = false;
			}
			
			if (acc > max) {
				ricorda = u.GetCod();
				max = acc;
			}
			
			acc = 0;
		}
		
		return ricorda;
	}
}