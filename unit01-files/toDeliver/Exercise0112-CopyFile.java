// Adrián Navarro Gabino

/*
 * Create a Java program capable of copying the entire contents of a file into
 * another file. You must read the entire initial file and save it in a single
 * block that will have the same file size. The names of both files can be
 * prefixed or requested from the user, as you prefer.
 */

import java.io.*;
import java.util.Scanner;

public class Exercise0112
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filenameIn = sc.nextLine();
        System.out.print("Where do you want to copy the file? ");
        String filenameOut = sc.nextLine();
        
        final int BUFFER_SIZE = 512*1024;
        
        try
        {
            InputStream fileIn = new FileInputStream( new File(filenameIn));
            OutputStream fileOut = new FileOutputStream( new File(filenameOut));
            byte[] buf = new byte[BUFFER_SIZE];
            int amountRead;
            while((amountRead = fileIn.read(buf, 0, BUFFER_SIZE)) > 0)
            {
                fileOut.write(buf, 0, amountRead);
            }
            
            fileIn.close();
            fileOut.close();
            
            System.out.println("File copied");
        }
        catch(Exception e)
        {
            System.out.println("There were some problems: " +
                e.getMessage());
        }
    }
}
