/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzcrypt;

/**
 *
 * @author Beerbuzz02
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
public class BuzzCrypt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("keyfile.txt"))) {
   String line = null;
   while ((line = br.readLine()) != null) {
       System.out.println(line);
   }
    }
    }
}

    
    

