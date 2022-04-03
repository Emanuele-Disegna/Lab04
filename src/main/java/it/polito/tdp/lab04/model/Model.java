package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {
	
	private StudenteDAO sd;
	private CorsoDAO cd;
	private Map<String, Corso> corsi;
	private Map<Integer, Studente> studenti;
	
	public Model() {
		super();
		sd = new StudenteDAO();
		cd = new CorsoDAO();
		
		corsi = new HashMap<String, Corso>();
		studenti = new HashMap<Integer, Studente>();
		
		for(Studente s : this.sd.getAllStudenti()) {
			studenti.put(s.getMatricola(), s);
		}
		
		for(Corso c : this.cd.getTuttiICorsi()) {
			corsi.put(c.getCodins(), c);
		}
		
	}

	public Studente getStudenteByMatricola(int matricola) {
		return studenti.get(matricola);
	}
	
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return cd.getStudentiIscrittiAlCorso(corso);
	}
	
	public Corso getCorso(String codins) {
		return corsi.get(codins);
	}
	
	public List<Corso> getCorsiByStudente(Studente s) {
		return cd.getCorsiByStudente(s);
	}
	
	public boolean iscriviStudenteACorso(Studente s, Corso c) {
		return cd.iscriviStudenteACorso(s, c);
	}

	public StudenteDAO getSd() {
		return sd;
	}

	public CorsoDAO getCd() {
		return cd;
	}

	public Collection<Corso> getCorsi() {
		return corsi.values();
	}

	public Collection<Studente> getStudenti() {
		return studenti.values();
	}
	
	
}
