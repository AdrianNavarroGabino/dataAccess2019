//Adrián Navarro Gabino

/*
 * 1.4.3: Open an image in BMP format and check if it is compressed, looking
 * at the value of the byte at position 30 (starting from 0). If that value
 * is 0 (which is usual), it will indicate that the file is not compressed.
 * You should read the entire header (the first 54 bytes) with a single order.
 */

import java.io.*;
import java.util.*;

class Exercise143
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
        boolean isCompressed = true;

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

                    if(buf[30] == 0)
                        isCompressed = false;
                }
            }
            
            file.close();

            if(isValidBMP)
            {
                System.out.println("It seems a valid BMP file");
                
                if(isCompressed)
                {
                    System.out.println("It is compressed");
                }
                else
                {
                    System.out.println("It is NOT compressed");
                }
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
