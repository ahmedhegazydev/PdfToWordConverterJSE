/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Eng Ahmed Hegazy
 */
public class lineWordsRevers {
    
    public static void main(String []args) throws IOException {
      File file = new File("E:\\NetbeansProjects\\JavaApplication6\\src\\files\\text.txt");
      
      // Create a File
      file.createNewFile();
      
      //  Creates a FileWriter Object using file object
      FileWriter writer = new FileWriter(file); 
      
      // Writes the content to the file
      writer.write("احمد عليوه ");
          writer.write("احمد عليوه ");

      
      // Flush the memory and close the file
      writer.flush();
      writer.close();
      
      // Creates a FileReader Object
      FileReader reader = new FileReader(file); 
      char [] a = new char[100];
      
      // Read file content in the array
      reader.read(a);
      System.out.println( a );
      
      // Close the file
      reader.close();
   }
    
}
