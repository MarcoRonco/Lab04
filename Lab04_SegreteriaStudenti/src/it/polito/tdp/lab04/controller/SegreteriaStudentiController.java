/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model;
	
	public void setModel(Model m){
		
		this.model = m;
		comboCorso.getItems().addAll(model.getCorsi());
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboCorso"
    private ComboBox<Corso> comboCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNome"
    private CheckBox btnCercaNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if(txtMatricola.getText().matches("[0-9]+")==false){
    		txtResult.setText("Inserimento matricola errato");
    		txtMatricola.clear();
    		return;
    	}
    	
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	if(model.getStudente(matricola)!=null){
    		
    		for(Corso ci :model.getCorsiStudente(matricola)){
    			txtResult.appendText(ci.toString()+'\n');
    		}
    	}
    	else {
    		
    		txtResult.setText("Studente non trovato");
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if(comboCorso.getValue()==null){
    		txtResult.setText("Scegliere corso nel menu a tendina"); 
    		return;
    	}
    	
    	List<Studente> st = model.getStudentiIscritti(comboCorso.getValue());

    	if(st.size()==0){
    		txtResult.setText("Nessuno studente frequenta questo corso"); 
    		return;
    	}
    	
    	for(Studente s : st){
    		txtResult.appendText(s.toString()+'\n');
    	}
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if(txtMatricola.getText().matches("[0-9]+")==false){
    		txtResult.setText("Inserimento matricola errato");
    		txtMatricola.clear();
    		return;
    	}
    	
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	if(model.getStudente(matricola)!=null){
    		
    		txtNome.setText(model.getStudente(matricola).getNome());
    		txtCognome.setText(model.getStudente(matricola).getCognome());
    	}
    	else {
    		
    		txtResult.setText("Studente non trovato");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    	txtResult.clear();
    	
    	if(txtMatricola.getText().matches("[0-9]+")==false){
    		txtResult.setText("Inserimento matricola errato");
    		txtMatricola.clear();
    		return;
    	}
    	
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	if(model.getStudente(matricola)!=null){
    		
    		if(comboCorso.getValue()==null){
        		txtResult.setText("Scegliere corso nel menu a tendina"); 
        		return;
        	}
    		
    		if(model.iscritto(model.getStudente(matricola), comboCorso.getValue())==true){
    			
        		txtResult.setText("Studente gia iscritto a questo corso"); 
        		return;
    			
    		}else{
    			
    			model.inscriviStudenteACorso(model.getStudente(matricola), comboCorso.getValue());
    			txtResult.setText("DB aggiornato: studente inserito nel corso"); 
        		return;
    		}
    		
    	}
    	else {
    		
    		txtResult.setText("Studente non trovato");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    	txtMatricola.clear();
    	txtCognome.clear();
    	txtNome.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
    }
}
