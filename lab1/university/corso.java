package university;

public class corso {
	String nomeCorso;
	String docente;
	int[] id = new int[100];
	int NumStudenti=0;
	esame[] esamiRegistrati = new esame[20];
	int idEsameRegistrato=0;
	
	
	public void setCorso(String title,String teacher) {
		nomeCorso = title;
		docente = teacher;
	}
	
	public String getNomeDoc() {
		return docente;
	}
	
	public String getNomeCorso() {
		return nomeCorso;
	}
	
	public String StampaCorso(int code) {
		String stringa = code + "," + nomeCorso + "," + docente;
		return stringa;
	}
	
	public void addStudent(int ID) {
		id[NumStudenti]=ID;
		NumStudenti++;
	}
	
	public String StampaIscritti(Studente[] studenti) {
		String stringa,stringaF="";
		for (int i=0;i<NumStudenti;i++) {
			stringa = studenti[id[i]-10000].StampaStudente(id[i]) + "\n";
			stringaF = stringaF + stringa;
		}
		return stringaF;
	}

	public void addEsameRegistrato(int studentId, int courseID, int grade) {
		esamiRegistrati[idEsameRegistrato] = new esame();
		esamiRegistrati[idEsameRegistrato].setExam(studentId, courseID, grade);
		idEsameRegistrato++;
	}
	
	public String stampaMediaCorso(int courseId) {
		String stringa="";
		float somma=0;
		float media=0;
		if (idEsameRegistrato==0) {
			stringa="No student has taken the exam in " + nomeCorso;
		}
		else {
			for (int i=0;i<idEsameRegistrato;i++){
				somma = somma + esamiRegistrati[i].getVoto();
			}
			
			media = somma/idEsameRegistrato;
			stringa="The average for the course " + nomeCorso + " is : " + media;
			
		}
		
		return stringa;
	}

}
