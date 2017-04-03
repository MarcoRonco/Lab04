package it.polito.tdp.lab04.DAO;

import java.sql.*;
import java.util.*;

import it.polito.tdp.lab04.model.*;

public class StudenteDAO {

	public Studente getStudente(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola=?";

		Studente s = null;

		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				s = new Studente(matricola, cognome, nome, cds);
			}
			
			conn.close();
			return s;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Corso> getCorsiStudente(int matricola) {
		
		final String sql = "SELECT corso.codins, corso.crediti, corso.nome, corso.pd "+
                           "FROM studente, iscrizione, corso "+
                           "WHERE studente.matricola=iscrizione.matricola "+
                           "AND corso.codins = iscrizione.codins "+
                           "AND iscrizione.matricola = ?";
		
		List<Corso> c = new ArrayList<Corso>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				Corso co = new Corso(codins, crediti, nome, pd);
				
				c.add(co);
			}
			
			conn.close();
			return c;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
}
