package ImagePackage;

import DebuggingPackage.DebuggingClass1;
//import static ImagePackage.ImageClass.toBufferedImage;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
import javax.swing.*;

public class ToolsClass extends JPanel{

        private int x1, y1, x2, y2, toolType;
        private Image loadedImage;
        private MouseListenerClass listener;
        public ToolsClass(Image image){
            try {
                loadedImage = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                listener = new MouseListenerClass();
                super.addMouseListener(listener);
                super.addMouseMotionListener(listener);
            } catch (Exception ex) {
                DebuggingClass1 err =  new DebuggingClass1();
                err.logException(ex.toString()); //Store exception in error ArrayList
            }
        }
        public void setToolType(int toolType) {
            this.toolType = toolType;
        }
        
        public void setStartPoint(int x, int y) {
            x1 = x;
            y1 = y;
        }
        
        public void setEndPoint(int x, int y) {
            x2 = (x);
            y2 = (y);
        }
        
        public int[] getCirclePoints()
        {
            int pw = Math.abs(x1-x2);
            int ph = Math.abs(y1-y2);
            return new int[]{x1, y1, x2, y2, pw, ph};
        }
        
        public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
            int px = Math.min(x,x2);
            int py = Math.min(y,y2);
            int pw=Math.abs(x-x2);
            int ph=Math.abs(y-y2);
            g.drawRect(px, py, pw, ph);
        }
        
        public void drawPerfectOval(Graphics g, int x, int y, int x2, int y2) {
            int px = Math.min(x,x2);
            int py = Math.min(y,y2);
            int pw=Math.abs(x-x2);
            int ph=Math.abs(y-y2);
            g.drawOval(px, py, pw, ph);
        }

        public Image getImage()
        {
            return loadedImage;
        }
        
        public void setImage(Image img)
        {
            loadedImage = img;
            toolType = 0;
//            loadImage(img);
            repaint();
        }
        
        public BufferedImage cropImage(BufferedImage bufferedImage) {
            if(toolType == 2)
            {
                int px = Math.min(x1,x2);
                int py = Math.min(y1,y2);
                int pw = Math.abs(x1-x2);
                int ph = Math.abs(y1-y2);
                BufferedImage circleBuffer = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);
                Graphics2D g2 = circleBuffer.createGraphics();
                g2.setClip(new Ellipse2D.Float(px, py, pw, ph));
                g2.drawImage(loadedImage, 0, 0, 500, 500, this);
                return circleBuffer.getSubimage(px, py, pw, ph);
            }
            else if(toolType == 1)
            {
                int px = Math.min(x1,x2);
                int py = Math.min(y1,y2);
                int pw=Math.abs(x1-x2);
                int ph=Math.abs(y1-y2);
//            System.out.println(px+" "+py+" "+pw+" "+ph);
//            ImageIO.write(croppedImage,"jpg", new File("C:\\Users\\egypt2\\Desktop\\test.jpg"));
                return bufferedImage.getSubimage(px, py, pw, ph);
            }
            return null;
        }
        
//        public BufferedImage loadImage(Image img)
//        {
//            BufferedImage imageBuffer = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);
//            Graphics2D g2d = imageBuffer.createGraphics();
//            if (loadedImage != null) {
//                g2    d.drawImage(loadedImage, 0, 0, this);
//            }
//            return imageBuffer;
//        }
                
        @Override
        public Dimension getPreferredSize() {
            return loadedImage == null ? new Dimension(500, 500) : new Dimension(500, 500);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (loadedImage != null) {
                g2d.drawImage(loadedImage, 0, 0, this);
            }
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            if(toolType == 1)
            {
                drawPerfectRect(g2d, x1, y1, x2, y2);
            }
            else if (toolType == 2)
            {
                drawPerfectOval(g2d, x1, y1, x2, y2);
            }
            g2d.dispose();
        }
        
    // Mouse listener class for painting
    private class MouseListenerClass extends MouseAdapter {

        public MouseListenerClass() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setStartPoint(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setEndPoint(e.getX(), e.getY());
            repaint();
        }
    }
}