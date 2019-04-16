/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import com.lowagie.text.Document;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class TesseractExample {

    public static void main(String[] args) throws IOException {
        // System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
       
        File imageFile = new File("C:\\Users\\user\\Desktop\\Capture.JPG");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setLanguage("ara");

        File tessDataFolder = new File("E:\\NetbeansProjects\\JavaApplication6\\tessdata"); // Maven build bundles English data
        instance.setDatapath(tessDataFolder.getPath());

        try {
            String result = instance.doOCR(imageFile);
//            Document doc = new Document();
//            FileOutputStream file = new FileOutputStream("file.docx");
//             doc.open();

            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
