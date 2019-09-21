// Adrián Navarro Gabino

/*
 * 1.2.1: Create a program that shows the first line of the content of the
 * "twoSentences.txt" file.
 */
 
import java.io.*;

public class Exercise121
{
    public static void main(String[] args)
    {
        if (! (new File("twoSentences.txt")).exists() ) {
            System.out.println("twoSentences.txt not found");
            return;
        }
        try
        {
            BufferedReader file = new BufferedReader(
                new FileReader(new File("twoSentences.txt")));
            String line = file.readLine();
            
            System.out.println(line);

            file.close();
        }
        catch(Exception e)
        {
            System.out.println( "There were some problems: " +
                e.getMessage() );
        }
    }
}
