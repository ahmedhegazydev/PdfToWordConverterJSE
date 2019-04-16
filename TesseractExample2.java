/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import com.lowagie.text.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

/**
 *
 * @author Eng Ahmed Hegazy
 */
public class TesseractExample2 {

//    private static String lanForRecognize = "ara";
    private static String lanForRecognize = "fra+eng+ara";
    private static int dpi = 1000;
//    private static int dpi = 500;
    private static String fileExt = ".jpg";
    private static BufferedImage image;
    private static PDFRenderer dFRenderer;
    private static PDDocument document;
    private static ITesseract instance = new Tesseract();
    private static File tessDataFolder;

    public static void main(String[] args) throws IOException, TesseractException {

//       System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
//        File file = new File("C:\\Users\\user\\Downloads\\OCR.pdf");
        File fileForExtraction = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\ddddddd.pdf");
        String strMainPath = "E:\\NetbeansProjects\\JavaApplication6\\src\\EXTRACTED_IMAGES\\image_extracted_";
        String tessDataFile = "E:\\NetbeansProjects\\JavaApplication6\\src\\tessdata\\";
        document = PDDocument.load(fileForExtraction);
        dFRenderer = new PDFRenderer(document);
//       System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
//        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setLanguage(lanForRecognize);
        tessDataFolder = new File(tessDataFile); // Maven build bundles English data
        instance.setDatapath(tessDataFolder.getPath());
//        use PDFTextStripper() to get Text from pdf
//        String text = new PDFTextStripper().getText(document);
//        System.out.println(text);
//        Rendering an image from the PDF document 

        int total = document.getNumberOfPages();

        XWPFDocument doc = new XWPFDocument();
        FileOutputStream fileOutputStream = null;
        File f;
        f = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\output3.docx");
//        f.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < total; i++) {
//            image = dFRenderer.renderImage(i);
//            image = dFRenderer.renderImageWithDPI(i, dpi);
//            ImageIO.write(image, "JPEG", new File(strMainPath + i + fileExt));
            File imageFile = new File(strMainPath + i + fileExt);
            String result = null;
            try {
                result = instance.doOCR(imageFile);
            } catch (TesseractException ex) {
                Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
            }
//            imageFile.delete();
            System.out.println(result);
            XWPFParagraph paragraph2 = doc.createParagraph();
            XWPFRun runText2 = paragraph2.createRun();
            runText2.setFontSize(18);
            runText2.setFontFamily("Calibri (Corps)");
            runText2.setBold(true);
            String res = "";
            for (int j = 0; j < result.length(); j++) {
               res +=   result.charAt(j) + '\u200e'; 
//                System.out.println(result.charAt(j));
            }
            runText2.setText(res);
            res = "";
            runText2.setColor("003894");
//            runText2.setAlignment(ParagraphAlignment.CENTER);
            runText2.setUnderline(UnderlinePatterns.SINGLE);
//            runText2.setSpacingAfter(300);
            setOrientation(paragraph2, TextOrientation.RTL);

            paragraph2.setPageBreak(true);
//--------------------------------------------
//            System.out.println("Image created");
//            new ThreadWriteImage(image, new File(strMainPath + i + fileExt), i).run();
        }
        try {
            doc.write(fileOutputStream);
        } catch (IOException ex) {
            Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
        }

//        new ThreadSplittter(0, 3).run();
//        new ThreadSplittter(3, 6).run();
//         new ThreadSplittter(6 , 10).run();
//----------------------------------------------
//        ExecutorService executors = Executors.newFixedThreadPool(3);
//        executors.execute(new ThreadSplittter(0, 3));
//        executors.execute(new ThreadSplittter(3, 6));
//        executors.execute(new ThreadSplittter(6, 10));
//        executors.shutdown();
//------------------------------------------------------
//        boolean odd = false, flag = true;
//        if (total % 2 == 0) {//odd
//            odd = true;
//        } else {
//            odd = false;
//        }
//        int start = 0, to = 0, count = 0;
//        int factor = 8;
//        for (; true;) {
//            count++;
//            if (to < total) {
//                start = to;
//                to += factor;
////                executorService.execute(new ThreadSplittter(start, to));
//            } else {
////                 executorService.execute(new ThreadSplittter(start, total));
//                break;
//            }
//        }
//        System.out.println("count = " + count);
//        start = to = 0;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(count);
//        for (; true;) {
//            start = to;
//            to += factor;
//            if (to < total) {
//                System.out.println("start = " + start + " to = " + to);
//                executorService.execute(new ThreadSplittter(start, to, instance, dFRenderer));
//            } else {
//                to = total;
//                System.out.println("rr start = " + start + " to = " + to);
//                executorService.execute(new ThreadSplittter(start, total, instance, dFRenderer));
//                break;
//            }
//        }
//        executorService.shutdown();
//        while (true) {
//            System.out.println("not terminated");
//            return;
//        }
//       
//        document.close();
//-----------------------   ``-------------------------------
//        ExecutorService executorService = Executors.newFixedThreadPool(total);
//        for (int i = 0; i < total; i++) {
//            start = to;
//            to++;
//            if (to < total) {
//                executorService.execute(new ThreadSplittter(start, to));
//            }
//        }
//        executorService.shutdown();
        //------------------------------------------------------   
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                XWPFDocument doc = new XWPFDocument();
//                FileOutputStream fileOutputStream = null;
//                File f;
//                f = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\input.docx");
//                f.deleteOnExit();
//                try {
//                    fileOutputStream = new FileOutputStream(f);
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                for (int i = 0; i < total; i++) {
////            image = dFRenderer.renderImage(i);
////             image = dFRenderer.renderImageWithDPI(i, dpi);
////            ImageIO.write(image, "JPEG", new File(strMainPath + i + fileExt));         
//                    File imageFile = new File(strMainPath + i + fileExt);
//                    String result = null;
//                    try {
//                        result = instance.doOCR(imageFile);
//                    } catch (TesseractException ex) {
//                        Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
//                    }
////            imageFile.delete();
//                    System.out.println(result);
//                    XWPFParagraph paragraph2 = doc.createParagraph();
//                    XWPFRun runText2 = paragraph2.createRun();
//                    runText2.setText(result);
//                    paragraph2.setPageBreak(true);
////            System.out.println("Image created");
////            new ThreadWriteImage(image, new File(strMainPath + i + fileExt), i).run();
//                }
//                try {
//                    doc.write(fileOutputStream);
//                } catch (IOException ex) {
//                    Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        }, 30000);
////      Closing the document 
//        document.close();
//        try {
//            for (int i = 0; i < 5; i++) {
//                File imageFile = new File(strMainPath + i + fileExt);
//                String result = instance.doOCR(imageFile);
//                imageFile.delete();
//                System.out.println(result);
//
//            }
//
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }
//           ExtractImage image = new ExtractImage();
//           ExtractText text = new ExtractText();
//           image.start();
//           text.start();
//            Thread t = new Thread(new ExtractImage());
//            Thread t2 = new Thread(new ExtractText());
//            t.start();
//            t2.start();
    }

    private static void setOrientation(XWPFParagraph par, TextOrientation orientation) {
        if (par.getCTP().getPPr() == null) {
            par.getCTP().addNewPPr();
        }
        if (par.getCTP().getPPr().getBidi() == null) {
            par.getCTP().getPPr().addNewBidi();
        }
        par.getCTP().getPPr().getBidi().setVal(orientation == TextOrientation.RTL ? STOnOff.ON : STOnOff.OFF);
    }

    public enum TextOrientation {
        LTR,
        RTL
    }

}
