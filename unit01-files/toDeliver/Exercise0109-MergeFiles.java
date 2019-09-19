// Adrián Navarro Gabino

/*
 * Create a program that reads from the command line the name of two input
 * and one output files. The output file will have the contents of all the
 * input files, sorted alphabetically.
 *
 * For example, if "sort 1.txt 2.txt output.txt" is used and the first file
 * (1.txt) contains
 *
 * b
 * d
 *
 * and the second (2.txt) contains
 *
 * a
 * c
 * The output file (output.txt) must contain
 *
 * a
 * b
 * c
 * d
 */
 
import java.io.*;
import java.util.*;

 public class Exercise0109
 {
    public static void main(String[] args)
    {
        List<String> text = new ArrayList<String>();

        if (! (new File(args[0])).exists() || ! (new File(args[1])).exists() ) {
            System.out.println("File not found");
            return;
        }

        try {
            BufferedReader file1 = new BufferedReader(
                new FileReader(new File(args[0])));
            BufferedReader file2 = new BufferedReader(
                new FileReader(new File(args[1])));
            
            String line1 = file1.readLine();
            String line2 = file2.readLine();

            while (line1 != null)
            {
                text.add(line1);
                line1 = file1.readLine();
            }
            while(line2 != null)
            {
                text.add(line2);
                line2 = file2.readLine();
            }
            
            file1.close();
            file2.close();

            Collections.sort(text);

            PrintWriter pw = new PrintWriter(args[2]);
            for(int i = 0; i < text.size(); i++)
            {
                pw.println(text.get(i));
            }
            pw.close();

            System.out.println("File created");
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
 }
