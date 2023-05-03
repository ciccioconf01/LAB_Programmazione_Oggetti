package university;

public class esame {
	int idStudente;
	int codCorso;
	int voto;
	
	
	
	public void setExam(int id,int cod,int grade) {
		idStudente = id;
		codCorso = cod;
		voto = grade;
		
	}
	
	public int getVoto() {
		
		return voto;
	}
}
