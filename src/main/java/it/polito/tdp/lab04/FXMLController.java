/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbCorsi"
    private ComboBox<Corso> cmbCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void handleCercaIscritti(ActionEvent event) {
    	Corso corso = cmbCorsi.getValue();
    	txtRisultato.clear();
    	
    	if(corso==null) {
    		txtRisultato.setText("Selezionare un corso dalla tendina");
    		return;
    	}
    	
    	if(corso.getCrediti()==null) {
    		for(Studente s: this.model.getStudenti()) {
    			txtRisultato.appendText(s+"\n");
    		}
    		return;
    	}
    	
    	for(Studente s: this.model.getStudentiIscrittiAlCorso(corso)) {
    		txtRisultato.appendText(s+"\n");
    	}
    	
    	if(this.model.getStudentiIscrittiAlCorso(corso).size()==0) {
    		txtRisultato.setText("Nessuno studente iscritto al corso selezionato");
    	}
    	
    }

    @FXML
    void handleCompletamentoAutomatico(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	int matricolaNum;
    	txtRisultato.clear();
    	
    	if(matricola.isEmpty()) {
    		txtRisultato.setText("Inserire una matricola");
    		return;
    	}
    	
    	try {
    		matricolaNum=Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica");
    		return;
    	}
    	
    	Studente res = model.getStudenteByMatricola(matricolaNum);
    	
    	if(res==null) {
    		txtRisultato.setText("Matricola non registrata come studente");
    		return;
    	}
    	
    	txtNome.setText(res.getNome());
    	txtCognome.setText(res.getCognome());
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtRisultato.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	
    	String matricola = txtMatricola.getText();
    	int matricolaNum;
    	txtRisultato.clear();
    	
    	if(matricola.isEmpty()) {
    		txtRisultato.setText("Inserire una matricola");
    		return;
    	}
    	
    	try {
    		matricolaNum=Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica");
    		return;
    	}
    	
    	Studente s = this.model.getStudenteByMatricola(matricolaNum);
    	
    	if(s==null) {
    		txtRisultato.setText("Errore! Studente non presente nel database");
    		return;
    	}
    	
    	
    	for(Corso c : this.model.getCorsiByStudente(s)) {
    		txtRisultato.appendText(c+"\n");
    	}
    	
    	if(this.model.getCorsiByStudente(s).size()==0) {
    		txtRisultato.setText("Studente non iscritto a nessun corso");
    	}
    }
    
    @FXML
    void handleCerca(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	int matricolaNum;
    	Corso c = cmbCorsi.getValue();
    	txtRisultato.clear();
    	
    	if(matricola.isEmpty()) {
    		txtRisultato.setText("Inserire una matricola");
    		return;
    	}
    	
    	if(c==null) {
    		txtRisultato.setText("Selezionare un corso");
    		return;
    	}
    	
    	try {
    		matricolaNum=Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica");
    		return;
    	}
    	
    	Studente s = this.model.getStudenteByMatricola(matricolaNum);
    	
    	if(!this.model.getStudenti().contains(s)) {
    		txtRisultato.setText("Matricola non associata a nessuno studente");
    	}
    	
    	if(this.model.getCorsiByStudente(s).contains(c)) {
    		txtRisultato.setText("Lo studente è regolarmente iscritto al corso selezionato");
    	} else {
    		txtRisultato.setText("Lo studente non è iscritto al corso selezionato");
    	}
    }
    
    @FXML
    void handleIscrivi(ActionEvent event) {
    	
    	String matricola = txtMatricola.getText();
    	int matricolaNum;
    	Corso c = cmbCorsi.getValue();
    	txtRisultato.clear();
    	
    	if(matricola.isEmpty()) {
    		txtRisultato.setText("Inserire una matricola");
    		return;
    	}
    	
    	if(c==null) {
    		txtRisultato.setText("Selezionare un corso");
    		return;
    	}
    	
    	try {
    		matricolaNum=Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica");
    		return;
    	}
    	
    	Studente s = this.model.getStudenteByMatricola(matricolaNum);
    	
    	if(!this.model.getStudenti().contains(s)) {
    		txtRisultato.setText("Matricola non associata a nessuno studente");
    		return;
    	}
    	
    	if(this.model.getCorsiByStudente(s).contains(c)) {
    		txtRisultato.setText("Lo studente è gia iscritto al corso selezionato");
    		return;
    	}
    	
    	if(this.model.iscriviStudenteACorso(s, c)) {
    		txtRisultato.setText("Studente iscritto al corso selezionato");
    	} else {
    		txtRisultato.setText("Errore, studente non iscritto al corso selezionato");
    	}
    	
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
 
    }

    public void setModel (Model model) {
    	this.model=model;
    	
    	cmbCorsi.getItems().clear();
        for(Corso c : this.model.getCorsi()) {
        	cmbCorsi.getItems().add(c);
        }
        cmbCorsi.getItems().add(new Corso("",null,"",null));
    }
}
