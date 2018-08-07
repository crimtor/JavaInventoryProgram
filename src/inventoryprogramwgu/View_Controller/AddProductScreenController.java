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
import inventoryprogramwgu.Model.Product;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AddProductScreenController implements Initializable {

    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Double> priceColumn;
    @FXML private TableColumn<Part, Integer> invColumn;
    @FXML private TableView<Part> addedPartsTable;
    @FXML private TableColumn<Part, Integer> partIDColumnTable2;
    @FXML private TableColumn<Part, String> partNameColumnTable2;
    @FXML private TableColumn<Part, Double> priceColumnTable2;
    @FXML private TableColumn<Part, Integer> invColumnTable2;
    @FXML private TextField searchPartBox;
    @FXML private TextField productIDField;
    @FXML private TextField productNameField;
    @FXML private TextField invAmountField;
    @FXML private TextField priceField;
    @FXML private TextField minAmountField;
    @FXML private TextField maxAmountField;
    @FXML public ObservableList<Part> dataTable1=FXCollections.observableArrayList();
    @FXML public ObservableList<Part> dataTable2=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * 
     */
    
    
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
     
    @FXML void SearchTable(ActionEvent event) {
    dataTable1 = partsTable.getItems();
    String searchItem= searchPartBox.getText();
    boolean found=false;
    try{
        int itemNumber=Integer.parseInt(searchItem);
        for(Part p: dataTable1){
            if(p.getPartID()==itemNumber){
                System.out.println("This is part "+ itemNumber);
                found=true;

                dataTable2 = addedPartsTable.getItems();
                dataTable2.add(p);

                    partIDColumnTable2.setCellValueFactory(new PropertyValueFactory<>("partID"));
                    partNameColumnTable2.setCellValueFactory(new PropertyValueFactory<>("name"));
                    priceColumnTable2.setCellValueFactory(new PropertyValueFactory<>("price"));
                    invColumnTable2.setCellValueFactory(new PropertyValueFactory<>("inStock"));

                addedPartsTable.setItems(dataTable2);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Part Added");
                alert.setContentText("Part added to product");

                alert.showAndWait();
            
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");

            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Part p: dataTable1){
            if(p.getName().toLowerCase().equals((searchItem).toLowerCase())){
                System.out.println("This is part "+p.getPartID());
                found=true;

               dataTable2 = addedPartsTable.getItems();
                dataTable2.add(p);

                    partIDColumnTable2.setCellValueFactory(new PropertyValueFactory<>("partID"));
                    partNameColumnTable2.setCellValueFactory(new PropertyValueFactory<>("name"));
                    priceColumnTable2.setCellValueFactory(new PropertyValueFactory<>("price"));
                    invColumnTable2.setCellValueFactory(new PropertyValueFactory<>("inStock"));

                addedPartsTable.setItems(dataTable2);
            
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Part not found");

            alert.showAndWait();
        }
    }

    }
    
      public void addPartButtonPushed()
    {
        ObservableList<Part> selectedRows, allParts;
        allParts = addedPartsTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = partsTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Part part: selectedRows)
        {
            allParts.add(part);
        }
    }
    
     public void deletePartButtonPushed()
    {
        ObservableList<Part> selectedRows, allParts;
        allParts = addedPartsTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = addedPartsTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (Part part: selectedRows)
        {
            allParts.remove(part);
        }
    }
    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */   
    
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        Parent backToMainParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreen = new Scene(backToMainParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation to Leave Add Product Page");
        alert.setHeaderText("Warning you are about to leave the Add Product page.");
        alert.setContentText("Are you sure you want to go back to the main screen? "
                + "\n" + "No product will be created and all data will be lost");
        
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
        Parent addProductParent = loader.load();
        
        Scene mainScreen = new Scene(addProductParent);
        
        //access the controller and call a method
        MainScreenController controller = loader.getController();
        
        ObservableList<Part> allParts;
        allParts = addedPartsTable.getItems();
        
        if (productNameField.getText().equalsIgnoreCase("") || priceField.getText().equalsIgnoreCase("") ||
        invAmountField.getText().equalsIgnoreCase("") || minAmountField.getText().equalsIgnoreCase("") ||
               maxAmountField.getText().equalsIgnoreCase("") || allParts.isEmpty()){
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Required Info Missing");
            alert.setHeaderText("Required fields are missing data");
            alert.setContentText("At least one of the following required fields was left"
            +" blank, Product Name, Price, Amount in Stock, Min on Hand, Max on Hand, or "
                  +"parts were not added to this product. Please correct and try again.");

            alert.showAndWait();
       }
       else{
           String pName = productNameField.getText();
           double pPrice = Double.parseDouble(priceField.getText());
           int pInStock = Integer.parseInt(invAmountField.getText());
           int pMin = Integer.parseInt(minAmountField.getText());
           int pMax = Integer.parseInt(maxAmountField.getText());  
           double totalCost = 0.00;
               
            for (int i=0 ; i < allParts.size(); i++){
                double cost = allParts.get(i).getPrice();
                totalCost = totalCost+cost;         
           }
            if (totalCost> pPrice){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error, Price Not High Enough");
                alert.setHeaderText("Cost of parts greater than price of product");
                alert.setContentText("Please set price to higher value and try again. Thank you.");

                alert.showAndWait();
            }else{
                if (pMin>pMax){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error, Min value too high");
                alert.setHeaderText("Min on hand must be less than Max on hand");
                alert.setContentText("Please select a lower value and try again. Thank you.");

                alert.showAndWait();
                   
            }else{
               Product newProduct = new Product();
               newProduct.setAssociatedParts(allParts);
               newProduct.setName(pName);
               newProduct.setPrice(pPrice);
               newProduct.setMin(pMin);
               newProduct.setMax(pMax);  
               
               if (pInStock > pMax || pInStock < pMin){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, Not valid In Stock Amount");
                    alert.setHeaderText("In Stock must be equal to or between Min on hand and Max on hand");
                    alert.setContentText("Please select different value and try again. Thank you.");

                    alert.showAndWait();
                }else{
                    newProduct.setInStock(pInStock);
       
                    controller.setProducts(newProduct);
       
        //This line gets the Stage information
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setTitle("Inventory Program - Main Screen");
                    window.setScene(mainScreen);
                    window.show();
               }
              }
            }
        }    
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        invColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        partIDColumnTable2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameColumnTable2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        priceColumnTable2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        invColumnTable2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        invAmountField.setTextFormatter(new TextFormatter<>(filter));
        priceField.setTextFormatter(new TextFormatter<>(filter));
        minAmountField.setTextFormatter(new TextFormatter<>(filter));
        maxAmountField.setTextFormatter(new TextFormatter<>(filter));
        
       
    }    
     
    public ObservableList<Part>  getParts(ObservableList<Part> partList)
    {
        ObservableList<Part> data = FXCollections.observableArrayList();
        data = partList;
        partsTable.setItems(data);
        return data;
    }
    
    
}
