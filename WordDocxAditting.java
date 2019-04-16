/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import com.lowagie.text.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Eng Ahmed Hegazy
 */
public class WordDocxAditting {

    public static void main(String[] args) throws IOException {
//         XWPFDocument doc = new XWPFDocument(OPCPackage.open(fileLocationPath + "Document.doc"));
        String text = "الحمد لله رب العالمين الرحمن الرحيم مالك يوم الدين ";
        try {
            XWPFDocument doc = new XWPFDocument();
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\input.docx"))) {
                
                
                XWPFParagraph paragraph = doc.createParagraph();
                XWPFRun runText = paragraph.createRun();
                runText.setText(text);
                paragraph.setPageBreak(true);
                
                 XWPFParagraph paragraph2 = doc.createParagraph();
                XWPFRun runText2 = paragraph2.createRun();
                runText2.setText(text);
                paragraph2.setPageBreak(true);
//                
                
                
//                PrintStream out = new PrintStream(fileOutputStream, true, "UTF-8");
//                XWPFParagraph paragraph = doc.createParagraph();
//                XWPFRun runText = paragraph.createRun();
//                runText.setText(text);
//                paragraph.setPageBreak(true);
//                runText.setText("appending here");
//                runText.setText("الحمد لله رب العالمين الرحمن الرحيم مالك يوم الدين ");
//                StringBuilder reverseString = new StringBuilder();
//                String[] words = text.split(" ");       //step 1
//                for (String word : words) {
//                    String reverseWord = new StringBuilder(word).reverse().toString();      //step 2
//                    reverseString.append(reverseWord + " ");                                //step 3
////                    System.out.println(reverseString);
////                    out.println(reverseWord);
//                }
//                System.out.println(text);
//                runText.setText(text);
//                paragraph.setPageBreak(true);

//    List<XWPFParagraph>  paragraphs = doc.getParagraphs();
//    XWPFParagraph paragraph =  paragraphs.get(paragraphs.size() - 1);
//    XWPFRun runText = paragraph.createRun();
////if you want to add text
//    runText.setText("appending here");
////if you want to add image
//    runText.addPicture(InputStream pictureData, int pictureType, String filename, int width, int height)
//    try (FileOutputStream out = new FileOutputStream(fileLocationPath + "Document.doc")) {
                doc.write(fileOutputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordDocxAditting.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
