/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ThreadSplittter implements Runnable {

    private static int dpi = 800;
    private static String fileExt = ".jpg";
    private static BufferedImage image;
    private static PDFRenderer pdfRenderer;
    private static PDDocument document;
    private static ITesseract instance = new Tesseract();
    private static File tessDataFolder;

    File fileForExtraction = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\Beyond-Profile.pdf");
    String strMainPath = "E:\\NetbeansProjects\\JavaApplication6\\src\\EXTRACTED_IMAGES\\image_extracted_";
    int from, to;

//    public ThreadSplittter(int from, int to, ITesseract instance, PDFRenderer dFRende) throws IOException {
//
//        
//        dFRenderer = new PDFRenderer(document);
//        this.document = document;
//        this.pdfRenderer = dFRende;
//        this.from = from;
//        this.to = to;
//        this.instance = instance;
//    }
//    public ThreadSplittter(int from, int to) {
//        this.from = from;
//        this.to = to;
//
//    }
    public ThreadSplittter(int start, int to, PDFRenderer pdfRenderer) {
        this.pdfRenderer = pdfRenderer;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            try {
//                image = dFRenderer.renderImage(i);
                image = pdfRenderer.renderImageWithDPI(i, dpi);
                ImageIO.write(image, "JPEG", new File(strMainPath + i + fileExt));
//            File imageFile = new File(strMainPath + i + fileExt);
//            String result = instance.doOCR(imageFile);
////            imageFile.delete();
//            System.out.println(result);
//            System.out.println("Image created");
//                new ThreadWriteImage(image, new File(strMainPath + i + fileExt), i, instance).run();
            } catch (IOException ex) {
                //Logger.getLogger(ThreadSplittter.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

    public static BufferedImage convertRenderedImage(RenderedImage img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
        img.copyData(raster);
        return result;
    }
}
