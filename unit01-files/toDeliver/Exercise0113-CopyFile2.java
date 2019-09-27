//Adrián Navarro Gabino

/*
 * Create a Java program capable of splitting a file into pieces of a preset
 * size. The names of both files and the size of the fragments can be prefixed
 * or requested from the user, as you prefer.
 */

import java.io.*;
import java.util.*;

class Exercise0113
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("File in: ");
        String filenameIn = sc.nextLine();
        System.out.print("File out: ");
        String filenameOut = sc.nextLine();
        System.out.print("Fragment size (Bytes): ");
        int bytes = sc.nextInt();

        if (! (new File(filenameIn)).exists() ) {
            System.out.println(filenameIn + " not found");
            return;
        }

        final int BUFFER_SIZE = bytes;

        try
        {
            InputStream fileIn =
                new FileInputStream( new File(filenameIn));
            OutputStream fileOut =
                new FileOutputStream( new File(filenameOut));
            
            byte[] buf = new byte[BUFFER_SIZE];
            int amountRead;
            
            while((amountRead = fileIn.read(buf, 0,
                BUFFER_SIZE)) > 0)
            {
                for(int i = 0; i < amountRead; i++)
                {
                    fileOut.write(buf[i]);
                }
            }
                
            fileOut.close();
            fileIn.close();

            System.out.println("File copied");
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}