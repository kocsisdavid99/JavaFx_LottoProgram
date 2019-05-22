/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotto;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LottoNezetController implements Initializable {
//<editor-fold defaultstate="collapsed" desc="Class Variables">
    private final int MAX = 90;
    private final int MIN = 1;
    private final String WIN0 = "Sajnos nem nyertél semmi.";
    private final String WIN1 = "Egyesed van a lottón, sajnos ez nem fizet semmit.";
    private final String WIN2 = "Kettesed van a lottón, sajnos ez csak mosolyra elég.";
    private final String WIN3 = "Hármasod van a lottón. Ez már valami!";
    private final String WIN4 = "Négyesed van a lottón. Legyél magadra nagyon büszke!";
    private final String WIN5 = "ÖTÖSÖD VAN! Gratulálunk!";
    private int genNum1;
    private int genNum2;
    private int genNum3;
    private int genNum4;
    private int genNum5;
    private int selNum1;
    private int selNum2;
    private int selNum3;
    private int selNum4;
    private int selNum5;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="FXML items import">
    @FXML
    private Pane basePane;
    
    @FXML
    private Pane alertPane;
    
    @FXML
    private Label alertText;
    
    @FXML
    private Button alertButton;
    
    @FXML
    private Label label1;
    
    @FXML
    private Label label2;
    
    @FXML
    private Label label3;
    
    @FXML
    private Label label4;
    
    @FXML
    private Label label5;
    
    @FXML
    private TextField input1;
    
    @FXML
    private TextField input2;
    
    @FXML
    private TextField input3;
    
    @FXML
    private TextField input4;
    
    @FXML
    private TextField input5;
    
    @FXML
    private Label resultLabel;
//</editor-fold>
    
    
    @FXML
    private void handleAlertButton(ActionEvent event) {
        basePane.setDisable(false);
        basePane.setOpacity(1);
        alertPane.setVisible(false);
        alertText.setText("");
        
    }
    
   
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //We are generating numbers
        genNum1 = 0;
        genNum2 = 0;
        genNum3 = 0;
        genNum4 = 0;
        genNum5 = 0;
        genNum1 = getRandomNumber();
        genNum2 = getRandomNumber();
        genNum3 = getRandomNumber();
        genNum4 = getRandomNumber();
        genNum5 = getRandomNumber();
        
        
        //We are setting the generated numbers to the labels
        label1.setText("" + genNum1);
        label2.setText("" + genNum2);
        label3.setText("" + genNum3);
        label4.setText("" + genNum4);
        label5.setText("" + genNum5);
        
        calculate();
    }
    
    private void calculate() {
        
        //Are they numbers?
        try {
            selNum1 = Integer.parseInt(input1.getText());
            selNum2 = Integer.parseInt(input2.getText());
            selNum3 = Integer.parseInt(input3.getText());
            selNum4 = Integer.parseInt(input4.getText());
            selNum5 = Integer.parseInt(input5.getText());
        } catch (Exception e) {
            alert("Nem jó számot adtál meg!");
            return;
            
        }
        
        //Are they unique
        HashSet<Integer> selectedNumbers = new HashSet<>();
        selectedNumbers.add(selNum1);
        selectedNumbers.add(selNum2);
        selectedNumbers.add(selNum3);
        selectedNumbers.add(selNum4);
        selectedNumbers.add(selNum5);
        
        if (selectedNumbers.size() < 5) {
            alert("A számoknak különbözőnek kell lenniük!");
            return;
        }
        
        ArrayList<Integer> userNumbers = new ArrayList<>(selectedNumbers);
        
        //Are they between 1-99?
        for(Integer number : userNumbers){
            if (number < MIN || number > MAX) {
                alert("Minden számnak 1 és 99 között kell lennie!");
                return;
            }
        
        }
        
        resultCheck(userNumbers);
    
        
     return;
        
        
    }
    
    private void alert(String text){
        basePane.setDisable(true);
        basePane.setOpacity(0.3);
        alertPane.setVisible(true);
        alertText.setText(text);
        
    }
    
    private void resultCheck(ArrayList<Integer> userNumbers){
       
        int result = 0;
        for (int i = 0; i < userNumbers.size(); i++) {
            if (userNumbers.get(i) == genNum1 ||  userNumbers.get(i) == genNum2 ||  userNumbers.get(i) == genNum3 ||  userNumbers.get(i) == genNum4 ||  userNumbers.get(i) == genNum5)
                result++;
        }
        
        switch(result){
            case 0 : resultLabel.setText(WIN0);
                break;
            case 1 : resultLabel.setText(WIN1);
                break;
            case 2 : resultLabel.setText(WIN2);
                break;
            case 3 : resultLabel.setText(WIN3);
                break;
            case 4 : resultLabel.setText(WIN4);
                break;
            case 5 : resultLabel.setText(WIN5);
                break;
        }
    
    }
    
    private int getRandomNumber() {
        int random = (int) (Math.random() * MAX) + MIN;
        
        if (random == genNum1 || random == genNum2 || random == genNum3 || random == genNum4 || random == genNum5){
            return getRandomNumber();
            
        }
        
        return random;
      
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
}
