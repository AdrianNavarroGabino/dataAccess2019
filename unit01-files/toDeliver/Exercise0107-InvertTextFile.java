// Adrián Navarro Gabino

/*
 * Create a program that asks the user for the name of a text file and dump its
 * contents to another file, but reversing the order of the lines (the last
 * will become the first, the second to last will be the second and so on).
 */

import java.io.*;
import java.util.*;

public class Exercise0107
{
    public static void main(String[] args)
    {
        List<String> text = new ArrayList<String>();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File(filename)));
            String line = file.readLine();
            while (line != null) {
                text.add(line);
                line = file.readLine();
            }
            file.close();
            
            String reversedLine;
            
            PrintWriter pw = new PrintWriter(
                filename.substring(0, filename.lastIndexOf(".")) + ".rev.txt");
            for(int i = text.size() - 1; i >= 0; i--)
            {
                pw.println(text.get(i));
            }
            pw.close();

            System.out.println("Text reversed succesfully");
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
