/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.equipe;

import Entities.Equipe;
import Entities.Joueur;
import Services.EquipeService;
import Services.JoueurService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author jrady
 */
public class StatequipeController implements Initializable {

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart<String, Integer> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setstatData();
    }   
    public void setstatData() {
        EquipeService es = new EquipeService();
        ObservableList<Equipe> list = es.afficherEquipe();
        JoueurService joueur = new JoueurService();
        

       

        ObservableList<String> array1 = FXCollections.observableArrayList();;
        ObservableList<Integer> array2 = FXCollections.observableArrayList();;

        for (Equipe e : list) {
            List<Joueur> list1 = joueur.afficherJoueursByEquipe(e.getId());
            System.out.println("Nom : " + e.getNom_equipe());
            System.out.println("ID : " + e.getId());
            array1.add(e.getNom_equipe());
            System.out.println(array1);
            array2.add(list1.size());
            System.out.println("Array 1 : " + array1);
            System.out.println("Array  2 : " + array2);
        }
         System.out.println(array1);

        xAxis.setCategories(array1);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        int k = 0;
        for (Equipe e : list) {
            series.getData().add(new XYChart.Data<>(array1.get(k), array2.get(k)));
            k++;
        }

        barChart.getData().add(series);
    }

    
}
