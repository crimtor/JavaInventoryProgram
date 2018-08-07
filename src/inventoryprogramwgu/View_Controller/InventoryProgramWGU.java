package inventoryprogramwgu.View_Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Inventory Control Program
 * Completed for WGU C482
 * Software 1
 * @author Shawn T Fox
 * Student ID: #000545644
 * 
 */
public class InventoryProgramWGU extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Inventory Program - Main Screen");
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
