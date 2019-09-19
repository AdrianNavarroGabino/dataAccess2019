// Adrián Navarro Gabino

/*
 * 1.1.1. Create a program that asks the user for two sentences and saves them
 * in a file called "twoSentences.txt".
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise111
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String firstSentence = sc.nextLine();
        System.out.print("Enter another sentence: ");
        String secondSentence = sc.nextLine();
        
        PrintWriter pw = null;
        
        try
        {
            pw = new PrintWriter("twoSentences.txt");
            pw.println(firstSentence);
            pw.println(secondSentence);

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
