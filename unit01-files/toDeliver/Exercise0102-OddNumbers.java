// Adrián Navarro Gabino

/*
 * Create a program that writes odd numbers from 1 to 10 in a text file, each
 * in a line.
 */

import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Exercise0102
{
    public static void main(String[] args)
    {
        try
        {
            PrintWriter pw = new PrintWriter("numbers.txt");
            
            for(int i = 1; i <= 10; i++)
                if(i % 2 == 1)
                    pw.println(i);
            
            pw.close();
            
            System.out.println("File created");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
