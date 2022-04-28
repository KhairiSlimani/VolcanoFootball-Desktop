/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.image.Image;
import javafx.collections.transformation.SortedList;
import javafx.collections.transformation.FilteredList;
//import java.awt.Image;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StadeController implements Initializable {

    @FXML
    private ImageView image_view;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfAdresse;
    @FXML
    private DatePicker  tfDateouverture;
    @FXML
    private TextField tfCapacite;
    @FXML
    private TableView<stade> tvStade;
    @FXML
    private TableColumn<stade, Integer> colId;
    @FXML
    private TableColumn<stade, String> colNom;
    @FXML
    private TableColumn<stade, String> colAdresse;
    @FXML
    private TableColumn<stade, String> colDateouverture;
    @FXML
    private TableColumn<stade, Integer> colCapacite;
    @FXML
    private TableColumn<stade, String> colImage;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField filterField;
    @FXML
    private Button insert_image;
    @FXML
    private ImageView logo;
    @FXML
    private Label file_path;
    @FXML
    private Button imprimer;

    

   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StadeAdd.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
           // ajoutersimple(event);
        }else if (event.getSource() == btnUpdate){
            updateRecord();
            //ajoutersimple(event);
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showStade();
       
    }   
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vfootball", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
         public ObservableList<stade> getStadeList(){
        ObservableList<stade> StadeList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM stade";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            stade stade;
            while(rs.next()){
               // stade = new stade(rs.getInt("id"), rs.getString("nom"), rs.getString("destination"), rs.getString("description"),rs.getInt("prix"));
                stade = new stade(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getString("dateouverture"),rs.getInt("capacite"));
               StadeList.add(stade);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return StadeList;
    }
             public void showStade(){
        ObservableList<stade> list = getStadeList();
        
        colId.setCellValueFactory(new PropertyValueFactory<stade, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<stade, String>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<stade, String>("adresse"));
        colDateouverture.setCellValueFactory(new PropertyValueFactory<stade, String>("Dateouverture"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<stade, Integer>("capacite"));
        
        tvStade.setItems(list);
         FilteredList<stade> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(stade -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (stade.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches nom.
				} else if (stade.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches destination.
				}
				else if (String.valueOf(stade.getDateouverture()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                
                                else if (String.valueOf(stade.getCapacite()).indexOf(lowerCaseFilter)!=-1){
				     return true;}
                                
                                 else if (String.valueOf(stade.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<stade> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvStade.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvStade.setItems(sortedData);
       
    }
             
    @FXML
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
             
          private void insertRecord() throws IOException{
 try{
           String query = "INSERT INTO stade (nom,adresse,capacite)VALUES ('"+  tfNom.getText() + "','"  + tfAdresse.getText() +"','"  + tfCapacite.getText() +")";
            Connection conn = getConnection();
            PreparedStatement prst = conn.prepareStatement(query);
               prst.executeUpdate();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kiosque.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
           
           }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }     
    }
          private void updateRecord(){
       // String query = "UPDATE  voyage SET nom  = '" + tfNom.getText() + "', destination = '" + tfDestination.getText() + "', description = '" + tfDescription.getText() + "', prix = '" + tfPrix.getText() + "' WHERE id = '" + tfId.getText() + "'";
        String query = "UPDATE  stade SET nom  = '" + tfNom.getText() + "', adresse = '" + tfAdresse.getText() +  "', capacite = '" + tfCapacite.getText() + "' WHERE id = '" + tfId.getText() + "'";
        executeQuery(query);
        showStade();
        //search();
    }
          private void deleteButton(){
        String query = "DELETE FROM stade WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showStade();
       // search();
    }
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

    @FXML
    private void handleMouseClicked(MouseEvent event) {
        stade stade = tvStade.getSelectionModel().getSelectedItem();
        tfId.setText(""+stade.getId()) ;
        tfNom.setText("" +stade.getNom());
        tfAdresse.setText("" +stade.getAdresse());
        tfCapacite.setText("" +stade.getCapacite());
        //image_view.set("" +stade.getId());
    }
       /*   public List<Voyage> getPodcastByCategorie(String  ReservationV) {
        String req = "select * from Voyage p,ReservationV c where p.id=c.idr ='" + ReservationV+"'";
      List<Voyage> Voyage = new ArrayList<>();
        
       try {
            //insert
             st = conn.createStatement();
            rs = st.executeQuery(query);
           // Connection conn = getConnection();
            // rs = st.executeQuery(query);
           // Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                ReservationV.add(new ReservationV(rs.getInt("id"),rs.getString("Nom"),rs.getString("destination"),rs.getString("description"),rs.getInt("Prix"),rs.getInt("id")));
            }
            } catch (SQLException ex) {
                ex.printStackTrace();
            
        }
                
                 return podcasts;
}*/
    @FXML
    private void Map(ActionEvent event) {
        Stage stage = new Stage ();
         
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/gui/googleMaps.html").toString());
       
        // create scene
        stage.getIcons().add(new Image("/Images/logo.png"));
        stage.setTitle("localisation");
        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
        stage.setScene(scene);
        // show stage
        stage.show();
    }
    static { // use system proxy settings when standalone application
        System.setProperty("java.net.useSystemProxies", "true");
    }
    @FXML
    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tvStade;
       
       
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
     }
    }
        @FXML
    private void stat(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PieChartStade.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }
/*private void ajoutersimple(ActionEvent event) throws IOException 
    {
        if ( tfId.getText().isEmpty() | tfNom.getText().isEmpty() | tfDestination.getText().isEmpty() | tfDescription.getText().isEmpty() | tfPrix.getText().isEmpty());
        {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
 
   
}*/
}

