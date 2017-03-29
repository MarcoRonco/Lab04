package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrittiDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	CorsoDAO cDAO = new CorsoDAO();
	Map<String, Corso> corsi = new HashMap<String, Corso>();
	
	private StudenteDAO sDAO = new StudenteDAO();
	Map<Integer, Studente> studenti = new HashMap<Integer, Studente>();
	
	private IscrittiDAO iDAO = new IscrittiDAO();
	
	public Model(){
         
		for(Corso f : cDAO.getTuttiICorsi()){
			
			corsi.put(f.getCodins(), f);
		}
		
        for(Studente f : sDAO.getTuttiStudenti()){
			
			studenti.put(f.getMatricola(), f);
		}
       
	}
	
    public List<Corso> getCorsi(){
		
		return cDAO.getTuttiICorsi();
	}
    
    public Studente getStudente(int matricola){
    	return studenti.get(matricola);
    }
	
    public List<Studente> getStudentiIscritti(Corso c){
    	
    	List<Studente> s = new ArrayList<Studente>();
    	
    	for(Iscritti i : iDAO.getTuttiIscritti()){
    		
    		if(i.getCorso().compareTo(c.getCodins())==0){
    			
    			s.add(studenti.get(i.getStudenti()));
    		}
    	}
    	
    	return s;
    }
}
