// Adrián Navarro Gabino

/*
 * 1.1.2. Create a program that asks the user for phrases (a quantity
 * undetermined, until you enter an empty line) and store them
 * in a file called "sentences.txt". Each time the program is launched,
 * the file "sentences.txt" will be destroyed and a new one will be created to
 * replace it.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise112
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String sentence;
        
        PrintWriter pw = null;
        
        try
        {
            pw = new PrintWriter("sentences.txt");

            do
            {
                System.out.print("Introduce una frase: ");
                sentence = sc.nextLine();

                if(!sentence.equals(""))
                    pw.println(sentence);
            }while(!sentence.equals(""));
            
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
