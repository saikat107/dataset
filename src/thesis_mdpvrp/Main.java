/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thesis_mdpvrp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author define
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           
            PrintStream pout=new PrintStream("out.txt");
            pout.println();
            pout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO code application logic here
        new dataConv();
    }

}
