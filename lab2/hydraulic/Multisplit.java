package hydraulic;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {

	String nome;
	int Num;
	Element[] next;
	double[] proporzioni;
	double maxF;
	
	public Multisplit(String name, int numOutput) {
		super(name);
		nome = name;
		Num = numOutput;
		next = new Element[Num];
	}
	
	@Override
	public String getName() {
		return nome;
	}
	
	@Override
	public void connect(Element elem, int index) {
		next[index]=elem;
	}
	
	@Override
	public Element[] getOutputs(){
		return next;
	}
	
	
	public void setProportions(double... proportions) {
		proporzioni = new double[Num];
		int conta=0;
		for (double elem : proportions) {
			proporzioni[conta] = elem;
			conta++;
		}
		
	}
	
	@Override
	public void recursive(double val,SimulationObserver observer) {
		
		for (int i = 0; i< Num;i++) {
			
			if (next[i]!=null){
				
				next[i].recursive(val*proporzioni[i], observer);
			
				
			}
			
		}
		
		double[] vet = new double[Num];
		for (int i=0;i<Num;i++) {
			vet[i]=val*proporzioni[i];
		}
		
		observer.notifyFlow("Multisplit", nome, val, vet);
		
		
		return;
	}
	
	@Override
	public String Stamparecursive(String stringa) {
		stringa=stringa+"["+nome+"]Multisplit +";
		int ricorda;
		ricorda = stringa.length()-1;
		stringa=stringa+"-> ";
		for (int i = 0; i< Num;i++) {
			
			if (next[i]!=null) {
				stringa = next[i].Stamparecursive(stringa);	
			}
			else {
				stringa=stringa+"*";
			}
			stringa=stringa+"\n";
			for (int j=0;j<ricorda;j++) {
				stringa=stringa+" ";
			}
			if (i<Num-1) {
				stringa=stringa+"|";
				
				stringa=stringa+"\n";
				for (int j=0;j<ricorda;j++) {
					stringa=stringa+" ";
				}
				
				stringa=stringa+"+-> ";
			}
		
			
		}
		
		return stringa;		
	}
	
	@Override
	public boolean delete(String name) {
		boolean[] bool1 = new boolean[Num];
		
		for (int i=0;i<Num;i++) {
			
			if (next[i]!=null) {
				if (next[i].getName() == name && next[i].canIdelete()==true) {
					
					next[i] = next[i].Getnext();
					return true;
				}
				else {
					bool1[i]=next[i].delete(name);
				}
			}
		}
		
		for (int i=0 ; i< Num; i++) {
			
			if (bool1[i]==true) {
				return true;
			}
		}
		return false;
	
}
	
	@Override 
	public Element Getnext() {
		
		for (int i=0;i<Num;i++) {
			if (next[i]!=null) {
				return next[i];
			}
		}
		return null;
		
	}
	
	@Override 
	public boolean canIdelete() {
		int conta=0;
		for (int i=0;i<Num;i++) {
			if (next[i]!=null) {
				conta++;
			}
		}
		
		if (conta<=1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override 
	public void setMaxFlow(double maxFlow) {
		maxF=maxFlow;
	}
	
	@Override
	public void recursiveCheck(double val,SimulationObserver observer) {
		
		if (val > maxF) {
			observer.notifyFlowError("Multisplit", nome, val, maxF);
		}
		
		for (int i = 0; i< Num;i++) {
			
			if (next[i]!=null){
				
				next[i].recursiveCheck(val*proporzioni[i], observer);	
				
			}
			
			
		}
		
		double[] vet = new double[Num];
		for (int i=0;i<Num;i++) {
			vet[i]=val*proporzioni[i];
		}
		
		observer.notifyFlow("Multisplit", nome, val, vet);
		
		
	}
	
}
