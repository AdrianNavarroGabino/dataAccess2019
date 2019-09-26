//Adrián Navarro Gabino

/*
 * 1.3.3: Create a program that extracts the contents of a binary file,
 * turning to a text file all that are printable characters (it is enough
 * that they are from A to Z, along with the blank) and ignoring all The
 * other characters. The name of the input file will be chosen by the user
 * and the output file will have the same name, but ending in ".txt" (for
 * example, if the original file was "1.zip", the output file will be
 * "1.zip .txt").
 */

import java.io.*;
import java.util.*;

class Exercise133
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
        try
        {
            FileInputStream file = 
                new FileInputStream(new File(filename));
            PrintWriter pw = new PrintWriter(filename + ".txt");

            int data = file.read();
            
            while(data != -1)
            {
                if(((char)data >= 'A' && (char)data <= 'Z') ||
                    ((char)data >= 'a' && (char)data <= 'z') ||
                    (char)data == ' ')
                {
                    pw.print((char)data);
                }

                data = file.read();
            }
            
            pw.close();
            file.close();
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}
