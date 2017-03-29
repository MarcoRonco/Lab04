package it.polito.tdp.lab04.model;

import java.util.*;

public class Iscritti {
	
	private String corso;
	private int studenti;
	
	public Iscritti(String corso, int studenti) {
	
		this.corso = corso;
		this.studenti = studenti;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getStudenti() {
		return studenti;
	}

	public void setStudenti(int studenti) {
		this.studenti = studenti;
	}
	
}
