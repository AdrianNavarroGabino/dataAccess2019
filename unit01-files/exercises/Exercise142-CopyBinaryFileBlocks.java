//Adrián Navarro Gabino

/*
 * 1.4.2: Create an alternative version of Exercise 1.3.3, reading the data to
 * an array instead of working byte by byte: a program that extracts the
 * contents of a binary file, turning everything that is characters into a text
 * file Printables (just be from A to Z, along with the blank).
 */

import java.io.*;
import java.util.*;

class Exercise142
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        final int BUFFER_SIZE = 512 * 1024;

        try
        {
            InputStream fileIn =
                new FileInputStream( new File(filename));
            OutputStream fileOut =
                new FileOutputStream( new File(filename + ".txt"));
            
            byte[] buf = new byte[BUFFER_SIZE];
            int amountRead;
            
            while((amountRead = fileIn.read(buf, 0,
                BUFFER_SIZE)) > 0)
            {
                for(int i = 0; i < amountRead; i++)
                {
                    if(((char)buf[i] >= 'A' && (char)buf[i] <= 'Z') ||
                        ((char)buf[i] >= 'a' && (char)buf[i] <= 'z') ||
                        (char)buf[i] == ' ')
                    {
                        fileOut.write(buf[i]);
                    }
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
