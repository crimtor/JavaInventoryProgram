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


import java.util.concurrent.atomic.AtomicInteger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Product {
    
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public static final AtomicInteger Count = new AtomicInteger(0); 
    int productID;
    String name;
    double price;
    int inStock;
    int min;
    int max;
    
    public Product(){
        productID = Count.incrementAndGet();
    }
    
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    //added so all parts could be assigned at once from a table
    public void setAssociatedParts(ObservableList<Part> partList) {
        this.associatedParts = partList;
    }

    public void addAssociatedPart(Part part) {
        this.getAssociatedParts().add(part);
    }
    
    public Part lookupAssociatedPart(int e) {
        Part searchPart = this.getAssociatedParts().get(e);
        return searchPart;
    }
    
     public boolean removeAssociatedPart(int e) {
        if(this.getAssociatedParts().get(e) != null ){
            this.getAssociatedParts().remove(e);
             return true;
        }
        else
            return false;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
}
