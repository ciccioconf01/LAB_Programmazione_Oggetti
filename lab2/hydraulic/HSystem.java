package hydraulic;

public class HSystem {
	Element[] elementi = new Element[100];
	int IdElement=0;
// R1
	
	public void addElement(Element elem){
		elementi[IdElement]=elem;
		IdElement++;
	}
	
	public Element[] getElements(){
		
		
		Element[] elements = new Element[IdElement];
		for (int i = 0;i<IdElement;i++) {
			
			elements[i]=elementi[i];
		}
		return elements;
		
		
	}

// R4
	
	
	public void simulate(SimulationObserver observer){
		
		for (int i = 0 ; i < IdElement;i++) {
			
			if(elementi[i] instanceof Source) {
				elementi[i].recursive(0,observer);
			}
		}
	}

// R6

	public String layout(){
		String stringa="";
		for (int i = 0 ; i < IdElement;i++) {
			
			if(elementi[i] instanceof Source) {
				stringa = elementi[i].Stamparecursive(stringa);
			}
		}
		return stringa;
	}

// R7
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public boolean deleteElement(String name) {
		boolean trovato=false;
		for (int i=0;i<IdElement;i++) {
			if (elementi[i] instanceof Source) {
				trovato=elementi[i].delete(name);
			}
		}
		
		boolean flag=false;
		if(trovato == true) {
			for (int i=0;i<IdElement;i++) {
				if (elementi[i].getName()==name || flag==true) {
					elementi[i]=elementi[i+1];
					flag=true;
				}
			}
			IdElement--;
			return true;
		}
		else {
			return false;
		}
	}

// R8
	/**
	 * starts the simulation of the system; if {@code enableMaxFlowCheck} is {@code true},
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserver observer, boolean enableMaxFlowCheck) {
		if (enableMaxFlowCheck == true) {
			
			for (int i = 0 ; i < IdElement;i++) {
				
				if(elementi[i] instanceof Source) {
					elementi[i].recursiveCheck(0,observer);
				}
			}
			
		}
	}
}
