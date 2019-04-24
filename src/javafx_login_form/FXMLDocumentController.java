/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_login_form;
import java.sql.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class FXMLDocumentController implements Initializable {
    @FXML
    PreparedStatement ps;
    Connection conn;
    ResultSet rs;
    
    @FXML
    private JFXTextField field1,field2,field3,signfield1,signfield2;
   
     @FXML
    private JFXButton btn_signin,btn_signup,btn_close,sign_up,sign_in;
    
    @FXML
    private AnchorPane pn_signin,pn_signup;
    
    public FXMLDocumentController()
            {
                conn=javaconnect.ConnectDB();
            }
    
    public void signup()
    {
        try
        {
            String sql="insert into userdata values(?,?,?)";
            ps=conn.prepareStatement(sql);
            
            ps.setString(1,field1.getText());
            ps.setString(2,field2.getText());
            ps.setString(3,field3.getText());
            
            ps.execute();
            JOptionPane.showMessageDialog(null,"Submit succesfull");
                       
           
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void signin()
    {
        try
        {
            String sql="select * from userdata where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,signfield1.getText());
            ps.setString(2,signfield2.getText());
            rs=ps.executeQuery();
            
            if(rs.next())
            {
               // Now you can add any operation in here as anything you want 
                //you add one or anchorpane or window  
                // you add passwordfield from place of textfield ok Thanks fro wathcing
                FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
                Parent root1= (Parent) fxmlloader.load();
                Stage stage=new Stage();
                Scene scene=new Scene(root1);
                stage.setScene(scene);
                stage.show();
                
                JOptionPane.showMessageDialog(null,"Sign in Succesfull");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Wrong userid or Password");
            }
             rs.close();
             ps.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }

   
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
          if(event.getSource()==btn_signin)
          {
             pn_signin.toFront();
             
          }
          else if(event.getSource()==btn_signup)
             {
                 pn_signup.toFront();
             } 
          else if(event.getSource()==btn_close)
                  {
                      System.exit(0);
                  }
          else if(event.getSource()==sign_up)
          {
              signup();
          }
          else if(event.getSource()==sign_in)
          {
              signin();
          }
         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
