package javaapplication6;

import com.lowagie.text.Paragraph;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication6.StringAlignUtils.Alignment;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

public class TesseractExample3 {

    private static String lanForRecognize = "fra+eng+ara";
    private static ITesseract instance = new Tesseract();
    private static File tessDataFolder;
    private static String fileExt = ".jpg";
    private static File fileForExtraction = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\ddddddd.pdf");
    private static PDFRenderer dFRenderer;
    private static PDDocument document;

    public static void main(String[] args) throws IOException, TesseractException {
        String newline = System.getProperty("line.separator");
        document = PDDocument.load(fileForExtraction);
        dFRenderer = new PDFRenderer(document);
        String strMainPath = "E:\\NetbeansProjects\\JavaApplication6\\src\\EXTRACTED_IMAGES\\image_extracted_";
        String tessDataFile = "E:\\NetbeansProjects\\JavaApplication6\\src\\tessdata\\";
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
        Paragraph para = new Paragraph();
        XWPFDocument doc = new XWPFDocument();
        FileOutputStream fileOutputStream = null;
        File f;
        f = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\output4.docx");
//        f.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(f);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesseractExample2.class.getName()).log(Level.SEVERE, null, ex);
        }

        StringAlignUtils util = new StringAlignUtils(50, Alignment.LEFT);
        for (int i = 6; i < 8; i++) {
            File imageFile = new File(strMainPath + i + fileExt);
            String result = instance.doOCR(imageFile)
                    .replace("\n", newline);
            String withNewLines = instance.doOCR(imageFile);

            System.out.println(util.format(result));
            XWPFParagraph paragraph = doc.createParagraph();
            StringBuilder allData = new StringBuilder();
            String[] strings = result.split("\n");
            for (int counter = 0; counter < strings.length; counter++) {
                StringBuilder stringbuilder = new StringBuilder();
                String lineword[] = strings[counter].split(" ");
                XWPFRun runText = paragraph.createRun();
                runText.setFontSize(15);
                runText.setFontFamily("Calibri (Corps)");
                runText.setBold(true);
               
                for (int k = lineword.length - 1; k >= 0; k--) {
//                    if (!lineword[k].trim().isEmpty()) {
//                    }
//                    System.out.println("index : " + k + "\t\t word:" + lineword[k]);
                    stringbuilder.append(lineword[k] + " ");

                }
                runText.setText(stringbuilder.toString());

                allData.append(stringbuilder.toString());
//                allData.append("\r\n" + newline);
                runText.addBreak();
//                 runText.addBreak(BreakType.COLUMN);
            }

            paragraph.setPageBreak(true);

            setOrientation(paragraph, TextOrientation.RTL);
//            fileOutputStream.close();
            //--------------------------
//            String newline = System.getProperty("line.separator");
//            boolean hasNewline = result.contains(newline);
//            System.out.println(hasNewline);
            //--------------------------
//            runText2.setText(result);
//            String[] lines = paragraph2.getParagraphText().split("\n");
//            StringBuilder input1 = new StringBuilder();
//            for (String line : lines) {         
//               input1.append(line);
//               input1.reverse();
//               res += input1.toString() + newline;
//                System.out.println(" ====================" + input1);
//            }
            //-------------------------
//            runText2.setText(res);
//            res = "";
//            res = "";
//            runText2.setColor("003894");
//            runText2.setAlignment(ParagraphAlignment.CENTER);
//            runText2.setUnderline(UnderlinePatterns.SINGLE);
//            runText2.setSpacingAfter(300);
//            setOrientation(paragraph2, TextOrientation.RTL);
//            setOrientation(paragraph2, TextOrientation.LTR);
        }
        doc.write(fileOutputStream);
        document.close();

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
