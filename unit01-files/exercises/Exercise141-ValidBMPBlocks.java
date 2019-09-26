//Adrián Navarro Gabino

/*
 * 1.4.1: Create a program that reads the first 54 bytes of a BMP file
 * (its header) and check if the first two bytes of those 54 correspond
 * to the letters B and M. If not, it will display the message
 * "It is not a valid BMP"; if they are, it will write the message
 * "It looks like a valid BMP".
 */

import java.io.*;
import java.util.*;

class Exercise141
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

        final int BUFFER_SIZE = 54;
        boolean isValidBMP = false;

        try
        {
            InputStream file =
                new FileInputStream( new File(filename));
            
            byte[] buf = new byte[BUFFER_SIZE];
            int amountRead = file.read(buf, 0, BUFFER_SIZE);
            
            if(amountRead == 54)
            {
                if((char)buf[0] >= 'B' && (char)buf[1] <= 'M')
                {
                    isValidBMP = true;
                }
            }
            
            file.close();

            if(isValidBMP)
            {
                System.out.println("It seems a valid BMP file");
            }
            else
            {
                System.out.println("It is NOT a valid BMP file");
            }
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}
