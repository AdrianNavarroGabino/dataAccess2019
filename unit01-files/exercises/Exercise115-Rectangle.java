// Adrián Navarro Gabino

/*
 * 1.1.5. Create a program that asks the user for a width and height and create
 * a file called "rectangle.txt" that contains an asterisk rectangle
 * of that width and height.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise115
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter height: ");
        int height = sc.nextInt();
        System.out.print("Enter width: ");
        int width = sc.nextInt();

        PrintWriter pw = null;

        try
        {
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
