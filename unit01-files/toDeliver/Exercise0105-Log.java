// Adrián Navarro Gabino

/*
 * Create a program that adds to a file "log.txt" a new line in each execution,
 * which will contain the date (in format 19-09-2019), the time (17:02:09) and
 * the text entered by the user.
 */

import java.io.*;
import java.util.*;

public class Exercise0105
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        Calendar date = Calendar.getInstance();
        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter(new BufferedWriter(
                                new FileWriter("log.txt", true)));
            
            pw.println(String.format("%02d", date.get(Calendar.DATE)) + "-" +
                String.format("%02d", (date.get(Calendar.MONTH) + 1)) + "-" +
                date.get(Calendar.YEAR) + " " +
                String.format("%02d", date.get(Calendar.HOUR)) + ":" +
                String.format("%02d", date.get(Calendar.MINUTE)) + ":" +
                String.format("%02d", date.get(Calendar.SECOND)) + " " +
                sentence);
            
            System.out.println("File created");
        }
        catch(IOException e)
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
