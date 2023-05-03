package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {

	String nome;
	Element next1=null;
	Element next2=null;
	double maxF;
	
	public Split(String name) {
		nome=name;
	}
	
	@Override
	public String getName() {
		return nome;
	}
	
	@Override
	public void connect(Element elem, int index) {
		if (index==0) {
			next1=elem;
		}
		else {
			next2=elem;
		}
	}
	
	@Override
	public Element[] getOutputs(){
		Element[] elements = new Element[2];
		
		elements[0] = next1;
		elements[1] = next2;
		
		return elements;
	}
	
	@Override
	public void recursive(double val,SimulationObserver observer) {
		if (next1!=null) {
			next1.recursive(val/2,observer);
		}
		
		if (next2!=null) {
			next2.recursive(val/2,observer);
		}
		observer.notifyFlow("Split", nome, val, val/2,val/2);
		
		return;
	}
	
	@Override
	public String Stamparecursive(String stringa) {
		int ricorda;
		stringa=stringa+"["+nome+"]Split +";
		ricorda =  stringa.length()-1;
		
		stringa=stringa+"-> ";
		
		if (next1!=null) {
		
			stringa=next1.Stamparecursive(stringa);
		}
		else {
			stringa = stringa + "*";
		}
			
		stringa=stringa+"\n";
		for (int i=0;i<ricorda;i++) {
			stringa=stringa+" ";
		}
		stringa=stringa+"|";
		
		stringa=stringa+"\n";
		for (int i=0;i<ricorda;i++) {
			stringa=stringa+" ";
		}
		stringa=stringa+"+-> ";
			
		if (next2!=null){
			stringa=next2.Stamparecursive(stringa);
		}
		else {
			stringa=stringa+"*";
		}
		
		return stringa;		
	}
	
	@Override
	public boolean delete(String name) {
		boolean bool1=false;
		boolean bool2=false;
		if(next1!=null) {
			if (next1.getName() == name && next1.canIdelete()==true) {
				
				next1 = next1.Getnext();
				return true;
			}
			else {
				bool1=next1.delete(name);
			}
		}
		
		if(next2!=null) {
			if (next2.getName() == name && next2.canIdelete()==true) {
				
				next2 = next2.Getnext();
				return true;
			}
			else {
				bool2=next2.delete(name);
				
			}
		}
		if (bool1!=true && bool2!=true) {
			return false;
		}
		else {
			return true;
		}
	
}
	
	@Override 
	public Element Getnext() {
		if (next1!=null) {
			return next1;
		}
		else {
			return next2;
		}
		
	}
	
	@Override 
	public boolean canIdelete() {
		if (next1!=null && next2!=null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override 
	public void setMaxFlow(double maxFlow) {
		maxF=maxFlow;
	}
	
	@Override
	public void recursiveCheck(double val,SimulationObserver observer) {
		
		if (val > maxF) {
			observer.notifyFlowError("Split", nome, val, maxF);
		}
		next1.recursiveCheck(val/2,observer);
		next2.recursiveCheck(val/2,observer);
		observer.notifyFlow("Split", nome, val, val/2,val/2);
		
	}
	
	

	
}
