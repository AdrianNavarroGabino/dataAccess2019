// Adrián Navarro Gabino

/*
 * 1.1.4. Create an improved version of Exercise 1.1.3, in which before
 * each sentence will record the date and time at which it was made
 * annotation.
 */

import java.util.*;
import java.io.*;

public class Exercise114
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String sentence;
            
        PrintWriter pw = null;
        Calendar date;

        try
        {
            pw = new PrintWriter(new BufferedWriter(
                                    new FileWriter("annotations.txt", true)));

            do
            {
                System.out.print("Enter a sentence: ");
                sentence = sc.nextLine();

                if(!sentence.equals(""))
                {
                    date = Calendar.getInstance();
                    pw.println(date.get(Calendar.DATE) + "/" +
                        date.get(Calendar.MONTH) + "/" +
                        date.get(Calendar.YEAR) + " " +
                        date.get(Calendar.HOUR) + ":" +
                        date.get(Calendar.MINUTE) + ":" +
                        date.get(Calendar.SECOND) + " - " + sentence);
                }
            }while(!sentence.equals(""));

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
