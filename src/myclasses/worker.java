
package myclasses;  /*you can remove this if you're not using packages*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class worker implements Runnable { /*implement runnable is better...you can google why*/
    FileInputStream file;
    File[] filelist;
    int proc_count = GlobalVars.proc_count;
    int total;
    String workername;
    /* Constructor.   */
    public worker(File[] myfiles, int total, String workername) {
    this.filelist = myfiles;  //file list
    this.total = total; //total number of files
    this.workername = workername;
    }
    @Override
	public void run() {
        while(proc_count <= total){  //while there are still files to be processed
        try {
            ArrayList<Integer> isdone =  GlobalVars.processed; //list of processed files in global class
            Random rdm = new Random();
            int list_size = rdm.nextInt(filelist.length);
            while(isdone.contains(list_size)){  //check if random file has been processed
                list_size = rdm.nextInt(filelist.length); 
            }
            isdone.add(list_size); //add the file number to the array
            GlobalVars.proc_count = GlobalVars.proc_count + 1; //increment the number of processed files
            file = new FileInputStream(filelist[list_size]); //get the random file
            System.out.println("Worker Working: " + workername);
            System.out.println("File being read is " + filelist[list_size]);// + "   run number: " + GlobalVars.proc_count);
            Scanner fileinput = new Scanner(file);
        //create array list , one to hold words, and one to hold count			 
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        
        //read through file and find words
			
        while(fileinput.hasNext()){
            //get the next word
            String nextword = fileinput.next();
            //Determine if word is in  the array list
            if (words.contains(nextword)) {
                int index = words.indexOf(nextword);
                count.set(index, count.get(index) + 1);
            }
            else {
                words.add(nextword);
                count.add(1);
            }
        }
        //close streams
        fileinput.close();
        try{
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //print result
        for (int j = 0; j < words.size(); j++){
            System.out.println(" '" +words.get(j) + "' occurred " + count.get(j) + " time(s)");
        }
        } catch (FileNotFoundException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
        }
}
