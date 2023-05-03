package university;

public class Studente {
	
	String nome;
	String cognome;
	int[] corsi = new int[25];
	int numCorsi=0;
	esame[] esami = new esame[20];
	int idEsame=0;
	
	public void setNeC(String first,String last) {
		nome = first;
		cognome = last;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String StampaStudente(int id) {
		String stringa = id + " " + nome + " " + cognome;
		return stringa;
	}
	
	public String StampaCourse(corso[] listaCorsi) {
		String stringa,stringaF="";
		for (int i=0;i<numCorsi;i++) {
			
			stringa = listaCorsi[corsi[i]-10].StampaCorso(corsi[i])+"\n";
			stringaF=stringaF+stringa;
		}
		return stringaF;
	}
	
	
	
	public void addCorsi(int code) {
		corsi[numCorsi]=code;
		numCorsi++;
	}
	
	
	public void addEsame(int studentId, int courseID, int grade) {
		esami[idEsame]=new esame();
		esami[idEsame].setExam(studentId, courseID, grade);
		idEsame++;
	}
	
	public String stampaMedia(int studentId) {
		String stringa="";
		float somma=0;
		float media=0;
		if (idEsame==0) {
			stringa="Student "+studentId+" hasn't taken any exams";
		}
		else {
			for (int i=0;i<idEsame;i++){
				somma = somma + esami[i].getVoto();
			}
			media=somma/idEsame;
			stringa="Student " + studentId + " : " + media;
			
		}
		
		return stringa;
	}
	
	public float getPunteggio() {
		float somma=0;
		float media=0;
		float punteggio=0;
		if (idEsame==0) {
			return 0;
		}
		else {
			for (int i=0;i<idEsame;i++){
				somma = somma + esami[i].getVoto();
			}
			
			media=somma/idEsame;
						
		}
		
		punteggio=media+(idEsame/numCorsi*10);
		return punteggio;
	}
	
	public int contaEsami() {
		if (idEsame==0) {
			return 0;
		}
		return 1;
	}
	

}
