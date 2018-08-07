/**
 * 
 * Inventory Control Program
 * Completed for WGU C482
 * Software 1
 * @author Shawn T Fox
 * Student ID: #000545644
 * 
 */
package inventoryprogramwgu.View_Controller;

import inventoryprogramwgu.Model.InhousePart;
import inventoryprogramwgu.Model.OutsourcedPart;
import inventoryprogramwgu.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class ModifyPartScreenController implements Initializable {

    @FXML private RadioButton inHousePartButton;
    @FXML private RadioButton outSourcedPartButton;
    @FXML private Label machineIDorCompanyName;
    private ToggleGroup whereIsPartMade;
    @FXML private TextField partIDField;
    @FXML private TextField partNameField;
    @FXML private TextField invAmountField;
    @FXML private TextField priceCostField;
    @FXML private TextField minAmountField;
    @FXML private TextField maxAmountField;
    @FXML private TextField companyNameField;
    @FXML private TextField machineIdField;
    private Part oldPart;
    
    
    //Setting text filter so only numeric values can be input into all fields that require
    // ints or doubles. Used to prevent parsing errors.
    UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

            @Override
            public TextFormatter.Change apply(TextFormatter.Change textField) {

                if (textField.isReplaced()) 
                    if(textField.getText().matches("[^0-9]"))
                        textField.setText(textField.getControlText().substring(textField.getRangeStart(), textField.getRangeEnd()));
                

                if (textField.isAdded()) {
                    if (textField.getControlText().contains(".")) {
                        if (textField.getText().matches("[^0-9]")) {
                            textField.setText("");
                        }
                    } else if (textField.getText().matches("[^0-9.]")) {
                        textField.setText("");
                    }
                }

                return textField;
            }
        };
    
 public void partMadeLocation()
    {
         if (this.whereIsPartMade.getSelectedToggle().equals(this.inHousePartButton)){
           companyNameField.setEditable(false);
           machineIdField.setEditable(true);
           machineIdField.setPromptText("Machine ID");
           companyNameField.setText("");
           companyNameField.setPromptText("Disabled");
           
        
        }
         
        if (this.whereIsPartMade.getSelectedToggle().equals(this.outSourcedPartButton)){
            machineIdField.setEditable(false);
            companyNameField.setEditable(true);
            companyNameField.setPromptText("Company Name");
            machineIdField.setText("");
            machineIdField.setPromptText("Disabled");
                   
            }    
    }
    
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        Parent backToMainParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreen = new Scene(backToMainParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation to Leave Modify Part Page");
        alert.setHeaderText("Warning you are about to leave the Modify Part page.");
        alert.setContentText("Are you sure you want to go back to the main screen? "
                + "\n" + "The part will not be updated and any changes will be lost.");
        
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton){
            window.setTitle("Inventory Program - Main Screen");
            window.setScene(mainScreen);
            window.show();
        }
      
    }
    public void saveButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        Parent addPartParent = loader.load();
        
        Scene mainScreen = new Scene(addPartParent);
        
        //access the controller and call a method
        MainScreenController controller = loader.getController();
        
        if (partNameField.getText().equalsIgnoreCase("") || priceCostField.getText().equalsIgnoreCase("") ||
        invAmountField.getText().equalsIgnoreCase("") || minAmountField.getText().equalsIgnoreCase("") ||
               maxAmountField.getText().equalsIgnoreCase("")){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Required Info Missing");
            alert.setHeaderText("Required fields are missing data");
            alert.setContentText("At least one of the following required fields was left"
            +" blank, Part Name, Price/Cost, Amount in Stock, Min on Hand, Max on Hand, and "
                  +"either the Machine Id or Company Name. Please correct and try again.");

            alert.showAndWait();
       }
       else{
           String pName = partNameField.getText();
           double pPrice = Double.parseDouble(priceCostField.getText());
           int pInStock = Integer.parseInt(invAmountField.getText());
           int pMin = Integer.parseInt(minAmountField.getText());
           int pMax = Integer.parseInt(maxAmountField.getText());  
        
           if (this.whereIsPartMade.getSelectedToggle().equals(this.inHousePartButton)){
                InhousePart newPart = new InhousePart();
                newPart.setName(pName);
                newPart.setPrice(pPrice);
                
               if (pMin>pMax){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, Min value too high");
                    alert.setHeaderText("Min on hand must be less than Max on hand");
                    alert.setContentText("Please select a lower value and try again. Thank you.");

                    alert.showAndWait();
                   
               }else{
                newPart.setMin(pMin);
                newPart.setMax(pMax);
                if (pInStock > pMax || pInStock < pMin){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, Not valid In Stock Amount");
                    alert.setHeaderText("In Stock must be equal to or between Min on hand and Max on hand");
                    alert.setContentText("Please select different value and try again. Thank you.");

                    alert.showAndWait();
                }else{
                    newPart.setInStock(pInStock);
                    if (machineIdField.getText().equalsIgnoreCase("")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Required Field Missing");
                        alert.setHeaderText("Required field missing");
                        alert.setContentText("Please select In House or Outsourced Part radio button"
                    +" and make sure Machine ID or Company Name is completed and try again. Thank you.");

                        alert.showAndWait();
                
                    }else{
                        int pMachineID = Integer.parseInt(machineIdField.getText());
                        newPart.setMachineID(pMachineID);
                        newPart.setPartID(oldPart.getPartID());
                        controller.updateParts(controller.getIndex(oldPart),newPart);
                        //This line gets the Stage information
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setTitle("Inventory Program - Main Screen");
                        window.setScene(mainScreen);
                        window.show(); 
                    }
                }
            }    
           }else if (this.whereIsPartMade.getSelectedToggle().equals(this.outSourcedPartButton)){
                OutsourcedPart newPart = new OutsourcedPart();
                newPart.setName(pName);
                newPart.setPrice(pPrice);
                
                if (pMin>pMax){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, Min value too high");
                    alert.setHeaderText("Min on hand must be less than Max on hand");
                    alert.setContentText("Please select a lower value and try again. Thank you.");

                    alert.showAndWait();
                   
               }else{
                newPart.setMin(pMin);
                newPart.setMax(pMax);
                if (pInStock > pMax || pInStock < pMin){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, Not valid In Stock Amount");
                    alert.setHeaderText("In Stock must be equal to or between Min on hand and Max on hand");
                    alert.setContentText("Please select different value and try again. Thank you.");

                    alert.showAndWait();
                }else{
                    newPart.setInStock(pInStock);
                               
                    if(companyNameField.getText().equalsIgnoreCase("")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Required Field Missing");
                        alert.setHeaderText("Required field missing");
                        alert.setContentText("Please select In House or Outsourced Part radio button"
                    +" and make sure Machine ID or Company Name is completed and try again. Thank you.");

                        alert.showAndWait();
                    
                    }else{
                        newPart.setCompanyName(companyNameField.getText());
                        newPart.setPartID(oldPart.getPartID());
                        controller.updateParts(controller.getIndex(oldPart),newPart);
                    //This line gets the Stage information
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setTitle("Inventory Program - Main Screen");
                        window.setScene(mainScreen);
                        window.show();
                    }
                }
            }    
        }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Radio Button Must be Selected");
                alert.setHeaderText("Required radio button not selected");
                alert.setContentText("Please select In House or Outsourced Part radio button"
                 +" and make sure Machine ID or Company Name is completed and try again. Thank you.");

                alert.showAndWait();     
            }
        }
    }
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        whereIsPartMade = new ToggleGroup();
        this.inHousePartButton.setToggleGroup(whereIsPartMade);
        this.outSourcedPartButton.setToggleGroup(whereIsPartMade);
        invAmountField.setTextFormatter(new TextFormatter<>(filter));
        priceCostField.setTextFormatter(new TextFormatter<>(filter));
        minAmountField.setTextFormatter(new TextFormatter<>(filter));
        maxAmountField.setTextFormatter(new TextFormatter<>(filter));
        machineIdField.setTextFormatter(new TextFormatter<>(filter));
      
}
    public Part updatePart(Part part){
    oldPart = part;   
    partIDField.setText(Integer.toString((part.getPartID())));
    partNameField.setText(part.getName());
    invAmountField.setText(Integer.toString((part.getInStock())));
    priceCostField.setText(Double.toString((part.getPrice())));
    minAmountField.setText(Integer.toString((part.getMin())));
    maxAmountField.setText(Integer.toString((part.getMax())));
    
    if ( part instanceof InhousePart) {
        InhousePart internalPart = (InhousePart) part;
        machineIdField.setText(Integer.toString(internalPart.getMachineID()));
        this.whereIsPartMade.selectToggle(inHousePartButton);
        machineIdField.setEditable(true);
        companyNameField.setEditable(false);
        machineIdField.setPromptText("Machine ID");
        companyNameField.setPromptText("Disabled");
                   
    }else{
        OutsourcedPart otherPeoplesPart = (OutsourcedPart) part;
        companyNameField.setText(otherPeoplesPart.getCompanyName());
        this.whereIsPartMade.selectToggle(outSourcedPartButton);
        machineIdField.setEditable(false);
        companyNameField.setEditable(true);
        machineIdField.setPromptText("Disabled");
        companyNameField.setPromptText("Company Name");
        
        }    
                   
        
    
    return part;    
    }
}
