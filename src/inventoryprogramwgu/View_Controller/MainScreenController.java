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
import javafx.application.Platform;
import static inventoryprogramwgu.Model.Inventory.allParts;
import static inventoryprogramwgu.Model.Inventory.products;
import inventoryprogramwgu.Model.OutsourcedPart;
import inventoryprogramwgu.Model.Part;
import inventoryprogramwgu.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MainScreenController implements Initializable {
    @FXML private Label label;
    @FXML private TextField searchPartTable;
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Double> priceColumn;
    @FXML private TableColumn<Part, Integer> invColumn;
    @FXML private TableColumn<Part, Integer> minColumn;
    @FXML private TableColumn<Part, Integer> maxColumn;
    @FXML private TextField searchProductTable;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Part, Integer> productIDColumn;
    @FXML private TableColumn<Part, String> productNameColumn;
    @FXML private TableColumn<Part, Double> productCostColumn;
    @FXML private TableColumn<Part, Integer> productInvColumn;
    @FXML public ObservableList<Part> dataTable1= allParts;
    @FXML public ObservableList<Product> productTable1= products;
    
   
    
    public void addPartButtonPushed(ActionEvent event) throws IOException
    {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene addPartScreen = new Scene(addPartParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Add Part Screen");
        window.setScene(addPartScreen);
        window.show();
    }
    public void modifyPartButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));
        Parent mainScreenParent = loader.load();
        
        Scene modifyPartScreen = new Scene(mainScreenParent);
        
        //access the controller and call a method
        ModifyPartScreenController controller = loader.getController();
        
        ObservableList<Part> selectedRows, allParts;
        allParts = partsTable.getItems();
       
        //this gives us the rows that were selected
        selectedRows = partsTable.getSelectionModel().getSelectedItems();
        
        Part partToPass = selectedRows.get(0);
   
        controller.updatePart(partToPass);
      
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Modify Part Screen");
        window.setScene(modifyPartScreen);
        window.show();
    }
    
    public void addProductButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddProductScreen.fxml"));
        Parent mainScreenParent = loader.load();
        
        Scene addProductScreen = new Scene(mainScreenParent);
        
        //access the controller and call a method
        AddProductScreenController controller = loader.getController();
        
        ObservableList<Part> allParts;
        allParts = dataTable1;
   
        controller.getParts(allParts);
      
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Add Product Screen");
        window.setScene(addProductScreen);
        window.show();
    }
  
     public void modifyProductButtonPushed(ActionEvent event) throws IOException
     {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProductScreen.fxml"));
        Parent mainScreenParent = loader.load();
        
        Scene modifyProductScreen = new Scene(mainScreenParent);
        
        //access the controller and call a method
        ModifyProductScreenController controller = loader.getController();
        
        ObservableList<Part> allParts;
        allParts = dataTable1;
   
        controller.getParts(allParts);
        ObservableList<Product> selectedRows;
        
        //this gives us the rows that were selected
        selectedRows = productTable.getSelectionModel().getSelectedItems();
        
        Product productToPass = selectedRows.get(0);
        controller.updateProduct(productToPass);
      
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Modify Product Screen");
        window.setScene(modifyProductScreen);
        window.show();
    }
    
    @FXML void SearchPartTable(ActionEvent event) {
    String searchItem= searchPartTable.getText();
    boolean found=false;
    try{
        int itemNumber=Integer.parseInt(searchItem);
        for(Part p: dataTable1){
            if(p.getPartID()==itemNumber || p.getName().equals(Integer.toString(itemNumber))){
                found=true;

                partsTable.getSelectionModel().select(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Part Found");
                alert.setContentText("Part " + searchItem + " was found");

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
                found = true;
                
                partsTable.getSelectionModel().select(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Part Found");
                alert.setContentText("Part " +searchItem+ " was found");

                alert.showAndWait();
            
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
    @FXML void SearchProductTable(ActionEvent event) {
    String searchItem= searchProductTable.getText();
    boolean found=false;
    try{
        int itemNumber=Integer.parseInt(searchItem);
        for(Product p: productTable1){
            if(p.getProductID()==itemNumber || p.getName().equals(Integer.toString(itemNumber))){
                found=true;
                
                productTable.getSelectionModel().select(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Product Found");
                alert.setContentText("Product " +searchItem+ " was found");

                alert.showAndWait();
            
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Product not found");

            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Product p: productTable1){
            if(p.getName().toLowerCase().equals((searchItem).toLowerCase())){
                found = true;
                
                productTable.getSelectionModel().select(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Product Found");
                alert.setContentText("Product " +searchItem+ " was found");

                alert.showAndWait();
            
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Product not found");

            alert.showAndWait();
        }
    }
    }
    
     public void deletePartButtonPushed()
    {
        
        ObservableList<Part> selectedRows, allParts;
        allParts = partsTable.getItems();
       
        //this gives us the rows that were selected
        selectedRows = partsTable.getSelectionModel().getSelectedItems();
        
        Part partToPass = selectedRows.get(0);
        
        //Makes the alert/confirmation box to verify delete
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation to Delete Part");
        alert.setHeaderText("Warning you are about to delete a part.");
        alert.setContentText("Are you sure you want to delete this part?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton){
         dataTable1.remove(dataTable1.indexOf(partToPass));
        }       
     
    }
       public void deleteProductButtonPushed()
    {
        
        ObservableList<Product> selectedRows, allParts;
        allParts = productTable.getItems();
       
        //this gives us the rows that were selected
        selectedRows = productTable.getSelectionModel().getSelectedItems();
        
        Product productToPass = selectedRows.get(0);
       
        //Makes the alert/confirmation box to verify delete
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation to Delete Product");
        alert.setHeaderText("Warning you are about to delete a product"
                + "\n and that product has a part associated with it.");
        alert.setContentText("Are you sure you still want to delete this product?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton){
         productTable1.remove(productTable1.indexOf(productToPass));
        }
    }
       
        public void exitButtonPushed(ActionEvent event) 
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exiting the Program");
            alert.setHeaderText("Warning you are about to close the program.");
            alert.setContentText("Are you sure you want to close the program?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton){
                Platform.exit();
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        invColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        minColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("min"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("max"));
        productIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        productCostColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inStock"));
        partsTable.setItems(getParts());
        productTable.setItems(getProducts());
       
}
    //used to set the part table view to the parts in the data(part) table list
    public ObservableList<Part>  getParts()
    {
        ObservableList<Part> data = FXCollections.observableArrayList();
        data = dataTable1;
        return data;
    }
    
    //used to set the product table view to the products in the product table list
    public ObservableList<Product>  getProducts()
    {
        ObservableList<Product> data2 = FXCollections.observableArrayList();
        data2 = productTable1;
        return data2;
    }
    
    //Used to put a new part into the data(part) table list.
     public ObservableList<Part>  setParts(Part part)
    {
        dataTable1.add(0, part); 
        
        return dataTable1;
    }
     
     // used to get the index of original part so it can be used in update to
      // replace in the same position
     public int getIndex(Part part)
     {
         int partIndex = dataTable1.indexOf(part);
         return partIndex;
     }  
     
     // Used to update a part with same ID and index and using the new data 
      public ObservableList<Part>  updateParts(int partIndex, Part part)
    {
        dataTable1.set(partIndex, part) ;
        return dataTable1;
    }
    
      //Used to put a new product into the product table list.
      public ObservableList<Product>  setProducts(Product product)
    {
        productTable1.add(0, product); 
        return productTable1;
    }
    
      // used to get the index of original product so it can be used in update to
      // replace in the same position
      public int getProductIndex(Product product)
     {
         int productIndex = productTable1.indexOf(product);
         return productIndex;
     }  
   
     // Used to update a product with same ID and index with new data 
     public ObservableList<Product>  updateProducts(int productIndex, Product product)
    {
        productTable1.set(productIndex, product) ;
        return productTable1;
    }
   
}