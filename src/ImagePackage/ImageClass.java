package ImagePackage;

import DebuggingPackage.DebuggingClass1;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import MainPackage.MainClass;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author Ahmed Hassan
 */
public class ImageClass {
    static JFrame imageFrame;
    static JPanel imagePanel;
    static Image loadedImage = null;
    static JLabel toolsBoxLabel, filtersBoxLabel;
    static JComboBox toolsBox, filtersBox;
    static JButton imageCropBtn, filtersApplyBtn, saveBtn, backBtn, exitBtn, undoBtn, redoBtn;
    static ActionListenerClass listener;
    static Stack undoStack, redoStack;
    static boolean editFlag = false, imageCropped = false;
//    static MouseListenerClass mouseListener;
    static ToolsClass drawTool;
    static Dimension screenSize;
    static int screenHeight, screenWidth;
      
    public ImageClass(String filepath)
    {
        SwingUtilities.invokeLater(() -> {
            try {
                undoStack = new Stack();
                redoStack = new Stack();
                imageFrame = new JFrame("Image Editor");
                imageFrame.pack();
                imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                imageFrame.setIconImage(ImageIO.read(new File("G:\\Jobs\\Projects\\Image Editing Program\\ImageEditorIcon.png")));
                imageFrame.setBackground(Color.BLACK);
                screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                screenHeight = (int) screenSize.getHeight();
                screenWidth = (int) screenSize.getWidth();
                int w = 5*screenWidth/100;
                int h = 5*screenHeight/100;
                imageFrame.setLocation(w, h);
                imageFrame.setLayout(null);
                imageFrame.setResizable(false);
                imageFrame.setVisible(true);
                imageFrame.setSize(900, 600);
                
                imagePanel = new JPanel();
                imagePanel.setBounds(0, 0, 500, 500);
                imageFrame.add(imagePanel);
                
                loadedImage = ImageIO.read(new File(filepath));
                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                drawTool = new ToolsClass(loadedImage);
                imagePanel.add(drawTool);
                
                
                toolsBoxLabel = new JLabel("Tool: ");
                toolsBoxLabel.setBounds(550, 10, 150, 30);
                imageFrame.add(toolsBoxLabel);
                
                String[] tools = {"None", "Rectangle", "Circle"};
                toolsBox = new JComboBox(tools);
                toolsBox.setSelectedIndex(0);
                toolsBox.setBounds(590, 10, 150, 30);
                imageFrame.add(toolsBox);
                listener = new ActionListenerClass();
                toolsBox.addActionListener(listener);
                
                imageCropBtn = new JButton("Crop Image");
                imageCropBtn.setBounds(750, 10, 110, 30);
                imageFrame.add(imageCropBtn);
                imageCropBtn.addActionListener(listener);
                
                filtersBoxLabel = new JLabel("Filter: ");
                filtersBoxLabel.setBounds(550, 100, 150, 30);
                imageFrame.add(filtersBoxLabel);
                
                String[] filters = {"None", "Light", "Dark", "Blur", "Invert"};
                filtersBox = new JComboBox(filters);
                filtersBox.setSelectedIndex(0);
                filtersBox.setBounds(590, 100, 150, 30);
                imageFrame.add(filtersBox);
                
                filtersApplyBtn = new JButton("Apply Filter");
                filtersApplyBtn.setBounds(750, 100, 110, 30);
                imageFrame.add(filtersApplyBtn);
                filtersApplyBtn.addActionListener(listener);
                
                saveBtn = new JButton("Save");
                saveBtn.setBounds(750, 280, 90, 30);
                imageFrame.add(saveBtn);
                saveBtn.addActionListener(listener);
                drawTool.setImage(loadedImage);
                undoBtn = new JButton("Undo");
                undoBtn.setBounds(600, 200, 90, 30);
                imageFrame.add(undoBtn);
                undoBtn.addActionListener(listener);
                
                redoBtn = new JButton("Redo");
                redoBtn.setBounds(750, 200, 90, 30);
                imageFrame.add(redoBtn);
                redoBtn.addActionListener(listener);
                
                backBtn = new JButton("Back");
                backBtn.setBounds(600, 500, 90, 30);
                imageFrame.add(backBtn);
                backBtn.addActionListener(listener);
                
                exitBtn = new JButton("Exit");
                exitBtn.setBounds(750, 500, 90, 30);
                imageFrame.add(exitBtn);
                exitBtn.addActionListener(listener);
                
            }
            catch(HeadlessException | IOException ex){
                DebuggingClass1 err =  new DebuggingClass1();
                err.logException(ex.toString()); //Store exception in error ArrayList
            }
        });
        
    }
    public static BufferedImage toBufferedImage(Image img)
    {
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    static class ActionListenerClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == toolsBox)
            {
                try
                {
                    if(toolsBox.getSelectedIndex() != 0)
                        drawTool.setToolType(toolsBox.getSelectedIndex());
                }
                catch(Exception ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            if (actionEvent.getSource() == imageCropBtn)
            {
                try
                {
                    if(toolsBox.getSelectedIndex() != 0)
                    {
                            undoStack.push(drawTool.getImage());
                            drawTool.setImage(drawTool.cropImage(toBufferedImage(drawTool.getImage())));
                            toolsBox.setSelectedIndex(0);
                            editFlag = true;
                    }
                    else
                        JOptionPane.showMessageDialog(imageFrame, "Please Choose a tool to cut with");
                }
                catch(HeadlessException | RasterFormatException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == filtersApplyBtn)
            {
                FiltersClass filterTool = new FiltersClass();
                try
                {
                    
                        switch (filtersBox.getSelectedIndex()) {
                            case 1:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.lightenImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 2:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.darkenImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 3:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.blurImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            case 4:
                                undoStack.push(drawTool.getImage());
                                loadedImage = filterTool.invertImage(toBufferedImage(drawTool.getImage()));
//                                loadedImage = loadedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                                drawTool.setImage(loadedImage);
                                redoStack.clear();
                                filtersBox.setSelectedIndex(0);
                                editFlag = true;
                                break;
                            default:
                                JOptionPane.showMessageDialog(imageFrame, "Please Choose a filter to apply");
                                break;
                        }
                    
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == saveBtn)
            {
                try
                {
                    if(editFlag)
                    {
                        String filePath = null;
                        JFileChooser fileChooser = new JFileChooser(filePath);
                        imageFrame.setVisible(false);
                        int choosenBtn = fileChooser.showSaveDialog(fileChooser);
                        if(choosenBtn == JFileChooser.APPROVE_OPTION)
                        {
                            File tempFile = new File(fileChooser.getSelectedFile().toString()+".png");
                            ImageIO.write(toBufferedImage(drawTool.getImage()), "png", tempFile);
                            imageFrame.setVisible(true);
                            
                        } else {
                            imageFrame.setVisible(true);
                        }
                    }
                    else JOptionPane.showMessageDialog(imageFrame, "You can NOT do this right now!");
                }
                catch(HeadlessException | IOException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == undoBtn)
            {
                try
                {
                    if(!undoStack.empty() && editFlag == true)
                    {
                        redoStack.push(drawTool.getImage());
                        loadedImage = toBufferedImage((Image)undoStack.pop());
//                        loadedImage = loadedImage.getScaledInstance(700, 700, Image.SCALE_DEFAULT);
                        drawTool.setImage(loadedImage);
                        drawTool.repaint();
                    }
                    else
                        JOptionPane.showMessageDialog(imageFrame, "You Can NOT do this right now!");
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == redoBtn)
            {
                try
                {
                    if(!redoStack.empty() && editFlag == true)
                    {
                        undoStack.push(drawTool.getImage());
                        loadedImage = toBufferedImage((Image)redoStack.pop());
//                        loadedImage = loadedImage.getScaledInstance(700, 700, Image.SCALE_DEFAULT);
                        drawTool.setImage(loadedImage);
                        drawTool.repaint();
                    }
                    else
                        JOptionPane.showMessageDialog(imageFrame, "You Can NOT do this right now!");
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == backBtn)
            {
                try
                {
                    if (JOptionPane.showConfirmDialog( imageFrame,"Are you sure?","Query", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        imageFrame.setVisible(false);
                        MainClass.main(null); // To back to main frame
                    }
                }
                catch(HeadlessException ex)
                {
                    DebuggingClass1 err =  new DebuggingClass1();
                    err.logException(ex.toString()); //Store exception in error ArrayList
                }
            }
            else if (actionEvent.getSource() == exitBtn)
            {
                try
                {
                    if (JOptionPane.showConfirmDialog( imageFrame,"Are you sure?","Query", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
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
