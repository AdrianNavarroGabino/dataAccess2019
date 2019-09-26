//Adrián Navarro Gabino

/*
 * 1.3.4: Open a GIF image and check if its first three bytes are the letters
 * G, I, F.
 */

import java.io.*;
import java.util.*;

class Exercise134
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

            char data1 = (char)file.read();
            char data2 = (char)file.read();
            char data3 = (char)file.read();
            
            file.close();

            if(data1 == 'G' && data2 == 'I' && data3 == 'F')
            {
                System.out.println("It seems a valid GIF file");
            }
            else
            {
                System.out.println("It is NOT an GIF file");
            }
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}
