package it.polito.oop.books;
import java.util.*;
import java.util.List;


public class ExerciseChapter {
	String titolo;
	int numeroPagine;
	List <Question> domande = new ArrayList <>();
	
	public ExerciseChapter(String tit, int num) {
		titolo = tit;
		numeroPagine = num;
	}
	
	public List <Question> GetDomande(){
		return domande;
	}

    public List<Topic> getTopics() {
    	Set <Topic> set = new HashSet <>();
    	List <Topic> lista2 = new ArrayList <>();
    	
    	for(Question q : domande) {
    		
    		set.add(q.getMainTopic());
    	}
    	
    	for (Topic elem : set) {
    		lista2.add(elem);
    	}
    	
    	Collections.sort(lista2,
    			(t1,t2)->{
    				return t1.getKeyword().compareTo(t2.getKeyword());
    			});
    	
        return lista2;
	};
	

    public String getTitle() {
        return titolo;
    }

    public void setTitle(String newTitle) {
    	titolo = newTitle;
    }

    public int getNumPages() {
        return numeroPagine;
    }
    
    public void setNumPages(int newPages) {
    	numeroPagine=newPages;
    }

	public void addQuestion(Question question) {
		domande.add(question);
	}	
}
