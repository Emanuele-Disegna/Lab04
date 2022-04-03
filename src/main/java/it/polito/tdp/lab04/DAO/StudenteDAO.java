package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteByMatricola(int matricola){
		
		Studente ret = null;
		
		String sql = "SELECT * FROM studente "
				+ "WHERE matricola = ?";
		
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pr = conn.prepareStatement(sql);
			pr.setInt(1, matricola);
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				ret=new Studente(matricola, rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				
			}
			
			conn.close();
			pr.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public List<Studente> getAllStudenti(){
		
		List<Studente> ret = new ArrayList<Studente>();
		
		String sql = "SELECT * FROM studente ";
		
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement pr = conn.prepareStatement(sql);
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				ret.add(new Studente(rs.getInt("matricola"), rs.getString("nome"), 
						rs.getString("cognome"), rs.getString("cds"))) ;
			}
			
			conn.close();
			pr.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
}
