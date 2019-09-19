// Adrián Navarro Gabino

/*
 * 1.1.3. Create a variant of exercise 1.1.2, in which the file is
 * will call "annotations.txt" and will not be destroyed in each new execution,
 * but that the new phrases will be added at the end of the existing ones.
 */
 
import java.io.*;
import java.util.Scanner;

public class Exercise113
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String sentence;
        
        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter(new BufferedWriter(
                                    new FileWriter("annotations.txt", true)));

            do
            {
                System.out.print("Enter a sentence: ");
                sentence = sc.nextLine();

                if(!sentence.equals(""))
                    pw.println(sentence);
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
