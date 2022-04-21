/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volcanofootball;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author jrady
 */
public class SendmailequipeController implements Initializable {

    
    @FXML
    private Button Retour;
    @FXML
    private Button btn_mail;
    @FXML
    private Text tmailto;
    @FXML
    private TextField tfmailfrom;
    @FXML
    private TextField tfsub;
    @FXML
    private TextArea tfmessage;
    @FXML
    private PasswordField tfpass;
    @FXML
    private Text tindice;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void displayMail(String email){
        tmailto.setText(email);
    }

    @FXML
    private void dashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Equipe.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sendMail(MouseEvent event)  {
        String to = tmailto.getText();
        String host = "smtp.gmail.com";
        final String username = tfmailfrom.getText();
        final String password = tfpass.getText();
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()  {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username,password);
            }
        });
        try{
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(username));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(tfsub.getText());
            m.setText(tfmessage.getText());
            
            Transport.send(m);
            System.out.println("Message envoyé");
            
            
            
            
            
        } catch(MessagingException e){
            e.printStackTrace();
        }
        tindice.setText("Message envoyé");
        
    }
    
    
    
}
