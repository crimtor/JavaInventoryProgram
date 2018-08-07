/**
 * 
 * Inventory Control Program
 * Completed for WGU C482
 * Software 1
 * @author Shawn T Fox
 * Student ID: #000545644
 * 
 */
package inventoryprogramwgu.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class Inventory {
    //setting the universal static lists so all screens work from same data
    @FXML public static ObservableList<Part> allParts=FXCollections.observableArrayList();
    @FXML public static ObservableList<Product> products=FXCollections.observableArrayList();
  
//product added thru the Add Products Screen, using main screen controller to add to the 
//static products list    
public void addProduct(Product product){}

//product removed through main screen delete button which removes it from static products list
public boolean removeProduct(int i)
{return true;}

//product found via main screen search which checks static products list and highlights in table
public Product lookupProduct(int i){
return products.get(i);}

//product is updated by selecting a product on main screen product table and passing that info
//to the update product screen where all info is  set into boxes
public void updateProduct(int i){}

//parts are added thru the add part screen, using the main screen controller to add to the
//static parts list
public void addPart(Part part){}

//part removed through main screen delete button which removes it from static parts list
public boolean deletePart(Part part){
return true;}

//part found via main screen search which checks static parts list and highlights in table
public Part lookupPart(int i){
return allParts.get(i);
}

//part is updated by selecting a part on main screen part table and passing that info
//to the update part screen where all info is  set into boxes
public void updatePart(Part part){}
}


