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
public class revers_word {
    
    public static void main(String[] args) {
        
        
        String s = "Ahmed          Mohammed        Ali";
        StringBuilder stringbuilder = new StringBuilder();
        String[] strings = s.split(" ");
        for (int i = strings.length - 1; i >= 0 ; i--) {
            stringbuilder.append(strings[i]+" ");
        }
        System.out.println(stringbuilder.toString());
    }
    
}
