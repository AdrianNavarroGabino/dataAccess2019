//Adrián Navarro Gabino

/*
 * Create a program that asks the user for the name of a file and says if it
 * looks like a valid BMP (its first two bytes are B and M) and, in that case,
 * its width and height. You must read by blocks (the header is a block of 54
 * bytes).
 */

import java.io.*;
import java.util.*;

class Exercise0119
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
                    System.out.println("It seems a valid BMP file");

                    byte[] auxWidth = {buf[18], buf[19], buf[20], buf[21]};
                    System.out.println("Width: " + readLittleEndianInteger(auxWidth));
                    byte[] auxHeight = {buf[22], buf[23], buf[24], buf[25]};
                    System.out.println("Height: " + readLittleEndianInteger(auxHeight));
                }
            }
            
            file.close();

            if(!isValidBMP)
            {
                System.out.println("It is NOT a valid BMP file");
            }
        }
        catch (Exception e)
        {
            System.out.println("There were some problems: " + e.getMessage());
        }
    }

    public static int readLittleEndianInteger(byte[] buffer)
                                          throws IOException {
        int result = (buffer[3] << 24) | (buffer[2] << 16) |
                    (buffer[1] << 8)  | buffer[0];
        return result;
    }
}