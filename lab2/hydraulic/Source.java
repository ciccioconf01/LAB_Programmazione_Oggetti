package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * Lo status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element {

	String nome;
	double flusso;
	Element next;
	
	public Source(String name) {
		nome=name;
	}

	public void setFlow(double flow){
		flusso=flow;
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
			next.recursive(flusso,observer);
		}
		observer.notifyFlow("Source", nome, observer.NO_FLOW , flusso);
		return;
	}
	
	@Override
	public String Stamparecursive(String stringa) {
		stringa=stringa+"["+nome+"]Source -> ";
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
		return false;
	}
	
	@Override 
	public void setMaxFlow(double maxFlow) {
		
	}
	
	@Override
	public void recursiveCheck(double val,SimulationObserver observer) {
		
		next.recursiveCheck(flusso, observer);
		observer.notifyFlow("Source", nome, observer.NO_FLOW , flusso);
	}

}
