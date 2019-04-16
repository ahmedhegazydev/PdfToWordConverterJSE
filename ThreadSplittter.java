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
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author Eng Ahmed Hegazy
 */
public class ThreadSplittter implements Runnable {

    private static String lanForRecognize = "fra+eng+ara";
//    private static int dpi = 1100;
//        private static int dpi = 800;
            private static int dpi = 1000;
//    private static int dpi = 500;
    private static String fileExt = ".jpg";
    private static BufferedImage image;
    private static PDFRenderer pdfRenderer;
    private static PDDocument document;
    private static ITesseract instance = new Tesseract();
    private static File tessDataFolder;

    File fileForExtraction = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\Beyond-Profile.pdf");
    String strMainPath = "E:\\NetbeansProjects\\JavaApplication6\\src\\EXTRACTED_IMAGES\\image_extracted_";
    String tessDataFile = "E:\\NetbeansProjects\\JavaApplication6\\src\\tessdata\\";

    int from, to;

    public ThreadSplittter(int from, int to, ITesseract instance, PDFRenderer dFRende) throws IOException {

//        document = PDDocument.load(fileForExtraction);
//        dFRenderer = new PDFRenderer(document);
        this.document = document;
        this.pdfRenderer = dFRende;
        this.from = from;
        this.to = to;
        this.instance = instance;
    }

    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            try {
//                image = dFRenderer.renderImage(i);
                image = pdfRenderer.renderImageWithDPI(i, dpi);
//            ImageIO.write(image, "JPEG", new File(strMainPath + i + fileExt));         
//            File imageFile = new File(strMainPath + i + fileExt);
//            String result = instance.doOCR(imageFile);
////            imageFile.delete();
//            System.out.println(result);
//            System.out.println("Image created");
                new ThreadWriteImage(image, new File(strMainPath + i + fileExt), i, instance).run();
            } catch (IOException ex) {
                //Logger.getLogger(ThreadSplittter.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

}
