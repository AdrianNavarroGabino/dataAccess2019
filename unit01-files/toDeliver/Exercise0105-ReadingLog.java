// Adrián Navarro Gabino

/*
 * Create a program that attempts to read the "log.txt" file and, if it does
 * not exist, read the file that the user enters.
 */

import java.io.*;
import java.util.Scanner;

public class Exercise0105b
{
    public static void main(String[] args)
    {
        String filename = "log.txt";

        if (! (new File(filename)).exists() ) {
            Scanner sc = new Scanner(System.in);
            System.out.println(filename + " not found");
            System.out.print("Enter filename: ");
            filename = sc.nextLine();
        }

        System.out.println("Reading file...");
        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File(filename)));
            String line = file.readLine();
            while (line != null) {
                System.out.println(line);
                line = file.readLine();
            }
            file.close();
            System.out.println("End of reading");
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
