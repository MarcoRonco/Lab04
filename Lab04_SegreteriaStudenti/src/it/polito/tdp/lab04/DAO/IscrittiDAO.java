package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Iscritti;
import it.polito.tdp.lab04.model.Studente;

public class IscrittiDAO {

	public List<Iscritti> getTuttiIscritti() {

		final String sql = "SELECT * FROM iscritti";

		List<Iscritti> iscritti = new LinkedList<Iscritti>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String codins = rs.getString("codins");
				
				Iscritti i = new Iscritti(codins, matricola);
				
				iscritti.add(i);
			}
			
			conn.close();
			return iscritti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
}
