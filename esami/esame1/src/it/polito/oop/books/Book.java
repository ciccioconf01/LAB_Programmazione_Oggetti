package it.polito.oop.books;
import java.util.*;
import java.util.List;
import java.util.Map;

public class Book {
	
	List <Topic> argomenti = new ArrayList<>();
	List <Question> domande = new ArrayList<>();
	List <TheoryChapter> capitoliT = new ArrayList<>();
	List <ExerciseChapter> capitoliE = new ArrayList<>();
	List <Assignment> esercizi = new ArrayList<>();

	public Topic getTopic(String keyword) throws BookException {
		if (keyword == null) {
			throw new BookException();
		}
		if (keyword.compareTo("")==0) {
			throw new BookException();
		}
		Topic elem2 = null;
	    boolean trovato = false;
		for (Topic elem : argomenti) {
	    	if (keyword.compareTo(elem.getKeyword())==0) {
	    		trovato = true;
	    		elem2 = elem;
	    	}
	    }
		
		if (trovato == false) {
			Topic elemento = new Topic(keyword);
			argomenti.add(elemento);
			return elemento;
		}
		
		else {
			return elem2;
		}
		
	}

	public Question createQuestion(String question, Topic mainTopic) {
        Question q = new Question(question,mainTopic);
        domande.add(q);
        return q;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
        TheoryChapter cap = new TheoryChapter (title,numPages,text);
        capitoliT.add(cap);
        return cap;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
        ExerciseChapter cap = new ExerciseChapter(title,numPages);
        capitoliE.add(cap);
        return cap;
	}

	public List<Topic> getAllTopics() {
        
		Set <Topic> argomenti = new HashSet <>();
		List <Topic> lista = new ArrayList <>();
		List <Topic> lista2 = new ArrayList <>();
		
		for (TheoryChapter cap : capitoliT) {
			
			lista = cap.getTopics();
			
			for (Topic elem : lista) {
				argomenti.add(elem);
			}
		}
		
		for (Topic elem : argomenti) {
			lista2.add(elem);
		}
		
		Collections.sort(lista2, 
				(t1,t2) -> {
					return t1.getKeyword().compareTo(t2.getKeyword());
				});
		
		return lista2;
		
		
	}

	public boolean checkTopics() {
		Set <Topic> set = new HashSet <>();
		List <Topic> lista = new ArrayList <>();
		
		Set <Topic> set2 = new HashSet <>();
		List <Topic> lista2 = new ArrayList <>();
		
		for (ExerciseChapter cap : capitoliE) {
			lista = cap.getTopics();
			for (Topic elem : lista) {
				set.add(elem);
			}
		}
		
		for (TheoryChapter cap : capitoliT) {
			lista2 = cap.getTopics();
			for (Topic elem : lista2) {
				set2.add(elem);
			}
		}
		
		boolean trovato = false;
		for (Topic elem : set) {
			for (Topic elem2 : set2) {
				if (elem.getKeyword().compareTo(elem2.getKeyword())==0) {
					trovato = true;
				}
			}
			
			if (trovato == false) {
				return false;
			}
			trovato = false;
		}
		
		return true;
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
        Assignment elem = new Assignment(ID,chapter);
        esercizi.add(elem);
        
		return elem;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
    	Map<Long,List<Question>> mappa = new TreeMap <>();
    	
    	
    	long n;
    	for (Question elem : domande) {
    		n = elem.numAnswers();
    		
    		if (mappa.containsKey(n)==false) {
    			List<Question> lista = new ArrayList <>();
    			lista.add(elem);
    			mappa.put(n, lista);
    		}
    		
    		else {
    			mappa.get(n).add(elem);
    		}
    	}
    	return mappa;
    }
    
}
    
  
