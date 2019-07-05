/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImagePackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.*;

/**
 *
 * @author Ahmed Hassan
 */
public class FiltersClass {
    
    public BufferedImage blurImage(BufferedImage BI)
    {
        Kernel k = new Kernel(3, 3, new float[]{1f/(3*3),1f/(3*3),1f/(3*3),
                                                1f/(3*3),1f/(3*3),1f/(3*3),
                                                1f/(3*3),1f/(3*3),1f/(3*3)});
        ConvolveOp op = new ConvolveOp(k);
         return op.filter(BI, null);
    }

    /**
     *
     * @param BI
     * @return
     */
    public Image lightenImage(BufferedImage BI)
    {
        RescaleOp op = new RescaleOp(2f, 0, null);
        return op.filter(BI, null);
    }
    public Image darkenImage(BufferedImage BI)
    {
        RescaleOp op = new RescaleOp(.5f, 0, null);
        return op.filter(BI, null);
    }
    public Image invertImage(BufferedImage BI)
    {
//        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
//        tx.translate(-BI.getWidth(null), 0);
//        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//        return op.filter(BI, null);
        for (int x = 0; x < BI.getWidth(); x++) {
            for (int y = 0; y < BI.getHeight(); y++) {
                int rgba = BI.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                BI.setRGB(x, y, col.getRGB());
            }
        }
        return BI;
    }
}
