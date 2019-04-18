/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author Eng Ahmed Hegazy
 */
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.io.FileInputStream;
import org.docx4j.Docx4J;

public class WordTest {

    public static final String input_DOCX = "E:\\Temp5\\Word document template.docx";

    public static final String input_XML = "E:\\Temp5\\Word document data.xml";

    public static final String output_DOCX = "E:\\Temp5\\Word document output.docx";

    public static void main(String[] args) throws Exception {
//        WordprocessingMLPackage wordMLPackage = Docx4J.load(new File(input_DOCX));
//        FileInputStream xmlStream = new FileInputStream(new File(input_XML));
//        Docx4J.bind(wordMLPackage, xmlStream, Docx4J.FLAG_BIND_INSERT_XML | Docx4J.FLAG_BIND_BIND_XML | Docx4J.FLAG_BIND_REMOVE_SDT);
//        Docx4J.save(wordMLPackage, new File(output_DOCX), Docx4J.FLAG_NONE);
//        System.out.println("Saved: " + output_DOCX);
    }

}