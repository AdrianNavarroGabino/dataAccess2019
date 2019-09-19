// Adrián Navarro Gabino

/*
 * Create a program that spreads in a "triangle.txt" file a growing triangle of
 * asterisks, with the height and height chosen by the user.
 */

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise0103
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter width: ");
        int width = sc.nextInt();
        
        PrintWriter pw = new PrintWriter("triangle.txt");
        
        for(int i = 1; i <= width; i++)
        {
            for(int j = 0; j < i; j++)
                pw.print("*");
            pw.println();
        }
        
        pw.close();
        
        System.out.println("File created");
    }
}
