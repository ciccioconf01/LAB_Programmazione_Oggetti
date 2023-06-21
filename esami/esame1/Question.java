package it.polito.oop.books;
import java.util.*;

import java.util.Set;

public class Question {
	String domanda;
	Topic argomento;
	List<risposta> risposte = new ArrayList <>();
	
	public Question(String question, Topic arg) {
		domanda = question;
		argomento = arg;
	}
	
	public String getQuestion() {
		return domanda;
	}
	
	public Topic getMainTopic() {
		return argomento;
	}

	public void addAnswer(String answer, boolean correct) {
		risposta ris = new risposta (answer,correct);
		risposte.add(ris);
	}
	
    @Override
    public String toString() {
        String stringa = domanda + "("+argomento+")";
        return stringa;
    }

	public long numAnswers() {
	    return risposte.size();
	}

	public Set<String> getCorrectAnswers() {
		Set <String> corrette = new HashSet<>();
		for (risposta ris : risposte) {
			if (ris.GetRisultato()==true) {
				corrette.add(ris.GetRisposta());
			}
		}
		return corrette;
	}

	public Set<String> getIncorrectAnswers() {
		Set <String> noncorrette = new HashSet<>();
		for (risposta ris : risposte) {
			if (ris.GetRisultato()==false) {
				noncorrette.add(ris.GetRisposta());
			}
		}
		return noncorrette;
	}
}
