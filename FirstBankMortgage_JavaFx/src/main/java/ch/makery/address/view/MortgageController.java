package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.Choice;
import java.awt.TextField;
import java.util.UUID;

import javax.swing.plaf.ComboBoxUI;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {

	@FXML
    private TextField txtIncome;
    @FXML
    private TextField txtExpenses;
    @FXML
    private TextField txtCreditScore;
    @FXML
    private TextField txtHouseCost;
    
   
	
	public static double Income;
    public static double Expenses;
    public static int CreditScore;
    public static double HouseCost;
    private boolean calculateClicked = false;
	
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
    }
    
    @FXML
    private void handleCalculate() {
        if (isInputValid()) {
        	String message = "";
            Income = Double.parseDouble(txtIncome.getText());
            Expenses = Double.parseDouble(txtExpenses.getText());
            CreditScore = Integer.parseInt(txtCreditScore.getText());
            HouseCost = Double.parseDouble(txtHouseCost.getText());

            calculateClicked = true;
            
            double bound1 = Income*.36;
            double bound2 = (Income+Expenses)*.28;
            double pmt = Rate.getPayment(CreditScore);
            
            if(pmt<=bound1 & pmt<=bound2){
            	message = "Your Rate is "+Double.toString(pmt);
            }else{
            	message = "You can't afford that!";
            }
            
            Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mortgage Rate");
			alert.setHeaderText("Mortgage Calculation");
			alert.setContentText(message);
            
        }
    }
        
    

   
    private boolean isInputValid() {
        String errorMessage = "";

        if (txtIncome.getText() == null || txtIncome.getText().length() == 0) {
            errorMessage += "No valid Income!\n"; 
        }else{
        	try {
                Double.parseDouble(txtIncome.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Income (must be a double)!\n"; 
            }
        }
        if (txtExpenses.getText() == null || txtExpenses.getText().length() == 0) {
            errorMessage += "No valid Expenses!\n"; 
        }else{
        	try {
                Double.parseDouble(txtExpenses.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Expenses (must be a double)!\n"; 
            }
        }
        if (txtCreditScore.getText() == null || txtCreditScore.getText().length() == 0) {
            errorMessage += "No valid Credit Score!\n"; 
        }else{
        	try {
                Integer.parseInt(txtCreditScore.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No Credit Score (must be an Integer)!\n"; 
            }
        }

        if (txtHouseCost.getText() == null || txtHouseCost.getText().length() == 0) {
            errorMessage += "No valid House Cost!\n"; 
        }else{
        	try {
                Double.parseDouble(txtHouseCost.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid House Cost (must be a double)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
        }
}
    
        
    