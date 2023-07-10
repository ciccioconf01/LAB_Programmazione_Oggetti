package it.polito.med;
import java.util.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MedManager {

	Set <String> specialita = new TreeSet<>();
	List <Medico> medici = new ArrayList<>();
	List<Turno> turni = new ArrayList<>();
	List<Appuntamento> appuntamenti = new ArrayList <>();
	int idApp=1;
	String data = "";
	
	public void addSpecialities(String... specialities) {
		for(String elem : specialities) {
			specialita.add(elem);
		}
	}

	/**
	 * retrieves the list of specialities offered in the med centre
	 * 
	 * @return list of specialities
	 */
	public Collection<String> getSpecialities() {
		 Collection<String> lista = new ArrayList<>();
		for(String elem : specialita) {
			lista.add(elem);
		}
		
		return lista;
	}
	
	
	/**
	 * adds a new doctor with the list of their specialities
	 * 
	 * @param id		unique id of doctor
	 * @param name		name of doctor
	 * @param surname	surname of doctor
	 * @param speciality speciality of the doctor
	 * @throws MedException in case of duplicate id or non-existing speciality
	 */
	public void addDoctor(String id, String name, String surname, String speciality) throws MedException {
		//controlli
		boolean trovato = false;
		for(String spec : specialita) {
			if (spec.compareTo(speciality)==0) {
				trovato = true;
			}
		}
		
		if (trovato == false) {
			throw new MedException();
		}
		
		for(Medico m : medici) {
			if(m.GetId().compareTo(id)==0) {
				throw new MedException();
			}
		}
		
		
		//aggiunta
		
		Medico m = new Medico (id,name,surname,speciality);
		medici.add(m);
	}

	/**
	 * retrieves the list of doctors with the given speciality
	 * 
	 * @param speciality required speciality
	 * @return the list of doctor ids
	 */
	public Collection<String> getSpecialists(String speciality) {
		 Collection<String> lista = new ArrayList<>();
		for(Medico m : medici) {
			if (m.GetSpecialita().compareTo(speciality)==0) {
				lista.add(m.GetId());
			}
		}
		
		return lista;
	}

	/**
	 * retrieves the name of the doctor with the given code
	 * 
	 * @param code code id of the doctor 
	 * @return the name
	 */
	public String getDocName(String code) {
		String stringa = "";
		for (Medico m : medici) {
			if(m.GetId().compareTo(code)==0) {
				stringa = m.GetNome();
			}
		}
		
		return stringa;
	}

	/**
	 * retrieves the surname of the doctor with the given code
	 * 
	 * @param code code id of the doctor 
	 * @return the surname
	 */
	public String getDocSurname(String code) {
		String stringa = "";
		for (Medico m : medici) {
			if(m.GetId().compareTo(code)==0) {
				stringa = m.GetCognome();
			}
		}
		
		return stringa;
	}

	/**
	 * Define a schedule for a doctor on a given day.
	 * Slots are created between start and end hours with a 
	 * duration expressed in minutes.
	 * 
	 * @param code	doctor id code
	 * @param date	date of schedule
	 * @param start	start time
	 * @param end	end time
	 * @param duration duration in minutes
	 * @return the number of slots defined
	 */
	public int addDailySchedule(String code, String date, String start, String end, int duration) {
		Turno t = new Turno(code,date,start,end,duration);
		turni.add(t);
		
		String oraIn = start.split(":")[0];
		String oraFin = end.split(":")[0];
		String minIn = start.split(":")[1]; 
		String minFin = end.split(":")[1];
		
		int minutiTot = ( Integer.parseInt(oraFin) - Integer.parseInt(oraIn) ) * 60 + (Integer.parseInt(minFin) - Integer.parseInt(minIn));
		
		return minutiTot/duration;
		
	}

	/**
	 * retrieves the available slots available on a given date for a speciality.
	 * The returned map contains an entry for each doctor that has slots scheduled on the date.
	 * The map contains a list of slots described as strings with the format "hh:mm-hh:mm",
	 * e.g. "14:00-14:30" describes a slot starting at 14:00 and lasting 30 minutes.
	 * 
	 * @param date			date to look for
	 * @param speciality	required speciality
	 * @return a map doc-id -> list of slots in the schedule
	 */
	public Map<String, List<String>> findSlots(String date, String speciality) {
		Map<String, List<String>> mappa = new TreeMap<>();
		String s = "";
		List<String> lista = new ArrayList<>();
		List<String> lista2 = new ArrayList<>();
		boolean flag = false;
		
		int val = 0;
		for (Turno t : turni){
			if(t.GetData().compareTo(date)==0) {
				for (Medico m : medici) {
					if(m.GetId()==t.GetId() && m.GetSpecialita()==speciality) {
						String oraAttuale =t.GetOraInizio();
						String oraPassata = t.GetOraInizio();
						
						if (mappa.containsKey(m.GetId()) == false) {
							
							while (flag==false) {
								if (Integer.parseInt(oraAttuale.split(":")[1])+ t.GetDurata() < 60) {
									val = Integer.parseInt(oraAttuale.split(":")[1])+ t.GetDurata();
									oraPassata = oraAttuale;
									oraAttuale = oraAttuale.split(":")[0]+":"+ String.valueOf(val);
									String ins = oraPassata+"-"+oraAttuale;
									lista.add(ins);
								}
								else {
									val = Integer.parseInt(oraAttuale.split(":")[1])+ t.GetDurata();
									val = val % 60;
									String v="";
									if (String.valueOf(val).length()==1) {
										v = val + "0";
									}
									else {
										v =  String.valueOf(val);
									}
									int val2 = Integer.parseInt(oraAttuale.split(":")[0]) + 1;
									oraPassata = oraAttuale;
									oraAttuale = String.valueOf(val2)+":"+v;
									String ins = oraPassata+"-"+oraAttuale;
									lista.add(ins);
								}
								
								if (oraAttuale.compareTo(t.GetOraFine())==0) {
									flag= true;
								}
								
							}
							
							mappa.put(m.GetId(),lista );
						}
						
						
				}
			}
		}
		
	}

		return mappa;
	}

	/**
	 * Define an appointment for a patient in an existing slot of a doctor's schedule
	 * 
	 * @param ssn		ssn of the patient
	 * @param name		name of the patient
	 * @param surname	surname of the patient
	 * @param code		code id of the doctor
	 * @param date		date of the appointment
	 * @param slot		slot to be booked
	 * @return a unique id for the appointment
	 * @throws MedException	in case of invalid code, date or slot
	 */
	public String setAppointment(String ssn, String name, String surname, String code, String date, String slot) throws MedException {
		//controlli
		boolean trovato = false;
		for (Medico m : medici) {
			if (m.GetId().compareTo(code)==0) {
				trovato = true;
			}
		}
		if (trovato == false) {
			throw new  MedException ();
			
		}
		
		
		trovato = false;
		
		for (Turno t : turni) {
			if (t.GetId().compareTo(code)==0 && t.GetData().compareTo(date)==0 ) {
				trovato = true;
			}
		}
		
		if (trovato == false) {
			throw new  MedException ();
			
		}
		
		if (slot.contains("-")==false || slot.contains(":")==false) {
			throw new  MedException ();
		}
		int minutiTot = ( Integer.parseInt(slot.split("-")[1].split(":")[0]) - Integer.parseInt(slot.split("-")[0].split(":")[0]) ) * 60 + (Integer.parseInt(slot.split("-")[1].split(":")[1]) - Integer.parseInt(slot.split("-")[0].split(":")[1]));
		trovato=false;
		for(Turno t : turni) {
			if(code.compareTo(t.GetId())==0 && date.compareTo(t.GetData())==0 && t.GetOraInizio().compareTo(slot.split("-")[0])<=0 && t.GetOraFine().compareTo(slot.split("-")[1])>=0 && t.GetDurata()==minutiTot) {trovato= true;}}
		if(trovato==false) {throw new  MedException ();}
		
		Appuntamento app = new Appuntamento ( ssn,  name,  surname,  code,  date,  slot);
		app.SetId(idApp);
		appuntamenti.add(app);
		String ret = String.valueOf(idApp);
		idApp++;
		return ret;
	}

	/**
	 * retrieves the doctor for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return doctor code id
	 */
	public String getAppointmentDoctor(String idAppointment) {
		String s = "";
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(idAppointment)==0) {
				s= a.GetCodDott();
			}
		}
		
		return s;
	}

	/**
	 * retrieves the patient for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return doctor patient ssn
	 */
	public String getAppointmentPatient(String idAppointment) {
		String s = "";
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(idAppointment)==0) {
				s= a.GetCodFiscale();
			}
		}
		
		return s;
	}

	/**
	 * retrieves the time for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return time of appointment
	 */
	public String getAppointmentTime(String idAppointment) {
		String s = "";
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(idAppointment)==0) {
				s= a.GetSlot().split("-")[0];
			}
		}
		
		return s;
	}

	/**
	 * retrieves the date for an appointment
	 * 
	 * @param idAppointment id of appointment
	 * @return date
	 */
	public String getAppointmentDate(String idAppointment) {
		String s = "";
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(idAppointment)==0) {
				s= a.GetData();
			}
		}
		
		return s;
	}

	/**
	 * retrieves the list of a doctor appointments for a given day.
	 * Appointments are reported as string with the format
	 * "hh:mm=SSN"
	 * 
	 * @param code doctor id
	 * @param date date required
	 * @return list of appointments
	 */
	public Collection<String> listAppointments(String code, String date) {
		Collection<String> lista = new ArrayList <>();
		String s = "";
		for (Appuntamento app : appuntamenti) {
			if (app.GetCodDott().compareTo(code)==0 && app.GetData().compareTo(date)==0) {
				s = app.GetSlot().split("-")[0] + "="+app.GetCodFiscale();
				lista.add(s);
			}
		}
		return lista;
	}

	/**
	 * Define the current date for the medical centre
	 * The date will be used to accept patients arriving at the centre.
	 * 
	 * @param date	current date
	 * @return the number of total appointments for the day
	 */
	public int setCurrentDate(String date) {
		data = date;
		int conta = 0;
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetData().compareTo(date)==0) {
				conta++;
			}
		}
		
		return conta;
	}

	/**
	 * mark the patient as accepted by the med centre reception
	 * 
	 * @param ssn SSN of the patient
	 */
	public void accept(String ssn) {
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetCodFiscale().compareTo(ssn)==0) {
				a.SetAssegnato();
			}
		}

	}

	/**
	 * returns the next appointment of a patient that has been accepted.
	 * Returns the id of the earliest appointment whose patient has been
	 * accepted and the appointment not completed yet.
	 * Returns null if no such appointment is available.
	 * 
	 * @param code	code id of the doctor
	 * @return appointment id
	 */
	public String nextAppointment(String code) {
		String s = "";
		boolean trovato = false;
		for (Appuntamento a : appuntamenti) {
			if (a.GetCodDott().compareTo(code) == 0 && a.GetAssegnato()==true && a.GetCompletato()==false && a.GetData().compareTo(data)==0 && trovato!=true) {
				s = a.GetId();
				trovato = true;
			}
		}
		if (trovato == false) {
			return null;
		}
		return s;
	}

	/**
	 * mark an appointment as complete.
	 * The appointment must be with the doctor with the given code
	 * the patient must have been accepted
	 * 
	 * @param code		doctor code id
	 * @param appId		appointment id
	 * @throws MedException in case code or appointment code not valid,
	 * 						or appointment with another doctor
	 * 						or patient not accepted
	 * 						or appointment not for the current day
	 */
	public void completeAppointment(String code, String appId)  throws MedException {
		
		boolean trovato = false;
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetCodDott().compareTo(code)==0) {
				trovato = true;
			}
		}
		
		if (trovato ==false) {
			throw new MedException();
		}
		
		trovato = false;
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(appId)==0) {
				trovato = true;
			}
		}
		
		if (trovato ==false) {
			throw new MedException();
		}
		
		
		
		for (Appuntamento a : appuntamenti) {
			if (appId.compareTo(a.GetId())==0 && a.GetData()!=data) {
				throw new MedException();
			}
		}
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(appId)==0 && a.GetCodDott().compareTo(code)!=0) {
				throw new MedException();
			}
		}
		
		for (Appuntamento a : appuntamenti) {
			if (appId.compareTo(a.GetId())==0 && a.GetAssegnato()!=true) {
				throw new MedException();
			}
		}
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetId().compareTo(appId)==0) {
				a.SetCompletato();
			}
		}

	}

	/**
	 * computes the show rate for the appointments of a doctor on a given date.
	 * The rate is the ratio of accepted patients over the number of appointments
	 *  
	 * @param code		doctor id
	 * @param date		reference date
	 * @return	no show rate
	 */
	public double showRate(String code, String date) {
		double contaAss=0;
		double conta=0;
		
		for (Appuntamento a : appuntamenti) {
			if (a.GetCodDott().compareTo(code)==0 && a.GetData().compareTo(date)==0) {
				if (a.GetAssegnato()==true) {
					contaAss++;
					conta++;
				}
				else {
					conta++;
				}
			}
			
		}
		return contaAss/conta;
	}

	/**
	 * computes the schedule completeness for all doctors of the med centre.
	 * The completeness for a doctor is the ratio of the number of appointments
	 * over the number of slots in the schedule.
	 * The result is a map that associates to each doctor id the relative completeness
	 * 
	 * @return the map id : completeness
	 */
	public Map<String, Double> scheduleCompleteness() {
		Map<String, Double> mappa = new TreeMap <>();
		double conta =0;
		double contaSlot = 0;
		for (Medico m : medici) {
			conta = 0;
			for (Appuntamento app : appuntamenti) {
				if (app.GetCodDott().compareTo(m.GetId())==0) {
					conta++;
				}
			}
			
			
			for (Turno t : turni) {
				
				if (t.GetId().compareTo(m.GetId())==0) {
					String oraIn = t.GetOraInizio().split(":")[0];
					String oraFin = t.GetOraFine().split(":")[0];
					String minIn = t.GetOraInizio().split(":")[1]; 
					String minFin = t.GetOraFine().split(":")[1];
					
					int minutiTot = ( Integer.parseInt(oraFin) - Integer.parseInt(oraIn) ) * 60 + (Integer.parseInt(minFin) - Integer.parseInt(minIn));
					contaSlot = contaSlot +  minutiTot/t.GetDurata();
				}
			}
			
			mappa.put(m.GetId(), conta/contaSlot);
		}
		
		return mappa;
	}


	
}
