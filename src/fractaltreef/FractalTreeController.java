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
    
    private final int firstLineWidth = 0;
    private final int firstLineHeight = -80;
    
    private int fractalLength;
    private int fractalAngle;
    private int fractalWidth;
    private int fractalHeight;
    private boolean isFirstLine;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setFractalTreeValues();
    }    
    
    private void setFractalTreeValues()
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
        isFirstLine = true;
        if (tglRandomize.isSelected())
        {
            makeTree (fractalLength, fractalAngle, fractalWidth, fractalHeight, true); 
        }
        else
        {
            makeTree (fractalLength, fractalAngle, fractalWidth, fractalHeight, false);
        }
    }
    
    public void makeTree(int length, int angle, int x, int y, boolean isRandomized) 
    {
        int xmove = getXMove(length, angle, isRandomized);
        int ymove = getYMove(length, angle, isRandomized);
        
        Line line = createLine(x, y, xmove, ymove);
        pnFractal.getChildren().add(line);
        
        if (length >= 1)
        {
            makeTree(length-10, angle + 30, x + xmove, y + ymove, isRandomized);
            makeTree(length-10, angle - 30, x + xmove, y + ymove, isRandomized);
        }
    }
    
    private int getXMove(int length, int angle, boolean isRandomized)
    {
        int xmove = 0;
        if (isFirstLine)
        {
            xmove= firstLineWidth;
            isFirstLine = false;
        }
        else if(!isFirstLine)
        {
            if (!isRandomized)
            {
                xmove=(int)(Math.cos(Math.toRadians(angle+90)) * length);
            }
            else if (isRandomized)
            {
                Random random = new Random();
                angle = random.nextInt(360);
                xmove=(int)(Math.cos(Math.toRadians(angle+90)) * length);
            }
        }
        return xmove;
    }
    
    private int getYMove(int length, int angle, boolean isRandomized)
    {
        int ymove = 0;
        if (isFirstLine)
        {
            ymove= firstLineHeight;
            isFirstLine = false;
        }
        else if(!isFirstLine)
        {
            if (!isRandomized)
            {
                ymove=(int)(Math.sin(Math.toRadians(angle-90)) * length);
            }
            else if (isRandomized)
            {
                Random random = new Random();
                angle = random.nextInt(360);
                ymove=(int)(Math.sin(Math.toRadians(angle-90)) * length);
            }
        }
        return ymove;
    }

    private Line createLine(int x, int y, int xmove, int ymove)
    {
        Line line = new Line();
        line.setStartX(x);
        line.setStartY(y);
        line.setEndX(x+xmove);
        line.setEndY(y+ymove);
        line.setStroke(clpFractal.getValue());
        return line;
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
