// Adrián Navarro Gabino

/*
 * 1.2.4: Create a program that shows the contents of a text file, whose name
 * the user must enter. You must notify if the file does not exist.
 */

import java.io.*;
import java.util.*;

public class Exercise124
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

            String line = file.readLine();
            while (line != null) {
                System.out.println(line);

                line = file.readLine();
            }
            
            file.close();
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
