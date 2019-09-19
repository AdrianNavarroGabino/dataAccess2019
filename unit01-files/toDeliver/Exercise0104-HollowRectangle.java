// Adrián Navarro Gabino

/*
 * Create a program that writes in a "rectangle.txt" file a hollow rectangle of
 * asterisks, with the width and height chosen by the user.
 */

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise0104
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
            
            for(int i = 0; i < width; i++)
            {
                pw.print("*");
            }
            pw.println();
            
            for(int i = 2; i < height; i++)
            {
                pw.print("*");
                for(int j = 2; j < width; j++)
                {
                    pw.print(" ");
                }
                pw.println("*");
            }
            
            for(int i = 0; i < width; i++)
            {
                pw.print("*");
            }
            pw.println();
            
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
