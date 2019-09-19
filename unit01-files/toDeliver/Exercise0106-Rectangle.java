// Adrián Navarro Gabino

/*
 * Create a program that asks the user for the number of rows and columns and
 * to create a rectangle of asterisks of that size in a file called
 * "rectangle.txt".
 */

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise0106
{
    public static void main(String[] args)
    {
        PrintWriter pw = null;
        
        try
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter height: ");
            int height = sc.nextInt();
            System.out.print("Enter width: ");
            int width = sc.nextInt();
            
            pw = new PrintWriter("rectangle.txt");
            
            for(int i = 0; i < height; i++)
            {
                for(int j = 0; j < width; j++)
                {
                    pw.print("*");
                }
                pw.println();
            }
            
            System.out.println("File created");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }
}
