
package javaapplication1;  /*you can remove this if you're not using packages*/

/**
 *
 * @author kosyn_000
 */
import java.io.*;
import java.util.*;
import myclasses.worker; /*you can remove this if you're not using packages*/

	
public class WordFrequency{
    public static void main(String[] args) throws IOException{
        File folder = new File("C:/Users/kosyn_000/Dropbox/Java/Help/files");
        
        if (!folder.exists()) {   /*check if folder exists*/
            System.out.println("Directory Doesn't exist");
        }
        else{ /*main function*/
            File[] filelist = folder.listFiles();
            int total_files = filelist.length; //total number of files
            Thread c = new Thread(new worker(filelist,total_files, "c")); /*instantiate thread*/
            Thread c2 = new Thread(new worker(filelist,total_files, "c2"));
            c.start();
            c2.start();
            //	c2.start();
        }   
    }
}
