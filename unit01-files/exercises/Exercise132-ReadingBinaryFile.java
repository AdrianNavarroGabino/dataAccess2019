//Adrián Navarro Gabino

/*
 * 1.3.2: Make a program that reads the contents of a binary file, showing on
 * the screen everything that is printable characters (it is enough that they
 * are from A to Z, along with the blank) and ignoring all other characters.
 * The name of the input file will be chosen by the user.
 */

import java.io.*;
import java.util.*;

class Exercise132
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

            int data = file.read();
            
            while(data != -1)
            {
                if(((char)data >= 'A' && (char)data <= 'Z') ||
                    ((char)data >= 'a' && (char)data <= 'z') ||
                    (char)data == ' ')
                {
                    System.out.print((char)data);
                }

                data = file.read();
            }
            
            file.close();
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}
