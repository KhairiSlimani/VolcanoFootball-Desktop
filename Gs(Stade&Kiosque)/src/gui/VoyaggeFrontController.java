/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class VoyaggeFrontController implements Initializable {

    @FXML
    private TableView<Voyage> tvVoyage;
    @FXML
    private TableColumn<Voyage, Integer> colId;
    @FXML
    private TableColumn<Voyage, String> colNom;
    @FXML
    private TableColumn<Voyage, String> colDestination;
    @FXML
    private TableColumn<Voyage, String> colDescription;
    @FXML
    private TableColumn<Voyage, Integer> colPrix;
    @FXML
    private TableColumn<Voyage, String> colImage;
    @FXML
    private TableColumn<Voyage, String> editCol;
    @FXML
    private Button ReservationVoyage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showVoyage();
    }    
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exx", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
         public ObservableList<Voyage> getVoyageList(){
        ObservableList<Voyage> voyageList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM voyage";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Voyage voyage;
            while(rs.next()){
               // voyage = new Voyage(rs.getInt("id"), rs.getString("nom"), rs.getString("destination"), rs.getString("description"),rs.getInt("prix"));
                voyage = new Voyage(rs.getInt("id"), rs.getString("nom"), rs.getString("destination"), rs.getString("description"),rs.getInt("prix"),rs.getString("image"));
                voyageList.add(voyage);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return voyageList;
    }
             public void showVoyage(){
        ObservableList<Voyage> list = getVoyageList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Voyage, String>("nom"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Voyage, String>("destination"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Voyage, String>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("prix"));
        colImage.setCellValueFactory(new PropertyValueFactory<Voyage, String>("image"));
        
        
              
        tvVoyage.setItems(list);
         
    }
             
   /* @FXML
        public void insertImage(){
        
        FileChooser open = new FileChooser();
        
        Stage stage = (Stage)image_view.getScene().getWindow();
        
        File file = open.showOpenDialog(stage);
        
        if(file != null){
            
            String path = file.getAbsolutePath();
            
           // path = path.replace("\\", "\\\\");
            path = path.replace("\\", "\\\\");
            
            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);
            
            image_view.setImage(image);
            
        }else{
            
            System.out.println("NO DATA EXIST!");
            
        }
    }
        */     
      /*    private void insertRecord(){
       // String query = "INSERT INTO voyage VALUES (" + tfId.getText() + ",'" + tfNom.getText() + "','" + tfDestination.getText() + "','" + tfDescription.getText() + "','" + tfPrix.getText() + "')";
        String query = "INSERT INTO voyage VALUES (" + tfId.getText() + ",'" + tfNom.getText() + "','" + tfDestination.getText() + "','" + tfDescription.getText() + "','" + tfPrix.getText() + "','" + image_view.getImage() + "')";
        // String query = "INSERT INTO voyage VALUES ('" + tfNom.getText() + "','" + tfDestination.getText() + "','" + tfDescription.getText() + "','" + tfPrix.getText() + "','" + image_view.getImage() + "')";
        executeQuery(query);
        showVoyage();
       // search();
    }*/
    /*      private void updateRecord(){
       // String query = "UPDATE  voyage SET nom  = '" + tfNom.getText() + "', destination = '" + tfDestination.getText() + "', description = '" + tfDescription.getText() + "', prix = '" + tfPrix.getText() + "' WHERE id = '" + tfId.getText() + "'";
        String query = "UPDATE  voyage SET nom  = '" + tfNom.getText() + "', destination = '" + tfDestination.getText() + "', description = '" + tfDescription.getText() + "', prix = '" + tfPrix.getText() + "', image = '" + image_view.getImage() + "' WHERE id = '" + tfId.getText() + "'";
        executeQuery(query);
        showVoyage();
        //search();
    }
          private void deleteButton(){
        String query = "DELETE FROM voyage WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showVoyage();
       // search();
    }*/
        private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     /*  void search() {          
        colId.setCellValueFactory(new PropertyValueFactory<Voyage,Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Voyage,String>("nom"));
        colDestination.setCellValueFactory(new PropertyValueFactory<Voyage,String>("destination"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Voyage,String>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Voyage,Integer>("prix"));
       
        Connection conn = getConnection();
        tvVoyage.setItems(dataList);
        //table_users.setItems(dataList);
        FilteredList<Voyage> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(voyage -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (voyage.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (voyage.getDestination().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (voyage.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(voyage.getId()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Voyage> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tvVoyage.comparatorProperty());  
  tvVoyage.setItems(sortedData);      
    }*/
       /* FilteredList<Voyage> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(maisonh -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (maisonh.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (maisonh.getDestination().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(maisonh.getDescription()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                
                                else if (String.valueOf(maisonh.getPrix()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                
                                 else if (String.valueOf(maisonh.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Voyage> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(Voyage.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvVoyage.setItems(sortedData);*/

   /* @FXML
    private void handleMouseClicked(MouseEvent event) {
        Voyage voyage = tvVoyage.getSelectionModel().getSelectedItem();
        tfId.setText("" +voyage.getId());
        tfNom.setText("" +voyage.getNom());
        tfDestination.setText("" +voyage.getDestination());
        tfDescription.setText("" +voyage.getDescription());
        tfPrix.setText("" +voyage.getPrix());
        //image_view.set("" +voyage.getId());
    }*/

    @FXML
    private void ReservationVoyage(ActionEvent event) throws IOException{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservationFront.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();

    }
    

    
}
