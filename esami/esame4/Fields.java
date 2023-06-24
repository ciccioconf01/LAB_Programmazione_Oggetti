package it.polito.oop.futsal;

import java.util.List;
import java.util.Map;

import java.util.*;

public class Fields {
	
	List<Campo> campi = new ArrayList <>();
	int numerazioneCampi = 1;
	String oraApertura;
	String oraChiusura;
	List<Socio> soci = new ArrayList <>();
	int numerazioneSoci = 1;
	List <Prenotazione> prenotazioni = new ArrayList<>();
	    
    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }
        public boolean GetCoperto() {
        	return indoor;
        }
        
        public boolean GetRiscaldamento() {
        	return heating;
        }
        
        public boolean GetAriaC() {
        	return ac;
        }
    }
    
	
    public void defineFields(Features... features) throws FutsalException {
    	
    	for(Features f : features) {
    		if (f.GetCoperto()==false && (f.GetAriaC()==true || f.GetRiscaldamento()==true)) {
    			throw new FutsalException();
    		}
    		
    		else {
    			Campo c = new Campo(numerazioneCampi,f);
    			campi.add(c);
    			numerazioneCampi++;
    		}
    	}
    	
    }
    
    public long countFields() {
        return numerazioneCampi-1;
    }

    public long countIndoor() {
    	long conta=0;
        for(Campo c : campi) {
        	if(c.GetFeatures().GetCoperto()==true) {
        		conta++;
        	}
        }
        return conta;
        
    }
    
    public String getOpeningTime() {
        return oraApertura;
    }
    
    public void setOpeningTime(String time) {
    	oraApertura = time;
    }
    
    public String getClosingTime() {
        return oraChiusura;
    }
    
    public void setClosingTime(String time) {
    	oraChiusura = time;
    }

    public int newAssociate(String first, String last, String mobile) {
        Socio s = new Socio(numerazioneSoci,first,last,mobile);
        soci.add(s);
        numerazioneSoci++;
        return numerazioneSoci-1;
    }
    
    public String getFirst(int associate) throws FutsalException {
        boolean trovato = false;
        String nome="";
        
        for (Socio s : soci) {
        	if(s.GetCod()==associate) {
        		trovato = true;
        		nome = s.GetNome();
        	}
        }
        
        if (trovato == true) {
        	return nome;
        }
        else {
        	throw new FutsalException();
        }
    }
    
    public String getLast(int associate) throws FutsalException {
    	boolean trovato = false;
        String cognome="";
        
        for (Socio s : soci) {
        	if(s.GetCod()==associate) {
        		trovato = true;
        		cognome = s.GetCognome();
        	}
        }
        
        if (trovato == true) {
        	return cognome;
        }
        else {
        	throw new FutsalException();
        }
    }
    
    public String getPhone(int associate) throws FutsalException {
    	boolean trovato = false;
        String tel="";
        
        for (Socio s : soci) {
        	if(s.GetCod()==associate) {
        		trovato = true;
        		tel = s.GetTel();
        	}
        }
        
        if (trovato == true) {
        	return tel;
        }
        else {
        	throw new FutsalException();
        }
    }
    
    public int countAssociates() {
        return numerazioneSoci-1;
    }
    
    
    
    public void bookField(int field, int associate, String time) throws FutsalException {
    	
    	//controlli
    	
    	boolean trovato = false;
    	
    	for (Campo c : campi) {
    		if (field==c.GetNumero()) {
    			trovato = true;
    		}
    	}
    	
    	if (trovato == false) {
    		throw new FutsalException();
    	}
    	
    	
    	trovato = false;
    	
    	for (Socio s : soci) {
    		if (s.GetCod()==associate) {
    			trovato = true;
    		}
    	}
    	
    	if (trovato == false) {
    		throw new FutsalException();
    	}
    	
    	String[] vet = time.split(":");
    	String[] vet2 = oraApertura.split(":");
    	if(vet[1].compareTo(vet2[1])!=0) {
    		throw new FutsalException();
    	}
    	
    	//aggiunta
    	
    	Prenotazione p = new Prenotazione(field, associate, time);
    	prenotazioni.add(p);
    	
    	
    }

    public boolean isBooked(int field, String time) {
    	boolean trovato = false;
        for(Prenotazione p : prenotazioni) {
        	if (p.GetNumCampo()==field && p.GetOrario().compareTo(time)==0) {
        		trovato = true;
        	}
        }
        
        return trovato;
    }
    

    public int getOccupation(int field) {
    	int conta=0;
        for (Prenotazione p : prenotazioni) {
        	if (p.GetNumCampo()==field) {
        		conta++;
        	}
        }
        
        return conta;
    }
    
    public List<FieldOption> findOptions(String time, Features required){
    	List <Campo> camp = new ArrayList<>();
        boolean noField = false;
    	for (Campo c : campi) {
    		if (required.GetCoperto()==true) {
    			if(c.GetFeatures().GetCoperto()!=true) {
    				noField = true;
    			}
    		}
    		
    		if (required.GetAriaC()==true) {
    			if(c.GetFeatures().GetAriaC()!=true) {
    				noField = true;
    			}
    		}
    		
    		if (required.GetRiscaldamento()==true) {
    			if(c.GetFeatures().GetRiscaldamento()!=true) {
    				noField = true;
    			}
    		}
    		
    		if (noField == false) {
    			camp.add(c);
    		}
    		
    		noField = false;
    	}
    	
    	List <Campo> camp2 = new ArrayList<>();
    	boolean occupato = false;
    	for (Campo c : camp) {
    		
    		for ( Prenotazione p : prenotazioni) {
    			
    			if (c.GetNumero()==p.GetNumCampo() && p.GetOrario()==time) {
    				occupato = true;
    			}
    		}
    		
    		if (occupato == false) {
    			camp2.add(c);
    		}
    		occupato = false;
    	}
    	
    	Map <Campo,Integer> mappa = new HashMap<>();
    	
    	for (Campo c : camp2) {
    		mappa.put(c, this.getOccupation(c.GetNumero()));
    	}
    	
    	SortedMap <Campo,Integer> sortedMap = new TreeMap<>(
    			(key1,key2) -> {
    				int occ1 = mappa.get(key1);
    				int occ2 = mappa.get(key2);
    				if(Integer.compare(occ1,occ2)==0) {
    					return Integer.compare(key1.GetNumero(), key2.GetNumero());
    				}
    				return Integer.compare(occ2, occ1);
    			});
    	
    	sortedMap.putAll(mappa);
    	
    	List<FieldOption> l = new ArrayList<>();
    	FieldOptionImpl prova ;
    	
    	for (Campo c : sortedMap.keySet()) {
    		
    		prova = new FieldOptionImpl(c.GetNumero(),sortedMap.get(c));
    		l.add(prova);
    	}
    	
    	return l;
    	
    	
    }
    
    public long countServedAssociates() {
    	boolean trovato = false;
    	long conta = 0;
        
    	for (Socio s : soci) {
        	for (Prenotazione p : prenotazioni) {
        		if (s.GetCod()==p.GetCodPers()) {
        			trovato = true;
        		}
        	}
        	
        	if (trovato == true) {
        		conta++;
        	}
        	trovato = false;
        }
    	
    	return conta;
    }
    
    public Map<Integer,Long> fieldTurnover() {
    	Map<Integer,Long> mappa = new TreeMap<>();
    	long conta = 0;
    	for ( Campo c : campi) {
    		for (Prenotazione p : prenotazioni) {
    			if (c.GetNumero()==p.GetNumCampo()) {
    				conta++;
    			}
    		}
    		
    		mappa.put(c.GetNumero(), conta);
    		conta = 0;
    	}
    	
    	return mappa;
    }
    
    public double occupation() {
        double totPrenotazioni = prenotazioni.size();
        String[] vet1 = oraChiusura.split(":");
        String[] vet2 = oraApertura.split(":");
        
        String v1 = vet1[0];
        String v2 = vet2[0];
        
        int a1 = Integer.parseInt(v1);
        int a2 = Integer.parseInt(v2);
        double blocchi =   (a1 - a2)*campi.size();
        
        return totPrenotazioni/blocchi;
    }
    
 }
