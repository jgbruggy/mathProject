package mathproject;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeff
 */



public class GuiFrame extends JFrame
{
    
private JLabel enterPoint1Label;
private JLabel enterPoint2Label;
private JLabel enterDirVectorLabel;
private JTextField enterPoint1Field;
private JTextField enterPoint2Field;
private JTextField enterDirVectorField;
private JButton computeButton;
private JButton exitButton;
private JPanel mainPanel;

    public GuiFrame()
   {
        final int FRAME_WIDTH = 350;
   	final int FRAME_HEIGHT = 200;
   	setSize(FRAME_WIDTH, FRAME_HEIGHT);
     
           
        // method that creates the visual elements
        createElements();
        createPanel();
   }
    
    private void createElements()
    {
        this.enterPoint1Label = new JLabel("Point (Ex: a,b,c ):");
        this.enterPoint2Label = new JLabel("Line begins at point:");
        this.enterDirVectorLabel = new JLabel("with direction vector:");
        
        this.enterPoint1Field = new JTextField(10);
        this.enterPoint2Field = new JTextField(10);
        this.enterDirVectorField = new JTextField(10);
        
        this.computeButton = new JButton("Compute");
        ActionListener compute= new computeListener();
        computeButton.addActionListener(compute);
        
        this.exitButton = new JButton("Exit");
        ActionListener exit= new exitListener();
        exitButton.addActionListener(exit);
    }
    
    class computeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent compute) 
        {
            String result=new String();
            double[] pointArray = mathMethods.getArray(enterPoint1Field.getText());
            double[] lineArray = mathMethods.getArray(enterPoint2Field.getText());
            double[] dirVectorArray = mathMethods.getArray(enterDirVectorField.getText());
            //getArray returns some array after delimiting the string input
            
            /*
            double[] pointArray = new double[3];
            pointArray[0]=4; pointArray[1]=-6; pointArray[2]=0;
            double[] lineArray = new double[3];
            lineArray[0]=2; lineArray[1]=0; lineArray[2]=-1;
            double[] dirVectorArray = new double[3];
            dirVectorArray[0]=5; dirVectorArray[1]=3; dirVectorArray[2]=-2;
            */
            result+="Point: ("+enterPoint1Field.getText()+")\nLine: ("+enterPoint2Field.getText()+")+("+enterDirVectorField.getText()+")t\n\n";
            String question1; 
            String question2;
            boolean onLine= mathMethods.onLine(pointArray, lineArray, dirVectorArray);
            
            System.out.println(onLine);
            if(onLine==true)
            {
                question1="The Point is on the Line";
                question2="Cannot find plane";
            }
            else
            {
                question1="The Point is not on the Line";
                question2 = mathMethods.planeEquation(pointArray, lineArray, dirVectorArray);
            }
            result+="1) "+question1+"\n\n";
            
            result+="2) The plane equation that contains the Line and Point: \nPlane: "+question2+"\n\n";
            
            String question3 = mathMethods.planeToString(dirVectorArray, pointArray);
            
            result+="3) The equation of the plane that passes through P perpendicular to l: \nPlane:"+question3+"\n\n";
            
            String question4 = mathMethods.reflection(pointArray, lineArray, dirVectorArray);
            
            result+="4) "+question4;
            
            JOptionPane.showMessageDialog(null, result, "Results", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    class exitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent exit)
        {
            System.exit(0);
        }
    }
    
    private void createPanel()
    {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        
        gbc.gridx=0;//1
        gbc.gridy=1;
        mainPanel.add(enterPoint1Label, gbc);
        gbc.gridx=1;//2
        gbc.gridy=1;
        mainPanel.add(enterPoint1Field, gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        mainPanel.add(enterPoint2Label, gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        mainPanel.add(enterPoint2Field, gbc);
        gbc.gridx=0;
        gbc.gridy=3;
        mainPanel.add(enterDirVectorLabel, gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        mainPanel.add(enterDirVectorField, gbc);
        gbc.gridx=0;
        gbc.gridy=4;
        mainPanel.add(computeButton, gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        mainPanel.add(exitButton, gbc);
        
        add(mainPanel);    
    }        
}
