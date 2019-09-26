//Adrián Navarro Gabino

/*
 * 1.3.1: Prepare a program that checks if an EXE file seems valid: you must
 * verify that your first byte has the value 0x4D and the second is a 0x5A.
 */

import java.io.*;
import java.util.*;

class Exercise131
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

            byte data1 = (byte)file.read();
            byte data2 = (byte)file.read();
            
            file.close();

            if(data1 == 0x4D && data2 == 0x5A)
            {
                System.out.println("It seems a valid EXE file");
            }
            else
            {
                System.out.println("It is NOT an EXE file");
            }
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}
