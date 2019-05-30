/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractaltreef;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Kiddo
 */
public class FractalTreeController implements Initializable {
    
    @FXML
    private Pane pnFractal;
    @FXML
    private JFXButton btnStart;
    @FXML
    private JFXTextField txtLength;
    @FXML
    private JFXTextField txtAngle;
    @FXML
    private JFXToggleButton tglRandomize;
    @FXML
    private JFXColorPicker clpFractal;
    @FXML
    private JFXTextField txtWidth;
    @FXML
    private JFXTextField txtHeight;
    
    private int fractalLength;
    private int fractalAngle;
    private int fractalWidth;
    private int fractalHeight;
    private boolean randomize;



    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fractalLength = 100;
        fractalAngle = 0;
        fractalWidth = 600;
        fractalHeight = 550;
        clpFractal.setValue(Color.BLACK);
    }    
    
    @FXML
    private void clickStartFractal(ActionEvent event) 
    {
        pnFractal.getChildren().clear();
        clpFractal.getCustomColors();
        if (tglRandomize.isSelected())
        {
            randomize = true;
            spaint (fractalLength, 0, 600, 550); 
        }
        else
        {
            randomize = false;
            spaint (fractalLength, fractalAngle, fractalWidth, fractalHeight);
        }
    }
    
    
    public void spaint (int length,int angle,int x, int y) 
    {  
        makeTree(length, angle, x, y);
    }
    
    public void makeTree(int length, int angle, int x, int y) 
    {
        int xmove=(int)(Math.cos(Math.toRadians(angle+90)) * length);
        int ymove=(int)(Math.sin(Math.toRadians(angle-90)) * length);
        
        Line linez = new Line();
        linez.setStartX(x);
        linez.setStartY(y);
        linez.setEndX(x+xmove);
        linez.setEndY(y+ymove);
        linez.setStroke(clpFractal.getValue());
        pnFractal.getChildren().add(linez);
        if (randomize == true)
        {
            Random random = new Random();
            angle = random.nextInt(360);
        }

        if (length>=1)
        {
            makeTree(length-10, angle + 30, x + xmove, y + ymove);
            makeTree(length-10, angle - 30, x + xmove, y + ymove);
        }
    }

    @FXML
    private void clickSetLength(KeyEvent event)
    {
        fractalLength = Integer.parseInt(txtLength.getText());
    }

    @FXML
    private void clickSetAngle(KeyEvent event)
    {
        fractalAngle = Integer.parseInt(txtAngle.getText());
    }
    @FXML
    private void clickSetWidth(KeyEvent event) 
    {
        fractalWidth = Integer.parseInt(txtWidth.getText());
    }

    @FXML
    private void clickSetHeight(KeyEvent event) 
    {
        fractalHeight = Integer.parseInt(txtHeight.getText());
    }
    
        @FXML
    private void clickClose(ActionEvent event) 
    {
         Platform.exit();
    }
}
