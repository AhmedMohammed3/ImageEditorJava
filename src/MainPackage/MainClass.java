/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import ImagePackage.ImageClass;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import DebuggingPackage.DebuggingClass1;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import java.awt.Color;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.awt.image.BufferedImage;

/**
 *
 * @author Ahmed Hassan
 */

public class MainClass {

    // Pre-Made Functions
    private static String getFileExtension(String fileName) {
        
        int i = fileName.lastIndexOf('.');
        if (i >= 0) 
            return fileName.substring(i+1);
        return "";
    }
//    public MainClass() {
//        String[] args = null;
//        MainClass.main(args);
//    }
    /**
     * @param args the command line arguments
     */
    static JFrame mainFrame;
    static JButton browseBtn, debugBtn, exitBtn;
    static ActionListenerClass listener;
    static Dimension screenSize;
    static int screenHeight, screenWidth;
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Sets the window style like windows operating system.
            //Constructing the main frame
            mainFrame = new JFrame("Image Editor");
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            screenHeight = (int) screenSize.getHeight();
            screenWidth = (int) screenSize.getWidth();
            int w = 20*screenWidth/100;
            int h = 15*screenHeight/100;
            mainFrame.setLocation(w,h);
//            mainFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("G:\\Jobs\\Projects\\Image Editing Program\\Background.jpg")))));
            mainFrame.pack();
            mainFrame.setVisible(true);
            mainFrame.setResizable(false);
            mainFrame.setSize(300, 160);
//            mainFrame.setIconImage(ImageIO.read(new File("G:\\Jobs\\Projects\\Image Editing Program\\ImageEditorIcon.png")));
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            //Creating a panel which contains buttons
            mainFrame.setLayout(null);
            
            listener = new ActionListenerClass();
            
            //Browse Button
            browseBtn = new JButton("Browse");
            browseBtn.setBounds(50, 50, 90, 30);
            mainFrame.add(browseBtn);
            browseBtn.addActionListener(listener);
            
            //Exit Button
            exitBtn = new JButton("Exit");
            exitBtn.setBounds(150, 50, 90, 30);
            mainFrame.add(exitBtn);
            exitBtn.addActionListener(listener);
            
            
            //Debug Button
            debugBtn = new JButton("Debug");
            debugBtn.setBounds(3, 3, 40, 20);
//            mainFrame.add(debugBtn);
            debugBtn.addActionListener(listener);
        }
        catch(HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException /*| IOException*/ ex){
            DebuggingClass1 err =  new DebuggingClass1();
            err.logException(ex.toString());
        }
    }
    public static boolean contains(String [] stringArray, String comparingString)
    {
        for (String stringInArray : stringArray)
            if (comparingString.equals(stringInArray))
                return true;
        return false;
    }
    static class ActionListenerClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
           if (actionEvent.getSource() == browseBtn)
           {
                try
                {
                    String filePath = null;
                    JFileChooser fileChooser = new JFileChooser(filePath);
                    String[] arrayOfFormats = new String[]{"jpg", "png", "gif", "bmp", "jpeg", "tiff", "JPG", "PNG", "GIF", "BMP", "JPEG", "TIFF"};
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Images", arrayOfFormats)); // Sets selectable formats to images only
                    mainFrame.setVisible(false);
                    int choosenBtn = fileChooser.showOpenDialog(fileChooser);
                    if(choosenBtn == JFileChooser.APPROVE_OPTION)
                    {
                        filePath = fileChooser.getSelectedFile().getAbsolutePath(); // Get the path of selected file
                    } else {
                        mainFrame.setVisible(true);
                    }
                    if(contains(arrayOfFormats, getFileExtension(filePath)))
                        new ImageClass(filePath); // Invoke ImageClass to run with filepath got by filechooser
                    else
                    {
                        JOptionPane.showMessageDialog(mainFrame, "This is not an image, please choose a correct file!");
                        mainFrame.setVisible(true);
                    }
                }
                catch(HeadlessException | NullPointerException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
           }
           else if (actionEvent.getSource() == debugBtn)
           {
               try
               {
                    DebuggingClass1 openDebugFrame = new DebuggingClass1();
                    openDebugFrame.openFrame();
               }
               catch(IOException ex)
               {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
               }
           }
           else if (actionEvent.getSource() == exitBtn)
            {
                try
                {
                    if (JOptionPane.showConfirmDialog(mainFrame,"Are you sure?","Query", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
        }        
    }
}
