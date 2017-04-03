package it.polito.tdp.lab04.DAO;

import java.sql.*;
import java.util.*;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				Corso c = new Corso(codins, crediti, nome, pd);
				
				corsi.add(c);
			}
			conn.close();
			return corsi;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public void inscriviStudenteACorso(Studente studente, Corso corso) {
		
        final String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?,?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			int rs = st.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
	
	public boolean iscritto(Studente studente, Corso corso) {
		
        final String sql = "SELECT * "+
        				   "FROM iscrizione "+
        				   "WHERE matricola = ? "+
        				   "AND codins = ?";
		
        boolean t = false;
        
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				t=true;
			}
			
			conn.close();
			return t;
			
		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
}
