// Adrián Navarro Gabino

/*
 * Create a program that asks for the name of a file (looking at args or asking
 * the user, if there are no args) and say if it looks like a valid BMP,
 * checking its first two bytes (its first two bytes are B and M).
 */

import java.io.*;
import java.util.*;

public class Exercise0114
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

            char firstChar = (char) file.read();
            char secondChar = (char) file.read();
            
            if(firstChar == 'B' && secondChar == 'M')
                System.out.println("It seems a valid BMP file");
            else
                System.out.println("It isn't a BMP file");

            file.close();
        }
        catch(Exception e)
        {
            System.out.println( "There were some problems: " +
                e.getMessage() );
        }
    }
}
