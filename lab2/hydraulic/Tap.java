package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {

	String nome;
	boolean opened;
	Element next;
	double maxF;
	
	public Tap(String name) {
		nome = name;
	}

	public void setOpen(boolean open){
		opened = open;
	}
	
	@Override
	public String getName() {
		return nome;
	}
	
	@Override
	public void connect(Element elem) {
		next=elem;
	}
	
	@Override
	public Element getOutput(){
		return next;
	}
	
	@Override
	public void recursive(double val,SimulationObserver observer) {
		if (next!=null) {
			if (opened == true) {
				next.recursive(val,observer);
				observer.notifyFlow("Tap", nome, val, val);
			}
			else {
				next.recursive(0,observer);
				observer.notifyFlow("Tap", nome, val, 0);
			}
		}
		return;


	}
	
	@Override
	public String Stamparecursive(String stringa) {
		
		stringa=stringa+"["+nome+"]Tap";
			
		stringa=stringa+" -> ";
		if (next!=null) {
		stringa = next.Stamparecursive(stringa);
		}
		
		else {
			stringa = stringa + "*";
		}

		return stringa;		
	}
	
	@Override
	public boolean delete(String name) {
		boolean bool;
		if (next!=null) {
			if (next.getName() == name && next.canIdelete()==true) {
				
				next = next.Getnext();
				return true;
			}
			else {
				bool=next.delete(name);
				return bool;
			}
		}
		else {
			return false;
		}
	}
	
	@Override 
	public Element Getnext() {
		return next;
	}
	
	@Override 
	public boolean canIdelete() {
		return true;
	}
	
	@Override 
	public void setMaxFlow(double maxFlow) {
		maxF=maxFlow;
	}
	
	@Override
	public void recursiveCheck(double val,SimulationObserver observer) {
		
		if (val > maxF) {
			observer.notifyFlowError("Tap", nome, val, maxF);
		}
		
		if (opened == true) {
			next.recursiveCheck(val,observer);
			observer.notifyFlow("Tap", nome, val, val);
		}
		else {
			next.recursiveCheck(0,observer);
			observer.notifyFlow("Tap", nome, val, observer.NO_FLOW);
		}
	}
	
	
}
