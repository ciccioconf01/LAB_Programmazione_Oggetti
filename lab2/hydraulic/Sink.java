package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {

	String nome;
	double maxF;
	
	public Sink(String name) {
		nome=name;
	}
	
	@Override
	public String getName() {
		return nome;
	}
	
	@Override
	public void recursive(double val,SimulationObserver observer) {
		observer.notifyFlow("Sink", nome, val,observer.NO_FLOW);
		return;
	}
	
	@Override
	public String Stamparecursive(String stringa) {
		stringa=stringa+"["+nome+"]Sink";
		return stringa;		
	}
	
	
	@Override 
	public Element Getnext() {
		return null;
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
			observer.notifyFlowError("Sink", nome, val, maxF);
		}
		
		observer.notifyFlow("Sink", nome, val,observer.NO_FLOW);
		
	}


}
