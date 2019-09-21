// Adrián Navarro Gabino

/*
 * Create a program that reads the contents of a binary file, turning to
 * another (text) file all that are printable characters (from 32 to 127) and
 * ignoring all other characters. The names of both files can be prefixed.
 */

import java.io.*;
import java.util.*;

public class Exercise0111
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }
        try
        {
            FileInputStream file = new FileInputStream(
                new File(filename));
            PrintWriter pw = new PrintWriter(filename + ".txt");
            int data = file.read();
            while (data != -1)
            {
                if (data >= 32 && data <= 127)
                    pw.print(Character.toString((char) data));
                
                data = file.read();
            }
            
            pw.close();
            file.close();
            
            System.out.println("File created");
        }
        catch(Exception e)
        {
            System.out.println( "There were some problems: " +
                e.getMessage() );
        }
    }
}
