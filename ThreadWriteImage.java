/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author Eng Ahmed Hegazy
 */
public class ThreadWriteImage
        //        extends Thread
        implements Runnable {

    BufferedImage bufferedImage;
    File file;
    private static ITesseract instance = null;
    String strMainPath = "E:\\NetbeansProjects\\JavaApplication6\\src\\EXTRACTED_IMAGES\\image_extracted_";
    private static String fileExt = ".jpg";
    public int pageNumber = 0;
    String result;
    private static String lanForRecognize = "fra+eng+ara";
    private static int dpi = 500;
    private static BufferedImage image;
    private static PDFRenderer dFRenderer;
    private static PDDocument document;
    private static File tessDataFolder;
    String tessDataFile = "E:\\NetbeansProjects\\JavaApplication6\\src\\tessdata\\";

    public ThreadWriteImage(BufferedImage bufferedImage, File file) {
        this.bufferedImage = bufferedImage;
        this.file = file;
    }

    public ThreadWriteImage(BufferedImage bufferedImage, File file, int pageNumber) {

        instance.setLanguage(lanForRecognize);
        tessDataFolder = new File(tessDataFile); // Maven build bundles English data
        instance.setDatapath(tessDataFolder.getPath());

        this.bufferedImage = bufferedImage;
        this.file = file;
        this.pageNumber = pageNumber;
    }

    ThreadWriteImage(BufferedImage image, int i) {

//        instance.setLanguage(lanForRecognize);
//        tessDataFolder = new File(tessDataFile); // Maven build bundles English data
//        instance.setDatapath(tessDataFolder.getPath());
        this.bufferedImage = bufferedImage;
//        this.file = file;
        this.pageNumber = pageNumber;

    }

    ThreadWriteImage(BufferedImage image, File file, int i, ITesseract instance) {
        this.bufferedImage = image;
        this.file = file;
        this.pageNumber = i;
        this.instance = instance;
    }

    @Override
    public void run() {
        try {
//            super.run(); //To change body of generated methods, choose Tools | Templates.
//            if (ImageIO.write(bufferedImage, "JPEG", file)) {
//                if (ImageIO.write(bufferedImage, "png", file)) {
            if (ImageIO.write(bufferedImage, "JPEG", file)) {
                System.out.println("imgage created");
//                File imageFile = new File(strMainPath + pageNumber + fileExt);
////                 new Runnable() {
////                       @Override
////                       public void run() {
//                try {
//                    result = instance.doOCR(imageFile);
//////                Thread.sleep(1000);
//                } catch (TesseractException ex) {
////                    Logger.getLogger(ThreadWriteImage.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                }
//                       }
//                   }.run();
//            imageFile.delete();
//                System.out.println(result);
                bufferedImage.flush();
//                       }
//                   }.run();
//            imageFile.delete();
//                System.out.println(result);
//            bufferedImage.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadWriteImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
