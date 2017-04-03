package it.polito.tdp.lab04.DAO;

import java.sql.*;
import java.util.*;

import it.polito.tdp.lab04.model.*;

public class IscrittiDAO {

	public List<Studente> getTuttiIscritti(String codins) {

		final String sql = "SELECT studente.matricola, cognome, nome, CDS "+
						   "FROM studente, iscrizione "+
						   "WHERE studente.matricola=iscrizione.matricola "+ 
						   "AND iscrizione.codins = ?";

		List<Studente> iscritti = new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				
				Studente s = new Studente(matricola, cognome, nome, cds);
				
				iscritti.add(s);
			}
			
			conn.close();
			return iscritti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
}
