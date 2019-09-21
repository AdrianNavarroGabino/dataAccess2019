// Adrián Navarro Gabino

/*
 * 1.2.5: Create a program that reads the contents of a text file and dumps
 * it to another text file, but converting each line to uppercase.
 */

import java.io.*;
import java.util.*;

public class Exercise125
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File(filename)));
            PrintWriter pw = new PrintWriter(filename + ".out.txt");

            String line = file.readLine();
            while (line != null) {
                pw.println(line.toUpperCase());

                line = file.readLine();
            }
            
            pw.close();
            file.close();
            
            System.out.println("File copied");
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
