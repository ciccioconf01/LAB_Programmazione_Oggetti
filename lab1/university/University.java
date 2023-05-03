package university;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	
// R1
	String nomeUniversita;
	String nomeRettore;
	String cognomeRettore;
	Studente[] studenti = new Studente[100];
	int idStudente = 10000;
	corso[] corsi = new corso[50];
	int idCorso = 10;
	
	
	
	public University(String name){
		
		nomeUniversita = name;
		
	}
	

	public String getName(){
		
		return nomeUniversita;
	}
	
	
	public void setRector(String first, String last){
		
		nomeRettore=first;
		cognomeRettore=last;
	}
	
	public String getRector(){
		
		String stringa;
		stringa=nomeRettore + " " + cognomeRettore;
		return stringa;
	}
	
// R2
	
	public int enroll(String first, String last){
		studenti[idStudente-10000] = new Studente();
		studenti[idStudente-10000].setNeC(first,last);
		idStudente++;
		int ident=idStudente-1;
		
	 	logger.info("New student enrolled: "+ident+", "+ first + " "+ last);

		return ident;
	}
	
	
	public String student(int id){
		//TODO: to be implemented
		String stringa = studenti[id-10000].StampaStudente(id);
		return stringa;
	}
	
// R3
	
	public int activate(String title, String teacher){
		corsi[idCorso-10] = new corso();
		corsi[idCorso-10].setCorso(title, teacher);
	 	
		idCorso++;
		int idReturn=idCorso-1;
		logger.info("New course activated: "+idReturn+", "+ title +" "+ teacher);
		return idReturn;
	}
	
	
	public String course(int code){
		//TODO: to be implemented
		String stringa = corsi[code-10].StampaCorso(code);
		return stringa;
	}
	
// R4
	
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		
		corsi[courseCode-10].addStudent(studentID);
		studenti[studentID-10000].addCorsi(courseCode);
		
	 	logger.info("Student "+studentID+" signed up for course "+courseCode);

	}
	
	
	public String listAttendees(int courseCode){
		String stringa="";
		stringa=corsi[courseCode-10].StampaIscritti(studenti);
		return stringa;
	}

	
	public String studyPlan(int studentID){
		String stringa="";
		stringa=studenti[studentID-10000].StampaCourse(corsi);
		return stringa;
	}

// R5
	
	public void exam(int studentId, int courseID, int grade) {
		studenti[studentId-10000].addEsame(studentId, courseID, grade);
		corsi[courseID-10].addEsameRegistrato(studentId, courseID, grade);
		logger.info("Student "+studentId+" took an exam in course "+courseID+" with grade "+grade);

	}

	public String studentAvg(int studentId) {
		String stringa;
		stringa=studenti[studentId-10000].stampaMedia(studentId);
		return stringa;
	}
	
	
	public String courseAvg(int courseId) {
		String stringa;
		stringa=corsi[courseId-10].stampaMediaCorso(courseId);
		return stringa;
	}
	

// R6
	
	public String topThreeStudents() {
		
		String nome,cognome;
		float punteggio;
		punteggio_studente comodo = new punteggio_studente();
		punteggio_studente[] punteggi = new punteggio_studente[idStudente-10000];
		
		for (int i=0;i<idStudente-10000;i++) {
			
			punteggio=studenti[i].getPunteggio();
			nome=studenti[i].getNome();
			cognome=studenti[i].getCognome();
			
			punteggi[i] = new punteggio_studente();
			
			punteggi[i].setPunteggio(nome, cognome, punteggio);
			
		}
		
		for (int i=0;i<idStudente-10000;i++) {
			for (int j=0; j<idStudente-10000;j++) {
				
				if (punteggi[i].getPunteggio() > punteggi[j].getPunteggio()) {
					
					comodo=punteggi[i];
					punteggi[i]=punteggi[j];
					punteggi[j]=comodo;
					
				}
			}
		}
		
		String stringa="",stringaF="";
		int conta=0;
		for(int i=0;i<idStudente-10000;i++) {
			conta=conta+studenti[i].contaEsami();			
		}
		
		if (conta>2) {
			for (int i=0;i<3;i++) {
				//if (i!=0) {
					//stringaF=stringaF+"\n";
				//}
				stringa=punteggi[i].getNomeStud()+" " + punteggi[i].getCognomeStud()+" : "+punteggi[i].getPunteggio()+"\n";
				stringaF=stringaF+stringa;
				}
			

			
			return stringaF;
		}
			
		
		for (int i=0;i<conta;i++) {
			//if (i!=0) {
				//stringaF=stringaF+"\n";
			//}
			stringa=punteggi[i].getNomeStud()+" " + punteggi[i].getCognomeStud()+" : "+punteggi[i].getPunteggio()+"\n";
			stringaF=stringaF+stringa;
			}
		//String[] rank = stringaF.split("\n");
		//System.out.println(rank.length);
		
		return stringaF;
		
	}

// R7
		   
	private final static Logger logger = Logger.getLogger("University");
	
	
}

