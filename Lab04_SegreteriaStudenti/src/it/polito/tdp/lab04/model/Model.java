package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {

	private CorsoDAO cDAO = new CorsoDAO();
	
	private StudenteDAO sDAO = new StudenteDAO();
	
	private IscrittiDAO iDAO = new IscrittiDAO();
	
	public Model(){}
	
    public List<Corso> getCorsi(){
		return cDAO.getTuttiICorsi();
	}
    
    public Studente getStudente(int matricola){
    	return sDAO.getStudente(matricola);
    }
	
    public List<Studente> getStudentiIscritti(Corso c){
    	return iDAO.getTuttiIscritti(c.getCodins());
    }
   
    public List<Corso> getCorsiStudente(int matricola){
    	return sDAO.getCorsiStudente(matricola);
    }
    
    public void inscriviStudenteACorso(Studente studente, Corso corso){
    	 cDAO.inscriviStudenteACorso(studente, corso);
    }
    
    public boolean iscritto(Studente studente, Corso corso){
   	 return cDAO.iscritto(studente, corso);
   }
}
