/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DebuggingPackage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Ahmed Hassan
 */
public class DebuggingClass1 {
    
    private static final ArrayList<String> errorsList = new ArrayList<String>() ;
    
    public void DebuggingClass1()
    {
        
    }
    public void logException(String err)
    {
       errorsList.add(err);
    }
    JFrame exceptionFrame;
    JPanel exceptionPanel;
    JTextArea exceptionTextArea;
    public void openFrame() throws IOException
    {
        try
        {
            exceptionFrame = new JFrame("Debugging Frame");
            exceptionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            exceptionPanel = new JPanel();
            exceptionFrame.setIconImage(ImageIO.read(new File("G:\\Jobs\\Projects\\Image Editing Program\\ImageEditorIcon.png")));
            exceptionFrame.add(exceptionTextArea, BorderLayout.WEST);
            exceptionTextArea = new JTextArea(30,72);
            exceptionTextArea.setEditable(false);
            exceptionPanel.add(exceptionTextArea, BorderLayout.PAGE_START);
            String errorText = "";
            for(int i=0;i<errorsList.size();i++)
            {
                errorText += (i+1)+". "+errorsList.get(i)+"\n";
            }
            if(!errorText.equals(""))
            {
                exceptionTextArea.setText(errorText);
                exceptionFrame.pack();
                exceptionFrame.setLocationRelativeTo(null);
                exceptionFrame.setVisible(true);
                exceptionFrame.setResizable(false);
                exceptionFrame.setSize(600, 600);
            }
            else
                JOptionPane.showMessageDialog(exceptionFrame, "No Errors Yet!");
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(exceptionFrame, "No Errors Yet!");
        }
        catch(HeadlessException ex)
        {
            logException(ex.toString()); //Store exception in error ArrayList
        }
    }
}
